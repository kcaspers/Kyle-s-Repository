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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author kylecaaspers
 */
public class OrganizationDaoDbImpl implements OrganizationDao {

    LocationDao locationDao = new LocationDaoDbImpl();

    private static final String SQL_INSERT_ORGANIZATION
            = "insert into organization (organizationName, organizationDescription, locationID) "
            + "values (?, ?, ?)";

    private static final String SQL_DELETE_ORGANIZATION
            = "delete from organization where organizationID = ?";

    private static final String SQL_UPDATE_ORGANIZATION
            = "update organization set organizationName = ?, organizationDescription = ?, "
            + "locationID = ? where organizationID = ?";

    private static final String SQL_SELECT_ORGANIZATION
            = "select * from organization where organizationID = ?";

    private static final String SQL_SELECT_ALL_ORGANIZATIONS
            = "select * from organization";

    //this one might need to be modified, I only need the organization info, not the bridge table info
    private static final String SQL_SELECT_ALL_ORGANIZATIONS_BY_SUPERHERO
            = "select * from organization o "
            + "inner join organizationHero oh on o.organizationID = oh.organizationID "
            + "where superheroID = ?";

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
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addOrganization(Organization organization) {
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION,
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getLocation().getLocationID());

        int organizationID
                = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        organization.setOrganizationID(organizationID);

    }

    @Override
    public void deleteOrganization(int organizationID) {
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION, organizationID);
    }

    @Override
    public void updateOrganization(Organization organization) {
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION,
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getLocation().getLocationID(),
                organization.getOrganizationID());
    }

    @Override
    public Organization getOrganizationById(int organizationID) {
        try {
            Organization organization = jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION,
                    new OrganizationMapper(),
                    organizationID);

            //have a helper method to associate them (look at findpublisherforbook)
            organization.setLocation(findLocationForOrganization(organization));
            return organization;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> getAllOrganizations() {
        //I need to assign locations for these organizations
        List<Organization> organizations = jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATIONS,
                new OrganizationMapper());
        for(Organization o : organizations){
            o.setLocation(findLocationForOrganization(o));
        }
        return organizations;
    }

    @Override
    public List<Organization> getAllOrganizationsBySuperhero(int superheroID) {
        List<Organization> organizations
                = jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATIONS_BY_SUPERHERO,
                        new OrganizationMapper(),
                        superheroID);

        return organizations;
    }

    //OrganizationMapper doesnt asign location to organization, we will assign that with a method
    private Location findLocationForOrganization(Organization organization) {
        return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_ORGANIZATION,
                new LocationDaoDbImpl.LocationMapper(),
                organization.getOrganizationID());
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
