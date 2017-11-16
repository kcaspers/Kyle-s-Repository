/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flashcards.flashcards.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author kylecaaspers
 */
@Entity
public class User {
    
    public User(){};
    
    private int id;
    private String userName;
    private String password;
    private List<Test> userTests;

    
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    @Column(name = "username")
    @NotNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    
    @Column(name = "password")
    @NotNull
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    //This is one half of the one-to-many relationship
    //it is allowed to be null, users don't start out with tests
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "user")
    public List<Test> getUserTests() {
        return userTests;
    }

    public void setUserTests(List<Test> userTests) {
        this.userTests = userTests;
    }
    
    
}
