/**
 * @name: Ashton Daniels
 * @date: 04/05/2024
 * JDK Version: 8
 */

package com.stride.testingchallenge.war;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
   * This Testing class will be focused on showing testing for several points in your instructions. 
   * 
   * Create JUnit tests for any constructors to validate its job (of making the class invariant true).
   * Create JUnit tests to check that all methods are called and their calls completed.
   * Create JUnit tests to see what other methods are available besides assertEquals.
   *     using a custom hamcrest library to extend the assertion capabilites into an easier to read format. 
   * Create JUnit tests for each object to validate that they each have the methods and properties that they should.
   * 
   * To go above and beyond for this test class I went ahead and made all my methodSources provide the values for the test at 
   * random so it is more dynamic and not the same tests performed each time but tests that still meet the criteria to prove
   * it passed. 
   */


public class CardTest {

  private static Random random = new Random(); 
  private static Card testCard;
  private static Card testCard2;


  @BeforeEach
  public void setup() {

    testCard = new Card();
    testCard2 = new Card('h', 12);
  }

  /**
   * Test Method to test setValue method with different random values.
   */
  private static Stream<Integer> provideRandomInt() {
    return IntStream.range(0, 20)
      .map(i -> random.nextInt(20))
      .boxed();
  }

  @DisplayName("Test SetValue Method With Random Integers.")
  @ParameterizedTest
  @MethodSource("provideRandomInt")
  public void setValueTest(int randNum) {
    testCard.setValue(randNum);

    //check to see that all numbers that are passed into setValue are handled appropriately. 
    //as shown in the debug console if you have a number greater than 13 the setValue method works as expected
    //and sets the number to 1 by default. 
    assertTrue(testCard.num <= 13);

    //checking for the inverse to ensure I have covered as much ground as possible and that no false positives happen
    //when data is passed into my method. 
    assertFalse(testCard.num > 13);

    testCard2.setValue(randNum);

    //check to see that all numbers that are passed into setValue are handled appropriately. 
    //as shown in the debug console if you have a number greater than 13 the setValue method works as expected
    //and sets the number to 1 by default. 
    assertTrue(testCard2.num <= 13);

    //checking for the inverse to ensure I have covered as much ground as possible and that no false positives happen
    //when data is passed into my method. 
    assertFalse(testCard2.num > 13);

    //print line to ensure that the numbers higher than 13 or lower than 1 are being handled appropriately.
    // System.out.println("The number sent into the method was: " + randNum + " The number it ended up being was: " + testCard.num);
  }

  /*
   * Method that will handle testing the Card Constructor which takes two arguments. 
   * I will be assigning the values randomly that will be sent into the test method.
   */
  private static Stream<Arguments> cardConstructorTest() {
    char[] suits = {'a', 'c', 'd', 's'};
    /*
    //used stream generator as it allowed me to set a limit for how many times I wanted this to run.
    I decided this way rather than having to implement a for loop or some alternative.
    */ 
    return Stream.generate(() -> {
      char randChar = suits[random.nextInt(3)];
      int randNum = random.nextInt(13);

      return Arguments.of(randChar, randNum);
    }).limit(20);
  }

  @DisplayName("Constructor Test with Random Values!")
  @ParameterizedTest
  @MethodSource("cardConstructorTest")
  public void fullConstructorTest(char suit, int num) {

    Card testCard3 = new Card(suit, num);

    //testing for equality to ensure values are being set as expected. 
    assertEquals(suit, testCard3.suit);

    assertEquals(num, testCard3.num);

    //testing the isEqual method for both equality and expected inequality.
    boolean test = testCard3.isEqual(suit, num);
    boolean falseTest = testCard3.isEqual('t', 10);

    assertTrue(test);
    assertFalse(falseTest);

    //print line to ensure the random test info is being set properly. 
    // System.out.println("Passed in Suit: " + suit + " Card Class Suit: " + testCard3.suit + " Passed in Num: " + num + " Card Class Num: " + testCard3.num);

  }

  @DisplayName("Test Constructor Properties and Property Values! ")
  @Test 
  public void propertyTest() {

    //simple test of a property for more practice
    assertThat(testCard2, hasProperty("value"));

  }
}
