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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author kylecaaspers
 */
@Entity
public class TestSuite {
    
    public TestSuite(){};
    
    private int id;
    private String title;
    private List<Card> cards;

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "title")
    @NotNull
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //cards has a many-to-many relationship with testSuite
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "TestSuite_Card", joinColumns = @JoinColumn(name = "TestSuite_id"), 
            inverseJoinColumns = @JoinColumn(name = "Card_id"))
    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
    
}
