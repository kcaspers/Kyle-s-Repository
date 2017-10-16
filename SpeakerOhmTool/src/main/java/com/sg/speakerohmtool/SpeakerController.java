package com.sg.speakerohmtool;

import com.sg.model.cabinet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SpeakerController {

    List<cabinet> cabinets = new ArrayList();
    ImpedanceCalculator impedanceCalculator = new ImpedanceCalculator();
    
    //these can be connected to a load
    double ampSpeakerOut1;
    double ampSpeakerOut2;
    
    public SpeakerController() {
    }

    @RequestMapping(value = "/loadPage", method = RequestMethod.GET)
    public String loadPage(Map<String, Object> model){
        
        double calculatedImpedance = impedanceCalculator.calculateImpedance(cabinets);
        model.put("calculatedImpedance", calculatedImpedance);
        
        model.put("cabinets", cabinets);
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

}
