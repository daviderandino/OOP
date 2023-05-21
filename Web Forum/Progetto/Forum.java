package forum;

import java.util.*;
import java.util.stream.Collectors;

public class Forum {

    private String url;
    private HashMap<String,User> usersPerNick = new HashMap<>();
    private HashMap<User,String> passwords = new HashMap<>();
    private ArrayList<Topic> topics = new ArrayList<>();


    public Forum(String url){
        this.url = url;
    }

    public String getUrl(){
        return url;
    }

    public User registerUser(String nick, String first, String last, String email, String password)
            throws DuplicateNickname {
        if(usersPerNick.containsKey(nick)) throw new DuplicateNickname();
        User user = new User(nick,first,last,email,password);
        usersPerNick.put(nick,user);
        passwords.put(user,password);
        return user;
    }

    public User login(String nick, String password){

        if(!usersPerNick.containsKey(nick)){
            return null;
        }
        User user = usersPerNick.get(nick);
        if(passwords.get(user).equals(password)) return user;
        return null;

    }

    public Topic createTopic(String name, String subject, User creator){
        Topic topic = new Topic(name,subject,creator);
        topics.add(topic);
        return topic;
    }

    public Collection<Topic> listTopic(){
        return topics;
    }

    public List<User> rankUsers(){
        List<User> userList = new LinkedList<>(usersPerNick.values());
        return userList.stream()
                .sorted((u1, u2) -> (int) (u2.numSubmitted() - u1.numSubmitted()))
                .collect(Collectors.toList());
    }

    public double averageMessages(){
        long numMsg = 0;
        int numTopics = 0;

        for (Topic t : topics) {
            numMsg += t.numMessages();
            numTopics++;
        }

        if (numTopics == 0) {
            return 0; // To avoid division by zero
        }

        return (double) numMsg / numTopics;
    }

}
