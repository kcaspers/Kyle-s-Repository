/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.dao.InsufficientFundsException;
import com.mycompany.vendingmachine.dao.VendingMachineInventoryException;
import com.mycompany.vendingmachine.dto.Change;
import com.mycompany.vendingmachine.dto.Item;
import java.math.BigDecimal;
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
public class VendingMachineServiceImplTest {
    
    VendingMachineService service;
    
    public VendingMachineServiceImplTest() {

        ApplicationContext ctx =
            new ClassPathXmlApplicationContext("testApplicationContext.xml");
         service =
                ctx.getBean("vendingMachineService", VendingMachineServiceImpl.class);
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
     * Test of isItemPresent method, of class VendingMachineServiceImpl.
     */
    @Test
    public void testIsItemPresent() throws Exception {
        boolean thrown = false;
        try{
            service.isItemPresent("shouldNotBePresent");
        } catch(VendingMachineInventoryException e){
            thrown = true;
        }
        assertTrue(thrown);
    }
    
    @Test
    public void testIsItemPresent2() throws Exception {
        //dao.addItem("Test Item", testItem);
        assertTrue(service.isItemPresent("Test Item"));
    }

    /**
     * Test of getUserFunds method, of class VendingMachineServiceImpl.
     */
    
    @Test
    public void testAreFundsSufficient() throws Exception {
        boolean thrown = false;
        //dao.addItem("Expensive Item", expensiveItem);
        try{
           service.areFundsSufficient("Expensive Item");
        } catch(InsufficientFundsException e){
            thrown = true;
        }
        assertTrue(thrown);
    }
    
    @Test
    public void testAreFundsSufficient2() throws Exception {
        //dao.addItem("Expensive Item", expensiveItem);
        BigDecimal moreMoney = new BigDecimal("10.00");
        service.addFunds("10.00");
        assertTrue(service.areFundsSufficient("Expensive Item"));
    }

    
    @Test
    public void calculateChange1() {
        Item testItem = service.getItem("Test Item");
        BigDecimal userFunds = new BigDecimal("3.00");
        Change change = service.calculateChange(userFunds, testItem);
        Change expectedChange = new Change(12, 0, 0, 0);
        assertEquals(change.getQuarters(), expectedChange.getQuarters());
    }
    
    @Test
    public void calculateChange2() {
        Item testItem = service.getItem("Test Item");
        BigDecimal userFunds = new BigDecimal("0.67");
        Change change = service.calculateChange(userFunds, testItem);
        Change expectedChange = new Change(2, 1, 1, 2);
        assertEquals(change.getQuarters(), expectedChange.getQuarters());
        assertEquals(change.getDimes(), expectedChange.getDimes());
        assertEquals(change.getNickels(), expectedChange.getNickels());
        assertEquals(change.getPennies(), expectedChange.getPennies());
    }

   
   
    
}
