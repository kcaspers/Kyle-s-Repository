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
public class OrganizationDaoDbImplTest {

    OrganizationDao dao;
    LocationDao locationDao;
    SightingDao sightingDao;
    SuperheroDao superheroDao;

    public OrganizationDaoDbImplTest() {
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
        dao = ctx.getBean("organizationDao", OrganizationDao.class);
        locationDao = ctx.getBean("locationDao", LocationDao.class);
        sightingDao = ctx.getBean("sightingDao", SightingDao.class);
        superheroDao = ctx.getBean("superheroDao", SuperheroDao.class);

        List<Superhero> superheroes = superheroDao.getAllSuperheroes();
        //first delete the superhero, then delete the organizations, sightings and locations
        for (Superhero s : superheroes) {
            superheroDao.deleteSuperhero(s.getSuperheroID());
        }
        
        List<Organization> organizations = dao.getAllOrganizations();
        for (Organization o : organizations) {
            dao.deleteOrganization(o.getOrganizationID());
        }

        List<Sighting> sightings = sightingDao.getAllSightings();
        for (Sighting s : sightings) {
            sightingDao.deleteSighting(s.getSightingID());
        }

        //delete all locations
        List<Location> locations = locationDao.getAllLocations();
        for (Location l : locations) {
            locationDao.deleteLocation(l.getLocationID());
        }

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setJdbcTemplate method, of class OrganizationDaoDbImpl.
     */
    @Test
    public void testSetJdbcTemplate() {
    }

    /**
     * Test of addOrganization method, of class OrganizationDaoDbImpl.
     */
    @Test
    public void addGetOrganization() {
        BigDecimal latitude = new BigDecimal("45.00");
        BigDecimal longitude = new BigDecimal("90.89");
        Location location = new Location();
        location.setLocationName("Test Location");
        location.setLocationDescription("Abandoned powerplant at the edge of town.");
        location.setAddress("666 Hell Street");
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        locationDao.addLocation(location);

        Organization organization = new Organization();
        organization.setOrganizationName("The Squad");
        organization.setOrganizationDescription("A squad of boys.");
        organization.setLocation(location);

        dao.addOrganization(organization);

        //make sure the organization that comes out has a location assigned
        Organization fromDao = dao.getOrganizationById(organization.getOrganizationID());

        assertEquals(organization, fromDao);
    }

    /**
     * Test of deleteOrganization method, of class OrganizationDaoDbImpl.
     */
    @Test
    public void deleteOrganization() {
        BigDecimal latitude = new BigDecimal("45.00");
        BigDecimal longitude = new BigDecimal("90.89");
        Location location = new Location();
        location.setLocationName("Test Location");
        location.setLocationDescription("Abandoned powerplant at the edge of town.");
        location.setAddress("666 Hell Street");
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        locationDao.addLocation(location);

        Organization organization = new Organization();
        organization.setOrganizationName("The Squad");
        organization.setOrganizationDescription("A squad of boys.");
        organization.setLocation(location);

        dao.addOrganization(organization);
        assertNotNull(dao.getOrganizationById(organization.getOrganizationID()));
        dao.deleteOrganization(organization.getOrganizationID());
        assertNull(dao.getOrganizationById(organization.getOrganizationID()));
    }

    /**
     * Test of updateOrganization method, of class OrganizationDaoDbImpl.
     */
    @Test
    public void testUpdateOrganization() {
        BigDecimal latitude = new BigDecimal("45.00");
        BigDecimal longitude = new BigDecimal("90.89");
        Location location = new Location();
        location.setLocationName("Test Location");
        location.setLocationDescription("Abandoned powerplant at the edge of town.");
        location.setAddress("666 Hell Street");
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        locationDao.addLocation(location);

        Organization organization = new Organization();
        organization.setOrganizationName("The Squad");
        organization.setOrganizationDescription("A squad of boys.");
        organization.setLocation(location);
        dao.addOrganization(organization);

        organization.setOrganizationDescription("A squad of men");

        dao.updateOrganization(organization);
        Organization fromDao = dao.getOrganizationById(organization.getOrganizationID());
        assertTrue(fromDao.getOrganizationDescription().equals("A squad of men"));
    }

    /**
     * Test of getOrganizationById method, of class OrganizationDaoDbImpl.
     */
    @Test
    public void testGetOrganizationById() {
        
    }

    /**
     * Test of getAllOrganizations method, of class OrganizationDaoDbImpl.
     */
    @Test
    public void testGetAllOrganizations() {
        BigDecimal latitude = new BigDecimal("45.00");
        BigDecimal longitude = new BigDecimal("90.89");
        Location location = new Location();
        location.setLocationName("Test Location");
        location.setLocationDescription("Abandoned powerplant at the edge of town.");
        location.setAddress("666 Hell Street");
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        locationDao.addLocation(location);
        
        Organization organization = new Organization();
        organization.setOrganizationName("The Squad");
        organization.setOrganizationDescription("A squad of boys.");
        organization.setLocation(location);
        dao.addOrganization(organization);
        
        Organization secondOrganization = dao.getOrganizationById(organization.getOrganizationID());
        dao.addOrganization(secondOrganization);
//        secondOrganization.setOrganizationName("Second Org");
//        secondOrganization.setOrganizationDescription("Here's another one");
//        secondOrganization.setLocation(location);
//        dao.addOrganization(secondOrganization);
        List<Organization> organizations = dao.getAllOrganizations();
        
        assertTrue(organizations.size() == 2);
        assertEquals(organization, secondOrganization);
        
        
    }

    /**
     * Test of getAllOrganizationsBySuperhero method, of class
     * OrganizationDaoDbImpl.
     */
    @Test
    public void testGetAllOrganizationsBySuperhero() {
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
        dao.addOrganization(organization);
        List<Organization> organizations = dao.getAllOrganizations();

        Superhero superhero = new Superhero();
        superhero.setSuperheroName("Testman");
        superhero.setDescription("He is a test.");
        superhero.setPower("Nothing");
        superhero.setOrganizations(organizations);
        superhero.setSightings(sightings);
        superheroDao.addSuperhero(superhero);

        List<Organization> organizationsBySuperhero = dao.getAllOrganizationsBySuperhero(superhero.getSuperheroID());
        assertTrue(organizationsBySuperhero.size() == 1);

    }

}
