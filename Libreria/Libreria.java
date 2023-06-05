package libreria;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Libreria {

    TreeMap<String,Editore> editori = new TreeMap<>();

    public Editore creaEditore(String nome, int tempoConsegna, String email){
        Editore editore = new Editore(nome,tempoConsegna,email);
        editori.put(nome,editore);
        return editore;
    }

    public Editore getEditore(String nome){
        return editori.get(nome);
    }

    public Collection getEditori(){
        /*
        return editori.keySet()
                .stream()
                .sorted()
                .collect(Collectors.toList());
         */
        return editori.keySet(); // TreeMap => chiavi già ordinate
    }

    TreeMap<String,Libro> libriPerAutore = new TreeMap<>();
    TreeMap<String,Libro> libriPerTitolo = new TreeMap<>();
    TreeMap<String,Libro> libriPerAutoreETitolo = new TreeMap<>();

    public Libro creaLibro(String titolo, String autore, int anno, double prezzo, String nomeEditore)
    			throws EditoreInesistente {

        if(!editori.containsKey(nomeEditore)) throw new EditoreInesistente();
        Editore editore = editori.get(nomeEditore);
        Libro libro = new Libro(titolo,autore,anno,prezzo,editore);
        libriPerAutore.put(autore,libro);
        libriPerTitolo.put(titolo,libro);
        libriPerAutoreETitolo.put(titolo+autore,libro);
        return libro;
    }
    
    public Libro getLibro(String autore, String titolo){

        if(autore==null)
            return libriPerTitolo.get(titolo);
        if(titolo==null)
            return libriPerAutore.get(autore);
        return libriPerAutoreETitolo.get(autore+titolo);

    }
    
    public Collection getClassificaSettimana(final int settimana){
        return libriPerAutore.values().stream()
                .sorted((l1, l2) -> (l2.getVenditeSettimana(settimana) - l1.getVenditeSettimana(settimana)))
                .collect(Collectors.toList());

    }
    
    public Collection getClassificaMese(final int mese){
        return libriPerAutore.values().stream()
                .sorted((l1, l2) -> (l2.getVenditeMese(mese) - l1.getVenditeMese(mese)))
                .collect(Collectors.toList());
    }
    
    public Collection getOrdini(){

        Collection<Ordine> ordini = new LinkedList<>();

        for(Libro libro:libriPerAutore.values()) {
            ordini.addAll(libro.getOrdini());
        }

        return ordini;
    }

    public TreeMap<Integer,Ordine> getMap(){

        TreeMap<Integer,Ordine> ordini = new TreeMap();

        for(Libro libro:libriPerAutore.values()) {
            for(Ordine ordine:libro.getOrdini()){
                ordini.put(ordine.getNumero(),ordine);
            }
        }
        return ordini;
    }
    
    public void ordineRicevuto(int numOrdine){

        TreeMap<Integer,Ordine> ordini = getMap();

        Ordine ordine = ordini.get(numOrdine);

        ordine.setConsegnato(true);
        Libro libro = ordine.getLibro();
        libro.setQuantita(libro.getQuantita() + ordine.getQuantita());

    }
    
    public void leggi(String file) throws EditoreInesistente {

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("E;")) {
                    String[] editorData = line.split(";");
                    if (editorData.length == 4) {
                        String nome = editorData[1];
                        int consegne = Integer.parseInt(editorData[2]);
                        String email = editorData[3];
                        creaEditore(nome, consegne, email);
                    }
                } else if (line.startsWith("L;")) {
                    String[] bookData = line.split(";");
                    if (bookData.length == 7) {
                        String titolo = bookData[1];
                        String autore = bookData[2];
                        int anno = Integer.parseInt(bookData[3]);
                        double prezzo = Double.parseDouble(bookData[4]);
                        String editore = bookData[5];
                        int qt = Integer.parseInt(bookData[6]);
                        Libro l = creaLibro(titolo, autore, anno, prezzo, editore);
                        l.setQuantita(qt);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EditoreInesistente e){
            throw new EditoreInesistente();
        }

    }
}
