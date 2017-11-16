/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author kylecaaspers
 */
public class SightingAndHeroes {
    private Sighting sighting;
    private int[] superheroes;

    public Sighting getSighting() {
        return sighting;
    }

    public void setSighting(Sighting sighting) {
        this.sighting = sighting;
    }

    public int[] getSuperheroes() {
        return superheroes;
    }

    public void setSuperheroes(int[] superheroes) {
        this.superheroes = superheroes;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.sighting);
        hash = 89 * hash + Arrays.hashCode(this.superheroes);
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
        final SightingAndHeroes other = (SightingAndHeroes) obj;
        if (!Objects.equals(this.sighting, other.sighting)) {
            return false;
        }
        if (!Arrays.equals(this.superheroes, other.superheroes)) {
            return false;
        }
        return true;
    }

    

    
    
}
