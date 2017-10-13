package com.mycompany.flooringmastery.dao;


import com.mycompany.flooringmastery.dao.FlooringDaoFileException;
import com.mycompany.flooringmastery.dao.OrderDao;
import com.mycompany.flooringmastery.model.Order;
import com.mycompany.flooringmastery.service.FlooringServiceFileException;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OrderDaoTrainingImpl implements OrderDao {
//I should start by auto generating todays file
    static {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate now = LocalDate.now();
        String date = now.format(format);
        String orderFile = "orders/Orders_" + date + ".txt";

        //make a file if it doesnt already exist
        File f = new File(orderFile);
        if (!f.exists()) {
            try {
                PrintWriter write = new PrintWriter(new FileWriter(orderFile));
            } catch (IOException e) {
            }
        }
    }

    Map<String, ArrayList<Order>> orderMap = new HashMap<>();

    @Override
    public Map<String, ArrayList<Order>> loadOrders() throws FlooringDaoFileException {
        Scanner scanner;

        ArrayList<String> dates = new ArrayList<>();
        File folder = new File("orders");
        File[] listOfFiles = folder.listFiles();
        for (File f : listOfFiles) {
            String trimmedFile = f.getName();
            trimmedFile = trimmedFile.replace("Orders_", "");
            trimmedFile = trimmedFile.replace(".txt", "");
            dates.add(trimmedFile); //i need to trim it so its just the date
        }

        for (String date : dates) {
            String orderFile = "orders/Orders_" + date + ".txt";
            try {
                scanner = new Scanner(new BufferedReader(new FileReader(orderFile)));
            } catch (FileNotFoundException e) {
                throw new FlooringDaoFileException("Could not load file", e);
            }
            String currentLine;
            String[] currentTokens;
            ArrayList<Order> ordersByDateFromFile = new ArrayList<>();
            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();
                //currentTokens = currentLine.split(",");
                currentTokens = currentLine.split(",(?=(?:[^\']*\'[^\']*\')*[^\']*$)", -1);
                Order currentOrder = new Order(currentTokens[1], currentTokens[2], currentTokens[4], currentTokens[5]);
                currentOrder.setOrderNumber(Integer.parseInt(currentTokens[0]));
                currentOrder.setTaxRate(new BigDecimal(currentTokens[3]));
                currentOrder.getProduct().setCostPerSquareFoot(new BigDecimal(currentTokens[6]));
                currentOrder.getProduct().setLaborCostPerSquareFoot(new BigDecimal(currentTokens[7]));
                currentOrder.setMaterialCost(new BigDecimal(currentTokens[8]));
                currentOrder.setLaborCost(new BigDecimal(currentTokens[9]));
                currentOrder.setTax(new BigDecimal(currentTokens[10]));
                currentOrder.setTotal(new BigDecimal(currentTokens[11]));

                if (currentOrder.getOrderName().contains("'")) {
                    currentOrder.setOrderName(currentOrder.getOrderName().replace("'", ""));
                }

                ordersByDateFromFile.add(currentOrder);
            }
            orderMap.put(date, ordersByDateFromFile);
            scanner.close();
        }
        return orderMap;
    }

    @Override
    public boolean saveOrder() throws FlooringServiceFileException {
            return false;
        
//        PrintWriter out;
//        ArrayList<String> dates = getAllDates();
//
//        for (String date : dates) {
//            String orderFile = "orders/Orders_" + date + ".txt"; //hopefully this gives me every date in a readable way
//            try {
//                out = new PrintWriter(new FileWriter(orderFile));
//            } catch (IOException e) {
//                throw new FlooringServiceFileException("Unable to write to file.", e);
//            }
//            ArrayList<Order> ordersForDate = getAllOrders(date);
//            for (Order currentOrder : ordersForDate) {
//
//                //before I write anything, alter all commas
//                String companyName = currentOrder.getOrderName();
//                String[] nameStrings = companyName.split("");
//                for (int i = 0; i < companyName.length(); i++) {
//                    if (nameStrings[i].contains(",")) {
//                        nameStrings[i] = "--";
//                    }
//                }
//                String alteredCompanyName = new String();
//                for(String s : nameStrings){
//                    alteredCompanyName += s;
//                }
//                currentOrder.setOrderName(alteredCompanyName);
//
//                out.println(currentOrder.getOrderNumber() + ","
//                        + currentOrder.getOrderName() + ","
//                        + currentOrder.getState().getStateName() + ","
//                        + currentOrder.getTaxRate().toString() + ","
//                        + currentOrder.getProduct().getProductType() + ","
//                        + currentOrder.getAreaAsString() + ","
//                        + currentOrder.getProduct().getCostPerSquareFoot().toString() + ","
//                        + currentOrder.getProduct().getLaborCostPerSquareFoot().toString() + ","
//                        + currentOrder.getMaterialCost().toString() + ","
//                        + currentOrder.getLaborCost().toString() + ","
//                        + currentOrder.getTax().setScale(2, RoundingMode.HALF_UP).toString() + ","
//                        + currentOrder.getTotal().setScale(2, RoundingMode.HALF_UP).toString()
//                );
//                out.flush();
//            }
//            out.close();
//        }
    }

    @Override
    public Order addOrder(String date, Order order) {
        Order addedOrder = order;
        if (orderMap.isEmpty()) {
            ArrayList<Order> firstOrderForDate = new ArrayList<>(Arrays.asList(order));
            orderMap.put(date, firstOrderForDate);
        } else {
            //I take the arraylist for this date, add my new order to it, and put it back into map
            ArrayList<Order> currentArrayList = new ArrayList<>(orderMap.get(date));
            currentArrayList.add(order);
            orderMap.put(date, currentArrayList);
        }

        return addedOrder;
    }

    @Override
    public Order getOrder(String date, int orderNumber) {
        ArrayList<Order> ordersByDate = orderMap.get(date);
        Order order = new Order();
        for (Order o : ordersByDate) {
            if (o.getOrderNumber() == orderNumber) {
                order = o;
            }
        }
        return order;
    }

    @Override
    public ArrayList<Order> getAllOrders(String date) {
        return orderMap.get(date); //this should return the arrayList of orders for the specified date
    }

    @Override
    public ArrayList<String> getAllDates() {
        ArrayList<String> dates = new ArrayList<>(orderMap.keySet());
        return dates;
    }

    @Override
    public Order removeOrder(String date, Order order) {
        boolean isPresent = false;
        Order orderToRemove = order;
        ArrayList<Order> ordersByDate = orderMap.get(date);
        for (Order o : ordersByDate) {
            if (o.getOrderNumber() == order.getOrderNumber()) {
                orderToRemove = o;
                isPresent = true;
            }
        }
        while (!isPresent) {
            return null;
        }
        ordersByDate.remove(orderToRemove);
        orderMap.put(date, ordersByDate); //hopefully this doesn't cause any duplicates
        return orderToRemove;
    }

    @Override
    public void removeAllOrdersByDate(String date) {
        String dateToClear = date;
        orderMap.remove(date);
    }

    @Override
    public Order editOrder(String date, Order editedOrder) {
        ArrayList<Order> ordersByDate = new ArrayList<>();
        ordersByDate = orderMap.get(date);
        int position = -1;
        for (int i = 0; i < ordersByDate.size(); i++) {
            if (ordersByDate.get(i).getOrderNumber() == editedOrder.getOrderNumber()) {
                position = i;
            }
        }
        ordersByDate.set(position, editedOrder);

//        for(Order o : ordersByDate){
//            if(o.getOrderNumber() == editedOrder.getOrderNumber()){
//                o = editedOrder;
//            }
//        }
        //might also need to recalculate those fields first
        orderMap.put(date, ordersByDate);
        return editedOrder;
    }
}
