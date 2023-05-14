package elezioni;

import java.util.Collection;
import java.util.HashMap;

public class Lista {

    private long numVoti;
    private String nome;
    private String motto;
    private Elettore capolista;
    private Elezione elezione;
    private HashMap<String,Elettore> candidati;

    public Lista(String nome, String motto){
        this.nome = nome;
        this.motto = motto;
        candidati = new HashMap();
    }

    public String getNome(){
        return nome;
    }

    public String getMotto(){
        return motto;
    }

    public void assegnaCapolista(Cittadino capolista)
            throws CandidatoNonValido {
        Elettore e = (Elettore) capolista;
        if(e.isCandidato()) throw new CandidatoNonValido();
        e.setCapolista();
        e.setCandidato();
        this.capolista = e;
    }

    public void aggiungiCandidato(Cittadino capolista)
            throws CandidatoNonValido {
        Elettore e = (Elettore) capolista;
        if(e.isCandidato()) throw new CandidatoNonValido();
        e.setCandidato();
        candidati.put(capolista.getNome()+capolista.getCognome(),e);
    }

    public Cittadino getCapolista(){
        return this.capolista;
    }

    /**
     * Restituisce la collezione dei candidati
     * (NON include il capolista)
     */
    public Collection getCandidati(){
        return candidati.values();
    }

    public void setElezione(Elezione elezione) {
        this.elezione = elezione;
    }

    public long getNumeroVoti(){
        return numVoti;
    }

    public void aggiungiVoto(){
        numVoti++;
    }

    public double getPercentualeVoti(){
        long votantiTotali = elezione.getNumeroVotanti();
        return (double) (numVoti/votantiTotali) * 100;
    }

    public Elettore getElettore(String nome,String cognome){
        if(nome.equals(capolista.getNome()) && cognome.equals(capolista.getCognome())) return capolista;
        Elettore elettore = candidati.get(nome+cognome);
        return elettore;
    }

}
