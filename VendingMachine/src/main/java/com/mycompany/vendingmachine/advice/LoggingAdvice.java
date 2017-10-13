/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.advice;

import com.mycompany.vendingmachine.dao.VendingMachineExceptionLogger;
import com.mycompany.vendingmachine.dao.VendingMachineLogFileException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author kylecaaspers
 */
public class LoggingAdvice {
    
    VendingMachineExceptionLogger logger;
    
    public LoggingAdvice(VendingMachineExceptionLogger logger){
        this.logger = logger;
    }
    
    public void createLogEntry(JoinPoint jp, Exception ex){
        Object[] args = jp.getArgs();
        String logEntry = jp.getSignature().getName() + ": ";
        for(Object current: args){
            logEntry += current;
        }
        logEntry += " " + ex.getMessage();
//        if(logEntry.contains("areFundsSufficient")){
//            logEntry += " could not be purchased.";
//        }
//        if(logEntry.contains("isItemPresent")){
//            logEntry += " was not present.";
//        }
        try{
            logger.writeAuditEntry(logEntry);
        } catch(VendingMachineLogFileException e){
            System.err.println("Unable to log exception.");
        }
    }
    
}
