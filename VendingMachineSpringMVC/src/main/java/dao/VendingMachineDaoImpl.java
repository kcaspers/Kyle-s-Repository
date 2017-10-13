/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import model.Item;

/**
 *
 * @author kylecaaspers
 */
public class VendingMachineDaoImpl implements VendingMachineDao{

    public static final String INVENTORY_FILE = "vendingInventory.txt";
    public static final String DELIMITER = "::";
    
    private static HashMap<String, Item> vendingMap = new HashMap<>();
    static {
        Item soda = new Item("Soda", "2.25", 6);
        vendingMap.put("soda", soda);
        Item candy = new Item("Candy", "1.75", 4);
        vendingMap.put("candy", candy);
        Item homiesfigurine = new Item("Homies Figurine", "3.00", 1);
        vendingMap.put("homies figurine", homiesfigurine);
        Item popcorn = new Item("Popcorn", "2.30", 3);
        vendingMap.put("popcorn", popcorn);
        Item orangesoda = new Item("Orange Soda", "2.25", 5);
        vendingMap.put("orange soda", orangesoda);
    }
    
    private BigDecimal userFunds = new BigDecimal("0");
        
    @Override
    public void clearFunds(){
        userFunds = new BigDecimal("0");
    }
    
    
    
    @Override
    public void writeInventory()throws VendingMachineInventoryException{
        PrintWriter out;
        try{
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e){
            throw new VendingMachineInventoryException("Unable to save inventory", e);
        }
        List<Item>itemList = this.getAllItems();
        for(Item currentItem : itemList){
            out.println(currentItem.getName() + DELIMITER
            + currentItem.getCost() + DELIMITER
            + currentItem.getQuantity());
            out.flush();
        }
        out.close();
    }
    
    @Override
    public HashMap<String, Item> loadInventory()throws VendingMachineInventoryException{
        Scanner scanner;
        
        try{
            scanner = new Scanner(new BufferedReader(new FileReader(INVENTORY_FILE)));
        } catch(FileNotFoundException e){
            throw new VendingMachineInventoryException("Could not load inventory into memory", e);
        }
        String currentLine;
        String[] currentTokens;
        //HashMap<String, Item> inventoryFromFile = new HashMap<>();
        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            //now create an item from this info
            Item currentItem = new Item(currentTokens[0],currentTokens[1],currentTokens[2]);
            //now put it in out temporary map
            vendingMap.put(currentItem.getName(), currentItem);
            //inventoryFromFile.put(currentItem.getName(),currentItem);
        }
        scanner.close();
        //return inventoryFromFile;
        return vendingMap;
    }
    
    @Override
    public Item itemSold(String item) throws VendingMachineInventoryException{
        //this will decrement the quantity of an item object by one
        Item itemToDecrease = vendingMap.get(item.toLowerCase());
        if(itemToDecrease.getQuantity() <= 0){
            //vendingMap.remove(itemToDecrease);
            throw new VendingMachineInventoryException("Item not present.");
        }
        itemToDecrease.setQuantity(itemToDecrease.getQuantity()-1);
        
        //writeInventory();
        return itemToDecrease;
    }

    @Override
    public ArrayList<Item> getAllItems(){
        //loadInventory();
        return new ArrayList<>(vendingMap.values());
    }

    @Override
    public void addFunds(BigDecimal money) {
        BigDecimal moneyToAdd = money;
        BigDecimal currentMoney = userFunds;
        this.userFunds = moneyToAdd.add(currentMoney);
        //this.userFunds = moneyToAdd.add(this.userFunds);
    }

    @Override
    public BigDecimal getUserFunds(){
          return userFunds;
        }
    
    @Override
    public void removeItem(String itemName){  //I use this in testing
        if(vendingMap.containsKey(itemName)){
            vendingMap.remove(itemName);
        }
    }

    @Override
    public void addItem(String ItemId, Item itemName) {
        vendingMap.put(ItemId, itemName);
    }

    @Override
    public Item getItem(String ItemId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    
}
