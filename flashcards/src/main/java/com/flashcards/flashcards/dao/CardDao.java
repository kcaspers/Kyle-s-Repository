/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flashcards.flashcards.dao;

import java.util.List;
import javax.transaction.Transactional;
import com.flashcards.flashcards.model.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kylecaaspers
 */

@Transactional
@Repository
public interface CardDao extends CrudRepository<Card, Integer>{
    
    public Card save(Card card);
    
    public Card findById(int id);
    
    public List<Card> findAll();
    
    public void deleteById(int id);
    
    
    
}
