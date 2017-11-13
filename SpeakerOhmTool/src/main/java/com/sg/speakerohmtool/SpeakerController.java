package com.sg.speakerohmtool;

import com.sg.model.cabinet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@CrossOrigin
@Controller
public class SpeakerController {

    boolean cabinetsPresent = false;
    List<cabinet> cabinets = new ArrayList();
    ImpedanceCalculator impedanceCalculator = new ImpedanceCalculator();
    
    //these can be connected to a load
    double ampSpeakerOut1;
    double ampSpeakerOut2;
    
    public SpeakerController() {
    }

    @RequestMapping(value = "/loadPage", method = RequestMethod.GET)
    public String loadPage(Map<String, Object> model){
        
        if(cabinets.size() > 0){
            cabinetsPresent = true;
        } else{
            cabinetsPresent = false;
        }
        
        //double calculatedImpedance = impedanceCalculator.calculateImpedance(cabinets);
        String calculatedImpedance = impedanceCalculator.calculateImpedance(cabinets);
        model.put("calculatedImpedance", calculatedImpedance);
        
        model.put("cabinets", cabinets);
        model.put("cabinetsPresent", cabinetsPresent);
        return "index";
    }
    
    @RequestMapping(value = "/addCabinet", method = RequestMethod.POST)
    public String addCabinet(Map<String, Object> model, @RequestParam("speakerSelection") String speakerSelection,
        HttpServletRequest request) {

        double cabOhms = Double.parseDouble(speakerSelection);
        
        //int cabNumber = (cabinets.size()) + 1;
        int cabNumber = 1;
        for(cabinet c : cabinets){
            int highestNum = 0;
            if(c.getCabNumber() > highestNum){
                highestNum = c.getCabNumber();
            }
            cabNumber = (highestNum + 1);
        }
        cabinet newCab = new cabinet(cabOhms, cabNumber);
        
        cabinets.add(newCab);

        model.put("cabinets", cabinets);
        return "redirect: loadPage";
    }
    
    @RequestMapping(value = "/deleteCabinet", method = RequestMethod.POST)
    public String deleteCabinet(Map<String, Object> model, HttpServletRequest request){
        String stringCabNumber = request.getParameter("speakerToDelete");
        int cabNumber = Integer.parseInt(stringCabNumber);
        cabinet Cabinet = new cabinet();
        for(cabinet c : cabinets){
            if(cabNumber == c.getCabNumber()){
                Cabinet = c;
            }
        }
        
        cabinets.remove(Cabinet);
        return "redirect: loadPage";
    }
    
    
    @RequestMapping(value = "/selectAmpOhm/{ampOhm}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String selectAmpOhm(Map<String, Object> model, @PathVariable("ampOhm") String ampOhm){
        
//        switch (ampOhm){
//            case "4":
//                //model.put("checked4", "checked");
//                model.put("ampOhm", 4);
//                break;
//            case "8":
//                //model.put("checked8", "checked");
//                model.put("ampOhm", 8);
//                break;
//            case "16":
//                //model.put("checked16", "checked");
//                model.put("ampOhm", 16);
//                break;
//        }
//        
//        return "redirect: loadPage";

          return null; //for now, this kind of method can't redirect, maybe try altering a class-level variable
    }
    
    

}