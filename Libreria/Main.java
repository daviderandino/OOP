package libreria;

import java.util.Collection;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Libreria l = new Libreria();
        Editore e1 = l.creaEditore("c",1,"c@gmail.com");
        Editore e2 = l.creaEditore("a",2,"a@gmail.com");
        Editore e3 = l.creaEditore("b",3,"b@gmail.com");

        Collection lista =  l.getEditori();

        for(Object s:lista){
            System.out.println(s);
        }
    }



}
