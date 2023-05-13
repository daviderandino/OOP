package restaurantChain;

public class Menu {

    private String nome;
    private double prezzo;

    public Menu(String nome, double prezzo) {
        this.nome = nome;
        this.prezzo = prezzo;
    }

    public String getNome() {
        return nome;
    }

    public double getPrezzo() {
        return prezzo;
    }


}
