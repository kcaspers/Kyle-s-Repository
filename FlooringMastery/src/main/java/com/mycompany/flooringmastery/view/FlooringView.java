/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.view;

import com.mycompany.flooringmastery.model.Order;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 *
 * @author kylecaaspers
 */
public class FlooringView {

    UserIO io; //I removed the specific implementation of UserIO that reviously had been here

    public FlooringView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection(){
        io.print("Flooring Program");
        io.print("1. Display Orders");
        io.print("2. Add Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Save Current Work");
        io.print("6. Quit");
        
        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public Order getNewOrderInfo() {
        //Get info about new order and return it to controller
        
        
        String name = io.readString("Enter customer name.");
        String state = io.readString("Enter state (we service OH, PA, MI, and IN)");
        String product = io.readString("Enter product (Carpet, Laminate, Tile, or Wood.) ");
        double area = io.readDouble("Enter area in feet.");
        io.print("\n");
        //the other order details will be calculated from this, possibly with service layer or internally
        
        Order currentOrder = new Order(name, state, product, area);
        return currentOrder;
    }
    
    public void displayOrderTotal(Order order){
//        String total;     
//        total = order.getTotal().setScale(2, RoundingMode.HALF_UP).toString();
//        io.print("The total for this order will be: " + total + "\n");
        io.print("The total for this order will be: " + order.getTotalFormatted() + "\n");
    }

    public String getEditOrderInfo() { //send a date and number string to validation
        String dateAndOrderNumber = "";
        //ask for which day and which order, we will reformat this in the service

        return dateAndOrderNumber;
    }

    public Order editOrder(Order order) {
        Order orderToEdit;
        //I need view to cycle through these fields and prompt the user to change them
        
        String customerName = order.getOrderName();
        customerName = io.readString("Enter customer name: " + customerName);
        if(customerName.equals("") || customerName == null){
            customerName = order.getOrderName(); //reverts new name back to original
        }
        
        String customerState = order.getState().getStateName();
        customerState = io.readString("Enter customer state: " + customerState);
        if(customerState.equals("") || customerState == null){
            customerState = order.getState().getStateName();
        }
        
        String material = order.getProduct().getProductType();
        material = io.readString("Enter customer product (Carpet, Laminate, Tile, Wood): " + material);
        if(material.equals("") || material == null){
            material = order.getProduct().getProductType();
        }
        
        double area = order.getArea();
        try{
          area = io.readInt("Enter area: " + area);  
        } catch(NumberFormatException n){
            area = order.getArea();
        }
        
        
        orderToEdit = new Order(customerName, customerState, material, area);//fill the constructor with our updated values
        orderToEdit.setOrderNumber(order.getOrderNumber());
        return orderToEdit;
    }

    public void displayOrder(Order order) {

    }

    public void displayAllOrders(ArrayList<Order> orders) {
        //cycle through each order
        for(Order o : orders){
            io.print("Order number: " + o.getOrderNumber());
            io.print("Client: " + o.getOrderName());
            io.print("State: " + o.getState().getStateName());
            io.print("Material: " + o.getProduct().getProductType());
            io.print("Total cost: " + o.getTotalFormatted());
            io.print("\n");
        }
    }
    
    public void displayNoOrders(){
        io.print("No orders for this date.");
        io.print("\n");
    }
    
    public void displayRemoveSuccess(){
        io.print("Order succesfully removed.");
        io.print("\n");
    }
    
    public void displaySaving(){
        io.print("Saving...");
        io.print("\n");
    }
    
    public void displayTrainingSave(){
        io.print("Saving is disabled in training mode");
        io.print("\n");
    }
    
    public void displaySaveSuccess(){
        io.print("Save successful");
        io.print("\n");
    }
    
    public void displayErrorMessage(String errorMessage){
        io.print("Error");
        io.print(errorMessage);
        io.print("\n");
    }
    
    public void displayEditSuccess(Order order){
        io.print("Order number " + order.getOrderNumber() + " succesfully edited.");
        io.print("New total is: " + order.getTotalFormatted());
        io.print("\n");
    }

    public String getDate() {
        return io.readString("View orders for which date? (MMDDYYYY)");
    }
    
    public int getOrderNumber(){
        return io.readInt("Which order number?");
    }
    
    public boolean quit(){
        boolean continueUsing = true;
        String userChoice = io.readString("Changes have been made. Quit without saving? (y/n)");
        if(userChoice.equalsIgnoreCase("y")){
            continueUsing = false;
        }else if(userChoice.equalsIgnoreCase("n")){
            continueUsing = true;
        } else{
            io.print("Unknown command.");
        }
        return continueUsing;
    }
    
    public void displayExitMessage(){
        io.print("Goodbye");
    }
}
