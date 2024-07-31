/**
 * @name: Ashton Daniels
 * @Date: 04/02/2024
 * JDK Version: 8
 * 
 * Description: 
 * 
 * ArrayListTest.java
 * 
 * This was created for the purpose of showing how to mock and test the class that you are working in. 
 * 
 * An ArrayList was created to store mock objects and then I wrote in the expected test scenarios for the mock. 
 * At that point I ran the tests using JUnit and they succeeded until I forced one to fail for testing purposes. 
 * 
 * ICalc.java
 * 
 * Simple interface created for the purpose of demonstrating interfaces and mocking. 
 * 
 * This interface has two methods to implement which are add and multiply. 
 * 
 * MathUtils.java
 * 
 * This is the collaborator/business object that we are using to test the mock with. This implements the ICalc interface
 * and sets up a constructor that takes an ICalc object. It also implements the add and multiply method by referring back 
 * to the calc object to run the calls. 
 * 
 * CalcTest.java
 * 
 * This is the class created to create a mock and pass it to the MathUtils class. Then I setup the expectations and JUnit tests
 * and it ran as expected. 
 */

package com.test;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Test;

public class CalcTest {

  @Test
  public void test() {

    ICalc calc = EasyMock.createMock(ICalc.class);

    //feed this mock to the business object in this case it is MathUtils
    MathUtils mathU = new MathUtils(calc);

    //start laying out the expectations for the mock test
    expect(calc.add(1, 1)).andReturn(2);

    expect(calc.multiply(10, 10)).andReturn(100);

    //set the mock to replay mode so you can start testing the mock. 
    replay(calc);

    //start running JUnit tests to test the mock object.

    assertEquals(mathU.add(1,1), 2);

    assertEquals(mathU.multiply(10, 10), 100);

    //verify the EasyMock object tests
    EasyMock.verify(calc);


  }

}
