/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.PreparedStatements.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Status;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author kylecaaspers
 */


public class StatusDaoImpl implements StatusDao{
    

     private JdbcTemplate jdbcTemplate;


    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addStatus(Status status) {
        jdbcTemplate.update(SQL_INSERT_STATUS, status.getName());
        status.setStatusId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteStatus(int statusId) {
        jdbcTemplate.update(SQL_DELETE_STATUS, statusId);
    }

    @Override
    public void updateStatus(Status status) {
        jdbcTemplate.update(SQL_UPDATE_STATUS, status.getName(), status.getStatusId());
    }

    @Override
    public Status getStatusById(int statusId) {
        try {
            Status status = jdbcTemplate.queryForObject(SQL_SELECT_STATUS, new StatusMapper(), statusId);
            return status;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Status> getAllStatuses() {
        List<Status> allStatus = jdbcTemplate.query(SQL_SELECT_ALL_STATUS, new StatusMapper());
        return allStatus;
    }
    
    public static final class StatusMapper implements RowMapper<Status> {

        @Override
        public Status mapRow(ResultSet rs, int i) throws SQLException {

            Status status = new Status();
            status.setStatusId(rs.getInt("statusId"));
            status.setName(rs.getString("name"));
            return status;
        }
    }
}
