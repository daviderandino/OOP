import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Example {

    HashMap<String,Lista> liste = new HashMap<>();

    public void aggLista(Lista l){
        liste.put(l.getNome(),l);
    }


    // ordinamento decrescente
    public Collection<Lista> getRisultatiListe(){

        return liste.values().stream()
                .sorted((l1,l2) -> (int) (l2.getNumVoti() - l1.getNumVoti()))
                .collect(Collectors.toList());
    }

    // ordinamento crescente
     public Collection<Lista> getRisultatiListe(){

        return liste.values().stream()
                .sorted((l1,l2) -> (int) (l1.getNumVoti() - l2.getNumVoti()))
                .collect(Collectors.toList());
    }


}
