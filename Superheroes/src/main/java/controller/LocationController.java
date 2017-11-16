/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.LocationDao;
import dao.OrganizationDao;
import dao.SightingDao;
import dao.SuperheroDao;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import model.Location;
import model.Organization;
import model.Sighting;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author kylecaaspers
 */
@CrossOrigin
@Controller
public class LocationController {

    LocationDao locationDao;
    OrganizationDao organizationDao;
    SightingDao sightingDao;
    SuperheroDao superheroDao;

    @Inject
    public LocationController(LocationDao locationDao, OrganizationDao organizationDao,
            SightingDao sightingDao, SuperheroDao superheroDao) {
        this.locationDao = locationDao;
        this.organizationDao = organizationDao;
        this.sightingDao = sightingDao;
        this.superheroDao = superheroDao;
    }

    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    @ResponseBody
    public List<Location> getAllLocations() {
        return locationDao.getAllLocations();
    }

    @RequestMapping(value = "/location/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Location getLocation(@PathVariable("id") int id) {
        return locationDao.getLocationById(id);
    }

    @RequestMapping(value = "/location/{id}", method = RequestMethod.DELETE)
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public String deleteLocation(@PathVariable("id") int id) {
        boolean isDependency = false;
        List<Object> locationDependencies = new ArrayList<>();
        
        Location location = locationDao.getLocationById(id);
        List<Organization> allOrganizations = organizationDao.getAllOrganizations();
        List<Sighting> allSightings = sightingDao.getAllSightings();
        
        for (Organization o : allOrganizations) {
            if (o.getLocation().getLocationName().equals(location.getLocationName())) {
                locationDependencies.add(o);
                isDependency = true;
            }
        }
        for (Sighting s : allSightings) {
            if (s.getLocation().getLocationName().equals(location.getLocationName())) {
                locationDependencies.add(s);
                isDependency = true;
            }
        }
        if(isDependency) {
            String errorMessage = "You can't delete that location, ";
            for(Object o: locationDependencies){
                errorMessage += o.toString() + ", ";
            }
            errorMessage += " depends on it.";
            return errorMessage;
        } else {
            locationDao.deleteLocation(id);
            return "";
        }

    }

    @RequestMapping(value = "/locations", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void addLocation(@RequestBody Location location) {

        locationDao.addLocation(location);

    }

    @RequestMapping(value = "/locations", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void updateLocation(@RequestBody Location location) {
        locationDao.updateLocation(location);
        Location fromDao = locationDao.getLocationById(location.getLocationID());
        System.out.println(fromDao);
    }

}
