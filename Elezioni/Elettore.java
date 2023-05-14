package elezioni;

public class Elettore implements Cittadino {

    private String nome;
    private String cognome;
    private boolean votato;
    private boolean capolista;
    private boolean candidato;
    private long votiRicevuti;

    public Elettore(String nome,String cognome) {
        this.nome = nome;
        this.cognome = cognome;
        votato = false;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getCognome() {
        return cognome;
    }

    public boolean haVotato(){
        return votato;
    }

    @Override
    public boolean isCapolista() {
        return capolista;
    }

    @Override
    public boolean isCandidato() {
        return candidato || capolista;
    }

    @Override
    public long getNumeroVoti() {
        return votiRicevuti;
    }

    public void setCapolista() {
        capolista = true;
    }

    public void setCandidato() {
        candidato = true;
    }

    public void setVotato() {
        votato = true;
    }

    public void aggiungiVoto(){
        votiRicevuti++;
    }
}
