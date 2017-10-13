/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

/**
 *
 * @author kylecaaspers
 */
public class FlooringDaoFileException extends Exception{

    public FlooringDaoFileException(String message) {
        super(message);
    }

    public FlooringDaoFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
