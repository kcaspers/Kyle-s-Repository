/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.time.LocalDate;
import java.util.List;
import model.Sighting;

/**
 *
 * @author kylecaaspers
 */
public interface SightingDao {
    //addSighting
    //deleteSighting
    //updateSighting
    //getSightingById
    //getAllSightings
    //getAllSightingsByDate

    public int addSighting(Sighting sighting);
    
    public void deleteSighting(int sightingID);
    
    public void updateSighting(Sighting sighting);
    
    public Sighting getSightingByID(int sightingID);
    
    public List<Sighting> getAllSightings();
    
    public List<Sighting> getAllSightingsByDate(LocalDate sightingDate);
    
    public List<Sighting> getMostRecentSightings();
    
    public List<Sighting> getAllSightingsBySuperhero(int superheroID);
}
