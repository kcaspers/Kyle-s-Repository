/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.greathealthwebsite;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import com.sendgrid.*;
import java.io.IOException;
import org.springframework.ui.Model;

/**
 *
 * @author kylecaaspers
 */
@Controller
public class FormSubmit {

    //look into using sendgrid for emails, read up on Java integration
    @RequestMapping(value = "/sendMail", method = RequestMethod.POST)
    public String sendMail(HttpServletRequest request, Model model) throws IOException {
        //we will need:
        //from, subject, to, content, mail(made up of these)
        Email from = new Email(request.getParameter("email"));
        Email to = new Email("k.caspers@comcast.net");
        String name = request.getParameter("name");
        String lastname = request.getParameter("surname");

        //contstruct content value
        String contentValue = new String();
        contentValue = name + "\n"
                + lastname + "\n"
                + request.getParameter("message");
        Content content = new Content("text/plain", contentValue);

        Mail mail = new Mail(from, "greathealthnutrition.com", to, content);


        SendGrid sg = new SendGrid(System.getenv(""));
        
        Request mailRequest = new Request();
        try {
            mailRequest.setMethod(Method.POST);
            mailRequest.setEndpoint("mail/send");
            mailRequest.setBody(mail.build());
            Response response = sg.api(mailRequest);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException e) {
            throw e;
        }
        model.addAttribute("messageSuccess", true);
        return "contact";
    }

}
