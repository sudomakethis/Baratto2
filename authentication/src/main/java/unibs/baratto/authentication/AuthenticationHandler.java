package unibs.baratto.authentication;

public interface AuthenticationHandler {
    AuthenticationResult requestAccess(String username, String password);

    void setNextHandler(AuthenticationHandler handler);

    String getStatus();
}
