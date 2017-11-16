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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author kylecaaspers
 */
public class LocationDaoDbImpl implements LocationDao {

    //create sql prepared statements
    private static final String SQL_INSERT_LOCATION
            = "insert into location (locationName, locationDescription, address, "
            + "latitude, longitude) values (?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_LOCATION
            = "delete from location where locationID = ?";

    private static final String SQL_UPDATE_LOCATION
            = "update location set locationName = ?, locationDescription = ?, "
            + "address = ?, latitude = ?, longitude = ? where locationID = ?";

    private static final String SQL_SELECT_LOCATION
            = "select * from location where locationID = ?";

    private static final String SQL_SELECT_ALL_LOCATION
            = "select * from location";

    //I think I will have this just return the location IDs, the method will get objects from that
    private static final String SQL_SELECT_LOCATION_BY_SUPERHERO
            = "select l.locationID, l.locationName, l.locationDescription, l.address, "
            + "l.latitude, l.longitude "
            + "from location l inner join sighting s on s.locationID = l.locationID "
            + "inner join sightingHero sh on sh.sightingID = s.sightingID "
            + "inner join superhero on superhero.superheroID = sh.superheroID "
            + "where superhero.superheroID = ?";

    private static final String SQL_SELECT_LOCATION_BY_ORGANIZATION
            = "select l.locationID, l.locationName, l.locationDescription, l.address, "
            + "l.latitude, l.longitude "
            + "from location l inner join organization o on l.locationID = o.locationID "
            + "where organizationID = ?";

    private static final String SQL_SELECT_LOCATION_BY_SIGHTING
            = "select l.locationID, l.locationName, l.locationDescription, l.address, "
            + "l.latitude, l.longitude "
            + "from location l inner join sighting s on s.locationID = l.locationID "
            + "where sightingID = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addLocation(Location location) {
        jdbcTemplate.update(SQL_INSERT_LOCATION,
                location.getLocationName(),
                location.getLocationDescription(),
                location.getAddress(),
                location.getLatitude(),
                location.getLongitude());

        int locationID
                = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                        Integer.class);

        location.setLocationID(locationID);
    }

    @Override
    public void deleteLocation(int locationID) {
        jdbcTemplate.update(SQL_DELETE_LOCATION, locationID);
    }

    @Override
    public void updateLocation(Location location) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                location.getLocationName(),
                location.getLocationDescription(),
                location.getAddress(),
                location.getLatitude(),
                location.getLongitude(),
                location.getLocationID());
    }

    @Override
    public Location getLocationById(int locationID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION,
                    new LocationMapper(),
                    locationID);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATION,
                new LocationMapper());
    }

    @Override
    public List<Location> getAllLocationsBySuperhero(int superheroID) {
        List<Location> locationList
                = jdbcTemplate.query(SQL_SELECT_LOCATION_BY_SUPERHERO,
                        new LocationMapper(),
                        superheroID);
        return locationList;
    }

    @Override
    public Location getLocationByOrganization(int organizationID) {
        Location location = jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_ORGANIZATION,
                new LocationMapper(),
                organizationID);
        return location;
    }

    @Override
    public Location getLocationBySighting(Sighting sighting) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_SIGHTING,
                    new LocationMapper(),
                    sighting.getSightingID());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    //create location mapper
    public static final class LocationMapper implements RowMapper<Location> {

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
