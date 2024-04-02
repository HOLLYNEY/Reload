package main.User;

public class User {

    private String login;
    private String password;
    private String timestamp;
    private String email;



    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTimestamp() {
        return timestamp;}

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    public User(String login, String password, String timestamp, String email) {
        this.login = login;
        this.password = password;
        this.timestamp = timestamp;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", timestamp=" + timestamp +
                ", email='" + email + '\'' +
                '}';
    }

}

