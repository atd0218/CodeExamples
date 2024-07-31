
/**
 * @name: Ashton Daniels
 * @date: 04/05/2024
 * JDK Version: 8
 */

package com.stride.testingchallenge.basicschallenge;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasLength;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;  

public class BankTellerTest {

  /**
   * This Testing class will be focused on showing testing for several points in your instructions. 
   * 
   * Create JUnit tests to check that all methods are called and their calls completed.
   * Create JUnit tests to see what other methods are available besides assertEquals.
   *     using a custom hamcrest library to extend the assertion capabilites into an easier to read format. 
   */

  private  BankTeller tellerTest;

  @BeforeEach
   void setup() {
    tellerTest = new BankTeller();
    
  }

  @SuppressWarnings("deprecation")
  @DisplayName("Deposit Money Test!")
  @Test
  public void DepositMoneyTest() {
      double expected = 3000.00;
      double expectedFalse = 2950.00;
      tellerTest.depositMoney(500.00);
      double actual = tellerTest.getTotal();
      assertEquals(expected, actual, 0.05);
      //test expected false to ensure I am not getting incorrect applications. 
      //assertEquals(expectedFalse, actual, 0.05);
      
    
  }

  @SuppressWarnings("deprecation")
  @DisplayName("Withdraw Money Test!")
  @Test
  public void WithdrawMoneyTest() {
      double expected = 2000.00;
      double expectedFalse = 2250.00;
      tellerTest.withdrawMoney(500.00);
      double actual = tellerTest.getTotal();
      assertEquals(expected, actual, 0.05);
      //test expected false to ensure I am not getting incorrect applications. 
      //assertEquals(expectedFalse, actual, 0.05);
  }

  @DisplayName("Test Name ArrayList Name Addition and Retreival!")
  @Test
  public void ArrayListtest() {
    tellerTest.addName("Hunter");
    tellerTest.addName("Joey");
    tellerTest.addName("bro");

    String expected = "Hunter";
    String expectedFail = "bro";

    //check that the first item in the list is Hunter
    assertThat(expected, equalTo(tellerTest.getName().get(0)));

    //expected fail
    //assertThat(expectedFail, equalTo(tellerTest.getName().get(0)));

    //check that the arrayList has a string in it. 
    assertThat(tellerTest.getName().get(1), containsString("Joey"));

    //expected fail
    // assertThat(tellerTest.getName().get(1), containsString("Hunter"));


  }

  @DisplayName("Test Name ArrayList Size!")
  @Test
  public void ArrayListSizetest() {
    tellerTest.addName("Hunter");
    tellerTest.addName("Joey");
    tellerTest.addName("bro");

    //check the size of the array that 
    assertThat(tellerTest.getName(), hasSize(3));

    //expected fail
    // assertThat(tellerTest.getName(), hasSize(7));

    //check the length of characters of one entry to ensure they match 
    assertThat(tellerTest.getName().get(2), hasLength(3));

    //expected fail 
    // assertThat(tellerTest.getName().get(2), hasLength(2));
  }

  @DisplayName("Test Transaction ArrayList Transaction Addition and Retreival!")
  @Test
  public void TransactionArrayListtest() {
    tellerTest.addTransaction("Deposit $250.00");
    tellerTest.addTransaction("Withdraw $500.00");
    tellerTest.addTransaction("Deposit $945.00");
    tellerTest.addTransaction("Withdraw $100.00");
    tellerTest.addTransaction("Deposit $780.00");
    tellerTest.addTransaction("Withdraw $1000.00");
  

    String expected = "Deposit $250.00";

    String expFail = "test";

    //check that the first item in the list is Hunter
    assertThat(expected, equalTo(tellerTest.getTransaction().get(0)));

    //expected fail
    // assertThat(expFail, equalTo(tellerTest.getTransaction().get(0)));

    //check that the arrayList has a string in it. 
    assertThat(tellerTest.getTransaction().get(1), containsString("$500.00"));

    //expected fail
    // assertThat(tellerTest.getTransaction().get(1), containsString("Deposit"));


  }

  @DisplayName("Test Transaction ArrayList Size and Item Size!")
  @Test
  public void TransactionArrayListSizetest() {
    tellerTest.addTransaction("Deposit $250.00");

    //check the size of the array that 
    assertThat(tellerTest.getTransaction(), hasSize(1));

    //expected fail
    // assertThat(tellerTest.getTransaction(), hasSize(7));

    //check the length of characters of one entry to ensure they match 
    assertThat(tellerTest.getTransaction().get(0), hasLength(15));

    //expected fail 
    // assertThat(tellerTest.getTransaction().get(0), hasLength(2));
  }
  

}
