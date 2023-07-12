package torneidicalcio;

public class Arbitro extends Tesserato{
	
	private String nome;
	private String cognome;
	private String sezione;

	public Arbitro(String nome, String cognome, String sezione) {
		super(nome, cognome);
		this.sezione = sezione;
	}

	public String getSezione(){
		return sezione;
	}

	@Override
	// Arbitro extends Tesserato => per accedere agli attributi nome e cognome bisogna usare i metodi Getter, altrimenti sono null
	public String toString() {
		return this.getNome() + "," + this.getCognome() + "," + sezione;
	}
	
	

}
