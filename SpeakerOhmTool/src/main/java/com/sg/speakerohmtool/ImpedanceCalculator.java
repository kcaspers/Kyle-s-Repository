/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.speakerohmtool;

import com.sg.model.cabinet;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

/**
 *
 * @author kylecaaspers
 */
public class ImpedanceCalculator {
    //we will be calling the methods here in the controller class to send the data up to the view

    
    
    //how do I handle more than two amps, a single amp
    public String calculateImpedance(List<cabinet> cabinets){
        DecimalFormat df = new DecimalFormat("##.###");
        df.setRoundingMode(RoundingMode.HALF_UP);
        
        double speakerLoads = 0;
        double calculatedImpedance;
        
        
        for(cabinet c : cabinets){
            speakerLoads += 1/(c.getImpedance());
            //this.speakerLoads += 1;
        }
        
        //calculatedImpedance = 1/(1/s1Imp + 1/s2Imp + 1/s3Imp);
        calculatedImpedance = 1/(speakerLoads);
        
        return df.format(calculatedImpedance);
        
    }
}
