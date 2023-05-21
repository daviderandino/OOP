package forum;

public class Message {

    private User user;
    private String title;
    private String body;
    private Topic topic;
    private long timestamp;

    public Message(User user, String title, String body) {
        this.user = user;
        this.title = title;
        this.body = body;
        this.timestamp = System.currentTimeMillis();
    }

    public String getTitle(){
        return title;
    }

    public String getBody(){
        return body;
    }

    public User getUser(){
        return user;
    }

    public Topic getTopic(){
        return topic;
    }

    public void setTopic(Topic t) {
        this.topic = t;
    }

    public long getTimestamp(){
        return timestamp;
    }
}
