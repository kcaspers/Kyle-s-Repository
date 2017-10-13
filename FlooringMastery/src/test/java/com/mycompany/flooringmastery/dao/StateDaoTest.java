/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.model.Order;
import java.math.BigDecimal;
import java.util.ArrayList;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kylecaaspers
 */
public class StateDaoTest {
    
    StateDao stateDao = new StateDao();
    
    public StateDaoTest() {
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
     * Test of getTaxRateFromFile method, of class StateDao.
     */
    @Test
    public void testGetTaxRateFromFile() throws Exception {
        BigDecimal expected = new BigDecimal("6.25");
        Order ohioOrder = new Order("Caspers", "OH", "Tile", 50);
        assertEquals(expected, stateDao.getTaxRateFromFile(ohioOrder));
        
        expected = new BigDecimal("6.75");
        Order pennOrder = new Order("Caspers", "PA", "Carpet", 150);
        assertEquals(expected, stateDao.getTaxRateFromFile(pennOrder));
    }

    /**
     * Test of getStates method, of class StateDao.
     */
    @Test
    public void testGetStates() throws Exception {
        //get states, then test size (it should be 4)
        int expectedSize = 4;
        assertEquals(expectedSize, stateDao.getStates().size());
    }
    
    @Test
    public void testGetStates2() throws Exception {
        ArrayList<String> stateNames = stateDao.getStates();
        assertTrue(stateNames.contains("PA"));
        assertTrue(stateNames.contains("OH"));
        assertTrue(stateNames.contains("IN"));
        assertTrue(stateNames.contains("MI"));
    }
}
