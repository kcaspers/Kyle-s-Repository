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

/**
 *
 * @author kylecaaspers
 */
public interface VendingMachineDao {
    
void writeInventory()throws VendingMachineInventoryException;

    public void clearFunds();

    HashMap<String, Item> loadInventory() throws VendingMachineInventoryException;
    
    Item itemSold(String item) throws VendingMachineInventoryException;
    
    ArrayList<Item> getAllItems();
    
    void addFunds(BigDecimal moneyToAdd);
    
    public BigDecimal getUserFunds();
    
    public void removeItem(String itemName);
    
    public void addItem(String ItemId, Item itemName);
    
    public Item getItem(String ItemId);
}
