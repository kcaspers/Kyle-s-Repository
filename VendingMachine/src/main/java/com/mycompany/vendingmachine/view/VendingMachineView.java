/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.view;

import com.mycompany.vendingmachine.dto.Change;
import com.mycompany.vendingmachine.dto.Item;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author kylecaaspers
 */
public class VendingMachineView {

    UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Vending Machine");
        io.print("1) View Available Items");
        io.print("2) Add funds");
        io.print("3) Exit");

        return io.readInt("Please select from the above choices", 1, 3);
    }

    public String requestMoneyToAdd() {
        return io.readString("How much money would you like to add?\n");
    }

    public void addMoneySuccess() {
        io.print("Money succesfully added.\n");
    }

    public void addMoneyError() {
        io.print("There was an error processing your money.\n");
    }

    public void insufficientFundsError(String purchaseChoice, String userfunds) {
        io.print("You are trying to buy " + purchaseChoice + " but you only gave $" + userfunds + "\n");
    }

    public void displayChange(Change userChange) {
        
        if (userChange.getQuarters() > 0 || userChange.getDimes() > 0
                || userChange.getNickels() > 0 || userChange.getPennies() > 0) {
            io.print("Your change is being dispensed:");

            if (userChange.getQuarters() > 0) {
                String quarterCount = Integer.toString(userChange.getQuarters());
                io.print(quarterCount + " quarters");
            }
            if (userChange.getDimes() > 0) {
                String dimeCount = Integer.toString(userChange.getDimes());
                io.print(dimeCount + " dimes");
            }
            if (userChange.getNickels() > 0) {
                String nickelCount = Integer.toString(userChange.getNickels());
                io.print(nickelCount + " nickels");
            }
            if (userChange.getPennies() > 0) {
                String pennyCount = Integer.toString(userChange.getPennies());
                io.print(pennyCount + " pennies");
            }
        }

    }

    public void unknownCommandBanner() {
        io.print("Unknown command!\n");
    }

    public void itemNotPresent() {
        io.print("Item not present\n");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg + "\n");
    }

    public void displayPurchaseSuccess() {
        io.print("Item succesfully purchased\n");
    }

    public String displayItems(ArrayList<Item> itemList) {

        for (Item currentItem : itemList) {
            io.print(currentItem.getName());
            io.print(currentItem.getStringCost()); //made a new getter that returns String
            io.print(Integer.toString(currentItem.getQuantity())); //just converted it to String
            io.print("\n");
        }

        String purchaseChoice = io.readString("Type name of item to purchase or type 'exit'\n");
        return purchaseChoice;
    }
}
