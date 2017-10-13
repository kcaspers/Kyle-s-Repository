/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author kylecaaspers
 */
public class VendingMachineExceptionLogger {
    
    public static final String LOG_FILE = "exceptionlog.txt";
    
    public void writeAuditEntry(String entry) throws VendingMachineLogFileException{
        PrintWriter out;
        
        try{
            out = new PrintWriter(new FileWriter(LOG_FILE, true));
        } catch (IOException e){                                     
            throw new VendingMachineLogFileException("could not persist exception log info", e);
        }
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    }
    
}
