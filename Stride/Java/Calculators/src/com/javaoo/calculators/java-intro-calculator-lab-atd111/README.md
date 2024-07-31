[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/huNulsFt)
# Instructions

## Objective

This lab will introduce the first of the larger systems that you will be programming. This system is a set of classes that perform basic math-oriented calculations. You will utilize the existing Java and Math class functionality to implement these classes and it will provide practice in creating classes, methods and attributes.

## Overview

In this lab you will:

    Create the necessary packages

    Create the necessary calculator classes

    Add the appropriate attributes

    Provide the appropriate method definitions (minus the actual code to make them work at this point)

__Create a Java Project and Packagee__

1. Create a new Java Project within the same workspace named Calculators.

2. Create a package named com.javaoo.calculators.

__Exercise 1: Creating the Calculator classes__

3. Create each of the following classes in the com.javaoo.calculators package:

    a. BasicCalculator
  
    b. ScientificCalculator
  
    c. TrigonometricCalculator 

__Exercise 2: Adding Attributes and Methods__

4. For each of the calculator classes, add the necessary attributes and methods as specified below.

5. __BasicCalculator__

    a. Declare the following public methods

        i. add()

        ii. subtract()

        iii. multiply()

        iv. divide() 

    b. Each method must accept two parameters, both of type double

    c. Each method must return a double. Add code to the return statement that calculates and returns the correct value. Example: 

    ```java
        public final double multiply( double x, double y) {
          return x * y;
        }
    ```

6. __ScientificCalculator__

    d. Must declare the following attributes:

        i.  A double named PI to hold the value of PI (3.14159). This attribute will be shared by all instances of the class and will be constant so it should be declared as static and final. Since it is a final, it can have public visibility so that anyone can use it.

        ii. A double named holdValue to hold a value in memory. Since it will be managed within the ScientificCalculator class, it should have private visibility.

    e. Declare the following public methods

        i. exp() which has one parameter of type double and  returns a double [This method will be used to calculate e^x]

        ii. log() which has one parameter of type double and returns a double [This method will be used to calculate ln x]

        iii. putValueInMemory() which has one parameter of type double and returns a void.  Implement this method.

        iv. getValueFromMemory() which has no parameters and returns a double  Implement this method

    f. We will not provide the details of each method in this lab. In order for your code to compile, add the following single statement to each method block that returns a double:
    ```java
      return (0);
    ```

7. __TrigonometricCalculator__

    g. Declare the following public methods

        i. sine()
    
        ii. cosine()
    
        iii. tangent()
    
        iv. arcsine()
    
        v. arccosine()
    
        vi. arctangent()

    h. Each method must accept one parameter of type double

      i. Each method must return a double
    
      j. We will not provide the details of each method in this lab. For your code to compile, add the following single statement to each method block:
      ```java
         return (0);
      ```

8. __BRAIN TEASER: Could and should  any of these classes or  methods be made static?__

9. Commit your code to GitHub classroom (link is provided in Canvas)
