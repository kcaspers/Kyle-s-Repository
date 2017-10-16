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
public class cabinet {
    private double impedance;
    private int cabNumber;

    public cabinet(double impedance, int cabNumber){
        this.impedance = impedance;
        this.cabNumber = cabNumber;
    }
    
    public cabinet(){
        
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
}
