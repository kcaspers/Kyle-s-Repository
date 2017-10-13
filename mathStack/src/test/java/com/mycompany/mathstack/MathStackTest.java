/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mathstack;

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
public class MathStackTest {
    
    MathStack mathStack = new MathStack();
    
    public MathStackTest() {
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
     * Test of mathStack method, of class MathStack.
     */
    @Test
    public void testMathStack() {
        int expected = 4;
        //mathStack.mathStack("2 2 3 * +");
        int expected2 = 8;
        assertEquals(expected, mathStack.mathStack("2 2 *"));
        assertEquals(expected2, mathStack.mathStack("2 2 3 * +"));
        
        
    }
    
    @Test
    public void testMathStack2() {
        // 3 3 2 9 + + -  = 11
        int expected = 11;
        assertEquals(expected, mathStack.mathStack("3 3 2 9 + + -"));
        
    }
    
    @Test
    public void testMathStack3() {
        // 80 10 2 / /
        int expected = 16;
        assertEquals(expected, mathStack.mathStack("80 10 2 / /"));
        
    }
    
    @Test
    public void testMathStack4() {
        // 100 5 6 2 / + * 
        int expected = 800;
        assertEquals(expected, mathStack.mathStack("100 5 6 2 / + *"));
        
    }
    
}
