/**
 * @name: Ashton Daniels
 * @date: 04/03/2024
 * JDK Version: 8 
 * Description: 
 * 
 * CostCalcTest.java
 * 
 * This class was created to perform JUnit testing for my CostCalc.java class. 
 * 
 * During this I make sure to test each method in the class to ensure they are all working properly 
 * and I was able to get the expected results to match the actual and can confirm all tests pass. 
 * 
 * Student.java
 * 
 * This class was made to represent the creation of a student in the system with some variables such as name, age, how many 
 * years a student, are you a resident, and how many weeks is the class you want to take. 
 * 
 * Then I created a constructor to properly intiialize all of the variables when an instance of Student is created. 
 * 
 * Additionally, I created getters and setters for each variable above. 
 * 
 * CostCalc.java
 * 
 * This class was made to represent a calculator that will inform you of the costs you should expect for the class you want
 * to take. 
 * 
 * I created several methods in this class
 * 
 * CalcCost
 * 
 * will calculate the cost that the student owes for the class and return that cost to them. 
 * 
 * weeksCost
 * 
 * will calclulate how much should be added to the total based on how long the class is.
 * 
 * residentCost
 * 
 * will look at their resident status and add a cost for not being a resident if that is the case. 
 * 
 * yearsDiscount
 * 
 * Will calculate a discount for the student based on how many years they have been enrolled. 
 */

package com.school.college;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class CostCalcTest {
  private static Student student = new Student("John Thomas", 27, 5, true, 4); ;
  private static CostCalc calc = new CostCalc();

  //test the overall class
  @DisplayName("Test the Calculator Costs!")
  @Test
  public void testCalcCost() {
    int expected = 450;
    int actual = calc.calcCost(student);
    assertEquals(expected,actual);
  }

  //test the class methods specifically
  @DisplayName("Test the Weeks Cost Calculator!")
  @Test
  public void weeksCostTest() {
    int expected = 2000;
    int actual = calc.weeksCost(12);
    assertEquals(expected, actual);
  }

  @DisplayName("Test the Resident Cost Calculator!")
  @Test
  public void residentPriceTest() {
    int expected = 70;
    int actual = calc.residentCost(700, false);
    assertEquals(expected, actual);
  }

  @DisplayName("Test the Years Discount!")
  @Test
  public void yearsDisTest() {
    int expected = 150;
    int actual = calc.yearsDiscount(3);
    assertEquals(expected, actual);
  }
  
}
