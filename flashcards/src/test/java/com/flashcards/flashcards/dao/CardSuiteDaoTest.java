/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flashcards.flashcards.dao;

import com.flashcards.flashcards.model.Card;
import com.flashcards.flashcards.model.CardSuite;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author kylecaaspers
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class CardSuiteDaoTest {
    
    @Autowired
    CardSuiteDao cardSuiteDao;
    
    public CardSuiteDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of save method, of class CardSuiteDao.
     */
    @Test
    public void testSaveFindById() {
        CardSuite cardSuite = new CardSuite();
        cardSuite.setTitle("Test suite");
        
        //create the cards to put on the cardSuite
        Card newCard1 = new Card();
        newCard1.setQuestion("question");
        newCard1.setAnswer("answer");
        
        Card newCard2 = new Card();
        newCard2.setQuestion("question");
        newCard2.setAnswer("answer");
        
        List<Card> cards = new ArrayList<>();
        cards.add(newCard1);
        cards.add(newCard2);
        
        cardSuite.setCards(cards);
        cardSuiteDao.save(cardSuite);
        CardSuite fromDao = cardSuiteDao.findById(cardSuite.getId());
        
        assertEquals(fromDao, cardSuite);
        
    }

    /**
     * Test of findAll method, of class CardSuiteDao.
     */
    @Test
    public void testFindAll() {
        CardSuite cardSuite = new CardSuite();
        cardSuite.setTitle("Test suite");
        
        CardSuite cardSuite2 = new CardSuite();
        cardSuite2.setTitle("Test suite 2");
        
        Card newCard1 = new Card();
        newCard1.setQuestion("question");
        newCard1.setAnswer("answer");
        
        Card newCard2 = new Card();
        newCard2.setQuestion("question");
        newCard2.setAnswer("answer");
        
        List<Card> cards = new ArrayList<>();
        cards.add(newCard1);
        cards.add(newCard2);
        
        cardSuite.setCards(cards);
        cardSuite2.setCards(cards);
        
        cardSuiteDao.save(cardSuite);
        cardSuiteDao.save(cardSuite2);
        
        List<CardSuite> allSuites = cardSuiteDao.findAll();
        assertEquals(allSuites.size(), 2);
    }

    /**
     * Test of delete method, of class CardSuiteDao.
     */
    @Test
    public void testDelete() {
        CardSuite cardSuite = new CardSuite();
        cardSuite.setTitle("Test suite");
        
        CardSuite cardSuite2 = new CardSuite();
        cardSuite2.setTitle("Test suite 2");
        
        Card newCard1 = new Card();
        newCard1.setQuestion("question");
        newCard1.setAnswer("answer");
        
        Card newCard2 = new Card();
        newCard2.setQuestion("question");
        newCard2.setAnswer("answer");
        
        List<Card> cards = new ArrayList<>();
        cards.add(newCard1);
        cards.add(newCard2);
        
        cardSuite.setCards(cards);
        cardSuite2.setCards(cards);
        cardSuiteDao.save(cardSuite);
        cardSuiteDao.save(cardSuite2);
        
        cardSuiteDao.delete(cardSuite.getId());
        
        List<CardSuite> allSuites = cardSuiteDao.findAll();
        
        assertEquals(1, allSuites.size());
        
    }
   
}
