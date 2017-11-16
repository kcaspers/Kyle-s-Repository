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
import static org.junit.Assert.assertNotNull;
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
public class SightingDaoDbImplTest {

    SightingDao sightingDao;
    LocationDao locationDao;
    OrganizationDao organizationDao;
    SuperheroDao superheroDao;

    public SightingDaoDbImplTest() {
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
        sightingDao = ctx.getBean("sightingDao", SightingDao.class);
        locationDao = ctx.getBean("locationDao", LocationDao.class);
        organizationDao = ctx.getBean("organizationDao", OrganizationDao.class);
        superheroDao = ctx.getBean("superheroDao", SuperheroDao.class);

        List<Superhero> superheroes = superheroDao.getAllSuperheroes();
        //first delete the superhero, then delete the organizations, sightings and locations
        for (Superhero s : superheroes) {
            superheroDao.deleteSuperhero(s.getSuperheroID());
        }
        
        List<Organization> organizations = organizationDao.getAllOrganizations();
        for(Organization o : organizations){
            organizationDao.deleteOrganization(o.getOrganizationID());
        }
        
        //delete all sightings
        List<Sighting> sightings = sightingDao.getAllSightings();
        for (Sighting s : sightings) {
            sightingDao.deleteSighting(s.getSightingID());
        }
        //delete the locations
        List<Location> locations = locationDao.getAllLocations();
        for (Location l : locations) {
            locationDao.deleteLocation(l.getLocationID());
        }

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setJdbcTemplate method, of class SightingDaoDbImpl.
     */
    @Test
    public void testSetJdbcTemplate() {
    }

    /**
     * Test of addSighting method, of class SightingDaoDbImpl.
     */
    @Test
    public void addGetSighting() {
        BigDecimal latitude = new BigDecimal("45.00");
        BigDecimal longitude = new BigDecimal("90.89");
        Location location = new Location();
        location.setLocationName("Test Location");
        location.setLocationDescription("Abandoned powerplant at the edge of town.");
        location.setAddress("666 Hell Street");
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        locationDao.addLocation(location);

        Sighting sighting = new Sighting();
        sighting.setLocation(location);
        sighting.setSightingDate(LocalDate.now());
        sightingDao.addSighting(sighting);
        
        Sighting fromDao = sightingDao.getSightingByID(sighting.getSightingID());

        assertTrue(sightingDao.getAllSightings().size() == 1);
        assertEquals(sighting, fromDao);
        
        

    }

    /**
     * Test of deleteSighting method, of class SightingDaoDbImpl.
     */
    @Test
    public void testDeleteSighting() {
        BigDecimal latitude = new BigDecimal("45.00");
        BigDecimal longitude = new BigDecimal("90.89");
        Location location = new Location();
        location.setLocationName("Test Location");
        location.setLocationDescription("Abandoned powerplant at the edge of town.");
        location.setAddress("666 Hell Street");
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        locationDao.addLocation(location);
        
        Sighting sighting = new Sighting();
        sighting.setLocation(location);
        sighting.setSightingDate(LocalDate.now());
        sightingDao.addSighting(sighting);
        
        assertNotNull(sightingDao.getSightingByID(sighting.getSightingID()));
        sightingDao.deleteSighting(sighting.getSightingID());
        assertNull(sightingDao.getSightingByID(sighting.getSightingID()));
    }

    /**
     * Test of updateSighting method, of class SightingDaoDbImpl.
     */
    @Test
    public void testUpdateSighting() {
        BigDecimal latitude = new BigDecimal("45.00");
        BigDecimal longitude = new BigDecimal("90.89");
        Location location = new Location();
        location.setLocationName("Test Location");
        location.setLocationDescription("Abandoned powerplant at the edge of town.");
        location.setAddress("666 Hell Street");
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        locationDao.addLocation(location);
        
        Sighting sighting = new Sighting();
        sighting.setLocation(location);
        sighting.setSightingDate(LocalDate.now());
        sightingDao.addSighting(sighting);
        
        String newDateString = "2017-08-07";
        LocalDate newDate = LocalDate.parse(newDateString);
        sighting.setSightingDate(newDate);
        
        sightingDao.updateSighting(sighting);
        Sighting fromDao = sightingDao.getSightingByID(sighting.getSightingID());
        assertEquals(fromDao.getSightingDate(),  newDate);
    }

    /**
     * Test of getSightingByID method, of class SightingDaoDbImpl.
     */
    @Test
    public void testGetSightingByID() {
    }

    /**
     * Test of getAllSightings method, of class SightingDaoDbImpl.
     */
    @Test
    public void getAllSightings() {
        BigDecimal latitude = new BigDecimal("45.00");
        BigDecimal longitude = new BigDecimal("90.89");
        Location location = new Location();
        location.setLocationName("Test Location");
        location.setLocationDescription("Abandoned powerplant at the edge of town.");
        location.setAddress("666 Hell Street");
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        locationDao.addLocation(location);
        
        Sighting sighting = new Sighting();
        sighting.setLocation(location);
        sighting.setSightingDate(LocalDate.now());
        sightingDao.addSighting(sighting);
        
        Sighting secondSighting = new Sighting();
        secondSighting.setLocation(location);
        secondSighting.setSightingDate(LocalDate.now());
        sightingDao.addSighting(sighting);
        
        List<Sighting> sightings = sightingDao.getAllSightings();
        assertEquals(sighting, secondSighting);

    }

    /**
     * Test of getAllSightingsByDate method, of class SightingDaoDbImpl.
     */
    @Test
    public void testGetAllSightingsByDate() {
        BigDecimal latitude = new BigDecimal("45.00");
        BigDecimal longitude = new BigDecimal("90.89");
        Location location = new Location();
        location.setLocationName("Test Location");
        location.setLocationDescription("Abandoned powerplant at the edge of town.");
        location.setAddress("666 Hell Street");
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        locationDao.addLocation(location);
        
        Sighting sighting = new Sighting();
        sighting.setLocation(location);
        String newDateString = "2017-08-07";
        LocalDate stringDate = LocalDate.parse(newDateString);
        sighting.setSightingDate(stringDate);
        sightingDao.addSighting(sighting);
        
        List<Sighting> sightingsByDate = sightingDao.getAllSightingsByDate(stringDate);
        assertTrue(sightingsByDate.size() == 1);
    }
    
    @Test
    public void testGetAllSightingsBySuperhero(){
        BigDecimal latitude = new BigDecimal("45.00");
        BigDecimal longitude = new BigDecimal("90.89");
        Location location = new Location();
        location.setLocationName("Test Location");
        location.setLocationDescription("Abandoned powerplant at the edge of town.");
        location.setAddress("666 Hell Street");
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        locationDao.addLocation(location);
        
        Sighting sighting = new Sighting();
        sighting.setLocation(location);
        String newDateString = "2017-08-07";
        LocalDate stringDate = LocalDate.parse(newDateString);
        sighting.setSightingDate(stringDate);
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
        
        List<Sighting> sightingsBySuperhero = sightingDao.getAllSightingsBySuperhero(superhero.getSuperheroID());
        assertTrue(sightingsBySuperhero.size() == 1);
    }

}
