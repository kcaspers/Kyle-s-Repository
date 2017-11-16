/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Location;
import model.Organization;
import model.Sighting;

/**
 *
 * @author kylecaaspers
 */
public interface LocationDao {
    //addLocation
    //deleteLocation
    //updateLocation
    //getLocationByID
    //getAllLocations
    //getAllLocationsBySuperhero
    
    public void addLocation(Location location);
    
    public void deleteLocation(int locationID);
    
    public void updateLocation(Location location);
    
    public Location getLocationById(int locationID);
    
    public List<Location> getAllLocations();
    
    public List<Location> getAllLocationsBySuperhero(int superheroID);
    
    public Location getLocationByOrganization(int organizationID);
    
    public Location getLocationBySighting(Sighting sighting);
}
