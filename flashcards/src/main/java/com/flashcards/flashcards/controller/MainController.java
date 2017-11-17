/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flashcards.flashcards.controller;

import com.flashcards.flashcards.dao.CardDao;
import com.flashcards.flashcards.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author kylecaaspers
 */

@Controller
public class MainController {
    
    @Autowired
    CardDao cardDao;
    
    //navigate to the main page
    @RequestMapping("/")
    public String sayHi(){
        System.out.println("You reached this endpoint");
        
        return "main";
    }
    
    
    //save a card object in the DB
//    @RequestMapping(value = "/saveCard", method = RequestMethod.POST)
//    public String saveCard(Card card){
//        
//        
//        
//    }
}
