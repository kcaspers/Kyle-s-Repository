/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.robertstreetprojects;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Alejandro
 */
public class PWEnc {

    public static void main(String[] args) {
        String clearTxtPw = "user";
        // BCrypt
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPw = encoder.encode("user");
        System.out.println(hashedPw);
    }
}
