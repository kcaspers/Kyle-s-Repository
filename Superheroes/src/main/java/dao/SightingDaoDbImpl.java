/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import model.Location;
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
public class SightingDaoDbImpl implements SightingDao {

    //I use this to assign the sighting a full location
    LocationDao locationDao = new LocationDaoDbImpl();

    private static final String SQL_INSERT_SIGHTING
            = "insert into sighting (locationID, sightingDate) "
            + "values (?, ?)";

    private static final String SQL_DELETE_SIGHTING
            = "delete from sighting where sightingID = ?";

    private static final String SQL_UPDATE_SIGHTING
            = "update sighting set locationID = ?, sightingDate = ? "
            + "where sightingID = ?";

    private static final String SQL_SELECT_SIGHTING
            = "select * from sighting where sightingID = ?";

    private static final String SQL_SELECT_ALL_SIGHTINGS
            = "select * from sighting";

    private static final String SQL_SELECT_SIGHTINGS_BY_DATE
            = "select * from sighting where sightingDate = ?";

    private static final String SQL_SELECT_ALL_SIGHTINGS_BY_SUPERHERO
            = "select s.sightingID, locationID, sightingDate "
            + "from sighting s  "
            + "inner join sightingHero sh on s.sightingID = sh.sightingID "
            + "where superheroID = ?";

    private static final String SQL_SELECT_LOCATION_BY_SIGHTING
            = "select l.locationID, l.locationName, l.locationDescription, l.address, "
            + "l.latitude, l.longitude "
            + "from location l inner join sighting s on s.locationID = l.locationID "
            + "where sightingID = ?";

    private static final String SQL_SELECT_MOST_RECENT_SIGHTINGS
            = "select * from sighting "
            + "order by sightingDate desc "
            + "limit 10";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public int addSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_INSERT_SIGHTING,
                sighting.getLocation().getLocationID(),
                sighting.getSightingDate().toString());

        int sightingID
                = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        sighting.setSightingID(sightingID);
        return sightingID;
    }

    @Override
    public void deleteSighting(int sightingID) {
        jdbcTemplate.update(SQL_DELETE_SIGHTING, sightingID);
    }

    @Override
    public void updateSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING,
                sighting.getLocation().getLocationID(),
                sighting.getSightingDate().toString(),
                sighting.getSightingID());
    }

    @Override
    public Sighting getSightingByID(int sightingID) {
        try {
            Sighting sighting = jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING,
                    new SightingMapper(),
                    sightingID);
            //have a method that connects the sighting locationID to an actual location object
            sighting.setLocation(findLocationForSighting(sighting));
            return sighting;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sighting> getAllSightings() {
        List<Sighting> sightingList
                = jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS,
                        new SightingMapper());
        //i will need to connect each of these to their respective locations
        for (Sighting s : sightingList) {
            s.setLocation(findLocationForSighting(s));
        }
        return sightingList;
    }

    @Override
    public List<Sighting> getAllSightingsByDate(LocalDate sightingDate) {
        List<Sighting> sightingsByDate
                = jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_DATE,
                        new SightingMapper(),
                        sightingDate.toString());
        for (Sighting s : sightingsByDate) {
            s.setLocation(findLocationForSighting(s));
        }
        return sightingsByDate;
    }

    public List<Sighting> getMostRecentSightings() {
        List<Sighting> mostRecentSightings
                = jdbcTemplate.query(SQL_SELECT_MOST_RECENT_SIGHTINGS,
                        new SightingMapper());
        for (Sighting s : mostRecentSightings) {
            s.setLocation(findLocationForSighting(s));
        }
        return mostRecentSightings;
    }

    private Location findLocationForSighting(Sighting sighting) {
        //Location location = locationDao.getLocationBySighting(sighting);

        return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_SIGHTING,
                new LocationDaoDbImpl.LocationMapper(),
                sighting.getSightingID());

    }

    @Override
    public List<Sighting> getAllSightingsBySuperhero(int superheroID) {
        List<Sighting> sightings
                = jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS_BY_SUPERHERO,
                        new SightingMapper(),
                        superheroID);
        for (Sighting s : sightings) {
            s.setLocation(findLocationForSighting(s));
        }
        return sightings;
    }

    //we are not setting the sighting location here, we will make a method to do that
    private static final class SightingMapper implements RowMapper<Sighting> {

        public Sighting mapRow(ResultSet rs, int rowNum) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingID(rs.getInt("sightingID"));
            sighting.setSightingDate(rs.getDate("sightingDate").toLocalDate());
            return sighting;
        }
    }

//    private static final class LocationMapper implements RowMapper<Location> {
//
//        public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Location location = new Location();
//            location.setLocationID(rs.getInt("locationID"));
//            location.setLocationName(rs.getString("locationName"));
//            location.setLocationDescription(rs.getString("locationDescription"));
//            location.setAddress(rs.getString("address"));
//            location.setLatitude(rs.getBigDecimal("latitude"));
//            location.setLongitude(rs.getBigDecimal("longitude"));
//            return location;
//        }
//
//    }
}
