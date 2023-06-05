package libreria;

public class Ordine {

    private Editore editore;
    private Libro libro;
    private int quantita;

    private static int numero_ordine = 0;

    private boolean consegnato;

    public Ordine(Editore editore, Libro libro, int quantita) {
        this.editore = editore;
        this.libro = libro;
        this.quantita = quantita;
        consegnato = false;
        numero_ordine ++;
    }

    public Editore getEditore(){
        return editore;
    }
    
    public Libro getLibro(){
        return libro;
    }
    
    public int getQuantita(){
        return quantita;
    }

    public boolean isConsegnato(){
        return consegnato;
    }

    public void setConsegnato(boolean consegnato) {
        this.consegnato = consegnato;
    }

    public int getNumero(){
        return numero_ordine;
    }
}
