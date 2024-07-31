package com.test;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class ArrayListTest {

  @Test
  public void test () {
    ArrayList<Integer> mockList = mock(ArrayList.class);

    //setup the mock expectations and result expectations
    expect(mockList.add(15)).andReturn(true);
    expect(mockList.add(10)).andReturn(true);
    expect(mockList.size()).andReturn(2);
    expect(mockList.get(0)).andReturn(15);

    //put mock in replay mode so it is ready to take calls
    replay(mockList);
    
    //you must make the calls in the same order that you set them up to be expected. 

    //add 15 and 10 to mocklist which will work since we have added these to the expectations above
    //if you try to add another number then you will get an error.
    mockList.add(15);
    mockList.add(10);

    //perform JUnit tests on the mock 
    //the first is set to succeed and the second to fail.
    assertEquals(mockList.size(), 2); 
    assertTrue(mockList.get(0) == 10);
    
  }

}
