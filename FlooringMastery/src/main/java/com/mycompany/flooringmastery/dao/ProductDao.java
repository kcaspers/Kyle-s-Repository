/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.model.Order;
import com.mycompany.flooringmastery.model.Product;
import com.mycompany.flooringmastery.service.FlooringServiceFileException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author kylecaaspers
 */
public class ProductDao {
    
    public BigDecimal getCostFromFile(Order order) throws FlooringServiceFileException {
        Scanner scanner;
        BigDecimal materialCostPerSquareFoot;
        String materialCost = "";
        String productName = order.getProduct().getProductType();

        try {
            scanner = new Scanner(new BufferedReader(new FileReader("Products.txt")));
        } catch (FileNotFoundException e) {
            throw new FlooringServiceFileException("Could not find Products file", e);
        }
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(",");
            if (currentTokens[0].equalsIgnoreCase(productName)) {
                materialCost = currentTokens[1];
            }
        }
        materialCostPerSquareFoot = new BigDecimal(materialCost);
        return materialCostPerSquareFoot;
    }
    
    public BigDecimal getLaborCostFromFile(Order order) throws FlooringServiceFileException {
        Scanner scanner;
        BigDecimal laborCostPerSquareFoot;
        String laborCost = "";
        String productName = order.getProduct().getProductType();

        try {
            scanner = new Scanner(new BufferedReader(new FileReader("Products.txt")));
        } catch (FileNotFoundException e) {
            throw new FlooringServiceFileException("Could not find Products file", e);
        }
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(",");
            if (currentTokens[0].equalsIgnoreCase(productName)) {
                laborCost = currentTokens[2];
            }
        }
        laborCostPerSquareFoot = new BigDecimal(laborCost);
        return laborCostPerSquareFoot;
    }
    
    public ArrayList<String> getProducts() throws FlooringServiceFileException{
        ArrayList<String> allProducts = new ArrayList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader("Products.txt")));
        } catch (FileNotFoundException e) {
            throw new FlooringServiceFileException("Could not find products file.", e);
        }
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(",");
            allProducts.add(currentTokens[0]);
        }
        return allProducts;
    }

}
