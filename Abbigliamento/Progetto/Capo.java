package abbigliamento;

public class Capo {

    private Modello modello;
    private Materiale materiale;
    private Colore colore;

    public Capo(Modello modello, Materiale materiale, Colore colore) {
        this.modello = modello;
        this.materiale = materiale;
        this.colore = colore;
    }

    public double prezzo() {
        return modello.getCosto() + modello.getQuantita()*materiale.getCosto();
    }

    @Override
    public String toString() {
        return modello + " " + colore + " di " + materiale;
    }

    public Modello getModello() {
        return modello;
    }

    public Materiale getMateriale() {
        return materiale;
    }

    public Colore getColore() {
        return colore;
    }
}
