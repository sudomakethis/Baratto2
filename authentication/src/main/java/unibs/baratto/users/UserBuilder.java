package unibs.baratto.users;

import java.util.Map;

public class UserBuilder {

    Map<String, String> credentials;

    public UserBuilder(Map<String, String> credentials) {
        this.credentials = credentials;
    }

    public void addNewCredential(String username, String password) {
        if (credentials.containsKey(username))

            credentials.put(username, password);
    }
}
