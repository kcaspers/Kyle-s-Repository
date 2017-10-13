/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.controller;

import com.mycompany.vendingmachine.dao.InsufficientFundsException;
import com.mycompany.vendingmachine.dao.VendingMachineInventoryException;
import com.mycompany.vendingmachine.dto.Change;
import com.mycompany.vendingmachine.dto.Item;
import com.mycompany.vendingmachine.service.VendingMachineService;
import com.mycompany.vendingmachine.service.VendingMachineServiceImpl;
import com.mycompany.vendingmachine.view.VendingMachineView;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author kylecaaspers
 */
public class VendingMachineController {

    VendingMachineView view;
    VendingMachineService service;

    public VendingMachineController(VendingMachineView view, VendingMachineService service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        boolean continueUsing = true;
        int menuSelection = -1;

        //try {
        while (continueUsing) {
            menuSelection = displayMenu();

            switch (menuSelection) {
                case 1:
                    try {
                        continueUsing = displayItems();
                    } catch (VendingMachineInventoryException e) {
                        view.displayErrorMessage(e.getMessage());
                    }

                    break;
                case 2:
                    addMoney();

                    break;
                case 3:
                    //continueUsing = false;
                    continueUsing = quitUsing(); //this will return a false

                    break;
                default:
                    unknownCommand();
            }

        }
        //} catch (VendingMachineInventoryException e) {
        //    view.displayErrorMessage(e.getMessage());
        //}
    }

    private int displayMenu() {
        return view.printMenuAndGetSelection();
    }

    private boolean displayItems() throws VendingMachineInventoryException {   //also make this a purchase function
        boolean continueUsing = true;

        ArrayList<Item> itemsList = service.getAllItems();
        String purchaseChoice = view.displayItems(itemsList);

        if (purchaseChoice.equalsIgnoreCase("exit")) {
            return continueUsing;
        }
        if (!service.isItemPresent(purchaseChoice)) {
            view.itemNotPresent();
            return continueUsing;
        }
        try {
            service.areFundsSufficient(purchaseChoice);
            //enact purchase. subtract 1 from item quantity, dispence change w/ a sevice method, write to file
            Change userChange;
            userChange = service.purchaseItem(purchaseChoice);
            view.displayPurchaseSuccess();
            view.displayChange(userChange);
            continueUsing = false;

        } catch (InsufficientFundsException e) {
            String userFunds = service.getUserFunds().toString();
            view.insufficientFundsError(purchaseChoice, userFunds);
        }

        return continueUsing;
    }

    private void addMoney() {
        String moneyToAdd = view.requestMoneyToAdd();
        if (service.addFunds(moneyToAdd)) {
            view.addMoneySuccess();
        } else {
            view.addMoneyError();
        }

    }

    private boolean quitUsing() {
        Change userChange = service.dispenseChange();
        view.displayChange(userChange);

        return false;
    }

    private void unknownCommand() {
        view.unknownCommandBanner();
    }
}
