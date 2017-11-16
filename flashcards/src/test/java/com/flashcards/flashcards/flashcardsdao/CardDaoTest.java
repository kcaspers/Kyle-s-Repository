/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flashcards.flashcards.flashcardsdao;

import com.flashcards.flashcards.model.Card;
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
public class CardDaoTest {
    
    @Autowired
    CardDao cardDao;
    
    public CardDaoTest() {
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
     * Test of save method, of class CardDao.
     */
    @Test
    public void testSave() {
        Card newCard = new Card();
        newCard.setQuestion("This is the question.");
        newCard.setAnswer("This is the answer.");
        cardDao.save(newCard);
        Card fromDao = cardDao.findById(newCard.getId());
        assertEquals(fromDao, newCard);
        
    }

    /**
     * Test of findById method, of class CardDao.
     */
    @Test
    public void testFindById() {
        Card newCard = new Card();
        newCard.setQuestion("This is the question.");
        newCard.setAnswer("This is the answer.");
        cardDao.save(newCard);
        Card fromDao = cardDao.findById(newCard.getId());
        assertEquals(fromDao, newCard);
    }

    /**
     * Test of findAll method, of class CardDao.
     */
    @Test
    public void testFindAll() {
        Card newCard1 = new Card();
        newCard1.setQuestion("This is the question.");
        newCard1.setAnswer("This is the answer.");
        cardDao.save(newCard1);
        
        Card newCard2 = new Card();
        newCard2.setQuestion("This is the question.");
        newCard2.setAnswer("This is the answer.");
        cardDao.save(newCard2);
        
        Card newCard3 = new Card();
        newCard3.setQuestion("This is the question.");
        newCard3.setAnswer("This is the answer.");
        cardDao.save(newCard3);
        
        List<Card> cards = cardDao.findAll();
        int expected = 3;
        assertEquals(expected, cards.size());
        
    }
    
}
