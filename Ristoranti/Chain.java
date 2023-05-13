package restaurantChain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Chain {

    Map<String,Restaurant> ristoranti;

    public Chain(){
        ristoranti = new HashMap<>();
    }

    public void addRestaurant(String name, int tables) throws InvalidName{
        if(ristoranti.containsKey(name)){
            throw new InvalidName();
        }
        Restaurant r = new Restaurant(name,tables);
        ristoranti.put(name,r);
    }

    public Restaurant getRestaurant(String name) throws InvalidName{
        if(!ristoranti.containsKey(name)){
            throw new InvalidName();
        }
        return ristoranti.get(name);
    }

    public List<Restaurant> sortByIncome(){
        return ristoranti.values()
                .stream()
                .sorted((r1,r2) -> (int)(r2.getIncome()-r1.getIncome())) // valori decrescenti
                .collect(Collectors.toList());
    }
    public List<Restaurant> sortByRefused(){
        return ristoranti.values()
                .stream()
                .sorted((r1,r2) ->(r1.getRefused()-r2.getRefused())) // valori crescenti
                .collect(Collectors.toList());
    }
    public List<Restaurant> sortByUnusedTables(){
        return ristoranti.values()
                .stream()
                .sorted((r1,r2) ->(r1.getUnusedTables()-r2.getUnusedTables()))
                .collect(Collectors.toList());
    }
}
