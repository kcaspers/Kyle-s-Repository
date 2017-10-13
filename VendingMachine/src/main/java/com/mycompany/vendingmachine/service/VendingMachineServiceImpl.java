/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.dao.InsufficientFundsException;
import com.mycompany.vendingmachine.dao.VendingMachineDao;
import com.mycompany.vendingmachine.dao.VendingMachineInventoryException;
import com.mycompany.vendingmachine.dto.Change;
import com.mycompany.vendingmachine.dto.Item;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author kylecaaspers
 */
public class VendingMachineServiceImpl implements VendingMachineService {

    VendingMachineDao dao;

    public VendingMachineServiceImpl(VendingMachineDao dao) {
        this.dao = dao;
    }



    @Override
    public boolean isItemPresent(String item) throws VendingMachineInventoryException {
        boolean isPresent = false;
        ArrayList<Item> itemList = dao.getAllItems();
        for (Item i : itemList) {
            if (i.getName().equalsIgnoreCase(item)) {
                isPresent = true; //this means it is something at one point contained in machine
                if(i.getQuantity() < 1){
                    isPresent = false; //but we also need to check if we are out of it, should throw an exception
                }
            }
            
        }
        if(!isPresent){
            throw new VendingMachineInventoryException("Item not present.");
        }
        return isPresent;
    }

    @Override
    public BigDecimal getUserFunds() {
        return dao.getUserFunds();
    }

    @Override
    public boolean areFundsSufficient(String purchaseChoice) throws VendingMachineInventoryException, InsufficientFundsException {
        boolean sufficientFunds = true;
        BigDecimal userFunds;
        ArrayList<Item> itemsList = dao.getAllItems();
        BigDecimal purchaseCost = new BigDecimal("0.00");
        for (Item currentItem : itemsList) {
            if (currentItem.getName().equalsIgnoreCase(purchaseChoice)) {
                purchaseCost = currentItem.getCost();
            }
        }
        
          userFunds = dao.getUserFunds();
        

        //now compare userFunds against purchaseCost, THIS IS TRICKY
        if (userFunds.compareTo(purchaseCost) == 1 || userFunds.compareTo(purchaseCost) == 0) {
            sufficientFunds = true;
        } else {
            //sufficientFunds = false;
            throw new InsufficientFundsException("Insufficient funds");
        }
        return sufficientFunds;
    }

    @Override
    public Change purchaseItem(String purchaseChoice) throws VendingMachineInventoryException {
        Item itemSold = dao.itemSold(purchaseChoice);
        BigDecimal userFunds = dao.getUserFunds();
        Change userChange = calculateChange(userFunds, itemSold);

        //return should be a change object containing those details
        return userChange;
    }
    
    @Override
    public Change dispenseChange(){
        BigDecimal userFunds = dao.getUserFunds();
        Item notItem = new Item("0.00");
        Change userChange = calculateChange(userFunds, notItem);
        
        return userChange;
    }

    
    public Change calculateChange(BigDecimal userFunds, Item itemSold) {
        //Change userChange;
        BigDecimal cost = itemSold.getCost();
        //BigDecimal difference = userFunds.subtract(cost);
        BigDecimal changeDue = userFunds.subtract(cost);
        
        BigDecimal timesHundred = new BigDecimal("100");
        BigDecimal nickel = new BigDecimal(".05");
        BigDecimal dime = new BigDecimal(".10");
        BigDecimal quarter = new BigDecimal(".25");
        BigDecimal penny = new BigDecimal(".01");
        
        BigDecimal quartersDue = changeDue.divide(quarter, 2);
        int quarters = quartersDue.intValue();
        BigDecimal quartersToSubtract = new BigDecimal(quarters);
        changeDue = changeDue.subtract(quartersToSubtract.multiply(quarter));
        
        BigDecimal dimesDue = changeDue.divide(dime, 2);
        int dimes = dimesDue.intValue();
        BigDecimal dimesToSubtract = new BigDecimal(dimes);
        changeDue = changeDue.subtract(dimesToSubtract.multiply(dime));
        
        BigDecimal nickelsDue = changeDue.divide(nickel, 2);
        int nickels = nickelsDue.intValue();
        BigDecimal nickelsToSubtract = new BigDecimal(nickels);
        changeDue = changeDue.subtract(nickelsToSubtract.multiply(nickel));
        
        BigDecimal penniesDue = changeDue.divide(penny, 2);
        int pennies = penniesDue.intValue();
        
        //userChange = new Change(quarters, dimes, nickels, pennies);
        //return userChange;
        return new Change(quarters, dimes, nickels, pennies);
  
    }

    @Override
    public ArrayList<Item> getAllItems() throws VendingMachineInventoryException {
        ArrayList<Item> itemsList = dao.getAllItems();  //we will check if there are empty entries here in service
        ArrayList<Item> itemsListFiltered = itemsList.stream()
                .filter(i -> i.getQuantity() != 0)
                .collect(Collectors.toCollection(ArrayList::new));

        return itemsListFiltered;

    }
    
    @Override
    public Item getItem(String ItemId){
        return dao.getItem(ItemId);
    }

    @Override
    public boolean addFunds(String moneyToAdd) {
        if(!isNumeric(moneyToAdd)){
           return false; 
        }
        //this first part just uses a double to check that it isnt negative
        double moneyInDouble = Double.parseDouble(moneyToAdd);
        if (moneyInDouble <= 0) {
            return false;
        } else {
            BigDecimal money = new BigDecimal(moneyToAdd);
            dao.addFunds(money);
            return true;
        }
    }

    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                return false;
            }
        }
        return true;
    }

}
