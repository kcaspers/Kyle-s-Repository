package com.sg.speakerohmtool;

import com.sg.model.Cabinet;
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
    List<Cabinet> cabinets = new ArrayList();
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
        
        //I should reset the numbers on the cabinets
        assignCabNumber();
        
        //double calculatedImpedance = impedanceCalculator.calculateImpedance(cabinets);
        double calculatedImpedance = impedanceCalculator.calculateImpedance(cabinets);
        model.put("calculatedImpedance", calculatedImpedance);
        impedanceCalculator.calculateSpeakerPercentage(cabinets);
        
        //calculate the best amp ohm setting for this configuration
        model.put("desiredAmpSetting", impedanceCalculator.desiredAmpSetting());
        
        model.put("cabinets", cabinets);
        model.put("cabinetsPresent", cabinetsPresent);
        return "index";
    }
    
    @RequestMapping(value = "/addCabinet", method = RequestMethod.POST)
    public String addCabinet(Map<String, Object> model, @RequestParam("speakerSelection") String speakerSelection,
        HttpServletRequest request) {
        
//        int cabNumber = 1;
//        for(Cabinet c : cabinets){
//            int highestNum = 0;
//            if(c.getCabNumber() > highestNum){
//                highestNum = c.getCabNumber();
//            }
//            cabNumber = (highestNum + 1);
//        }
        double cabOhms = Double.parseDouble(speakerSelection);
        Cabinet newCab = new Cabinet(cabOhms);
        cabinets.add(newCab);
        
        

        model.put("cabinets", cabinets);
        return "redirect: loadPage";
    }
    
    @RequestMapping(value = "/deleteCabinet", method = RequestMethod.POST)
    public String deleteCabinet(Map<String, Object> model, HttpServletRequest request){
        String stringCabNumber = request.getParameter("speakerToDelete");
        int cabNumber = Integer.parseInt(stringCabNumber);
        Cabinet cabinet = new Cabinet();
        for(Cabinet c : cabinets){
            if(cabNumber == c.getCabNumber()){
                cabinet = c;
            }
        }
        
        cabinets.remove(cabinet);
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
    
    private void assignCabNumber(){
        for(int i = 0; i < cabinets.size(); i++){
            cabinets.get(i).setCabNumber(i+1);
        }
    }
    
    

}
