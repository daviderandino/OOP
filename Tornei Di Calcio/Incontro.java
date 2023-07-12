package torneidicalcio;

public class Incontro {
	
	private Torneo torneo;
	private int giornata;
	private Squadra squadraCasa;
	private Squadra squadraOspite;
	private String risultato;
	private Tesserato arbitro;
	
	private int differenza_Reti;

	public Incontro(Torneo torneo, int giornata, Squadra squadraCasa, Squadra squadraOspite, String risultato,
			Tesserato arbitro) {
		this.torneo = torneo;
		this.giornata = giornata;
		this.squadraCasa = squadraCasa;
		this.squadraOspite = squadraOspite;
		this.risultato = risultato;
		this.arbitro = arbitro;
		this.setPunti();
		
	}
		
	private void setPunti() {
		String str[] = risultato.split("-");
		int golSquadraCasa = Integer.parseInt(str[0]);
		int golSquadraOspite = Integer.parseInt(str[1]);
		differenza_Reti = Math.abs (golSquadraCasa - golSquadraOspite);
		if(golSquadraCasa > golSquadraOspite) {
			squadraCasa.setPuntiSquadra(3 + squadraCasa.getPuntiSquadra());
		}
		if(golSquadraOspite > golSquadraCasa) {
			squadraOspite.setPuntiSquadra(3 + squadraOspite.getPuntiSquadra());
		}
		if(golSquadraCasa == golSquadraOspite) {
			squadraCasa.setPuntiSquadra(1 + squadraCasa.getPuntiSquadra());
			squadraOspite.setPuntiSquadra(1 + squadraOspite.getPuntiSquadra());
		}
	}

	public int getDifferenza_Reti() {
		return differenza_Reti;
	}

	public void setDifferenza_Reti(int differenza_Reti) {
		this.differenza_Reti = differenza_Reti;
	}

	public int getGiornata() {
		return giornata;
	}

	public Squadra getSquadraCasa() {
		return squadraCasa;
	}

	public Squadra getSquadraOspite() {
		return squadraOspite;
	}

	public int getNumeroGolSquadraCasa() {
		String str[] = risultato.split("-");
		return Integer.parseInt(str[0]);
	}

	public int getNumeroGolSquadraOspite() {
		String str[] = risultato.split("-");
		return Integer.parseInt(str[1]);
	}

	public String getArbitro() {
		return arbitro.toString();
	}

}
