/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flashcards.flashcards.dao;

import com.flashcards.flashcards.model.CardSuite;
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
public interface CardSuiteDao extends CrudRepository<CardSuite, Integer>{
    
    public CardSuite save(CardSuite cardSuite);
    
    public CardSuite findById(int id);
    
    public List<CardSuite> findAll();
    
    public void deleteById(int id);
    
}
