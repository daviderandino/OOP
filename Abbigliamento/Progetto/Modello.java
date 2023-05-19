package abbigliamento;

public class Modello {

    private String nome;
    private double costo;
    private double quantita;

    public Modello(String nome,double costo,double quantita) {
        this.nome = nome;
        this.costo = costo;
        this.quantita = quantita;
    }

    public String getNome(){
        return nome;
    }
    public double getCosto(){
        return costo;
    }

    public double getQuantita() {
        return quantita;
    }

}
