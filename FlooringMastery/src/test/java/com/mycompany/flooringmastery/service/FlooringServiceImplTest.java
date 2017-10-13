/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.dao.StateDao;
import com.mycompany.flooringmastery.model.Order;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author kylecaaspers
 */
public class FlooringServiceImplTest {
    
    FlooringService service;
    
    public FlooringServiceImplTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("TestApplicationContext.xml");
        service = ctx.getBean("serviceLayer", FlooringService.class);
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
     * Test of generateDailyOrders method, of class FlooringServiceImpl.
     */
    @Test
    public void testGetAllOrders() throws Exception {
        int expected = 2;
        ArrayList<Order> ordersForDate = service.getAllOrders("08061992");
        assertEquals(expected, ordersForDate.size());
    }
    
    @Test
    public void testGetOrder() throws Exception {
        int expected = 1;
        Order order = service.getOrder("08061992", 1);
        assertEquals(1, order.getOrderNumber());
        //essentially proving that we retrieved the correct order
        
    }
    
    @Test
    public void testAddOrder() throws Exception {
       
    }
    
    @Test
    public void testRemoveOrder() throws Exception {
        
    }
    
    @Test
    public void testReplaceOrder() throws Exception {
        
    }
    
    @Test
    public void testValidateOrder() throws Exception {
        //make a wacky order and test that it throws an exception
        boolean caught = false;
        Order wackyOrder = new Order("", "", "", 0);
        try{
            service.validateOrder(wackyOrder);
        } catch(FlooringServiceValidationException e){
            caught = true;
        }
        assertTrue(caught);
    }
    
    @Test
    public void testCalculateFields() throws Exception {
        //give a basic order like what the user inputs and show that I can fill out the rest of it
        Order userInput = new Order("Caspers", "IN", "Tile", 50);
        userInput = service.calculateFields(userInput);
        Order preFabOrder = service.getOrder("08061992", 1);
        //This should be equal to my pre-made dao Order
        assertEquals(userInput.getTotalFormatted(), preFabOrder.getTotalFormatted());
        //I am making the assumption that because the total was equal, all of the previous fields
        //used in that calculation were true as well.
        
    }
    
    @Test
    public void testAssignOrderNumber(){
        //I am making the assumption that because our stubDao has two items for the date in question
        //the service will give my new order an orderNumber of 3
        Order newOrder = new Order("Kahl", "MI", "Laminate", 30);
        int orderNumber = service.assignOrderNumber(newOrder, "08061992");
        assertEquals(3, orderNumber);
        
    }
    
    
    
}
