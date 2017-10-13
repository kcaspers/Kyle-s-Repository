/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.dao.FlooringDaoFileException;
import com.mycompany.flooringmastery.dao.OrderDao;
import com.mycompany.flooringmastery.dao.OrderDaoImpl;
import com.mycompany.flooringmastery.dao.ProductDao;
import com.mycompany.flooringmastery.dao.StateDao;
import com.mycompany.flooringmastery.model.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.text.DateFormatter;

/**
 *
 * @author kylecaaspers
 */
public class FlooringServiceImpl implements FlooringService {

    private OrderDao orderDao;
    ProductDao productDao;
    StateDao stateDao;

    public FlooringServiceImpl(OrderDao orderDao, ProductDao productDao, StateDao stateDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.stateDao = stateDao;
    }

    public FlooringServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public void loadOrders() throws FlooringDaoFileException {
        orderDao.loadOrders();
    }

    @Override
    public boolean saveOrder() throws FlooringServiceFileException {
        return orderDao.saveOrder();
    }

    @Override
    public ArrayList<Order> getAllOrders(String date) {
        ArrayList<Order> ordersByDate = new ArrayList<>();
        ordersByDate = orderDao.getAllOrders(date);
        return ordersByDate;
    }

    @Override
    public Order getOrder(String date, int orderNumber) {
        Order orderToEdit = new Order();
        ArrayList<Order> ordersByDate = new ArrayList<>(orderDao.getAllOrders(date));
        orderToEdit = ordersByDate.stream()
                .filter(o -> o.getOrderNumber() == orderNumber)
                .findFirst()
                .get();
        return orderToEdit;
    }

    @Override
    public Order addOrder(Order order) throws FlooringServiceValidationException, FlooringServiceFileException {
        //we need to add this to the orderDao, but first we must generate its other fields and validate it
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate now = LocalDate.now();
        String date = now.format(format);

        validateOrder(order);
        order.setOrderNumber(assignOrderNumber(order, date));
        order = calculateFields(order);
        order = formatOrder(order);
        //once all fields are filled this can go into the dao
        orderDao.addOrder(date, order);
        return order;
    }

    public Order formatOrder(Order order) {
        String productType = order.getProduct().getProductType();
        
        char[] productChars = productType.toCharArray();
        for(int i = 0; i < productChars.length; i ++){
            if(i == 0){
                char firstChar = Character.toUpperCase(productChars[i]);
                productChars[i] = firstChar;
            } else{
                char currentChar = Character.toLowerCase(productChars[i]);
                productChars[i] = currentChar;
            }
        }
        String fixedProductType = new String(productChars);
        order.getProduct().setProductType(fixedProductType);
        
        String stateName = order.getState().getStateName();
        
        char[] stateChars = stateName.toCharArray();
        for(int i = 0; i < stateName.length(); i++){
            stateChars[i] = Character.toUpperCase(stateChars[i]);
        }
        String fixedStateName = new String(stateChars);
        order.getState().setStateName(fixedStateName);
        
        //maybe add formatting to manage commas in a company name
//        String companyName = order.getOrderName();
//        String[] nameStrings = companyName.split("");
//        for(int i = 0; i < companyName.length(); i++){
//            if(nameStrings[i].contains(",")){
//                nameStrings[i] = "--";
//            }
//        }
//        String alteredCompanyName = new String();
//        for(String s : nameStrings){
//            alteredCompanyName += s;
//        }
//        order.setOrderName(alteredCompanyName);
        return order;
    }

    @Override
    public Order removeOrder(String date, int orderNumber) {
        //get a handle on the actual object
        Order orderToRemove = orderDao.getOrder(date, orderNumber);
        
        return orderDao.removeOrder(date, orderToRemove);
    }

    @Override
    public Order replaceOrder(String date, Order editedOrder) throws FlooringServiceFileException {
        editedOrder = calculateFields(editedOrder);
        editedOrder = formatOrder(editedOrder);
        orderDao.editOrder(date, editedOrder);
        return editedOrder;
    }

