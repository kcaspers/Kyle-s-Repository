/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.advice;

import com.mycompany.flooringmastery.dao.OrderDao;
import com.mycompany.flooringmastery.dao.OrderDaoImpl;
import com.mycompany.flooringmastery.model.Order;
import com.mycompany.flooringmastery.service.FlooringServiceFileException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author kylecaaspers
 */
public class FlooringAdvice {
    
    FlooringAdvice(){
        
    }

    public static final String AUDIT_FILE = "audit.txt";
    ArrayList<String> auditData = new ArrayList<>();

    public void buildAudit(JoinPoint jp) {
        //collect info on methods that add, delete and replace orders

        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        
        for (Object currentArg : args) {
            if(currentArg instanceof Order){
                auditEntry += " " + ((Order) currentArg).getOrderName();
                auditEntry += " " + ((Order) currentArg).getState().getStateName();
                auditEntry += " " + ((Order) currentArg).getProduct().getProductType();
                auditEntry += " " + ((Order) currentArg).getAreaAsString();
                auditEntry += " " + ((Order) currentArg).getTax().setScale(2, RoundingMode.HALF_UP).toString();
                auditEntry += " " + ((Order) currentArg).getTotal().setScale(2, RoundingMode.HALF_UP).toString();
            }
        }
        LocalDateTime timestamp = LocalDateTime.now();
        auditData.add(timestamp.toString() + " : " + auditEntry);
    }

    public void writeAudit() throws FlooringAspectException{
        //actually commits our file, occurs on save
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new FlooringAspectException("Could not persist audit data.", e);
        }
        for(String currentLine : auditData){
            out.println(currentLine);
        }
        out.flush();
    }

}
