public class Lista {

    private long numVoti;
    private String nome;

    public Lista(int numVoti, String nome) {
        this.numVoti = numVoti;
        this.nome = nome;
    }

    public long getNumVoti() {
        return numVoti;
    }

    public String getNome() {
        return nome;
    }
}
