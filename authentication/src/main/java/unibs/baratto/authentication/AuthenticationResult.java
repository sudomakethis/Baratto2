package unibs.baratto.authentication;

import unibs.baratto.users.User;

public class AuthenticationResult {
    private boolean passed;
    private User user;

    public AuthenticationResult(boolean passed, User user) {
        this.passed = passed;
        this.user = user;
    }

    public boolean isPassed() {
        return passed;
    }

    public User getUser() {
        return user;
    }
}
