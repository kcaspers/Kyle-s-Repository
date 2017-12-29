/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kylecaaspers
 */
public class Rig {

    List<Cabinet> cabinets;
    int ampOhm;
    String title;
    LocalDate date;
    
    public Rig() {

    }
    
    public Rig(List<Cabinet> cabinets, int ampOhm){
        this.cabinets = cabinets;
        this.ampOhm = ampOhm;
    }

    public List<Cabinet> getCabinets() {
        return cabinets;
    }

    public void setCabinets(List<Cabinet> cabinets) {
        this.cabinets = cabinets;
    }

    public int getAmpOhm() {
        return ampOhm;
    }

    public void setAmpOhm(int ampOhm) {
        this.ampOhm = ampOhm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
}
