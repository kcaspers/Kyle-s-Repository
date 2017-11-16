/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import model.Location;
import model.Organization;
import model.Sighting;
import model.Superhero;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author kylecaaspers
 */
public class LocationDaoDbImplTest {

    LocationDao dao;
    SightingDao sightingDao;
    SuperheroDao superheroDao;
    OrganizationDao organizationDao;

    public LocationDaoDbImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("locationDao", LocationDao.class);
        sightingDao = ctx.getBean("sightingDao", SightingDao.class);
        superheroDao = ctx.getBean("superheroDao", SuperheroDao.class);
        organizationDao = ctx.getBean("organizationDao", OrganizationDao.class);

        List<Superhero> superheroes = superheroDao.getAllSuperheroes();
        //first delete the superhero, then delete the organizations, sightings and locations
        for (Superhero s : superheroes) {
            superheroDao.deleteSuperhero(s.getSuperheroID());
        }
        
        List<Organization> organizations = organizationDao.getAllOrganizations();
        for(Organization o : organizations){
            organizationDao.deleteOrganization(o.getOrganizationID());
        }
        
        List<Sighting> sightings = sightingDao.getAllSightings();
        for(Sighting s : sightings){
            sightingDao.deleteSighting(s.getSightingID());
        }
        
        List<Location> locations = dao.getAllLocations();
        for (Location l : locations) {
            dao.deleteLocation(l.getLocationID());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setJdbcTemplate method, of class LocationDaoDbImpl.
     */
    @Test
    public void testSetJdbcTemplate() {
    }

    /**
     * Test of addLocation method, of class LocationDaoDbImpl.
     */
    @Test
    public void addGetLocation() {
        BigDecimal latitude = new BigDecimal("40.00");
        BigDecimal longitude = new BigDecimal("34.00");

        Location location = new Location();
        location.setLocationName("Location one");
        location.setLocationDescription("It's a location.");
        location.setAddress("123 place place");
        location.setLatitude(latitude);
        location.setLongitude(longitude);

        dao.addLocation(location);

        Location fromDao = dao.getLocationById(location.getLocationID());
        assertEquals(location, fromDao);
    }

    /**
     * Test of deleteLocation method, of class LocationDaoDbImpl.
     */
    @Test
    public void deleteLocation() {
        BigDecimal latitude = new BigDecimal("40.00");
        BigDecimal longitude = new BigDecimal("34.00");

        Location location = new Location();
        location.setLocationName("Location one");
        location.setLocationDescription("It's a location.");
        location.setAddress("123 place place");
        location.setLatitude(latitude);
        location.setLongitude(longitude);

        dao.addLocation(location);
        
        //Location fromDao = dao.getLocationById(location.getLocationID());
        //assertEquals(location, fromDao);
        dao.deleteLocation(location.getLocationID());
        assertNull(dao.getLocationById(location.getLocationID()));
    }

    /**
     * Test of updateLocation method, of class LocationDaoDbImpl.
     */
    @Test
    public void testUpdateLocation() {
        BigDecimal latitude = new BigDecimal("40.00");
        BigDecimal longitude = new BigDecimal("34.00");
        Location location = new Location();
        location.setLocationName("Location one");
        location.setLocationDescription("It's a location.");
        location.setAddress("123 place place");
        location.setLatitude(latitude);
        location.setLongitude(longitude);

        dao.addLocation(location);
        
        location.setAddress("456 a different street.");
        location.setLocationName("Location two");
        
        dao.updateLocation(location);
        Location fromDao = dao.getLocationById(location.getLocationID());
        assertTrue(fromDao.getLocationName().equals("Location two"));
    }

    /**
     * Test of getLocationById method, of class LocationDaoDbImpl.
     */
    @Test
    public void testGetLocationById() {
    }

    /**
     * Test of getAllLocations method, of class LocationDaoDbImpl.
     */
    @Test
    public void testGetAllLocations() {
    }

    /**
     * Test of getAllLocationsBySuperhero method, of class LocationDaoDbImpl.
     */
    @Test
    public void testGetAllLocationsBySuperhero() {
        BigDecimal latitude = new BigDecimal("45.00");
        BigDecimal longitude = new BigDecimal("90.89");
        Location location = new Location();
        location.setLocationName("Test Location");
        location.setLocationDescription("Abandoned powerplant at the edge of town.");
        location.setAddress("666 Hell Street");
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        dao.addLocation(location);
        
        Sighting sighting = new Sighting();
        sighting.setLocation(location);
        sighting.setSightingDate(LocalDate.now());
        sightingDao.addSighting(sighting);
        List<Sighting> sightings = sightingDao.getAllSightings();
        
        Organization organization = new Organization();
        organization.setOrganizationName("The Squad");
        organization.setOrganizationDescription("A squad of boys.");
        organization.setLocation(location);
        organizationDao.addOrganization(organization);
        List<Organization> organizations = organizationDao.getAllOrganizations();
        
        Superhero superhero = new Superhero();
        superhero.setSuperheroName("Testman");
        superhero.setDescription("He is a test.");
        superhero.setPower("Nothing");
        superhero.setOrganizations(organizations);
        superhero.setSightings(sightings);
        superheroDao.addSuperhero(superhero);
        
        List<Location> locationsByHero = dao.getAllLocationsBySuperhero(superhero.getSuperheroID());
        assertTrue(locationsByHero.size() == 1);
    }

}
