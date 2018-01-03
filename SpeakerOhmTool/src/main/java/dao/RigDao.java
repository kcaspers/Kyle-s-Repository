/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.sg.model.Cabinet;
import com.sg.model.Rig;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;

/**
 *
 * @author kylecaaspers
 */
public class RigDao {

    private static final String SQL_INSERT_RIG
            = "insert into rig (ampOhm, title, date) values (?, ?, ?)";
    
    private static final String SQL_SELECT_RIG_BY_TITLE 
            = "select * from rig where title = (?)";
    
    private static final String SQL_SELECT_CAB_BY_RIG
            = "select cabinetID from cabinet where rigID = (?)";
    
    private static final String SQL_INSERT_CAB 
            = "insert into cabinet (impedance, cabNumber, outputPercentage, rigID)"
            + "values (?, ?, ?, ?)";
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void saveRig(Rig rig) {
        //this should insert rig and call a helper method to associate cabinets
        jdbcTemplate.update(SQL_INSERT_RIG,
                rig.getAmpOhm(),
                rig.getTitle(),
                java.sql.Date.valueOf(rig.getDate()));
        
        associateCabinets(rig);
    }
    
    //helper to get the associated cabinets
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    private void associateCabinets(Rig rig){
        rig.setId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        List<Cabinet> cabinets = rig.getCabinets();
        for(Cabinet c : cabinets){
            jdbcTemplate.update(SQL_INSERT_CAB,
                    c.getImpedance(),
                    c.getCabNumber(),
                    c.getOutputPercentage(),
                    rig.getId());
        }
    }
    
    private Rig getRigByTitle(String title){
        return jdbcTemplate.queryForObject(SQL_SELECT_RIG_BY_TITLE,
                new rigMapper(),
                title);
    }
    
    //write a rig mapper
    public static final class rigMapper implements RowMapper<Rig> {

        @Override
        public Rig mapRow(ResultSet rs, int i) throws SQLException {
            Rig rig = new Rig();
            rig.setId(rs.getInt("rigID"));
            rig.setTitle(rs.getString("title"));
            rig.setDate(rs.getDate("date").toLocalDate());
            //get cabinets
            rig.setAmpOhm(rs.getInt("ampOhm"));
            return rig;
        }
    
    }
    
}
