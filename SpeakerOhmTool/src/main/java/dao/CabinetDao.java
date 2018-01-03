/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author kylecaaspers
 */
public class CabinetDao {
    
    
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    
}
