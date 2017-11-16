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
import model.Organization;
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
public class OrganizationController {

    LocationDao locationDao;
    OrganizationDao organizationDao;
    SightingDao sightingDao;
    SuperheroDao superheroDao;

    @Inject
    public OrganizationController(LocationDao locationDao, OrganizationDao organizationDao,
            SightingDao sightingDao, SuperheroDao superheroDao) {
        this.locationDao = locationDao;
        this.organizationDao = organizationDao;
        this.sightingDao = sightingDao;
        this.superheroDao = superheroDao;
    }
    
    @RequestMapping(value = "/organizations", method = RequestMethod.GET)
    @ResponseBody
    public List<Organization> getAllOrganizations(){
        return organizationDao.getAllOrganizations();
    }
    
    @RequestMapping(value = "/organization/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Organization getOrganization(@PathVariable("id") int id){
        return organizationDao.getOrganizationById(id);
    }
    
    @RequestMapping(value = "/superheroes/organization/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<Superhero> getSuperheroesByOrganization(@PathVariable("id") int id){
        return superheroDao.getAllSuperheroesByOrganization(id);
    }
    
    @RequestMapping(value = "/organizations", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void updateOrganization(@RequestBody Organization organization){
        organizationDao.updateOrganization(organization);
    }
    
    @RequestMapping(value = "/organizations", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void addOrganization(@RequestBody Organization organization){
        //System.out.println(organization);
        organizationDao.addOrganization(organization);
    }
    
    @RequestMapping(value = "/organization/{id}", method = RequestMethod.DELETE)
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public String deleteOrganization(@PathVariable("id") int id){
        String message = "";
        //check to see if any superheroes belong to this org
        List<Superhero> superHeroes = superheroDao.getAllSuperheroesByOrganization(id);
        if(superHeroes.size() > 0){
            //dont delete this one
            message += "unable to delete, this organization is still active";
        }else{
           organizationDao.deleteOrganization(id); 
        }
        return message;
    }
}
