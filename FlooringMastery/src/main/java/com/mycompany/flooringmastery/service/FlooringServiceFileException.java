/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

/**
 *
 * @author kylecaaspers
 */
public class FlooringServiceFileException extends Exception{
    
        public FlooringServiceFileException(String message) {
        super(message);
    }

    public FlooringServiceFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
