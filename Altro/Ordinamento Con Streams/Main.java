import java.util.Collection;

public class Main {

    public static void main(String[] args) {

        Example c = new Example();
        Collection<Lista> liste_ord;

        Lista l1 = new Lista(20,"a");
        Lista l2 = new Lista(18,"b");
        Lista l3 = new Lista(24,"c");
        Lista l4 = new Lista(36,"d");
        Lista l5 = new Lista(21,"e");

        c.aggLista(l1); c.aggLista(l2); c.aggLista(l3); c.aggLista(l4); c.aggLista(l5);

        liste_ord = c.getRisultatiListe();

        for(Lista lista:liste_ord){
            System.out.println(lista.getNumVoti());
        }


    }





}
