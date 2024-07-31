/**
 * Author: Ashton Daniels
 * FileName: BasicCalculator.Java
 * Description: Class to hold basic calculation information. 
 */

package com.javaoo.calculators;

/**
 * Basic Calculator class that will contain methods to perform
 * basic calculations.
 */
public class BasicCalculator {

    //add method that takes two numbers and adds them together
    public double add (double x, double y) {
        return x + y;
    }

    //subtract method that takes two numbers and subtracts them
    public double subtract (double x, double y) {
        return x - y;
    }

    //multiplication method that takes two numbers and multiplies them 
    public double multiply (double x, double y) {
        return x * y;
    }

    //division method that takes two numbers and divides them 
    public double divide (double x, double y) {
        if (x > y) {
            return x / y;
        }
        else {
            return y / x;
        }
        
    }
    
}
