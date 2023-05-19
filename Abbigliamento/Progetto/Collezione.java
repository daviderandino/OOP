package abbigliamento;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Collezione {

    HashMap<Modello,List<Capo>> capiComeModello = new HashMap<>();
    HashMap<Colore,List<Capo>> capiComeColore = new HashMap<>();
    HashMap<Materiale,List<Capo>> capiComeMateriale = new HashMap<>();

    public void add(Capo capo) {

        if(capiComeModello.containsKey(capo.getModello())){
            List<Capo> listaCapi = capiComeModello.get(capo.getModello());
            listaCapi.add(capo);
        }
        else{
            List<Capo> nuovaListaCapi = new ArrayList<>();
            nuovaListaCapi.add(capo);
            capiComeModello.put(capo.getModello(), nuovaListaCapi);
        }

        if(capiComeMateriale.containsKey(capo.getMateriale())){
            List<Capo> listaCapi = capiComeMateriale.get(capo.getMateriale());
            listaCapi.add(capo);
        }
        else{
            List<Capo> nuovaListaCapi = new ArrayList<>();
            nuovaListaCapi.add(capo);
            capiComeMateriale.put(capo.getMateriale(), nuovaListaCapi);
        }

        if(capiComeColore.containsKey(capo.getColore())){
            List<Capo> listaCapi = capiComeColore.get(capo.getColore());
            listaCapi.add(capo);
        }
        else{
            List<Capo> nuovaListaCapi = new ArrayList<>();
            nuovaListaCapi.add(capo);
            capiComeColore.put(capo.getColore(), nuovaListaCapi);
        }

    }

    public Collection trova(Colore colore) {
        return capiComeColore.get(colore);
    }

    public Collection trova(Materiale materiale) {
        return capiComeMateriale.get(materiale);
    }

    public Collection trova(Modello modello) {
        return capiComeModello.get(modello);
    }

}
