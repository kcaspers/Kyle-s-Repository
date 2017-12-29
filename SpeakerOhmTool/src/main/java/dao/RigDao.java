/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.sg.model.Rig;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;

/**
 *
 * @author kylecaaspers
 */
public class RigDao {

    private static final String SQL_INSERT_RIG
            = "insert into rig (ampOhm, title) values (?, ?)";
    
    private static final String SQL_SELECT_CAB_BY_RIG
            = "select cabinetID from cabinet where rigID = (?)";
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void saveRig(Rig rig) {
        //this should insert rig and call a helper method to associate cabinets
    }
    
    //helper to get the associated cabinets
    private void associateCabinets(Rig rig){
        
        
    }
    
}
