/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Location;
import model.Organization;
import model.Sighting;
import model.Superhero;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author kylecaaspers
 */
public class SuperheroDaoDbImpl implements SuperheroDao {

    OrganizationDao organizationDao = new OrganizationDaoDbImpl();
    SightingDao sightingDao = new SightingDaoDbImpl();

    private static final String SQL_INSERT_SUPERHERO
            = "insert into superhero (superheroName, description, power) "
            + "values (?, ?, ?)";

    private static final String SQL_INSERT_ORGANIZATION_HERO
            = "insert into organizationHero (organizationID, superheroID) "
            + "values (?, ?)";

    private static final String SQL_INSERT_SIGHTING_HERO
            = "insert into sightingHero (sightingID, superheroID) "
            + "values (?, ?)";

    private static final String SQL_DELETE_ORGANIZATION_HERO
            = "delete from organizationHero where superheroID = ?";

    private static final String SQL_DELETE_SIGHTING_HERO
            = "delete from sightingHero where superheroID = ?";

    private static final String SQL_DELETE_SUPERHERO
            = "delete from superhero where superheroID = ?";

    private static final String SQL_UPDATE_SUPERHERO
            = "update superhero set superheroName = ?, description = ?, power = ? "
            + "where superheroID = ?";

    private static final String SQL_SELECT_SUPERHERO
            = "select * from superhero where superheroID = ?";

    private static final String SQL_SELECT_ALL_SUPERHEROES
            = "select * from superhero";

    private static final String SQL_SELECT_SUPERHEROES_BY_LOCATION
            = "select superhero.superheroID, superheroName, description, power "
            + "from superhero "
            + "inner join sightingHero sh on superhero.superheroID = sh.superheroID "
            + "inner join sighting s on sh.sightingID = s.sightingID "
            + "inner join location l on s.locationID = l.locationID "
            + "where l.locationID = ?";

    private static final String SQL_SELECT_SUPERHEROES_BY_ORGANIZATION
            = "select s.superheroID, superheroName, description, power "
            + "from superhero s "
            + "inner join organizationHero oh on oh.superheroID = s.superheroID "
            + "where organizationID = ?";

    private static final String SQL_SELECT_SUPERHEROES_BY_SIGHTING
            = "select s.superheroID, superheroName, description, power "
            + "from superhero s "
            + "inner join sightingHero sh on s.superheroID = sh.superheroID "
            + "where sightingID = ?";

    private static final String SQL_SELECT_ORGANIZATIONS_BY_SUPERHERO
            = "select * from organization o "
            + "inner join organizationHero oh on o.organizationID = oh.organizationID "
            + "where superheroID = ?";

    private static final String SQL_SELECT_SIGHTINGS_BY_SUPERHERO
            = "select s.sightingID, sightingDate "
            + "from sighting s "
            + "inner join sightingHero sh on s.sightingID = sh.sightingID "
            + "where superheroID = ?";

    private static final String SQL_SELECT_LOCATION_BY_SIGHTING
            = "select l.locationID, l.locationName, l.locationDescription, l.address, "
            + "l.latitude, l.longitude "
            + "from location l inner join sighting s on s.locationID = l.locationID "
            + "where sightingID = ?";

    private static final String SQL_SELECT_LOCATION_BY_ORGANIZATION
            = "select l.locationID, l.locationName, l.locationDescription, l.address, "
            + "l.latitude, l.longitude "
            + "from location l inner join organization o on l.locationID = o.locationID "
            + "where organizationID = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            readOnly = false)
    public void addSuperhero(Superhero superhero) {
        jdbcTemplate.update(SQL_INSERT_SUPERHERO,
                superhero.getSuperheroName(),
                superhero.getDescription(),
                superhero.getPower());
        superhero.setSuperheroID(
                jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));

