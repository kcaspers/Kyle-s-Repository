/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author kylecaaspers
 */
public class RemoveSuperObj {
    private int SightingID;
    private int SuperheroID;

    public int getSightingID() {
        return SightingID;
    }

    public void setSightingID(int SightingID) {
        this.SightingID = SightingID;
    }

    public int getSuperheroID() {
        return SuperheroID;
    }

    public void setSuperheroID(int SuperheroID) {
        this.SuperheroID = SuperheroID;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.SightingID;
        hash = 37 * hash + this.SuperheroID;
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
        final RemoveSuperObj other = (RemoveSuperObj) obj;
        if (this.SightingID != other.SightingID) {
            return false;
        }
        if (this.SuperheroID != other.SuperheroID) {
            return false;
        }
        return true;
    }
    
}
