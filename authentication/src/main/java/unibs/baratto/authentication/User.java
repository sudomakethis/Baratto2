package unibs.baratto.authentication;

public abstract class User {

    private final String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public abstract Runnable login();
}
