package elezioni;

import java.util.*;
import java.util.stream.Collectors;

public class Elezione {

    private long numVotanti;
    private HashMap<String,Cittadino> elettori;
    private HashMap<String,Lista> liste;

    public Elezione(){
        elettori = new HashMap<>();
        liste = new HashMap<>();
    }

    public Cittadino aggiungiElettore(String nome, String cognome){
        Cittadino cittadino = new Elettore(nome,cognome);
        elettori.put(nome+cognome,cittadino);
        return cittadino;
    }

    public Collection getElettori(){
        return elettori.values();
    }

    public Cittadino getElettore(String nome, String cognome){
        return elettori.get(nome+cognome);
    }

    public void registraLista(Lista lista){
        lista.setElezione(this);
        liste.put(lista.getNome(),lista);
    }

    /**
     * Il cittadino votante esprime un voto per la lista ed
     * un voto di preferenza per il candidato identificato
     * da nome e cognome
     * @throws TentatoDoppioVoto se il cittadino ha già votato
     * @throws TaglioNonPermesso se il candidato per cui si esprime
     * 							la preferenza non appartiene alla lista
     */
    public void vota(Cittadino votante, String lista, String nome, String cognome)
            throws TentatoDoppioVoto, TaglioNonPermesso{

        if(votante.haVotato()) throw new TentatoDoppioVoto();
        Lista l = liste.get(lista);
        Elettore el = l.getElettore(nome,cognome);
        Elettore cittadino = (Elettore) votante;

        if(el==null) throw new TaglioNonPermesso();
        cittadino.setVotato();
        numVotanti++;
        el.aggiungiVoto();
        l.aggiungiVoto();

    }

    /**
     * Il cittadino votante esprime un voto per la lista
     * il voto di preferenza va automaticamente al capolista
     * @throws TentatoDoppioVoto se il cittadino ha già votato
     */
    public void vota(Cittadino votante, String lista)
            throws TentatoDoppioVoto{

        if(votante.haVotato()) throw new TentatoDoppioVoto();
        Lista l = liste.get(lista);
        ((Elettore) l.getCapolista()).aggiungiVoto();
        ((Elettore) votante).setVotato();

    }

    public long getNumeroVotanti(){
        return numVotanti;
    }

    public Collection getRisultatiListe(){

        return liste.values().stream()
                .sorted((l1,l2) -> (int) (l2.getNumeroVoti() - l1.getNumeroVoti()))
                .collect(Collectors.toList());
    }

    public Collection getRisultatiCandidati() {

        HashMap<String,Elettore> candidati_tot = new HashMap<>();

        for(Lista l:liste.values()){
            Collection<Elettore> candidati;
            candidati = l.getCandidati();
            for(Elettore el:candidati){
                candidati_tot.put(el.getNome(),el);
            }
        }

        return candidati_tot.values().stream()
                .sorted((c1,c2) -> (int) (c2.getNumeroVoti() - c1.getNumeroVoti()))
                .collect(Collectors.toList());
    }

}