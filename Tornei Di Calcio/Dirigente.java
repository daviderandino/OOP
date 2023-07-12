package torneidicalcio;

public class Dirigente extends Tesserato {
	
	private String nome;
	private String cognome;
	private Squadra squadra;
	private String ruolo;
	
	public Dirigente(String nome, String cognome, Squadra squadra, String ruolo) {
		super(nome, cognome);
		this.squadra = squadra;
		this.ruolo = ruolo;
	}

	public Squadra getSquadra() {
		return null;
	}

	public String getRuolo() {
		return null;
	}
	
}
