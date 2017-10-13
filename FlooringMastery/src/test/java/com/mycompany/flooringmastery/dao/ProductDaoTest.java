/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.model.Order;
import java.math.BigDecimal;
import java.util.ArrayList;
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
public class ProductDaoTest {
    
    ProductDao productDao = new ProductDao();
    
    public ProductDaoTest() {
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
     * Test of getCostFromFile method, of class ProductDao.
     */
    @Test
    public void testGetCostFromFile() throws Exception {
        BigDecimal expected = new BigDecimal("3.50");
        Order tileOrder = new Order("Caspers", "OH", "Tile", 300);
        assertEquals(expected, productDao.getCostFromFile(tileOrder));
        
        expected = new BigDecimal("5.15");
        Order woodOrder = new Order("Caspers", "OH", "Wood", 9);
        assertEquals(expected, productDao.getCostFromFile(woodOrder));
    }

    /**
     * Test of getLaborCostFromFile method, of class ProductDao.
     */
    @Test
    public void testGetLaborCostFromFile() throws Exception {
        BigDecimal expected = new BigDecimal("4.15");
        Order tileOrder = new Order("Caspers", "OH", "Tile", 300);
        assertEquals(expected, productDao.getLaborCostFromFile(tileOrder));
        
        expected = new BigDecimal("4.75");
        Order woodOrder = new Order("Caspers", "OH", "Wood", 9);
        assertEquals(expected, productDao.getLaborCostFromFile(woodOrder));
    }

    /**
     * Test of getProducts method, of class ProductDao.
     */
    @Test
    public void testGetProducts() throws Exception {
        int expectedSize = 4;
        assertEquals(expectedSize, productDao.getProducts().size());
    }
    
    @Test
    public void testGetProducts2() throws Exception {
        ArrayList<String> productNames = productDao.getProducts();
        assertTrue(productNames.contains("Tile"));
        assertTrue(productNames.contains("Carpet"));
        assertTrue(productNames.contains("Wood"));
        assertTrue(productNames.contains("Laminate"));
    }
    
}
