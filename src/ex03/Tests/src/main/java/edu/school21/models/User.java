package edu.school21.models;

import java.util.Objects;

public class User {
    private int identifier;
    private String login;
    private String password;
    private boolean authenticationStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return identifier == user.identifier
                && authenticationStatus == user.authenticationStatus
                && Objects.equals(login, user.login)
                && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, login, password, authenticationStatus);
    }

    public User() {
        new User();
    }

    public User(int id, String userLogin, String userPassword, boolean status) {
        identifier = id;
        login = userLogin;
        password = userPassword;
        authenticationStatus = status;
    }

    public void setAuthenticationStatus(boolean newStatus) {
        authenticationStatus = newStatus;
    }

    public int getId() {
        return identifier;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean getAuthenticationStatus() {
        return authenticationStatus;
    }
}
