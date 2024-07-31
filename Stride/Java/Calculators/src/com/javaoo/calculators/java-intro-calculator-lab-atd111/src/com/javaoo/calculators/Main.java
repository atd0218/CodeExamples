/**
 * Author: Ashton Daniels
 * FileName: Main.Java
 * Description: Main Class to call and test the basic Calculator function as that is all that is required for this lab.  
 */

package com.javaoo.calculators;

public class Main {

    public static void main (String[] args) {
        //create an instance/object of my BasicCalculator class. 
        BasicCalculator calc = new BasicCalculator();

        //create attributes to hold the value of each basic calculator operation. 
        double add = calc.add(2, 5);
        double subtract = calc.subtract(10, 2);
        double multiply = calc.multiply(10, 10);
        double divide = calc.divide(1000 , 10);

        //print out all attributes to the console to show that the tests worked as expected. 
        System.out.println(add);
        System.out.println(subtract);
        System.out.println(multiply);
        System.out.println(divide);
    }
    
}
