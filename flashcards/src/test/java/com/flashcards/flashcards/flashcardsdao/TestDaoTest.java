/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flashcards.flashcards.flashcardsdao;

import com.flashcards.flashcards.dao.TestDao;
import com.flashcards.flashcards.model.TestSuite;
import com.flashcards.flashcards.model.TestTaken;
import com.flashcards.flashcards.model.User;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
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
public class TestDaoTest {
    
    @Autowired
    TestDao testDao;
    
    public TestDaoTest() {
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
     * Test of save method, of class TestDao.
     */
    @Test
    public void testSaveFindById() {
        //create a date, user, testSuite
        LocalDate date = LocalDate.of(2016, Month.AUGUST, 6);
        User newUser = new User();
        newUser.setUserName("test User");
        newUser.setPassword("test password");
        
        
        TestSuite testSuite = new TestSuite();
        testSuite.setTitle("fake TestSuite");
        
        TestTaken test = new TestTaken();
        test.setTestDate(date);
        test.setTestSuite(testSuite);
        test.setUser(newUser);
        
        testDao.save(test);
        //TestTaken fromDao = testDao.findById(test.getId());
        
        //assertEquals(fromDao, test);
        
    }

    /**
     * Test of findAll method, of class TestDao.
     */
    @Test
    public void testFindAll() {
    }

    /**
     * Test of deleteById method, of class TestDao.
     */
    @Test
    public void testDeleteById() {
    }
    
}
