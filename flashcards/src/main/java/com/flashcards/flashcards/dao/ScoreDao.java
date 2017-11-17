/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flashcards.flashcards.dao;

import com.flashcards.flashcards.model.Score;
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
public interface ScoreDao extends CrudRepository<Score, Integer>{
    
    public Score save(Score score);
    
    public Score findById(int it);
    
    public List<Score> findAll();
    
    public void deleteById(int id);
    
}
