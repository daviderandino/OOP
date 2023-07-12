package torneidicalcio;

public class Tesserato {
	
	private String nome;
	private String cognome;
	private int IDs = 1000;
	private int ID;
	private String nomecognome;
	
	private char  tipoTesserato;

	public Tesserato(String nome, String cognome) {
		this.nome = nome;
		this.cognome = cognome;
		ID = IDs;
		IDs ++;
		nomecognome = nome + cognome;
	}

	public char getTipoTesserato() {
		return tipoTesserato;
	}

	public void setTipoTesserato(char tipoTesserato) {
		this.tipoTesserato = tipoTesserato;
	}

	public String getNomecognome() {
		return nomecognome;
	}
	
	public void setNomecognome(String nomecognome) {
		this.nomecognome = nomecognome;
	}

	public int getIDs() {
		return IDs;
	}

	public void setIDs(int iDs) {
		IDs = iDs;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}
}
