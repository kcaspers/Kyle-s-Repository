/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Superhero;

/**
 *
 * @author kylecaaspers
 */
public interface SuperheroDao {
    //addSuperhero
    //deleteSuperhero
    //updateSuperhero
    //getSuperheroById
    //getAllSuperheroes
    //getAllSuperherosByLocation
    //getAllSuperheroesByOrganization
    
    public void addSuperhero(Superhero superhero);
    
    public void deleteSuperhero(int superheroID);
    
    public void updateSuperhero(Superhero superhero);
    
    public Superhero getSuperheroByID(int superheroID);
    
    public List<Superhero> getAllSuperheroes();
    
    public List<Superhero> getAllSuperheroesByLocation(int locationID);
    
    public List<Superhero> getAllSuperheroesByOrganization(int organizationID);
    
    public List<Superhero> getAllSuperheroesBySighting(int sightingID);
    
}
