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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author kylecaaspers
 */
@Entity
public class TestTaken {
    
    public TestTaken(){};
    
    private int id;
    private User user;
    private LocalDate testDate;
    private CardSuite cardSuite;
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
    //I might need a fetch-join or something
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "userid")
    @NotNull
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "TestTakenDate")
    @NotNull
    public LocalDate getTestDate() {
        return testDate;
    }

    public void setTestDate(LocalDate testDate) {
        this.testDate = testDate;
    }

    //a test references a testSuite, but testSuite doesn't need to know
    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    public CardSuite getCardSuite() {
        return cardSuite;
    }

    public void setCardSuite(CardSuite cardSuite) {
        this.cardSuite = cardSuite;
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
