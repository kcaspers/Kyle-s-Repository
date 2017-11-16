/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.PreparedStatements.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.BlogPost;
import model.User;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addUser(User user) {
        jdbcTemplate.update(SQL_INSERT_USER,
                user.getUserName(),
                user.getUserPassword());
        user.setUserId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        
        ArrayList<String> authorities = user.getAuthorities();
        for(String authority : authorities){
            jdbcTemplate.update(SQL_INSERT_AUTHORITY, 
                    user.getUserName(), 
                    authority);
        }
        
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteUser(String username) {
        jdbcTemplate.update(SQL_DELETE_AUTHORITY, username);
        jdbcTemplate.update(SQL_DELETE_USER, username);
    }

    @Override
    public void updateUser(User user) {
        jdbcTemplate.update(SQL_UPDATE_USER, 
                user.getUserName(),
                user.getUserPassword(),
                user.isEnable(),
                user.getUserId());
    }

    @Override
    public User getUserById(int userId) {
   
        try{
            User user = jdbcTemplate.queryForObject(SQL_SELECT_USER, new UserMapper(), userId );
            return user;
        }catch(EmptyResultDataAccessException ex){
            return null;
        }
    }
    
     @Override
    public User getUserByUsername(String userName) {
        try{
            User user = jdbcTemplate.queryForObject(PreparedStatements.SQL_SELECT_USER_BY_USERNAME, new UserMapper(), userName );
            return user;
        }catch(EmptyResultDataAccessException ex){
            return null;
        }
    }
    
    @Override
    public List<User> getAllUsers() {
        List <User> users = jdbcTemplate.query(SQL_SELECT_ALL_USERS, 
                new UserMapper());
        return  users; 
    }

    public static final class UserMapper implements RowMapper<User>{

        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
         User user = new User();
         user.setUserName(rs.getString("username"));
         user.setUserPassword(rs.getString("userpassword"));
         user.setEnable(rs.getBoolean("enabled"));
         user.setUserId(rs.getInt("userId"));

         return user;
        }
    }
}
