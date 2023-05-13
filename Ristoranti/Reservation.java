package restaurantChain;

public class Reservation {

    private String name;
    private int num_commensali;
    private double bill;
    private boolean ordered = false;
    private boolean paid = false;

    public Reservation(String name, int num_commensali) {
        this.name = name;
        this.num_commensali = num_commensali;
    }

    public String getName() {
        return name;
    }

    public int getNum_commensali() {
        return num_commensali;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public void setOrdered() {
        this.ordered = true;
    }
    public boolean getOrdered() {
        return ordered;
    }

    public double getBill() {
        return bill;
    }
    public void setPaid() {
        this.paid = true;
    }

    public boolean getPaid() {
        return paid;
    }
}
