/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import model.Item;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author kylecaaspers
 */
public class VendingMachineDaoImplTest {
    
    private VendingMachineDao dao;
    private static HashMap<String, Item> vendingMap = new HashMap<>();
    
    public VendingMachineDaoImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("dao", VendingMachineDao.class);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of clearFunds method, of class VendingMachineDaoImpl.
     */
    @Test
    public void testItemSold() throws VendingMachineInventoryException{
        Item testItem = new Item("testitem", "1.50", "5");
        dao.addItem("testitem", testItem);
        dao.itemSold(testItem.getName());
        assertEquals(4, testItem.getQuantity());
        dao.removeItem(testItem.getName());
        
    }
    
    @Test
    public void testGetAllItems() throws VendingMachineInventoryException{
        ArrayList<Item> inventoryMap = dao.getAllItems();
        int firstSize = inventoryMap.size();
        //add one and check the resulting size
        Item testItem = new Item("testitem", "1.50", "5");
        dao.addItem("testitem", testItem);
        int secondSize = (dao.getAllItems()).size();
        firstSize++;
        assertEquals(firstSize, secondSize);
        dao.removeItem(testItem.getName());
    }
    
    @Test
    public void testAddGetFunds(){
        BigDecimal testFunds = new BigDecimal("29.00");
        dao.addFunds(testFunds);
        BigDecimal pulledFunds = dao.getUserFunds();
        BigDecimal expectedFunds = new BigDecimal("29.00");
        assertEquals(pulledFunds, expectedFunds);
    }
    
    @Test
    public void testClearFunds(){
        BigDecimal testFunds = new BigDecimal("29.00");
        dao.addFunds(testFunds);
        assertEquals(testFunds, dao.getUserFunds());
        dao.clearFunds();
        BigDecimal expected = new BigDecimal("0");
        assertEquals(dao.getUserFunds(), expected);
    }
    
    
    
}
