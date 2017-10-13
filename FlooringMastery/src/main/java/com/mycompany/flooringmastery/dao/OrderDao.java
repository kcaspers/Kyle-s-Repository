/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.model.Order;
import com.mycompany.flooringmastery.service.FlooringServiceFileException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kylecaaspers
 */
public interface OrderDao {
    
    //HashMap<String(date), Arraylist<order>> orderMap = new HashMap<>()
    
    public Map<String, ArrayList<Order>> loadOrders()  throws FlooringDaoFileException;
    
    public boolean saveOrder() throws FlooringServiceFileException;
    
    public Order addOrder(String date, Order order);
    
    public Order getOrder(String date, int orderNumber);
    
    public ArrayList<Order> getAllOrders(String date);
    
    public ArrayList<String> getAllDates();
    
    public Order removeOrder(String date, Order order);
    
    public void removeAllOrdersByDate(String date);
    
    public Order editOrder(String date, Order order); //this just replaces order with matching number with new one
    
}
