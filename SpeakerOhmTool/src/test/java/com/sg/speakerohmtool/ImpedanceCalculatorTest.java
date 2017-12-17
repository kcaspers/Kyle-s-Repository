/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.speakerohmtool;

import com.sg.model.Cabinet;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kylecaaspers
 */
public class ImpedanceCalculatorTest {
    
    ImpedanceCalculator impedanceCalculator = new ImpedanceCalculator();
    
    public ImpedanceCalculatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of calculateImpedance method, of class ImpedanceCalculator.
     */
    @Test
    public void testCalculateImpedance() {
        List<Cabinet> cabinets = new ArrayList<>();
        Cabinet cabA = new Cabinet(16.00);
        Cabinet cabB = new Cabinet(8.00);
        cabinets.add(cabA);
        cabinets.add(cabB);
        
        Double impedance = impedanceCalculator.calculateImpedance(cabinets);
        Double expected = 5.33;
        assertEquals(impedance, expected);
        
        cabinets.get(0).setImpedance(4.00);
        cabinets.get(1).setImpedance(4.00);
        impedance = impedanceCalculator.calculateImpedance(cabinets);
        expected = 2.00;
        assertEquals(impedance, expected);
        
        cabinets.get(0).setImpedance(8.00);
        cabinets.get(1).setImpedance(4.00);
        impedance = impedanceCalculator.calculateImpedance(cabinets);
        expected = 2.67;
        assertEquals(impedance, expected);
        
    }

    /**
     * Test of calculateSpeakerPercentage method, of class ImpedanceCalculator.
     */
    @Test
    public void testCalculateSpeakerPercentage() {
        List<Cabinet> cabinets = new ArrayList<>();
        Cabinet cabA = new Cabinet(16.00, 1);
        Cabinet cabB = new Cabinet(8.00, 2);
        cabinets.add(cabA);
        cabinets.add(cabB);
        
        //
        impedanceCalculator.calculateSpeakerPercentage(cabinets);
        double cabAExpected = 33.33;
        assertEquals(cabAExpected, cabA.getOutputPercentage(), 0.0);
        
        double cabBExpected = 66.67;
        assertEquals(cabBExpected, cabB.getOutputPercentage(), 0.0);
        
    }

    /**
     * Test of desiredAmpSetting method, of class ImpedanceCalculator.
     */
    @Test
    public void testDesiredAmpSetting() {
    }
    
}
