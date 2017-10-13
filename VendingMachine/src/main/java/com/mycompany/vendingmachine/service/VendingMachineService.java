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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author kylecaaspers
 */
public interface VendingMachineService {
    
    
    

    
    boolean isItemPresent(String item) throws VendingMachineInventoryException;
    
    public BigDecimal getUserFunds();
    
    boolean areFundsSufficient(String purchaseChoice) throws VendingMachineInventoryException, InsufficientFundsException;
    
    public Change purchaseItem(String purchaseChoice) throws VendingMachineInventoryException;
    
    public Change dispenseChange();
    //Change calculateChange(BigDecimal userFunds, Item itemSold);
    
    ArrayList<Item> getAllItems() throws VendingMachineInventoryException;
    
    public Item getItem(String ItemId);
    
    boolean addFunds(String moneyToAdd);
    
    public Change calculateChange(BigDecimal userFunds, Item itemSold);
}
