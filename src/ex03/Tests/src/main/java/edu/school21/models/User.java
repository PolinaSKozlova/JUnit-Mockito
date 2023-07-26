package edu.school21.models;

public class User {
    private int identifier;
    private String login;
    private String password;
    private boolean authenticationStatus;

    public User() {
    }

    public User(int id, String userLogin, String userPassword) {
        identifier = id;
        login = userLogin;
        password = userPassword;
        authenticationStatus = false;
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
