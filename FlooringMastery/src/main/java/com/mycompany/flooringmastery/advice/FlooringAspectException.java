/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.advice;

/**
 *
 * @author kylecaaspers
 */
public class FlooringAspectException extends Exception{
    
    public FlooringAspectException(String message) {
        super(message);
    }

    public FlooringAspectException(String message, Throwable cause) {
        super(message, cause);
    }
}
