/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author kylecaaspers
 */
public class Item {

    private String name;
    private BigDecimal cost;
    private int quantity;

    public Item(String name, String cost, int quantity) {
        this.name = name;
        this.cost = new BigDecimal(cost);
        this.quantity = quantity;
    }

    public Item(String name, String cost, String quantity) {
        this.name = name;
        this.cost = new BigDecimal(cost);
        this.quantity = Integer.parseInt(quantity);
    }

    public Item(String cost) {
        this.cost = new BigDecimal(cost);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {

        return cost;
    }

    public String getStringCost() {  //lets have this return a string so it is easy to work with
        String stringCost = this.cost.toString();
        return stringCost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
