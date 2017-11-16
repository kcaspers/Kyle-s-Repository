/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flashcards.flashcards.model;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author kylecaaspers
 */
@Entity
public class Test {
    
    public Test(){};
    
    private int id;
    private User user;
    private LocalDate date;
    private TestSuite testSuite;
    private Score score;

    
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    //this is one half of the one-to-many relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "date")
    @NotNull
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    //a test references a testSuite, but testSuite doesn't need to know
    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    public TestSuite getTestSuite() {
        return testSuite;
    }

    public void setTestSuite(TestSuite testSuite) {
        this.testSuite = testSuite;
    }

    //Test references a score, which is associated with this particular test
    //score is the child
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}
