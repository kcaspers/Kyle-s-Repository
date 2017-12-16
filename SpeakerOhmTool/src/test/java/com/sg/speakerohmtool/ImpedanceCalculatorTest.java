/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.speakerohmtool;

import com.sg.model.Cabinet;
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
        Cabinet cabA = new Cabinet(16.00);
        Cabinet cabB = new Cabinet(8.00);
        
    }

    /**
     * Test of calculateSpeakerPercentage method, of class ImpedanceCalculator.
     */
    @Test
    public void testCalculateSpeakerPercentage() {
    }

    /**
     * Test of desiredAmpSetting method, of class ImpedanceCalculator.
     */
    @Test
    public void testDesiredAmpSetting() {
    }
    
}
