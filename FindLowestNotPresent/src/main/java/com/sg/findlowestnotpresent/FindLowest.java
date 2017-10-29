/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.findlowestnotpresent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author kylecaaspers
 */
public class FindLowest {

    public static void main(String[] args) {
        int[] array = {0, 1, 2, 4, 5, 11};
        
        System.out.println(findLowestIntNotPresent(array));
        
    }
    
    public static int findLowestIntNotPresent(int[] array) {
        int result = 0;
        int highestVal = 0;
        int lowestVal;
        //first find the max value of the array
        for(int i : array){
            if(i > highestVal){
                highestVal = i;
            }
        }
        //add every value to notPresent, we will edit this is a bit
        List<Integer> notPresent = new ArrayList<>();
        for(int i = 0; i < highestVal; i++){
            notPresent.add(i);
        }
        //subtract every int from our array so we are left with the non-present ints
        for(int i : array){
            notPresent.remove(Integer.valueOf(i));
        }
        
        //find the lowest value using the inverse of the logic we used earlier
        lowestVal = highestVal;
        for(int v : notPresent){
            if(v < lowestVal){
                lowestVal = v;
            }
        }
        return lowestVal;
    }
    
}
