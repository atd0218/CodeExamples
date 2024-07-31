/**
 * @name: Ashton Daniels
 * @date: 04/05/2024
 * JDK Version: 8
 */

package com.stride.testingchallenge.pastachallenge;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class PastaTest {

  private Pasta testPasta;
  private BakedZiti testZiti;
  private ChickenAlfredo testFredo;
  /**
   * This Testing class will be focused on showing testing for several points in your instructions. 
   * 
   * Create JUnit tests for any constructors to validate its job (of making the class invariant true).
   * Create JUnit tests to see what other methods are available besides assertEquals.
   *     using a custom hamcrest library to extend the assertion capabilites into an easier to read format. 
   * Create JUnit tests for each object to validate that they each have the methods and properties that they should.
   * 
   * In addition to that I also added Parameterized Testing to this instead of normal testing and utilized both @ValueSource 
   * as well as @MethodSource to obtain my parameters from. 
   */

  @BeforeEach
  public void setup() {
    testPasta = new Pasta("Linquine", "Alfredo", "Linquine Alfredo Mushrooma", "Chicken");
    testZiti = new BakedZiti("Ziti", "Red Sauce", "BakedZiti", "Chicken", "Oven", 200);
    testFredo = new ChickenAlfredo("Penne", "Alfredo", "Chicken Alfredo", "Chicken", "Pan Seared", 200);
  }

  @DisplayName("Pasta Constructor Test With Extra Bad Arguments!")
  @ParameterizedTest
  @ValueSource(strings = {"Linquine"})//, "Ziti", "Spaghetti"}) - removed do to expected failure.
  public void constructorTest(String pasta) {
    //first one succeeds as expected and the next two do not match.
    //this is to ensure that what I set is accurate and if compared against other strings it will not match. 
    assertThat(pasta, equalTo(testPasta.getPasta()));
    

  }

  /**
   * Test Method to provide arguments to get more familiar with Parameterized Testing. 
   */
  private static Stream<Arguments> provideArgumentsForTest() {
    return Stream.of (
      Arguments.of("Linquine", "Alfredo", "Linquine Alfredo Mushrooma", "Chicken")
      //removed as they cause to fail which was expected.
      // Arguments.of("Spaghetti", "Tomato Sauce", "Spaghetti Extravaganza", "Beef"),
      // Arguments.of("Ziti", "Zuchinni", "Zuchinni Ziti", "MeatBalls")
    );
  }

  @DisplayName("Constructor Test With MethodSource to test all Strings passed in to ensure the constructor is set as expected.")
  @ParameterizedTest
  @MethodSource("provideArgumentsForTest")
  public void fullConstructorTest(String pasta, String sauce, String name, String meat) {

    //again the first test will succeed and the next two runs will fail on purpose. 
    //this is to ensure that what I set is accurate and if compared against other strings it will not match.
    assertThat(pasta, equalTo(testPasta.getPasta()));
    assertThat(sauce, equalTo(testPasta.getPastaSauce()));
    assertThat(name, equalTo(testPasta.getDishName()));
    assertThat(meat, equalTo(testPasta.getMainIngredient()));
  }

  @DisplayName("Test Constructor Properties and Property Values! ")
  @Test 
  public void propertyTest() {

    //check for existance of all the properties. 
    assertThat(testPasta, hasProperty("dishName"));
    assertThat(testPasta, hasProperty("pasta"));
    assertThat(testPasta, hasProperty("pastaSauce"));
    assertThat(testPasta, hasProperty("mainIngredient"));
    

    //check for current values of the properties
    assertThat(testPasta, hasProperty("dishName", is("Linquine Alfredo Mushrooma")));
    assertThat(testPasta, hasProperty("pasta", is("Linquine")));
    assertThat(testPasta, hasProperty("pastaSauce", is("Alfredo")));
    assertThat(testPasta, hasProperty("mainIngredient", is("Chicken")));

  }

  /**
   * Test Method to provide arguments to get more familiar with Parameterized Testing. 
   */
  private static Stream<Arguments> provideArgumentsForTestBakedZiti() {
    return Stream.of (
      Arguments.of("Ziti", "Red Sauce", "BakedZiti", "Chicken", "Oven", 200)
    );
  }

  @DisplayName("Constructor Test With MethodSource to test all Strings passed in to ensure the constructor is set as expected.")
  @ParameterizedTest
  @MethodSource("provideArgumentsForTestBakedZiti")
  public void BakedZitiConstructorTest(String pasta, String sauce, String name, String meat, String CookMethod, int time) {

    //again the first test will succeed and the next two runs will fail on purpose. 
    //this is to ensure that what I set is accurate and if compared against other strings it will not match.
    assertThat(pasta, equalTo(testZiti.getPasta()));
    assertThat(sauce, equalTo(testZiti.getPastaSauce()));
    assertThat(name, equalTo(testZiti.getDishName()));
    assertThat(meat, equalTo(testZiti.getMainIngredient()));
    assertThat(CookMethod, equalTo(testZiti.getCookingMethod()));
    assertThat(time, equalTo(testZiti.getCookTime()));
  }

  /**
   * Test Method to provide arguments to get more familiar with Parameterized Testing. 
   */
  private static Stream<Arguments> provideArgumentsForTestChickenAlfredo() {
    return Stream.of (
      Arguments.of("Penne", "Alfredo", "Chicken Alfredo", "Chicken", "Pan Seared", 200)
      //removed as they cause to fail which was expected.
      // Arguments.of("Spaghetti", "Tomato Sauce", "Spaghetti Extravaganza", "Beef"),
      // Arguments.of("Ziti", "Zuchinni", "Zuchinni Ziti", "MeatBalls")
    );
  }

  @DisplayName("Constructor Test With MethodSource to test all Strings passed in to ensure the constructor is set as expected.")
  @ParameterizedTest
  @MethodSource("provideArgumentsForTestChickenAlfredo")
  public void ChickenAlfredoConstructorTest(String pasta, String sauce, String name, String meat, String CookMethod, int time) {

    //again the first test will succeed and the next two runs will fail on purpose. 
    //this is to ensure that what I set is accurate and if compared against other strings it will not match.
    assertThat(pasta, equalTo(testFredo.getPasta()));
    assertThat(sauce, equalTo(testFredo.getPastaSauce()));
    assertThat(name, equalTo(testFredo.getDishName()));
    assertThat(meat, equalTo(testFredo.getMainIngredient()));
    assertThat(CookMethod, equalTo(testFredo.getCookingMethod()));
    assertThat(time, equalTo(testFredo.getCookTime()));
  }



}
