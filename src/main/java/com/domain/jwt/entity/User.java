package com.domain.jwt.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class User {
    @Id
    private String username;
    private String userfirst_name;
    private String userlast_name;
    private String password;


    //association(connection between two table= Role and User)
     @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
     @JoinTable(name="USER_ROLE",    //CREATED A THIRD TABLE AND WHICH STORE USER AND ITS ROLE DETAILS
     joinColumns = {
             @JoinColumn(name = "USER_ID")

     },
             inverseJoinColumns = {
                       @JoinColumn(name="ROLE_ID")
             }
     )
    private Set<Role> role;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public String getUserfirst_name() {
        return userfirst_name;
    }

    public void setUserfirst_name(String userfirst_name) {
        this.userfirst_name = userfirst_name;
    }

    public String getUserlast_name() {
        return userlast_name;
    }

    public void setUserlast_name(String userlast_name) {
        this.userlast_name = userlast_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
