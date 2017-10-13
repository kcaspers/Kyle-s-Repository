/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.controller;

import com.mycompany.flooringmastery.dao.FlooringDaoFileException;
import com.mycompany.flooringmastery.model.Order;
import com.mycompany.flooringmastery.service.FlooringService;
import com.mycompany.flooringmastery.service.FlooringServiceFileException;
import com.mycompany.flooringmastery.service.FlooringServiceValidationException;
import com.mycompany.flooringmastery.view.FlooringView;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 *
 * @author kylecaaspers
 */
public class Controller {

    FlooringView view;
    FlooringService service;

    public Controller(FlooringView view, FlooringService service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        try {
            loadOrders();
        } catch (FlooringDaoFileException e) {
            view.displayErrorMessage(e.getMessage());
        }

        boolean continueUsing = true;
        boolean saved = true;
        int menuSelection = 0;

        while (continueUsing) {
            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1://    * 1. Display Orders
                    displayOrders();
                    break;
                case 2://    * 2. Add an Order
                    addOrder();
                    saved = false;
                    break;
                case 3://    * 3. Edit an Order
                    editOrder();
                    saved = false;
                    break;
                case 4://    * 4. Remove an Order
                    removeOrder();
                    saved = false;
                    break;
                case 5://    * 5. Save Current Work
                    saveOrder();
                    saved = true;
                    break;
                case 6://    * 6. Quit
                    if (saved == false) {
                        continueUsing = quitWithoutSavingMessage();
                    } else {
                        continueUsing = false;
                    }
                    break;
                default:
                    unknownCommand();
            }
        }
        exitMessage();

    }

    private int getMenuSelection() {
        int menuSelection = 0;
        boolean hasErrors = false;
        do {
            try {
                menuSelection = view.printMenuAndGetSelection();
                hasErrors = false;
            } catch (NumberFormatException e) {
                view.displayErrorMessage(e.getMessage());
                hasErrors = true;
            }
        } while (hasErrors == true);

        return menuSelection;
    }

    private void displayOrders() {
        String date = view.getDate();
        try {
            ArrayList<Order> orders = service.getAllOrders(date);
            if (orders.isEmpty()) {
                view.displayNoOrders();
            } else {
                view.displayAllOrders(orders);
            }

        } catch (NullPointerException n) {
            view.displayNoOrders();
        }

    }

    private void addOrder() {
        boolean hasErrors = false;
        //do {
        try {
            Order newOrder = view.getNewOrderInfo();
            newOrder = service.addOrder(newOrder);
            hasErrors = false;
            view.displayOrderTotal(newOrder);
        } catch (FlooringServiceValidationException | FlooringServiceFileException | NumberFormatException e) {
            hasErrors = true;
            view.displayErrorMessage(e.getMessage());
        }
        //} while (!hasErrors);
    }

    private void editOrder() {
        boolean hasErrors = false;
        //do {
        try {
            String date = view.getDate();
            int orderNumber = view.getOrderNumber();
            //now get a hold on that particular order
            Order orderToEdit = service.getOrder(date, orderNumber);
            //have view cycle through its properties and prompt user to change them
            Order editedOrder = view.editOrder(orderToEdit);
            //I should re-calculate some of those fields
            //now put replace it in Dao
            service.replaceOrder(date, editedOrder);
            view.displayEditSuccess(editedOrder);
        } catch (NullPointerException | FlooringServiceFileException | NumberFormatException | NoSuchElementException e) {
            hasErrors = true;
            view.displayErrorMessage(e.getMessage());
        }
        //} while (!hasErrors);

    }

    private void removeOrder() {
        boolean hasErrors = false;
        //do {
        try {
            String date = view.getDate();
            int orderNumber = view.getOrderNumber();
            //Order orderToRemove = service.getOrder(date, orderNumber);

            if (service.removeOrder(date, orderNumber) == null) {
                hasErrors = true;
            }
            view.displayRemoveSuccess();
            hasErrors = false;
        } catch (NullPointerException | NumberFormatException e) {
            hasErrors = true;
            view.displayErrorMessage(e.getMessage());
        }
        //} while (!hasErrors);
    }

    private boolean saveOrder() {
        boolean isProd = true;
        view.displaySaving();
        try {
            isProd = service.saveOrder();
            if (!isProd) {
                view.displayTrainingSave();
            } else {
                view.displaySaveSuccess();
            }
        } catch (FlooringServiceFileException e) {
            view.displayErrorMessage(e.getMessage());
        }

        return true;
    }

    private void loadOrders() throws FlooringDaoFileException {
        service.loadOrders();
    }

    private boolean quitWithoutSavingMessage() {
        return view.quit();
    }

    private void exitMessage() {
        view.displayExitMessage();
    }

    private void unknownCommand() {

    }

}
