package forum;

public class Main {
    public static void main(String[] args) throws DuplicateNickname {

        Forum forum = new Forum("url");
        User user = forum.registerUser("ciao","d","d","d","abcd");
        Topic topic = forum.createTopic("ciao","c",user);
        Message m = topic.submitMessage(user,"ciao","ciao");
        Message m2 = topic.submitMessage(user,"boh","ciao");
        System.out.println(topic.numMessages());
        System.out.println(forum.averageMessages());

    }
}
