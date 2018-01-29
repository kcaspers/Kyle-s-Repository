package com.sg.greathealthwebsite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GHController {
    
    @RequestMapping(value="/sayhi", method=RequestMethod.GET)
    public String sayHi(Map<String, Object> model) {
        model.put("message", "Hello from the controller" );
        return "hello";
    }
    
    @RequestMapping(value = "/robots.txt", method=RequestMethod.GET)
    @ResponseBody
    public String getRobots(HttpServletRequest request) throws IOException{
        Path filePath = Paths.get("src/main/webapp/robots.txt");
        InputStream in = null;
        StringBuffer cBuf = new StringBuffer();
        
        try{
            in = new FileInputStream("/Users/kylecaaspers/Desktop/Repos/Kyle-s-Repository/GreatHealthWebsite/src/main/webapp/robots.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            
            while ((line = reader.readLine()) != null){
                System.out.println(line);
                cBuf.append("\n");
                cBuf.append(line);
            }
        } catch(IOException e){
            System.out.println(e.getMessage());
        } finally {
            if (in != null) in.close();
        }
        
        return cBuf.toString();
    }
    
    
}
