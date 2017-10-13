/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.view;

import java.util.Scanner;

/**
 *
 * @author kylecaaspers
 */
public class UserIOConsoleImpl implements UserIO {

    Scanner sc = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);        //I guess this only prints strings

    }

    @Override
    public double readDouble(String prompt) {

        print(prompt);      //this method starts by asking them for input
        String input = sc.nextLine();
        return Double.parseDouble(input);

    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        do {
            print(prompt);
            String input = sc.nextLine();
            double d = Double.parseDouble(input);

            if (d >= min && d <= max) {
                return d;
            } else {
                System.out.println("Invalid input, enter a value from " + min + " to " + max);
            }
        } while (true);

    }

    @Override
    public float readFloat(String prompt) {
        print(prompt);      //again, it asks the user what the main asks it to
        String input = sc.nextLine();
        return Float.parseFloat(input);
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        do {
            print(prompt);
            String input = sc.nextLine();
            float f = Float.parseFloat(input);
            if (f >= min && f <= max) {
                return f;
            } else {
                System.out.println("Invalid input, enter a value from " + min + " to " + max);
            }
        } while (true);  //this doesnt catch input that cand be converted into float values, just ones out of range
    }

    @Override
    public int readInt(String prompt) {
        print(prompt);  //prints what we ask them
        String input = sc.nextLine();
        return Integer.parseInt(input);

        //I really should make something that tests for valid input
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        String input;
        do {
            do {
                print(prompt);
                input = sc.nextLine();
            } while(!isNumeric(input));
            int i = Integer.parseInt(input);
            if (i >= min && i <= max) {
                return i;
            } else {
                System.out.println("Invalid input, enter a value from " + min + " to " + max);
            }
        } while (true);

    }

    @Override
    public long readLong(String prompt) {

        print(prompt);
        String input = sc.nextLine();
        return Long.parseLong(input);

        //longs need to be a whole number, should test for that
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        do {
            print(prompt);
            String input = sc.nextLine();
            long l = Long.parseLong(input);
            if (l >= min && l <= max) {
                return l;
            } else {
                System.out.println("Invalid input, enter a value from " + min + " to " + max);
            }
        } while (true);
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        String input = sc.nextLine();
        return input;
    }

    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                return false;
            } else if(Character.isWhitespace(c)){
                return false;
            }
        }
        return true;
    }
}
