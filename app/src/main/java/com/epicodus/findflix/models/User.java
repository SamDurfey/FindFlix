package com.epicodus.findflix.models;

public class User {
    private String userName;
    private String email;
    private String password;
    private String uid;

    public User(String userName, String email, String password, String uid) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUid() {
        return uid;
    }
}
