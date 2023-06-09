package social;

import java.util.*;
import java.util.stream.Collectors;


public class Social {

    /**
     * Creates a new account for a person
     *
     * @param code	nickname of the account
     * @param name	first name
     * @param surname last name
     * @throws PersonExistsException in case of duplicate code
     */

    TreeMap<String,Person> persons = new TreeMap<>();

    public void addPerson(String code, String name, String surname)
            throws PersonExistsException {

        if(persons.containsKey(code)) throw new PersonExistsException();
        Person person = new Person(code,name,surname);
        persons.put(code,person);

    }

    /**
     * Retrieves information about the person given their account code.
     * The info consists in name and surname of the person, in order, separated by blanks.
     *
     * @param code account code
     * @return the information of the person
     * @throws NoSuchCodeException
     */
    public String getPerson(String code) throws NoSuchCodeException {
        return persons.get(code).toString();
    }

    /**
     * Define a friendship relationship between to persons given their codes.
     *
     * Friendship is bidirectional: if person A is friend of a person B, that means that person B is a friend of a person A.
     *
     * @param codePerson1	first person code
     * @param codePerson2	second person code
     * @throws NoSuchCodeException in case either code does not exist
     */
    public void addFriendship(String codePerson1, String codePerson2)
            throws NoSuchCodeException {

        if(!persons.containsKey(codePerson1) || !persons.containsKey(codePerson2)) throw new NoSuchCodeException();

        Person p1 = persons.get(codePerson1);
        Person p2 = persons.get(codePerson2);

        p1.addFriend(p2);
        p2.addFriend(p1);

    }

    /**
     * Retrieve the collection of their friends given the code of a person.
     *
     *
     * @param codePerson code of the person
     * @return the list of person codes
     * @throws NoSuchCodeException in case the code does not exist
     */
    public Collection<String> listOfFriends(String codePerson)
            throws NoSuchCodeException {

        if(!persons.containsKey(codePerson)) throw new NoSuchCodeException();

        Person p = persons.get(codePerson);
      /*  return p.getFriends().values().stream() // Stream<Person>
                .map(Person::getCode) // Stream<String>
                .collect(Collectors.toList()); */

        return p.getFriends().keySet();
    }

    /**
     * Retrieves the collection of the code of the friends of the friends
     * of the person whose code is given, i.e. friends of the second level.
     *
     *
     * @param codePerson code of the person
     * @return collections of codes of second level friends
     * @throws NoSuchCodeException in case the code does not exist
     */
    public Collection<String> friendsOfFriends(String codePerson)
            throws NoSuchCodeException {

        List<String> fof = new LinkedList<>();

        if(!persons.containsKey(codePerson)) throw new NoSuchCodeException();
        Person person = persons.get(codePerson);

        for(String s:person.friendsList()){
            Person p2 = persons.get(s);
            fof.addAll(p2.friendsList());
        }

        return fof;
    }

    /**
     * Retrieves the collection of the code of the friends of the friends
     * of the person whose code is given, i.e. friends of the second level.
     * The result has no duplicates.
     *
     *
     * @param codePerson code of the person
     * @return collections of codes of second level friends
     * @throws NoSuchCodeException in case the code does not exist
     */
    public Collection<String> friendsOfFriendsNoRepetition(String codePerson)
            throws NoSuchCodeException {

        if(!persons.containsKey(codePerson)) throw new NoSuchCodeException();

        Set<String> fof = new TreeSet<>();

        Person person = persons.get(codePerson);

        for(String s:person.friendsList()){
            Person p2 = persons.get(s);
            fof.addAll(p2.friendsList());
        }

        return fof;

    }

    /**
     * Creates a new group with the given name
     *
     * @param groupName name of the group
     */

    TreeMap<String,Group> groups = new TreeMap<>();

    public void addGroup(String groupName) {
        Group group = new Group(groupName);
        groups.put(groupName,group);
    }

    /**
     * Retrieves the list of groups.
     *
     * @return the collection of group names
     */
    public Collection<String> listOfGroups() {
        return groups.keySet();
    }

    /**
     * Add a person to a group
     *
     * @param codePerson person code
     * @param groupName  name of the group
     * @throws NoSuchCodeException in case the code or group name do not exist
     */
    public void addPersonToGroup(String codePerson, String groupName) throws NoSuchCodeException {
        if(!persons.containsKey(codePerson)) throw new NoSuchCodeException();
        if(!groups.containsKey(groupName)) throw new NoSuchCodeException();

        Person person = persons.get(codePerson);
        Group group = groups.get(groupName);
        person.addToGroup(group);
        group.addPersons(person);

    }

    /**
     * Retrieves the list of people on a group
     *
     * @param groupName name of the group
     * @return collection of person codes
     */
    public Collection<String> listOfPeopleInGroup(String groupName) {
        if(!groups.containsKey(groupName)) return null;

        Group group = groups.get(groupName);
        return group.personList().stream()
                .map(Person::getCode)
                .collect(Collectors.toList());

    }

    /**
     * Retrieves the code of the person having the largest
     * group of friends
     *
     * @return the code of the person
     */
    public String personWithLargestNumberOfFriends() {
        return persons.values().stream()
                .max(Comparator.comparingInt(Person::getNumFriends))
                .map(Person::getCode)
                .orElse(null);
    }

    /**
     * Find the code of the person with largest number
     * of second level friends
     *
     * @return the code of the person
     */
    public String personWithMostFriendsOfFriends() {
        return persons.values().stream()
                .max(Comparator.comparingInt(Person::getNumberOfFriendsOfFriends))
                .map(Person::getCode)
                .orElse(null);
    }

    /**
     * Find the name of group with the largest number of members
     *
     * @return the name of the group
     */
    public String largestGroup() {
        int max = 0;
        Group groupMax = null;
        for(Group group: groups.values()){
            if(group.getNumPersone() > max){
                max = group.getNumPersone();
                groupMax = group;
            }
        }
        if(groupMax == null) return "";
        return groupMax.getName();
    }

    /**
     * Find the code of the person that is member of
     * the largest number of groups
     *
     * @return the code of the person
     */
    public String personInLargestNumberOfGroups() {

        int max = 0;
        Person personLargNumGroups = null;

        for(Person person: persons.values()){
            if(person.getNumGruppi() > max){
                max = person.getNumGruppi();
                personLargNumGroups = person;
            }
        }
        if(personLargNumGroups == null) return "";
        return personLargNumGroups.getCode();
    }
}