package social;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class Group {

    private String name;
    private List<Person> persons;
    private int numPersone;

    public Group(String name) {
        this.name = name;
        persons = new LinkedList<>();
        numPersone = 0;
    }

    public void addPersons(Person person){
        persons.add(person);
        numPersone++;
    }

    public Collection<Person> personList(){
        return persons;
    }

    public String getName() {
        return name;
    }

    public int getNumPersone() {
        return numPersone;
    }
}
