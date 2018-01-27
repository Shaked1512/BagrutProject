package com.appschool.bagrutproject.Classes_OF_Eli_De_Shpitz;



public class User {
    private String name;
    private String password;
    private String created;

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public User(String name, String password, String created) {
        this.name = name;
        this.password = password;
        this.created = created;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", created='" + created + '\'' +
                '}';
    }
}