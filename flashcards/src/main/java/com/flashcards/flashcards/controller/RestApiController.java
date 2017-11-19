/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flashcards.flashcards.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.flashcards.flashcards.dao.CardDao;
import com.flashcards.flashcards.model.Card;
import java.util.List;
import static org.hibernate.annotations.common.util.impl.LoggerFactory.logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author kylecaaspers
 */
@RestController
@RequestMapping("/api")
public class RestApiController {
    
    public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);
    
    @Autowired
    CardDao cardDao;
    
    @RequestMapping(value = "/card", method = RequestMethod.GET)
    public ResponseEntity<List<Card>> listAllCards(){
        List<Card> cards = cardDao.findAll();
        if(cards.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Card>>(cards, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/card/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCard(@PathVariable("id") int id){
        logger.info("Fetching Card with id {}", id);
        Card card = cardDao.findById(id);
        if(card == null){
            logger.error("Card with id {} not found", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Card>(card, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/card/", method = RequestMethod.POST)
    public ResponseEntity<?> createCard(@RequestBody Card card, UriComponentsBuilder ucBuilder){
        logger.info("Creating Card : {}", card);
        cardDao.save(card);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user.{id}").buildAndExpand(card.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/card/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCard(@PathVariable("id") int id, @RequestBody Card card){
        Card currentCard = cardDao.findById(id);
        
        currentCard.setAnswer(card.getAnswer());
        currentCard.setQuestion(card.getQuestion());
        currentCard.setChoices(card.getChoices());
        
        cardDao.save(card);
        return new ResponseEntity<Card>(currentCard, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCard(@PathVariable("id") int id){
        Card card = cardDao.findById(id);
        if(card == null){
            logger.error("Unable to delete. User with id {} not found.", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        
        cardDao.deleteById(id);
        return new ResponseEntity<Card>(HttpStatus.NO_CONTENT);
        
    }
    
}
