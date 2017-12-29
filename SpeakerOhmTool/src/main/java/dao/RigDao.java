/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.sg.model.Rig;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kylecaaspers
 */

@Transactional
@Repository
public interface RigDao extends CrudRepository<Rig, Integer>{
    
    //addRig
    public Rig save(Rig rig);
    
    //deleteRig
    public void deleteById(int id);
    
    //getRig
    public Rig getById(int id);
    
    //getAllRigs
    public List<Rig> findAll();
}
