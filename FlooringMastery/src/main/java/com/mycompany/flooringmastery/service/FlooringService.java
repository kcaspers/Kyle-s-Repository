/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.dao.FlooringDaoFileException;
import com.mycompany.flooringmastery.dao.OrderDaoImpl;
import com.mycompany.flooringmastery.dao.ProductDao;
import com.mycompany.flooringmastery.dao.StateDao;
import com.mycompany.flooringmastery.model.Order;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author kylecaaspers
 */
public interface FlooringService {
    
    public void loadOrders() throws FlooringDaoFileException;
    
    public boolean saveOrder()  throws FlooringServiceFileException;
    
    public ArrayList<Order> getAllOrders(String date);
    
    public Order getOrder(String date, int orderNumber);
    
    public Order addOrder(Order order) throws FlooringServiceValidationException , FlooringServiceFileException;
    
    public Order formatOrder(Order order);
    
    public Order removeOrder(String date, int orderNumber);
    
    public Order replaceOrder(String date, Order order) throws FlooringServiceFileException;
    
    public void validateOrder(Order order) throws FlooringServiceValidationException, FlooringServiceFileException;
    
    public int assignOrderNumber(Order order, String date);
    
    //public BigDecimal calculateMaterialCost(Order order);
    
    //public BigDecimal calculateLaborCost(Order order);
    
    //public BigDecimal calculateTax(Order order);
    
    //public BigDecimal calculateCost(Order order);
    
    public Order calculateFields(Order order) throws FlooringServiceFileException;
    
}
