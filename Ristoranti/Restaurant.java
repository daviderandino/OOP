package restaurantChain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Restaurant {

    private String name;
    private int totalTables;
    private int availableTables;
    private int refused;
    private double income;

    Map<String,Menu> menus;
    Map<String,Reservation> reservations;

    public Restaurant(String name,int tables) {
        this.name = name;
        totalTables = availableTables = tables;
        menus = new HashMap<>();
        reservations = new HashMap<>();
        refused = 0;
        income = 0.0;
    }

    public String getName(){
        return name;
    }

    public void addMenu(String name, double price) throws InvalidName{
        if(menus.containsKey(name)){
            throw new InvalidName();
        }
        menus.put(name,new Menu(name,price));
    }

    public int reserve(String name, int persons) throws InvalidName{

       if(reservations.containsKey(name)){
           throw new InvalidName();
       }

       int req_tables = (int) Math.ceil(persons/4.);

       if(this.availableTables < req_tables){
           System.out.println("Operazione rifiutata");
           refused += persons;
           return 0;

       }

        this.availableTables = this.availableTables - req_tables;
        Reservation r = new Reservation(name,persons);
        reservations.put(name,new Reservation(name,persons));
        return req_tables;

    }

    public int getRefused(){
        return refused;
    }

    public int getUnusedTables(){
        return this.availableTables;
    }

    public boolean order(String name, String... menu) throws InvalidName{

        double bill = 0.0;

        if(!reservations.containsKey(name)){
            throw new InvalidName();
        }

        Reservation r = reservations.get(name);

        if(menu.length<r.getNum_commensali()){
            return false;
        }

        for(String s:menu){
            if(!this.menus.containsKey(s)){
                throw new InvalidName();
            }
            Menu m = menus.get(s);
            bill += m.getPrezzo();
        }

        income += bill;
        r.setBill(bill);
        r.setOrdered();
        return true;

    }

    public List<String> getUnordered(){
        return reservations.values()
                .stream()
                .filter(r -> !r.getOrdered())
                .map(Reservation::getName)
                .sorted()
                .collect(Collectors.toList());
    }

    public double pay(String name) throws InvalidName{

        if(!reservations.containsKey(name)){
            throw new InvalidName();
        }

        Reservation r = reservations.get(name);
        if(!r.getOrdered()){
            return 0.0;
        }
        r.setPaid();
        return r.getBill();
    }

    public List<String> getUnpaid(){
        return reservations.values()
                .stream()
                .filter(Reservation::getOrdered)
                .filter(r -> !r.getPaid())
                .map(Reservation::getName)
                .sorted()
                .collect(Collectors.toList());
    }

    public double getIncome(){
        return income;
    }

}