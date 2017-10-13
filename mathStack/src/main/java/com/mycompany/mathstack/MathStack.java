/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mathstack;

import java.util.Stack;

/**
 *
 * @author kylecaaspers
 */
public class MathStack {
    
    //this is a little method I made that processes numbers like this:
    // 4 5 8 / *
    //it will grab the last digit and the one preceding it and process it according to the first fuunction
    //return the resulting number and repeat the process for the next number/function pair
    //until we have just a value remaining
    
    public int mathStack(String string) {
        int result = 0;
        int currentResult = 0;
        String[] stackString = string.split(" ");
        Stack<Integer> numberStack = new Stack();
        for (String s : stackString) {
            if (!s.contains("+") && !s.contains("-") && !s.contains("*") && !s.contains("/") && !s.contains(" ")){  //i.e. is a number
                int currentInt = Integer.parseInt(s);
                numberStack.push(currentInt);
            }//else if(s.equals(" ")){   //if it is extra whitespace
                
            //}
            else{   //if its an operation
                int secondNumber = numberStack.pop();
                int firstNumber = numberStack.pop();
                if(s.equals("+")){
                    currentResult = secondNumber + firstNumber;
                }
                if(s.equals("-")){
                    currentResult = secondNumber - firstNumber;
                }
                if(s.equals("*")){
                    currentResult = secondNumber * firstNumber;
                }
                if(s.equals("/")){
                    currentResult =  firstNumber / secondNumber;
                }
                numberStack.push(currentResult);
            }
        }
        result = numberStack.pop();
        return result;
    }
}
    
    


