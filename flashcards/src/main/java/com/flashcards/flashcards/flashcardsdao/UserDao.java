/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flashcards.flashcards.flashcardsdao;

import com.flashcards.flashcards.model.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author kylecaaspers
 */
public interface UserDao extends CrudRepository<User, Integer>{
    
    public User save(User user);
    
    public User findById(int id);
    
    public List<User> findAll();
    
}
