package com.example.plantmanager.models;

public class User {
    private int id;
    private String lastName;
    private String firstName;
    private String email;
    private String username;
    private String password;
    private boolean active;

    public User(int id, String lastName, String firstName, String email, String username, String password, boolean active) {
        this(lastName, firstName, email, username, password, active);
        this.id = id;
    }

    public User(String lastName, String firstName, String email, String username, String password, boolean active) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
