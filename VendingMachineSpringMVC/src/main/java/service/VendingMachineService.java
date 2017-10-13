/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import dao.InsufficientFundsException;
import dao.VendingMachineInventoryException;
import java.math.BigDecimal;
import java.util.ArrayList;
import model.Change;
import model.Item;

/**
 *
 * @author kylecaaspers
 */
public interface VendingMachineService {
    
    public void clearFunds();
    
    boolean isItemPresent(String item) throws VendingMachineInventoryException;
    
    public void addItem(String ItemId, Item itemName);
    
    public BigDecimal getUserFunds();
    
    boolean areFundsSufficient(String purchaseChoice) throws VendingMachineInventoryException, InsufficientFundsException;
    
    public BigDecimal moreRequired(String purchaseChoice);
    
    //public Change purchaseItem(String purchaseChoice) throws VendingMachineInventoryException;
    
    public String purchaseItem(String purchaseChoice) throws VendingMachineInventoryException;
    
    //public Change dispenseChange();
    
    public String dispenseChange();
    //Change calculateChange(BigDecimal userFunds, Item itemSold);
    
    ArrayList<Item> getAllItems() throws VendingMachineInventoryException;
    
    public Item getItem(String ItemId);
    
    boolean addFunds(String moneyToAdd);
    
    //public Change calculateChange(BigDecimal userFunds, Item itemSold);
    
    public String calculateChange(BigDecimal userFunds, Item itemSold);
}
