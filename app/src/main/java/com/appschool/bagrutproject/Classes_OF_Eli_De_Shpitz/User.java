package com.appschool.bagrutproject.Classes_OF_Eli_De_Shpitz;



public class User {
    private String username;
    private String firstname;
    private String lastname;
    private String created;

    public User(String username, String firstname, String lastname, String created) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.created = created;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return
                "username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", created='" + created + '\'' +
                '}';
    }
}