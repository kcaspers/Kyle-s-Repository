/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

/**
 *
 * @author kylecaaspers
 */
public class VendingMachineLogFileException extends Exception{
    
    public VendingMachineLogFileException(String message) {
        super(message);
    }

    public VendingMachineLogFileException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
