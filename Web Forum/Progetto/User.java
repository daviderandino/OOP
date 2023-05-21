package forum;

public class User implements Comparable<User> {

    private String nick;
    private String first;
    private String last;
    private String email;
    private String password;
    private long numSubmittedMsg;

    public User(String nick, String first, String last, String email, String password) {
        this.nick = nick;
        this.first = first;
        this.last = last;
        this.email = email;
        this.password = password;
        this.numSubmittedMsg = 0;
    }

    public String getNick(){
        return nick;
    }

    public String getFirst(){
        return first;
    }

    public String getLast(){
        return last;
    }

    public String getEmail(){
        return email;
    }

    public void setNumSubmittedMsg(){
        numSubmittedMsg++;
    }

    public long numSubmitted(){
        return numSubmittedMsg;
    }

    public int compareTo(User arg0) {
        return 0;
    }
}
