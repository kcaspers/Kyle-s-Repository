/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author kylecaaspers
 */
@Entity
public class Rig {

    int id;
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

    
    //This might need to be a many-to-many for individual cabs
    public List<Cabinet> getCabinets() {
        return cabinets;
    }

    public void setCabinets(List<Cabinet> cabinets) {
        this.cabinets = cabinets;
    }

    
    @Column(name = "ampOhm")
    public int getAmpOhm() {
        return ampOhm;
    }

    public void setAmpOhm(int ampOhm) {
        this.ampOhm = ampOhm;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "date")
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
