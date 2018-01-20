/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.greathealthwebsite;

import java.util.Properties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.mail.*;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

/**
 *
 * @author kylecaaspers
 */
@Controller
public class FormSubmit {

    //look into using sendgrid for emails, read up on Java integration

    @RequestMapping(value = "/sendMail", method = RequestMethod.POST)
    public void sendMail(HttpServletRequest request) {
        String name = request.getParameter("name");
        System.out.println(name);
    }

}
