/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.model.Order;
import com.mycompany.flooringmastery.model.State;
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
public class StateDao {

    public BigDecimal getTaxRateFromFile(Order order) throws FlooringServiceFileException {
        BigDecimal taxRate = new BigDecimal("0");
        String stateName = order.getState().getStateName();
        Scanner scanner;
        //now read from file that contains the relevant info
        try {
            scanner = new Scanner(new BufferedReader(new FileReader("Taxes.txt")));
        } catch (FileNotFoundException e) {
            throw new FlooringServiceFileException("Could not find states/taxes file.", e);
        }
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(",");
            if (currentTokens[0].equalsIgnoreCase(stateName)) {
                taxRate = new BigDecimal(currentTokens[1]);
            }
        }
        return taxRate; //hopefully we can get at the taxRate from the while loop
    }

    public ArrayList<String> getStates() throws FlooringServiceFileException {
        ArrayList<String> allStates = new ArrayList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader("Taxes.txt")));
        } catch (FileNotFoundException e) {
            throw new FlooringServiceFileException("Could not find states/taxes file.", e);
        }
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(",");
            allStates.add(currentTokens[0]);
        }
        return allStates;
    }
}
