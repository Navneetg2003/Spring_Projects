package com.myapp.p_23_maplocator.Model;

import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String emailId;
    private String password;

    private boolean oauthUser;

    public Users(Long id, String username, String emailId, String password) {
        this.id = id;
        this.username = username;
        this.emailId = emailId;
        this.password = password;
    }

    public Users() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isOauthUser() {
        return oauthUser;
    }

    public void setOauthUser(boolean oauthUser) {
        this.oauthUser = oauthUser;
    }
}