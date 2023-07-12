package torneidicalcio;

public class Calciatore extends Tesserato{
	
	private String nome;
	private String cognome;
	private Squadra squadra;
	private String ruolo;
	private int numeroMaglia;
	//private int IDs = 1000;
	//private int ID;
	private String nomecognome;

	public Calciatore(String nome, String cognome, Squadra squadra, String ruolo, int numeroMaglia) {
		super(nome, cognome);
		this.squadra = squadra;
		this.ruolo = ruolo;
		this.numeroMaglia = numeroMaglia;
	}

	public Squadra getSquadra() {
		return squadra;
	}

	public String getRuolo() {
		return ruolo;
	}

	public int getNumeroMaglia() {
		return numeroMaglia;
	}
	
}

