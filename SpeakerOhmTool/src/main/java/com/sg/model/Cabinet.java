/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.model;

/**
 *
 * @author kylecaaspers
 */
public class Cabinet {
    private double impedance;
    private int cabNumber;
    private double outputPercentage;

    public Cabinet(double impedance, int cabNumber){
        this.impedance = impedance;
        this.cabNumber = cabNumber;
    }
    
    public Cabinet(double impedance){
        this.impedance = impedance;
    }
    
    public Cabinet(){
        
    }
    
    public double getImpedance() {
        return impedance;
    }

    public void setImpedance(double impedance) {
        this.impedance = impedance;
    }

    public int getCabNumber() {
        return cabNumber;
    }

    public void setCabNumber(int cabNumber) {
        this.cabNumber = cabNumber;
    }

    public double getOutputPercentage() {
        return outputPercentage;
    }

    public void setOutputPercentage(double outputPercentage) {
        this.outputPercentage = outputPercentage;
    }
    
    
}
