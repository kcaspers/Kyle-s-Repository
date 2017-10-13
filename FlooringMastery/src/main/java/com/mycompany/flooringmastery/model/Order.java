/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 *
 * @author kylecaaspers
 */
public class Order {
    //I should create constructors for bigdecimal fields that make it easy
    //to send it a String value
    
    private int orderNumber;
    private String orderName;
    private State state;
    private Product product;
    private double area;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal tax;
    private BigDecimal taxRate;
    private BigDecimal total;
    
    //name, state, product, area
    public Order(String name, String state, String product, double area){
        this.orderName = name;
        this.state = new State(state);
        this.product = new Product(product);
        this.area = area;
    }
    
    public Order(String name, String state, String product, String area){
        this.orderName = name;
        this.state = new State(state);
        this.product = new Product(product);
        this.area = Double.parseDouble(area);
    }
    
    //this next constructor is used for testing
    public Order(String name, String state, String product, double area, int orderNumber,
            String materialCost, String laborCost, String tax, String taxRate, String total){
        
        this.orderName = name;
        this.state = new State(state);
        this.product = new Product(product);
        this.area = area;
        this.orderNumber = orderNumber;
        this.materialCost = new BigDecimal(materialCost);
        this.laborCost = new BigDecimal(laborCost);
        this.tax = new BigDecimal(tax);
        this.taxRate = new BigDecimal(taxRate);
        this.total = new BigDecimal(total);
    }
    
    public Order(){
        
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getArea() {
        return area;
    }
    
    public String getAreaAsString() {
        return Double.toString(area);
    }

    public void setArea(double area) {
        this.area = area;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal tax) {
        this.taxRate = tax;
    }

    public BigDecimal getTotal() {
        return total;
    }
    
    public String getTotalFormatted() {
        String formattedTotal = total.setScale(2, RoundingMode.HALF_UP).toString();
        return formattedTotal;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    private BigDecimal BigDecimal(String materialCost) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.orderNumber;
        hash = 97 * hash + Objects.hashCode(this.orderName);
        hash = 97 * hash + Objects.hashCode(this.state);
        hash = 97 * hash + Objects.hashCode(this.product);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.area) ^ (Double.doubleToLongBits(this.area) >>> 32));
        hash = 97 * hash + Objects.hashCode(this.materialCost);
        hash = 97 * hash + Objects.hashCode(this.laborCost);
        hash = 97 * hash + Objects.hashCode(this.tax);
        hash = 97 * hash + Objects.hashCode(this.taxRate);
        hash = 97 * hash + Objects.hashCode(this.total);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.orderNumber != other.orderNumber) {
            return false;
        }
        if (Double.doubleToLongBits(this.area) != Double.doubleToLongBits(other.area)) {
            return false;
        }
        if (!Objects.equals(this.orderName, other.orderName)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        if (!Objects.equals(this.materialCost, other.materialCost)) {
            return false;
        }
        if (!Objects.equals(this.laborCost, other.laborCost)) {
            return false;
        }
        if (!Objects.equals(this.tax, other.tax)) {
            return false;
        }
        if (!Objects.equals(this.taxRate, other.taxRate)) {
            return false;
        }
        if (!Objects.equals(this.total, other.total)) {
            return false;
        }
        return true;
    }

    
            
}
