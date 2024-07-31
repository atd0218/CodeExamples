/**
 * @name: Ashton Daniels
 * @date: 04/02/2024
 * JDK Version: 8 
 * Description: 
 * 
 * IFeeProvider.java
 * 
 * Interface created which has methods to setFee, payFee, and see how much is due still. 
 * 
 * StudentRegistration.java
 * 
 * This class is the business object/collaborator used for mocks. This has a constructor which takes a 
 * student as well as an instance of the IFeeProvider interface to be used for mocks and testing. 
 * 
 * This also has overridden methods for the interface methods which call back to the interface to complete. 
 * 
 * StudentRegistrationTest.java
 * 
 * This class is used to create a mock and set the expectations and then perform testing using JUnit to ensure that the
 * mock is working as we would expect. 
 */

package com.testing;

import org.easymock.EasyMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;

import org.easymock.IMockBuilder;
import org.junit.Test;

public class StudentRegistrationTest {

  @Test
  public void test() {
    //setup single mock object
    IFeeProvider feeProvider = createMock(IFeeProvider.class);

    //setup mock builder object to hold multiple mocks if desired. 
    IMockBuilder<Student> builder = EasyMock.createMockBuilder(Student.class);
    //create a Student mock using the builder
    Student student = builder.createMock();
    //create an instance of the business object and pass the mocks to it. 
    StudentRegistration studentR = new StudentRegistration(student, feeProvider);

    //setup the expectations for the mock. 
    feeProvider.setFee(100.00);
    expect(feeProvider.feesDue()).andReturn(100.00);
    feeProvider.payFee(75.00);
    expect(feeProvider.feesDue()).andReturn(25.00);
    replay(feeProvider);

    //setup the tests for the mock. 
    assertEquals(studentR.setFee(100.00), "fee set");
    //states that it can be 100 as the answer with a variance of 0.001
    assertEquals(studentR.feesDue(), 100.00, 0.001);
    assertEquals(studentR.payFee(75.00), "fee payment");
    assertEquals(studentR.feesDue(), 25.00, 0.001);

    EasyMock.verify(feeProvider);
  }

}
