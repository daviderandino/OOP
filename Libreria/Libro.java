package libreria;

import java.util.LinkedList;
import java.util.List;

public class Libro {

    private String titolo;
    private String autore;
    private int anno;
    private double prezzo;
    private Editore editore;
    private int qt;

    private int venditeSettimana[];
    private int venditeMese[];

    private int soglia;
    private int qtDaRiordinare;

    private LinkedList<Ordine> ordini;

    public Libro(String titolo, String autore, int anno, double prezzo, Editore editore) {

        this.titolo = titolo;
        this.autore = autore;
        this.anno = anno;
        this.prezzo = prezzo;
        this.editore = editore;

        venditeMese = new int[12+1];
        venditeSettimana = new int[52+1];

        ordini = new LinkedList<>();

    }

    public String getTitolo(){
        return titolo;
    }
    
    public String getAutore(){
        return autore;
    }
    
    public int getAnno(){
        return anno;
    }

    public double getPrezzo(){
        return prezzo;
    }
    
    public Editore getEditore(){
        return editore;
    }

    public void setQuantita(int q){
        this.qt = qt;
    }
    
    public int getQuantita(){
        return qt;
    }

    public void registraVendita(int settimana, int mese){

        this.setQuantita(qt--);
        venditeSettimana[settimana] ++;
        venditeMese[mese] ++;

        if(qt == soglia){
            nuovoOrdine();
        }

    }

    public void nuovoOrdine(){
        Ordine ordine = new Ordine(editore,this,qtDaRiordinare);
        ordini.add(ordine);
    }

    public int getVenditeSettimana(int settimana){
        return venditeSettimana[settimana];
    }

    public int getVenditeMese(int mese){
        return venditeMese[mese];
    }
    

    public void setParametri(int soglia, int quantitaRiordino){
        this.soglia = soglia;
        this.qtDaRiordinare = quantitaRiordino;

    }

    public List<Ordine> getOrdini(){
        return ordini;
    }
}
