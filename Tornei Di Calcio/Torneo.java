package torneidicalcio;

import java.util.TreeMap;

public class Torneo {
	
	private String nomeTorneo;
	private int numeroSquadre;
	private int numeroSquadreIscritte;
	
	TreeMap<String, Squadra> squadre;

	public Torneo(String nomeTorneo, int numeroSquadre) {
		this.nomeTorneo = nomeTorneo;
		this.numeroSquadre = numeroSquadre;
		numeroSquadreIscritte = 0;
		squadre = new TreeMap<>();
	}
	
	public void iscriviSquadra(String nomesquadra,Squadra squadra) {
		squadre.put(nomesquadra, squadra);
		numeroSquadreIscritte ++;
	}
	
	public int getNumeroSquadreIscritte() {
		return numeroSquadreIscritte;
	}

	public void setNumeroSquadreIscritte(int numeroSquadreIscritte) {
		this.numeroSquadreIscritte = numeroSquadreIscritte;
	}

	public String getNome() {
		return nomeTorneo;
	}

	public int getNumeroSquadre() {
		return numeroSquadre;
	}

	public String getNomeTorneo() {
		return nomeTorneo;
	}

	public void setNomeTorneo(String nomeTorneo) {
		this.nomeTorneo = nomeTorneo;
	}

	public TreeMap<String, Squadra> getSquadre() {
		return squadre;
	}

	public void setSquadre(TreeMap<String, Squadra> squadre) {
		this.squadre = squadre;
	}

	public void setNumeroSquadre(int numeroSquadre) {
		this.numeroSquadre = numeroSquadre;
	}

}
