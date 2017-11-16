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
import java.util.List;
import javax.inject.Inject;
import model.Sighting;
import model.SightingAndHeroes;
import model.Superhero;
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
public class SightingController {

    LocationDao locationDao;
    OrganizationDao organizationDao;
    SightingDao sightingDao;
    SuperheroDao superheroDao;

    @Inject
    public SightingController(LocationDao locationDao, OrganizationDao organizationDao,
            SightingDao sightingDao, SuperheroDao superheroDao) {
        this.locationDao = locationDao;
        this.organizationDao = organizationDao;
        this.sightingDao = sightingDao;
        this.superheroDao = superheroDao;
    }

    @RequestMapping(value = "/sightings", method = RequestMethod.GET)
    @ResponseBody
    public List<Sighting> getAllSightings() {
        return sightingDao.getAllSightings();
    }

    @RequestMapping(value = "/sighting/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Sighting getSighting(@PathVariable("id") int id) {
        return sightingDao.getSightingByID(id);
    }

    @RequestMapping(value = "/sighting/superheroes/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<Superhero> getAllSupersBySighting(@PathVariable("id") int id) {
        return superheroDao.getAllSuperheroesBySighting(id);

    }

    @RequestMapping(value = "/sightings/mostRecent", method = RequestMethod.GET)
    @ResponseBody
    public List<Sighting> getMostRecentSightings() {
        return sightingDao.getMostRecentSightings();
    }

//    @RequestMapping(value = "/sightings", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    @ResponseBody
//    public void addSighting(@RequestBody Sighting sighting, @RequestParam("superheroes") List<Integer> heroIDs ) {
//        
//        int sightingID = sightingDao.addSighting(sighting);
//        
//        
//    }
    @RequestMapping(value = "/sightings", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void addSighting(@RequestBody SightingAndHeroes sightingAndHeroes) {
        System.out.println(sightingAndHeroes);
        Sighting newSighting = sightingAndHeroes.getSighting();
        sightingDao.addSighting(newSighting);
        //cycle throught the heroes and update their sightings to reflect this one
        int[] superIDs = sightingAndHeroes.getSuperheroes();
        for (int s : superIDs) {
            Superhero superhero = superheroDao.getSuperheroByID(s);
            //get its sightings, change it and put it back in
            List<Sighting> sightings = sightingDao.getAllSightingsBySuperhero(s);
            sightings.add(newSighting);
            superhero.setSightings(sightings);
            superheroDao.updateSuperhero(superhero);
        }

    }

//    @RequestMapping(value = "/sighting", method = RequestMethod.PUT)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void updateSighting(@RequestBody Sighting sighting) {
//        sightingDao.updateSighting(sighting);
//        
//        //I also will need to get the supers for this sighting
//    }
    @RequestMapping(value = "/sighting", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSighting(@RequestBody SightingAndHeroes sightingAndHeroes) {
        Sighting updateSighting = sightingAndHeroes.getSighting();
        sightingDao.updateSighting(updateSighting);
        int[] superIDs = sightingAndHeroes.getSuperheroes();
        for (int s : superIDs) {
            Superhero superhero = superheroDao.getSuperheroByID(s);
            List<Sighting> sightings = sightingDao.getAllSightingsBySuperhero(s);
            boolean alreadyPresent = false;
            for (Sighting si : sightings) {
                if (si.getSightingID() == updateSighting.getSightingID()) {
                    alreadyPresent = true;
                }
            }
            if (!alreadyPresent) {
                sightings.add(updateSighting);
                superhero.setSightings(sightings);
            }
            superheroDao.updateSuperhero(superhero);
        }
        //how do we represent a super deleted from the sighting? They aren't present on the requestbody

    }

    @RequestMapping(value = "/sighting/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteSighting(@PathVariable("id") int id) {
        String message = "";
        Sighting sighting = sightingDao.getSightingByID(id);
        //check to see if any superheroes were present here
        List<Superhero> superheroes = superheroDao.getAllSuperheroesBySighting(id);
        if (superheroes.size() > 0) {
            for (Superhero s : superheroes) {
                //update them to remove any reference to this sighting
                List<Sighting> sightings = s.getSightings();
                sightings.remove(sighting);
                s.setSightings(sightings);
                superheroDao.updateSuperhero(s);
            }
            sightingDao.deleteSighting(id);
        } else {
            sightingDao.deleteSighting(id);
        }

        return message;
    }
}