    @Override
    public void validateOrder(Order order) throws FlooringServiceValidationException, FlooringServiceFileException {
        //validate name, state, product, area
        if (order.getOrderName() == null
                || order.getOrderName().trim().length() == 0
                || order.getState() == null
                || order.getState().getStateName().trim().length() == 0
                || order.getProduct().getProductType() == null
                || order.getProduct().getProductType().trim().length() == 0
                || order.getArea() == 0) {
            throw new FlooringServiceValidationException("Error filling order fields.");
        }

        //get all states from state dao, check it against their input
//        if (!stateDao.getStates().contains(order.getState().getStateName())) {
//            throw new FlooringServiceValidationException("State not valid.");
//        }
        boolean validState = false;
        boolean validProduct = false;
        for (String currentState : stateDao.getStates()) {
            if (currentState.equalsIgnoreCase(order.getState().getStateName())) {
                validState = true;
            }
        }
        while (validState == false) {
            throw new FlooringServiceValidationException("State not valid.");
        }

        for (String currentProduct : productDao.getProducts()) {
            if (currentProduct.equalsIgnoreCase(order.getProduct().getProductType())) {
                validProduct = true;
            }
        }
        while (validProduct == false) {
            throw new FlooringServiceValidationException("Product not valid.");
        }
    }

    @Override
    public int assignOrderNumber(Order order, String date) {
        int orderNumber = 0;
        ArrayList<Order> ordersByDate = new ArrayList<>();
        ordersByDate = orderDao.getAllOrders(date);
        //check the largest integer value assigned to an ordernumber and ++ it
        //int largestNumber = 0;
        if (ordersByDate == null) {
            orderNumber = 1;
        } else {
            for (Order currentOrder : ordersByDate) {
                int currentValue = currentOrder.getOrderNumber();
                if (currentValue > orderNumber) {
                    orderNumber = currentValue;
                }
            }
            orderNumber++;
        }
        return orderNumber;
    }

    private BigDecimal calculateMaterialCost(Order order) {
        BigDecimal materialCost;
        BigDecimal areaAsBig = new BigDecimal(order.getArea());
        materialCost = order.getProduct().getCostPerSquareFoot().multiply(areaAsBig); //costPerSquareFoot/Area = cost
        return materialCost;
    }

    private BigDecimal calculateLaborCost(Order order) {
        BigDecimal laborCost;
        BigDecimal areaAsBig = new BigDecimal(order.getArea());
        laborCost = order.getProduct().getLaborCostPerSquareFoot().multiply(areaAsBig);
        return laborCost;
    }

    private BigDecimal calculateTax(Order order) {
        BigDecimal tax;
        BigDecimal laborPlusMaterialCost = order.getLaborCost().add(order.getMaterialCost());
        BigDecimal calculatedTaxRate;
        BigDecimal hund = new BigDecimal("100");
        calculatedTaxRate = order.getTaxRate().divide(hund, 4, RoundingMode.HALF_UP);
        tax = laborPlusMaterialCost.multiply(calculatedTaxRate);
        return tax;
    }

    private BigDecimal calculateCost(Order order) {
        BigDecimal cost;
        cost = order.getLaborCost().add(order.getMaterialCost()).add(order.getTax());
        return cost;
    }

    @Override
    public Order calculateFields(Order order) throws FlooringServiceFileException {

        //order.setTaxRate(getTaxRateFromFile(order)); //this maybe should go in state dao
        order.setTaxRate(stateDao.getTaxRateFromFile(order));

        //order.getProduct().setCostPerSquareFoot(getCostFromFile(order));
        order.getProduct().setCostPerSquareFoot(productDao.getCostFromFile(order));

        //order.getProduct().setLaborCostPerSquareFoot(getLaborCostFromFile(order));
        order.getProduct().setLaborCostPerSquareFoot(productDao.getLaborCostFromFile(order));

        order.setMaterialCost(calculateMaterialCost(order));
        order.setLaborCost(calculateLaborCost(order));
        order.setTax(calculateTax(order));
        order.setTotal(calculateCost(order));

        return order;
    }

}
