package com.tbp.av.model;


import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @Column(nullable = false, unique = true)
    String username;
    @Column(nullable = false)
    String password;
    @Column(nullable = false)
    String salt;

    public User() {

    }

    public User(String username, String password, String salt) {
        this.username = username;
        this.password = password;
        this.salt = salt;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }
}
