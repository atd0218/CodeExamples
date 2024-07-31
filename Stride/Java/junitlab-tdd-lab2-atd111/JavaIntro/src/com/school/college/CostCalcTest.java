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

import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

public class CostCalcTest {

  //private static Student student1 = new Student("John Thomas", 27, 5, true); 
  private static Student student2 = new Student("Jim Thomas", 26, 7, true); 
  private static Student student3 = new Student("Jan Thomas", 28, 8, true); 
  private static Student student4 = new Student("Joan Thomas", 32, 2, true); 
  private static Student student5 = new Student("Jenny Thomas", 36, 4, true); 

  private static ArrayList<Student> students = new ArrayList<Student>();

  private static CostCalc calc = new CostCalc();

  private static Aclass aclass = new Aclass("science", students, 12);

  @BeforeAll
  static void setup() {
    students.add(student2);
    students.add(student3);
    students.add(student4);
    students.add(student5);
  }

  

  //test the overall class
  @SuppressWarnings("deprecation")
  @DisplayName("Test the Calculator Costs!")
  @Test
  public void testCalcCost() {
    students.add(student2);
    students.add(student3);
    students.add(student4);
    students.add(student5);
    int actual1 = calc.calcCost(aclass).get(student2);
    int actual2 = calc.calcCost(aclass).get(student3);
    int actual3 = calc.calcCost(aclass).get(student4);
    int actual4 = calc.calcCost(aclass).get(student5);
    assertEquals(1650, actual1);
    assertEquals(1600, actual2);
    assertEquals(1900, actual3);
    assertEquals(1800, actual4);
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

  @SuppressWarnings("deprecation")
  @DisplayName("Test the Class Size Discount")
  @Test
  public void classSizeDiscount() {
    double expected = 150.00;
    double actual = calc.classSizeDiscount(25, 1500);
    assertEquals(expected, actual, 0.05);
  }
  
}
