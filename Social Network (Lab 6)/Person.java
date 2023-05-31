package social;

import java.util.Collection;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Person {

    private String code;
    private String name;
    private String surname;
    private List<Group> groups;

    private TreeMap<String,Person> friends;
    private int numGruppi;
    private int numFriends;

    public Person(String code, String name, String surname) {
        this.code = code;
        this.name = name;
        this.surname = surname;
        friends = new TreeMap<>();
        numGruppi = 0;
        numFriends ++;
    }

    @Override
    public String toString() {
        return code + " " + name + " " + surname;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public TreeMap<String, Person> getFriends() {
        return friends;
    }

    public void addFriend(Person p){
        friends.put(p.getCode(),p);
        numFriends ++;
    }

    public Collection<String> friendsList(){
        return friends.keySet();
    }

    public void addToGroup(Group group){
        groups.add(group);
        numGruppi ++;
    }

    public int getNumGruppi() {
        return numGruppi;
    }

    public int getNumFriends() {
        return numFriends;
    }

    public int getNumberOfFriendsOfFriends() {
        return this.getFriends().values().stream() // Ottieni uno stream degli amici della persona
                .flatMap(friend -> friend.getFriends().values().stream()) // Appiattisci gli amici degli amici in uno stream singolo
                .distinct() // Rimuovi duplicati
                .collect(Collectors.toList()) // Raccogli gli amici degli amici in una lista
                .size(); // Restituisci la dimensione della lista (numero di amici degli amici)
    }
}
