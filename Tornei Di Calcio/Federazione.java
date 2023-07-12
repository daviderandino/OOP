package torneidicalcio;

import java.util.*;
import java.util.stream.Collectors;

public class Federazione {
	
	private String sigla;
	private String denominazione;
	private String sitoWeb;

	public Federazione(String sigla, String denominazione, String sitoWeb){
		this.sigla = sigla;
		this.denominazione = denominazione;
		this.sitoWeb = sitoWeb;
	}
	
	public String getSigla() {
		return sigla;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public String getSitoWeb() {
		return sitoWeb;
	}
	
	LinkedHashMap<String, Torneo> tornei = new LinkedHashMap<>();

	public Torneo nuovoTorneo(String nomeTorneo, int numeroSquadre){
		if(tornei.containsKey(nomeTorneo)) return tornei.get(nomeTorneo);
		Torneo torneo = new Torneo(nomeTorneo,numeroSquadre);
		tornei.put(nomeTorneo, torneo);
		return torneo;
	}
	
	TreeMap<String, Squadra> squadre = new TreeMap<>();

	public void nuovaSquadra(String nome, String citta, int anno, String stadio){
		if(squadre.containsKey(nome)) return;
		Squadra squadra = new Squadra(nome,citta,anno,stadio);
		squadre.put(nome, squadra);
	}

	public Squadra cercaSquadra(String nomeSquadra){
		return squadre.get(nomeSquadra);
	}

	public Collection<Torneo> elencoTornei(){
		return tornei.values();
	}

	public Collection<Squadra> elencoSquadre(){
		return squadre.values();
	}
	
	public void iscriviSquadraTorneo(String nomeTorneo, String nomeSquadra){
		if(!tornei.containsKey(nomeTorneo) || !squadre.containsKey(nomeSquadra)) return;
		Squadra squadra = squadre.get(nomeSquadra);
		Torneo torneo = tornei.get(nomeTorneo);
		if(torneo.getNumeroSquadreIscritte() == torneo.getNumeroSquadre()) return;
		squadra.setTorneo(torneo);
		torneo.iscriviSquadra(nomeSquadra, squadra);	
	}

	public Collection<Squadra> elencoSquadreTorneo(String nomeTorneo){
		if(!tornei.containsKey(nomeTorneo)) return null;
		Torneo torneo = tornei.get(nomeTorneo);
		return torneo.getSquadre().values();
	}
	
	TreeMap<Integer, Tesserato> tesserati = new TreeMap<>();
	TreeMap<String, Tesserato> tesseratiPerNome = new TreeMap<>();
	
	public int tesseramento(String nome, String cognome, String nomeSquadra, String ruolo) throws EccezioneErroreDatiTesseramento{
		if(!squadre.containsKey(nomeSquadra) || nome == "" || cognome == "" || ruolo == "" || nome==null || cognome == null || ruolo == null) throw new EccezioneErroreDatiTesseramento();
		Squadra squadra = squadre.get(nomeSquadra);
		Tesserato dirigente = new Dirigente(nome,cognome,squadra,ruolo);
		tesserati.put(dirigente.getID(), dirigente);
		tesseratiPerNome.put(nome+cognome, dirigente);
		squadra.aggiungiTesserato(dirigente);
		dirigente.setTipoTesserato('D');
		return dirigente.getID();
	}

	public int tesseramento(String nome, String cognome, String nomeSquadra, String ruolo, int numeroMaglia) throws EccezioneErroreDatiTesseramento{
		if(!squadre.containsKey(nomeSquadra) || nome == "" || cognome == "" || ruolo == "" || nome==null || cognome == null || ruolo == null || numeroMaglia<0) throw new EccezioneErroreDatiTesseramento();
		Squadra squadra = squadre.get(nomeSquadra);
		Tesserato calciatore = new Calciatore(nome,cognome,squadra,ruolo,numeroMaglia); 
		tesserati.put(calciatore.getID(), calciatore);
		tesseratiPerNome.put(nome+cognome, calciatore);
		squadra.aggiungiTesserato(calciatore);
		calciatore.setTipoTesserato('C');
		return calciatore.getID();
	}
	
	public int tesseramento(String nome, String cognome, String sezione) throws EccezioneErroreDatiTesseramento{
		if( nome == "" || cognome == "" ||  nome==null || cognome == null) throw new EccezioneErroreDatiTesseramento();
		Tesserato arbitro = new Arbitro(nome,cognome,sezione); 
		tesserati.put(arbitro.getID(), arbitro);
		tesseratiPerNome.put(nome+cognome, arbitro);
		arbitro.setTipoTesserato('A');
		return arbitro.getID();
	}
	
	public Tesserato cercaTesseratoPerNumeroTessera(int numeroTessera) throws EccezioneTesseratoInesistente{
		if(!tesserati.containsKey(numeroTessera));
		return tesserati.get(numeroTessera);
	}

	public Tesserato cercaTesseratoPerNomeCognome(String nome, String cognome) throws EccezioneTesseratoInesistente{
		if(!tesseratiPerNome.containsKey(nome+cognome)) throw new EccezioneTesseratoInesistente();
		return tesseratiPerNome.get(nome+cognome);
	}
	
	public Collection<Tesserato> elencoTesseratiSquadra(String nomeSquadra){
		
		List<Tesserato> dirigenti = new LinkedList<>();
		List<Tesserato> calciatori = new LinkedList<>();
		
		dirigenti =  squadre.get(nomeSquadra).getTesserati().stream()
				.filter(d -> d.getTipoTesserato() == 'D')
				.sorted(Comparator.comparing(Tesserato::getNomecognome))
				.collect(Collectors.toList());
		
		calciatori =  squadre.get(nomeSquadra).getTesserati().stream()
				.filter(d -> d.getTipoTesserato() == 'C')
				.sorted(Comparator.comparing(Tesserato::getNomecognome))
				.collect(Collectors.toList());
		
		List<Tesserato> lista = new LinkedList<>();
		
		for(Tesserato t:dirigenti) {
			lista.add(t);
		}
		for(Tesserato t:calciatori) {
			lista.add(t);
		}
		return lista;
	}
	
	TreeMap<String, List<Incontro>> incontri = new TreeMap<>();
	
	public Incontro nuovoIncontro(String nomeTorneo, int giornata, String nomeSquadraCasa, String nomeSquadraOspite, String risultato, String nomeArbitro, String cognomeArbitro){
		if(!tornei.containsKey(nomeTorneo) || !squadre.containsKey(nomeSquadraCasa) || !squadre.containsKey(nomeSquadraOspite)) return null;
		Squadra squadraCasa = squadre.get(nomeSquadraCasa);
		Squadra squadraOspite = squadre.get(nomeSquadraOspite);
		Torneo torneo = tornei.get(nomeTorneo);
		Tesserato arbitro = tesseratiPerNome.get(nomeArbitro+cognomeArbitro);
		Incontro incontro = new Incontro(torneo,giornata,squadraCasa,squadraOspite,risultato,arbitro);
		
		if(incontri.containsKey(nomeTorneo)) {
			List<Incontro> lista = incontri.get(nomeTorneo);
			lista.add(incontro);
			incontri.put(nomeTorneo, lista);
		}
		else {
			incontri.put(nomeTorneo, new LinkedList<>());
			List<Incontro> lista = incontri.get(nomeTorneo);
			lista.add(incontro);
			incontri.put(nomeTorneo, lista);
		}
		
		return incontro;
	}

	public Collection<Incontro> elencoIncontriPerGiornata(String nomeTorneo){
		return incontri.get(nomeTorneo).stream()
				.sorted(Comparator.comparing(Incontro::getGiornata))
				.collect(Collectors.toList());
	}

	public Collection<Incontro> elencoIncontriPerDifferenzaReti(String nomeTorneo){
		return incontri.get(nomeTorneo).stream()
				.sorted(Comparator.comparing(Incontro::getDifferenza_Reti))
				.collect(Collectors.toList());
	}

	public int puntiSquadra(String nomeSquadra){
		return squadre.get(nomeSquadra).getPuntiSquadra();
	}
	
	public String classificaTorneo(String nomeTorneo){
		Torneo torneo = tornei.get(nomeTorneo);
		String classifica = "";
		
		List<Squadra> squadrePerPt = torneo.getSquadre().values().stream()
				.sorted(Comparator.comparing(Squadra::getPuntiSquadra).reversed())
				.collect(Collectors.toList());
		
		for(Squadra s:squadrePerPt) {
			classifica = classifica +s.getPuntiSquadra() + " " + s.getNome() + ";" + "\n";
		}
		
		String classificaMod = classifica.substring(0,classifica.length()-2) + ".";
		
		return classificaMod;
		
	}
}
