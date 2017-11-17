/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flashcards.flashcards.dao;

import com.flashcards.flashcards.model.TestTaken;
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
public interface TestDao extends CrudRepository<TestTaken, Integer>{
    
    public TestTaken save(TestTaken testTaken);
    
    public TestTaken findById(int id);
    
    public List<TestTaken> findAll();
    
    public void deleteById(int id);
    
}
