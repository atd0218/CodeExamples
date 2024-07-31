/**
 * @name Ashton Daniels
 * Date: 02/20/2024
 * JDK Version: 8
 * 
 * Description:
 * 
 * Created a calculator with four different files and used the Math java class to complete my calculations. 
 * 
 * BasicCalculator.java
 * 
 * Handles calculating the basic operations of add, subtract, multiply, and divide
 * 
 * For the basic arithmetic operations (add, subtract, multiply, divide), the time complexity is O(1) because 
 * these operations involve simple mathematical calculations that can be done in constant time.
 * 
 * because the class does not use any additional data structures that grow with the size of the input. The class 
 * only uses a constant amount of memory to store the input parameters and perform the calculations.
 * 
 * ScientificCalculator.java
 * 
 * This file will allow you to find an exponent of a number as well as find the log of a number. 
 * 
 * Overall, the time complexity of all methods in the 'ScientificCalculator' class is O(1) because 
 * they all perform a constant number of operations regardless of the input size.

 * The space complexity of the 'ScientificCalculator' class is O(1) because it only uses a constant amount of memory 
 * to store a single class variable 'holdValue' regardless of the input size.
 * 
 * TrigonometricCalculator.java
 * 
 * This file will handle sine, cosine, tangent, arcsine, arccosine, arctangent. 
 * 
 * The time complexity of each trigonometric function (sine, cosine, tangent, arcsine, arccosine, arctangent) in the 
 * TrigonometricCalculator class is O(1). This is because each function simply calls the corresponding Math library function 
 * (sin, cos, tan, asin, acos, atan) which have constant time complexity for calculating the trigonometric values.
 * 
 * The space complexity of each function is also O(1) because they do not use any additional data structures that grow with the 
 * input size. Each function only requires a constant amount of memory to store the input value and the result.
 * 
 * CalculatorDriver.java
 * 
 * Contains the main method which will instantiate the other classes and perform operations to test them. 
 * 
 * Time complexity is O(1) as there will be a constant amount of work done at each step in this class. 
 * 
 * In terms of space complexity, the CalculatorDriver class does not use any additional data structures that 
 * would significantly impact space complexity. The space complexity is O(1) as the amount of memory used does 
 * not increase with the size of the input.
 */

package com.javaoo.calculators;

public class CalculatorDriver {

    public static void main(String[] args) {
        BasicCalculator bc = new BasicCalculator();
        ScientificCalculator sc = new ScientificCalculator();
        TrigonometricCalculator tc = new TrigonometricCalculator();

        System.out.println("Basic Calculator Operations: ");
        System.out.println("Add: " + bc.add(25, 25));
        System.out.println("Subtract: " + bc.subtract(50, 25));
        System.out.println("Divide: " + bc.divide(12, 4));
        System.out.println("Multiply: " + bc.multiply(25, 25));

        System.out.println("\nScientific Calculator Operations: ");
        System.out.println("Exponent test: " + sc.exp(4));
        System.out.println("Log Test: " + sc.log(25));

        System.out.println("\nTrigonometric Calculator Operations: ");
        System.out.println("Sine: " + (tc.sine(.523)));
        System.out.println("Cosine: " + (tc.cosine(.523)));
        System.out.println("Tangent: " + (tc.tangent(.523)));
        System.out.println("ArcSine: " + Math.toDegrees(tc.arcsine(0.5)));
        System.out.println("ArcCosine: " + Math.toDegrees(tc.arccosine(0.5)));
        System.out.println("ArcTangent: " + Math.toDegrees(tc.arctangent(1)));
        
    }
    
}
