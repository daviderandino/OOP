package torneidicalcio;

import java.util.LinkedList;

public class Squadra {
	
	private String nome;
	private String citta;
	private int anno;
	private String stadio;
	
	private Torneo torneo;
	
	private LinkedList<Tesserato> tesserati;
	private int puntiSquadra;

	public Squadra(String nome, String citta, int anno, String stadio) {
		this.nome = nome;
		this.citta = citta;
		this.anno = anno;
		this.stadio = stadio;
		tesserati = new LinkedList<>();
		puntiSquadra = 0;
	}
	
	public int getPuntiSquadra() {
		return puntiSquadra;
	}

	public void setPuntiSquadra(int puntiSquadra) {
		this.puntiSquadra = puntiSquadra;
	}

	public LinkedList<Tesserato> getTesserati() {
		return tesserati;
	}

	public void setTesserati(LinkedList<Tesserato> tesserati) {
		this.tesserati = tesserati;
	}

	public void aggiungiTesserato(Tesserato t) {
		tesserati.add(t);
	}

	public String getNome() {
		return nome;
	}

	public String getCitta() {
		return citta;
	}

	public int getAnno() {
		return anno;
	}

	public String getStadio() {
		return stadio;
	}

	public Torneo getTorneo() {
		return torneo;
	}

	public void setTorneo(Torneo torneo) {
		this.torneo = torneo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public void setStadio(String stadio) {
		this.stadio = stadio;
	}
	
}
