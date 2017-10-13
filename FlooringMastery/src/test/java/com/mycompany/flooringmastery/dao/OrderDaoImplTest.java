/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.model.Order;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
public class OrderDaoImplTest {
    
    private OrderDao dao = new OrderDaoImpl();
    //static Map<String, ArrayList<Order>> orderMap = new HashMap<>();
    
    public OrderDaoImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
       
       ArrayList<String> dates = dao.getAllDates();
       for(String s : dates){
           dao.removeAllOrdersByDate(s);
       }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of loadOrders method, of class OrderDaoImpl.
     */
    @Test
    public void testAddOrder() {
        //make an order, add it, get it back and compare them
        Order testOrder = new Order("Caspers", "IN", "Tile", 30);
        dao.addOrder("08061992", testOrder);
        
        assertEquals(dao.getAllOrders("08061992").size(), 1);
    }
    
    @Test
    public void testGetOrder(){
        Order testOrder = new Order("Caspers", "IN", "Tile", 30);
        testOrder.setOrderNumber(3);
        dao.addOrder("08061992", testOrder);
        Order retrievedOrder = dao.getOrder("08061992", 3);
        assertEquals(testOrder, retrievedOrder);
    }
    
    @Test
    public void testRemoveOrder() {
        //add an order, remove it, then check dao size
        Order testOrder = new Order("Caspers", "IN", "Tile", 30);
        testOrder.setOrderNumber(1);
        dao.addOrder("08061992", testOrder);
        dao.removeOrder("08061992", testOrder);
        ArrayList<Order> orders = dao.getAllOrders("08061992");
        assertEquals(0,orders.size());
    }
    
    @Test
    public void testRemoveAllOrdersByDate(){
        //add orders to a date, remove the whole date, then make sure nothing is there
        Order testOrder = new Order("Caspers", "IN", "Tile", 30);
        Order testOrder2 = new Order("Kahl", "IN", "Tile", 30);
        dao.addOrder("08061992", testOrder);
        dao.addOrder("08061992", testOrder2);
        assertEquals(2, dao.getAllOrders("08061992").size());
        
        dao.removeAllOrdersByDate("08061992");
        assertNull(dao.getAllOrders("08061992"));
    }
    
    @Test
    public void testEditOrder(){
        //create an order with an order number, create a new one with that same number and replace it
        Order testOrder = new Order("Patrick", "MI", "Wood", 30);
        testOrder.setOrderNumber(3);
        dao.addOrder("08061992", testOrder);
        Order editedOrder = new Order("Patrick", "MI", "Tile", 40);
        editedOrder.setOrderNumber(3);
        dao.editOrder("08061992", editedOrder);
        //there should be only 1 order for that day and it should be the second one
        assertEquals(1, dao.getAllOrders("08061992").size());
        assertEquals(editedOrder, dao.getOrder("08061992", 3));
        
    }
    
    
    
}
