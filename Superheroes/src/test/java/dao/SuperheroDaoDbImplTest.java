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
public class SuperheroDaoDbImplTest {

    SuperheroDao superheroDao;
    OrganizationDao organizationDao;
    SightingDao sightingDao;
    LocationDao locationDao;

    public SuperheroDaoDbImplTest() {
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
        superheroDao = ctx.getBean("superheroDao", SuperheroDao.class);
        organizationDao = ctx.getBean("organizationDao", OrganizationDao.class);
        sightingDao = ctx.getBean("sightingDao", SightingDao.class);
        locationDao = ctx.getBean("locationDao", LocationDao.class);

        //delete each superhero, make sure the getAll method works
        List<Superhero> superheroes = superheroDao.getAllSuperheroes();
        //first delete the superhero, then delete the organizations, sightings and locations
        for (Superhero s : superheroes) {
            superheroDao.deleteSuperhero(s.getSuperheroID());
        }

        List<Organization> organizations = organizationDao.getAllOrganizations();
        for (Organization o : organizations) {
            organizationDao.deleteOrganization(o.getOrganizationID());
        }

        List<Sighting> sightings = sightingDao.getAllSightings();
        for (Sighting s : sightings) {
            sightingDao.deleteSighting(s.getSightingID());
        }

        List<Location> locations = locationDao.getAllLocations();
        for (Location l : locations) {
            locationDao.deleteLocation(l.getLocationID());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setJdbcTemplate method, of class SuperheroDaoDbImpl.
     */
    @Test
    public void testSetJdbcTemplate() {
    }

    /**
     * Test of addSuperhero method, of class SuperheroDaoDbImpl.
     */
    @Test
    public void addGetSuperhero() {
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
        Superhero fromDao = superheroDao.getSuperheroByID(superhero.getSuperheroID());

        assertEquals(fromDao, superhero);
        location.setLocationName("Testy ZONE");

        //notice that when I update the location, that change doesnt transfer until I pull the new
        //sighting and superhero objects from the DB. Don't save anything in memory, stuff will be
        //up-to-date as long as we are always pulling from the DB.
        locationDao.updateLocation(location);
        Superhero fromDao2 = superheroDao.getSuperheroByID(superhero.getSuperheroID());

    }

    /**
     * Test of deleteSuperhero method, of class SuperheroDaoDbImpl.
     */
    @Test
    public void deleteSuperhero() {
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
        Superhero fromDao = superheroDao.getSuperheroByID(superhero.getSuperheroID());
        assertEquals(fromDao, superhero);
        superheroDao.deleteSuperhero(superhero.getSuperheroID());
        assertNull(superheroDao.getSuperheroByID(superhero.getSuperheroID()));

    }

    /**
     * Test of updateSuperhero method, of class SuperheroDaoDbImpl.
     */
    @Test
    public void testUpdateSuperhero() {
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

        superhero.setPower("Dances well");
        superheroDao.updateSuperhero(superhero);
        Superhero fromDao = superheroDao.getSuperheroByID(superhero.getSuperheroID());
        assertEquals(fromDao.getPower(), "Dances well");
    }

    /**
     * Test of getSuperheroByID method, of class SuperheroDaoDbImpl.
     */
    @Test
    public void testGetSuperheroByID() {
    }

    /**
     * Test of getAllSuperheroes method, of class SuperheroDaoDbImpl.
     */
    @Test
    public void testGetAllSuperheroes() {
    }

    /**
     * Test of getAllSuperheroesByLocation method, of class SuperheroDaoDbImpl.
     */
    @Test
    public void testGetAllSuperheroesByLocation() {
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

        Superhero jeff = new Superhero();
        jeff.setSuperheroName("Jeff");
        jeff.setDescription("Jeff is also a test");
        jeff.setPower("Nothing");
        jeff.setOrganizations(organizations);
        jeff.setSightings(sightings);
        superheroDao.addSuperhero(jeff);

        List<Superhero> superheroesByLocation
                = superheroDao.getAllSuperheroesByLocation(location.getLocationID());

        assertTrue(superheroesByLocation.size() == 2);

    }

    @Test
    public void testGetAllSuperheroesBySighting() {
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
        
        List<Superhero> supers = superheroDao.getAllSuperheroesBySighting(sighting.getSightingID());

    }

    /**
     * Test of getAllSuperheroesByOrganization method, of class
     * SuperheroDaoDbImpl.
     */
    @Test
    public void testGetAllSuperheroesByOrganization() {
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

        Superhero jeff = new Superhero();
        jeff.setSuperheroName("Jeff");
        jeff.setDescription("Jeff is also a test");
        jeff.setPower("Nothing");
        jeff.setOrganizations(organizations);
        jeff.setSightings(sightings);
        superheroDao.addSuperhero(jeff);

        List<Superhero> superheroesByOrganization
                = superheroDao.getAllSuperheroesByOrganization(organization.getOrganizationID());

        assertEquals(superheroesByOrganization.size(), 2);
    }

}
