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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author kylecaaspers
 */
@Entity
public class User {
    
    public User(){};
    
    private int userid;
    private String userName;
    private String password;
    private List<TestTaken> userTests;

    
    @Id
    @GeneratedValue
    @Column(name = "userid", unique = true)
    public int getId() {
        return userid;
    }

    public void setId(int userid) {
        this.userid = userid;
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
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    //@JoinColumn(name = "user_id")
    public List<TestTaken> getUserTests() {
        return userTests;
    }

    public void setUserTests(List<TestTaken> userTests) {
        this.userTests = userTests;
    }
    
    
}