        //these helper classes translate the java superhero-organization-sighting relationship into the db
        insertOrganizationHero(superhero);
        insertSightingHero(superhero);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            readOnly = false)
    public void deleteSuperhero(int superheroID) {
        //I will delete the superhero, but first I must delete the bridge table relationships
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION_HERO, superheroID);
        jdbcTemplate.update(SQL_DELETE_SIGHTING_HERO, superheroID);
        //now delete the actual book
        jdbcTemplate.update(SQL_DELETE_SUPERHERO, superheroID);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateSuperhero(Superhero superhero) {
        jdbcTemplate.update(SQL_UPDATE_SUPERHERO,
                superhero.getSuperheroName(),
                superhero.getDescription(),
                superhero.getPower(),
                superhero.getSuperheroID());
        //delete organization and sighting relationships and reset them
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION_HERO, superhero.getSuperheroID());
        jdbcTemplate.update(SQL_DELETE_SIGHTING_HERO, superhero.getSuperheroID());
        insertOrganizationHero(superhero);
        insertSightingHero(superhero);
    }

    @Override
    public Superhero getSuperheroByID(int superheroID) {
        try {
            Superhero superhero = jdbcTemplate.queryForObject(SQL_SELECT_SUPERHERO,
                    new SuperheroMapper(),
                    superheroID);
            //make sure the locations in the organizations are being set also
            superhero.setOrganizations(findOrganizationsForSuperhero(superhero));
            superhero.setSightings(findSightingsForSuperhero(superhero));

            return superhero;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Superhero> getAllSuperheroes() {
        List<Superhero> superheroes
                = jdbcTemplate.query(SQL_SELECT_ALL_SUPERHEROES,
                        new SuperheroMapper());
        //need to set the organizations and sightings for each superhero, this is giving me errors
        for (Superhero s : superheroes) {
            s.setOrganizations(findOrganizationsForSuperhero(s));
            s.setSightings(findSightingsForSuperhero(s));
        }
        return superheroes;
    }

    @Override
    public List<Superhero> getAllSuperheroesByLocation(int locationID) {
        List<Superhero> superheroes
                = jdbcTemplate.query(SQL_SELECT_SUPERHEROES_BY_LOCATION,
                        new SuperheroMapper(),
                        locationID);
        for (Superhero s : superheroes) {
            s.setOrganizations(findOrganizationsForSuperhero(s));
            s.setSightings(findSightingsForSuperhero(s));
        }
        return superheroes;
    }

    @Override
    public List<Superhero> getAllSuperheroesByOrganization(int organizationID) {
        List<Superhero> superheroes
                = jdbcTemplate.query(SQL_SELECT_SUPERHEROES_BY_ORGANIZATION,
                        new SuperheroMapper(),
                        organizationID);
        for (Superhero s : superheroes) {
            s.setOrganizations(findOrganizationsForSuperhero(s));
            s.setSightings(findSightingsForSuperhero(s));
        }
        return superheroes;
    }

    @Override
    public List<Superhero> getAllSuperheroesBySighting(int sightingID) {
        List<Superhero> superheroes
                = jdbcTemplate.query(SQL_SELECT_SUPERHEROES_BY_SIGHTING,
                        new SuperheroMapper(),
                        sightingID);
        for (Superhero s : superheroes) {
            s.setOrganizations(findOrganizationsForSuperhero(s));
            s.setSightings(findSightingsForSuperhero(s));
        }
        return superheroes;
    }

    private void insertOrganizationHero(Superhero superhero) {
        final int superheroID = superhero.getSuperheroID();
        final List<Organization> organizations = superhero.getOrganizations();

        for (Organization o : organizations) {
            jdbcTemplate.update(SQL_INSERT_ORGANIZATION_HERO,
                    o.getOrganizationID(),
                    superheroID);
        }
    }

    private void insertSightingHero(Superhero superhero) {
        final int superheroID = superhero.getSuperheroID();
        final List<Sighting> sightings = superhero.getSightings();

        for (Sighting s : sightings) {
            jdbcTemplate.update(SQL_INSERT_SIGHTING_HERO,
                    s.getSightingID(),
                    superheroID);
        }
    }

    private List<Organization> findOrganizationsForSuperhero(Superhero superhero) {
        List<Organization> organizations = jdbcTemplate.query(SQL_SELECT_ORGANIZATIONS_BY_SUPERHERO,
                new OrganizationMapper(),
                superhero.getSuperheroID());
        //I need this to connect the locations to the organizations before I return it
        for (Organization o : organizations) {
            o.setLocation(findLocationForOrganization(o));
        }
        return organizations;
    }

    private List<Sighting> findSightingsForSuperhero(Superhero superhero) {
        //I probably will need to give these sightings locations before they get connected to superheroes
        List<Sighting> sightings = jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_SUPERHERO,
                new SightingMapper(),
                superhero.getSuperheroID());
        for (Sighting s : sightings) {
            s.setLocation(findLocationForSighting(s));
        }
        return sightings;
    }

    private Location findLocationForSighting(Sighting sighting) {
        return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_SIGHTING,
                new LocationMapper(),
                sighting.getSightingID());

    }

    private Location findLocationForOrganization(Organization organization) {
        return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_ORGANIZATION,
                new LocationMapper(),
                organization.getOrganizationID());
    }

    //superhero sightings and the orgs they belong to will be assigned through a helper class
    private static final class SuperheroMapper implements RowMapper<Superhero> {

        public Superhero mapRow(ResultSet rs, int rowNum) throws SQLException {
            Superhero superhero = new Superhero();
            superhero.setSuperheroID(rs.getInt("superheroID"));
            superhero.setSuperheroName(rs.getString("superheroName"));
            superhero.setDescription(rs.getString("description"));
            superhero.setPower(rs.getString("power"));
            return superhero;
        }
    }

    private static final class OrganizationMapper implements RowMapper<Organization> {

        public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
            Organization organization = new Organization();
            organization.setOrganizationID(rs.getInt("organizationID"));
            organization.setOrganizationName(rs.getString("organizationName"));
            organization.setOrganizationDescription(rs.getString("organizationDescription"));
            return organization;
        }
    }

    private static final class SightingMapper implements RowMapper<Sighting> {

        public Sighting mapRow(ResultSet rs, int rowNum) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingID(rs.getInt("sightingID"));
            sighting.setSightingDate(rs.getDate("sightingDate").toLocalDate());
            return sighting;
        }
    }

    private static final class LocationMapper implements RowMapper<Location> {

        public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
            Location location = new Location();
            location.setLocationID(rs.getInt("locationID"));
            location.setLocationName(rs.getString("locationName"));
            location.setLocationDescription(rs.getString("locationDescription"));
            location.setAddress(rs.getString("address"));
            location.setLatitude(rs.getBigDecimal("latitude"));
            location.setLongitude(rs.getBigDecimal("longitude"));
            return location;
        }

    }

}
