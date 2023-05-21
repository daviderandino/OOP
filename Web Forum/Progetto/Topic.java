package forum;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class Topic {

    private String name;
    private String subject;
    private User creator;
    private LinkedList<Message> messages = new LinkedList<>();
    private HashMap<User,Message> mappaMsg = new HashMap<>();
    private long numMsg = 0;

    public Topic(String name, String subject, User creator) {
        this.name = name;
        this.subject = subject;
        this.creator = creator;
    }

    public String getName(){
        return name;
    }

    public String getSubject(){
        return subject;
    }

    public User getUser(){
        return creator;
    }

    public Message submitMessage(User user, String title, String body){
        Message message = new Message(user,title,body);
        message.setTopic(this);
        messages.add(message);
        numMsg++;
        user.setNumSubmittedMsg();
        return message;
    }

    public Collection<Message> getMessagges(){
        return messages;
    }

    public long numMessages(){
        return numMsg;
    }
}