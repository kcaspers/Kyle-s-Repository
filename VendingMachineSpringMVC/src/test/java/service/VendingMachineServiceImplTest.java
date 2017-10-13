/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.InsufficientFundsException;
import dao.VendingMachineInventoryException;
import java.math.BigDecimal;
import javax.inject.Inject;
import model.Change;
import model.Item;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author kylecaaspers
 */
public class VendingMachineServiceImplTest {

    private VendingMachineService service;

    @Inject
    public VendingMachineServiceImplTest() {
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
        service = ctx.getBean("service", VendingMachineService.class);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of clearFunds method, of class VendingMachineServiceImpl.
     */
    @Test
    public void testIsItemPresent() throws VendingMachineInventoryException {
        boolean thrown = false;
        try {
            service.isItemPresent("shouldNotBePresent");
        } catch (VendingMachineInventoryException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
    
    @Test
    public void testIsItemPresent2() throws Exception {
        Item testItem = new Item("testitem", "1.50", "5");
        service.addItem("testitem", testItem);
        assertTrue(service.isItemPresent("testitem"));
    }
    
    @Test
    public void testAreFundsSufficient() throws VendingMachineInventoryException {
        boolean thrown = false;
        Item expensiveitem = new Item("Expensive Item", "200.25", 6);
        service.addItem("expensive item", expensiveitem);
        try{
           service.areFundsSufficient("expensive item");
        } catch(InsufficientFundsException e){
            thrown = true;
        }
        assertTrue(thrown);
    }
    
    @Test
    public void testAreFundsSufficient2() throws Exception {
        Item expensiveitem = new Item("Expensive Item", "200.25", 6);
        service.addItem("Expensive Item", expensiveitem);
        service.addFunds("300.00");
        assertTrue(service.areFundsSufficient("Expensive Item"));
    }
    
    @Test
    public void testcalculateChange1() {
        Item testItem = new Item("testitem", "1.50", "5");
        BigDecimal userFunds = new BigDecimal("3.00");
        String change = service.calculateChange(userFunds, testItem);
        String expectedChange = "6 quarters<br>";
        assertEquals(change, expectedChange);
    }
    
    @Test
    public void testcalculateChange2() {
        Item testItem = new Item("testitem", "1.50", "5");
        BigDecimal userFunds = new BigDecimal("1.67");
        String change = service.calculateChange(userFunds, testItem);
        String expectedChange = "1 dimes<br>1 nickels<br>2 pennies<br>";
        
        assertEquals(change, expectedChange);
    }
    
    @Test
    public void testMoreRequired() throws VendingMachineInventoryException{
        //the moreRequired method returns the difference between how much money the
        //user has, vs how much the item costs
        service.addFunds("1.00");
        Item testitem = new Item("testitem", "1.50", "5");
        service.addItem("testitem", testitem);
        BigDecimal moneyRequired = service.moreRequired("testitem");
        BigDecimal expectedMoneyRequired = new BigDecimal("0.50");
        assertEquals(moneyRequired, expectedMoneyRequired);
    }

}
