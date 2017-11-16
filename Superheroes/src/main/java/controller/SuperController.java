package controller;

import dao.LocationDao;
import dao.OrganizationDao;
import dao.SightingDao;
import dao.SuperheroDao;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import model.RemoveSuperObj;
import model.Sighting;
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

@CrossOrigin
@Controller
public class SuperController {

    //I will probably make some class level variables
    //and inject the dao or service if I make one
    LocationDao locationDao;
    OrganizationDao organizationDao;
    SightingDao sightingDao;
    SuperheroDao superheroDao;

    @Inject
    public SuperController(LocationDao locationDao, OrganizationDao organizationDao,
            SightingDao sightingDao, SuperheroDao superheroDao) {
        this.locationDao = locationDao;
        this.organizationDao = organizationDao;
        this.sightingDao = sightingDao;
        this.superheroDao = superheroDao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loadPage(Map<String, Object> model) {

        return "home";
    }

    @RequestMapping(value = "/getMostRecent", method = RequestMethod.GET)
    @ResponseBody //this tag tells Spring that the return should be sent back to client
    public Map<String, LocalDate> getMostRecent() {
        HashMap<String, LocalDate> mostRecent = new HashMap<>();
        //I want to return the locationName and date from the most recent sightings
        List<Sighting> allSightings = sightingDao.getAllSightings();
        //how do I refine this to be just the most recent? Should this be in a servicelayer?
        //have a stream arrange sightings by date and get the 10 most recent.

//        Comparator<Sighting> byDate = (s1, s2) -> LocalDate.compareTo(
//        s1.getSightingDate(), s2.getSightingDate());
//        allSightings.stream().sorted()
        //maybe I should make the sighting object comparable
        Collections.sort(allSightings);
        System.out.println(allSightings); //If this is sorted, let's get the dates and location for them
        //I need to loop through them, maybe return an arraylist actually
        return mostRecent;
    }

    @RequestMapping(value = "/superheroes", method = RequestMethod.GET)
    @ResponseBody
    public List<Superhero> getAllSupers() {
        List<Superhero> allSupers = superheroDao.getAllSuperheroes();

        return allSupers;
    }

    @RequestMapping(value = "/superhero/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Superhero getSuper(@PathVariable("id") int id) {
        Superhero superhero = superheroDao.getSuperheroByID(id);
        return superhero;
    }

    @RequestMapping(value = "/superhero/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSuper(@PathVariable("id") int id) {
        System.out.println("don't shoot!");
        superheroDao.deleteSuperhero(id);
    }

    @RequestMapping(value = "/superhero", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void addSuper(@RequestBody Superhero superhero) {
        System.out.println("reached add super endpoint");
        superheroDao.addSuperhero(superhero);
    }

    @RequestMapping(value = "/superhero", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void updateSuper(@RequestBody Superhero superhero) {
        superheroDao.updateSuperhero(superhero);
    }
    
    @RequestMapping(value = "/removeFromSighting", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void removeSuperFromSighting(@RequestBody RemoveSuperObj removeSuperObj){
        Sighting sighting = sightingDao.getSightingByID(removeSuperObj.getSightingID());
        Superhero superHero = superheroDao.getSuperheroByID(removeSuperObj.getSuperheroID());
        List<Sighting> sightings = superHero.getSightings();
        //change the sightings to not include the sighting above
        sightings.remove(sighting);
        superHero.setSightings(sightings);
        superheroDao.updateSuperhero(superHero);
        System.out.println(superHero);
    }

//    @RequestMapping(value = "/superheroes", method = RequestMethod.PUT)
//    //@ResponseStatus(HttpStatus.NO_CONTENT)
//    @ResponseBody
//    public void updateSuperList(@RequestBody SightingAndHeroes heroesAndSighting) {
//        System.out.println("");
//        int sightingID = heroesAndSighting.getSightingID();
//        int[] heroIds = heroesAndSighting.getSuperheroesID();
//        for (int i : heroIds){
//            Superhero currentHero = superheroDao.getSuperheroByID(i);
//            Sighting sighting = sightingDao.getSightingByID(sightingID);
//            List<Sighting> sightings = currentHero.getSightings();
//            sightings.add(sighting);
//            currentHero.setSightings(sightings);
//            superheroDao.updateSuperhero(currentHero);
//            superheroDao.addSuperhero(currentHero);
//        }
//    }
}
