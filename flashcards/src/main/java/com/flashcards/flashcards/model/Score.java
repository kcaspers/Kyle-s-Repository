/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flashcards.flashcards.model;

import javax.persistence.Entity;

/**
 *
 * @author kylecaaspers
 */
@Entity
public class Score {
    
    public Score(){};
    
    int correctAnswer;
    int wrongAnswer;  
    
}
