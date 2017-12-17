/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.speakerohmtool;

import com.sg.model.Cabinet;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

/**
 *
 * @author kylecaaspers
 */
public class ImpedanceCalculator {
    //we will be calling the methods here in the controller class to send the data up to the view
    double calculatedImpedance;
    DecimalFormat df = new DecimalFormat("##.###");
    
    //how do I handle more than two amps, a single amp
    public double calculateImpedance(List<Cabinet> cabinets){
        df.setRoundingMode(RoundingMode.HALF_UP);
        
        double speakerLoads = 0;
        //double calculatedImpedance;
        
        
        for(Cabinet c : cabinets){
            speakerLoads += 1/(c.getImpedance());
            //this.speakerLoads += 1;
        }
        
        //calculatedImpedance = 1/(1/s1Imp + 1/s2Imp + 1/s3Imp);
        this.calculatedImpedance = 1/(speakerLoads);
        return (Math.round(calculatedImpedance * 100.0))/100.0;
    }
    
    //I should also make a method that calculates the resulting output for each speaker
    public void calculateSpeakerPercentage(List<Cabinet> cabinets){
        df.setRoundingMode(RoundingMode.HALF_UP);
         //impedance = Double.parseDouble(calculateImpedance(cabinets));
        double voltage = Math.sqrt(this.calculatedImpedance * 100);
        for(Cabinet c : cabinets){
           double output = (voltage * voltage)/c.getImpedance();
           output = Math.round(output * 100);
           output = output/100;
           c.setOutputPercentage(output);
        }
    }
    
    //the desired amp setting should be lower than calculated impedance if it can't be equal
    public int desiredAmpSetting(){
        int ampSetting = 0;
        if(this.calculatedImpedance >= 16){
            //set to 16
            ampSetting = 16;
        } else if(this.calculatedImpedance >= 8){
            //set to 8
            ampSetting = 8;
        } else if(this.calculatedImpedance >= 4){
            //set to 4
            ampSetting = 4;
        } else{
            //it is lower than 4, not ideal, but have the amp set to 4ohms
            ampSetting = 4;
        }
        return ampSetting;
    }

    public double getCalculatedImpedance() {
        return calculatedImpedance;
    }

    public void setCalculatedImpedance(double calculatedImpedance) {
        this.calculatedImpedance = calculatedImpedance;
    }
    
    
}
