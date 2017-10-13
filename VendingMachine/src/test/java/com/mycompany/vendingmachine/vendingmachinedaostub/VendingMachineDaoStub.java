/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.vendingmachinedaostub;

import com.mycompany.vendingmachine.dao.VendingMachineDao;
import com.mycompany.vendingmachine.dao.VendingMachineInventoryException;
import com.mycompany.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;


/**
 *
 * @author kylecaaspers
 */
public class VendingMachineDaoStub implements VendingMachineDao{

    //make this always work
    private HashMap<String, Item> vendingMap = new HashMap<>();
    private BigDecimal userFunds = new BigDecimal("0");
    
    //create a test item, and an expensive item
    Item testItem;
    Item expensiveItem;
    
    public VendingMachineDaoStub(){
        testItem = new Item("Test Item", "0.00", 6);
        expensiveItem = new Item("Expensive Item", "5.50", 3);
        
        vendingMap.put("Test Item", testItem);
        vendingMap.put("Expensive Item", expensiveItem);
    }
    
    @Override
    public void writeInventory() throws VendingMachineInventoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<String, Item> loadInventory() throws VendingMachineInventoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Item itemSold(String item) throws VendingMachineInventoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Item> getAllItems() throws VendingMachineInventoryException {
        return new ArrayList<>(vendingMap.values());
    }

    @Override
    public void addFunds(BigDecimal money) {
        BigDecimal moneyToAdd = money;
        this.userFunds = moneyToAdd;
    }

    @Override
    public BigDecimal getUserFunds() {
        return userFunds;
    }

    @Override
    public void removeItem(String itemName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addItem(String ItemId, Item itemName) {
        vendingMap.put(ItemId, itemName);
    }
    
    @Override
    public Item getItem(String ItemId){
        Item itemToReturn = vendingMap.get(ItemId);
        return itemToReturn;
        
    }
    
    
}
