/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flashcards.flashcards.model;

import java.util.List;
import javax.persistence.Entity;

/**
 *
 * @author kylecaaspers
 */
@Entity
public class TestSuite {
    
    public TestSuite(){};
    
    private String title;
    private List<Card> cards;
    
}
