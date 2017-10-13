/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.service.*;
import com.mycompany.flooringmastery.dao.OrderDao;
import com.mycompany.flooringmastery.model.Order;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kylecaaspers
 */
public class OrderDaoStubImpl implements OrderDao{

    //my stub dao should have orderMap with at least one date key filled
    Map<String, ArrayList<Order>> orderMap = new HashMap<>();
    ArrayList<Order> ordersFor08061992 = new ArrayList<>();
    Order order1;
    Order order2;
    
    public OrderDaoStubImpl(){
        order1 = new Order("Caspers", "IN", "Tile", 50, 1, "3.50", "4.15", "22.95", "6.00", "405.45");
        order2 = new Order("Patrick", "MI", "Wood", 50, 2, "5.15", "4.75", "28.46", "5.75", "523.46");
        ordersFor08061992.add(order1);
        ordersFor08061992.add(order2);
        orderMap.put("08061992", ordersFor08061992);
        
        
    }
    
    @Override
    public Map<String, ArrayList<Order>> loadOrders()  throws FlooringDaoFileException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean saveOrder() throws FlooringServiceFileException{
        return true;
    }

    @Override
    public Order addOrder(String date, Order order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order getOrder(String date, int orderNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Order> getAllOrders(String date) {
        return orderMap.get(date);
    }

    @Override
    public Order removeOrder(String date, Order order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order editOrder(String date, Order order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> getAllDates() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeAllOrdersByDate(String date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
