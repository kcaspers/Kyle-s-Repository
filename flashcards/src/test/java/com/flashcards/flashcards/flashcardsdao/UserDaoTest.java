/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flashcards.flashcards.flashcardsdao;

import com.flashcards.flashcards.dao.UserDao;
import com.flashcards.flashcards.model.User;
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
public class UserDaoTest {
    
    @Autowired
    UserDao userDao;
    
    public UserDaoTest() {
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
     * Test of save method, of class UserDao.
     */
    @Test
    public void testSaveFindById() {
        User newUser = new User();
        newUser.setUserName("testUser");
        newUser.setPassword("password");
        
        userDao.save(newUser);
        User fromDao = userDao.findById(newUser.getId());
        assertEquals(newUser, fromDao);
        
    }

    /**
     * Test of findAll method, of class UserDao.
     */
    @Test
    public void testFindAll() {
        User newUser = new User();
        newUser.setUserName("testUser");
        newUser.setPassword("password");
        userDao.save(newUser);
        
        User newUser2 = new User();
        newUser2.setUserName("testUser2");
        newUser2.setPassword("password");
        userDao.save(newUser2);
        
        List<User> users = userDao.findAll();
        assertEquals(users.size(), 2);
        
    }

    /**
     * Test of deleteById method, of class UserDao.
     */
    @Test
    public void testDeleteById() {
        User newUser = new User();
        newUser.setUserName("testUser");
        newUser.setPassword("password");
        userDao.save(newUser);
        
        User newUser2 = new User();
        newUser2.setUserName("testUser2");
        newUser2.setPassword("password");
        userDao.save(newUser2);
        
        List<User> users = userDao.findAll();
        assertEquals(users.size(), 2);
        
        userDao.delete(newUser2.getId());
        
        users = userDao.findAll();
        assertEquals(1, users.size());
    }
    
}
