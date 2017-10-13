/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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
public class VendingMachineDaoTest {

    private VendingMachineDao dao = new VendingMachineDaoImpl();
    static ArrayList<Item> itemList;
    
    public VendingMachineDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        itemList = dao.getAllItems();
        for (Item currentItem : itemList) {
            dao.removeItem(currentItem.getName());
        }
    }

    @After
    public void tearDown() throws Exception{
        for(Item currentItem : itemList){
            dao.addItem(currentItem.getName(), currentItem);
        }
        dao.writeInventory();
        //this should get my file back to normal after the tests
    }

    /**
     * Test of writeInventory method, of class VendingMachineDao.
     */
    @Test
    public void testWriteInventory() throws Exception {
    }

    /**
     * Test of loadInventory method, of class VendingMachineDao.
     */
    @Test
    public void testLoadInventory() throws Exception {
    }

    /**
     * Test of itemSold method, of class VendingMachineDao.
     */
    @Test
    public void testItemSold() throws Exception {
        Item testitem = new Item("testitem", "1.50", "5");
        dao.addItem("testitem", testitem);
        dao.itemSold(testitem.getName());
        
        assertEquals(4, testitem.getQuantity());
        dao.removeItem(testitem.getName());
//        for(Item currentItem : itemList){
//            dao.addItem(currentItem.getName(), currentItem);
//        }
//        dao.writeInventory();
    }

    /**
     * Test of getAllItems method, of class VendingMachineDao.
     */
    @Test
    public void testGetAllItems() throws Exception {
        //I check the size of the getAllItems array against the loadInventory return size
        HashMap<String, Item> inventoryMap = dao.loadInventory();

        assertEquals(inventoryMap.size(), dao.getAllItems().size());
    }

    /**
     * Test of addFunds method, of class VendingMachineDao.
     */
    @Test
    public void testAddGetFunds() {
        BigDecimal testFunds = new BigDecimal("29.00");
        dao.addFunds(testFunds);
        BigDecimal pulledFunds = dao.getUserFunds();
        BigDecimal expectedFunds = new BigDecimal("29.00");
        assertEquals(pulledFunds, expectedFunds);
    }

}
