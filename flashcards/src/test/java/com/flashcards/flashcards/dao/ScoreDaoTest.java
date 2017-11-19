/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flashcards.flashcards.dao;

import com.flashcards.flashcards.model.Score;
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
public class ScoreDaoTest {
    
    @Autowired
    ScoreDao scoreDao;
    
    public ScoreDaoTest() {
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
     * Test of save method, of class ScoreDao.
     */
    @Test
    public void testSaveFindById() {
        Score newScore = new Score();
        newScore.setCorrectAnswer(1);
        newScore.setWrongAnswer(5);
        
        scoreDao.save(newScore);
        Score fromDao = scoreDao.findById(newScore.getId());
        
        assertEquals(fromDao, newScore);
        
        
    }

    /**
     * Test of findAll method, of class ScoreDao.
     */
    @Test
    public void testFindAll() {
        
        Score newScore = new Score();
        newScore.setCorrectAnswer(1);
        newScore.setWrongAnswer(5);
        
        Score newScore2 = new Score();
        newScore2.setCorrectAnswer(1);
        newScore2.setWrongAnswer(5);
        
        Score newScore3 = new Score();
        newScore3.setCorrectAnswer(1);
        newScore3.setWrongAnswer(5);
        
        scoreDao.save(newScore);
        scoreDao.save(newScore2);
        scoreDao.save(newScore3);
        
        List<Score> scoresFromDao = scoreDao.findAll();
        assertEquals(scoresFromDao.size(), 3);
        
    }

    /**
     * Test of deleteById method, of class ScoreDao.
     */
    @Test
    public void testDeleteById() {
        Score newScore = new Score();
        newScore.setCorrectAnswer(1);
        newScore.setWrongAnswer(5);
        
        Score newScore2 = new Score();
        newScore2.setCorrectAnswer(1);
        newScore2.setWrongAnswer(5);
        
        Score newScore3 = new Score();
        newScore3.setCorrectAnswer(1);
        newScore3.setWrongAnswer(5);
        
        scoreDao.save(newScore);
        scoreDao.save(newScore2);
        scoreDao.save(newScore3);
        scoreDao.delete(newScore3.getId());
        
        List<Score> scoresFromDao = scoreDao.findAll();
        assertEquals(scoresFromDao.size(), 2);
    }
    
}
