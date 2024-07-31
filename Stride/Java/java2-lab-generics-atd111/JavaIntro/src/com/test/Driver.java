/**
 * @name: Ashton Daniels
 * Date: 03/21/2024
 * Description: 
 * 
 * Driver.java
 * 
 * Class used to test Generic class, Generic Method, and Maximum test. 
 * 
 * Maximum.java
 * 
 * restricts the type this class can be to ensure that it inherits from the comparable interface. 
 * This is important for this class as is usses .compareTo from the comparable interface to complete its actions. 
 * This restriction of types can be very powerful in situations like these when a class is reliant on an interface in order to 
 * be used. 
 * 
 * GenericMethod.java
 * 
 * This class simply uses generics to allow you to send an array of different types to it and it will print them out using a for
 * each loop. 
 * 
 * GenericBox.java
 * 
 * an example of a generic class in which we allow for any type to be passed in and we store that type in the class as well as 
 * provide getters and setters for messing with the private variable T. 
 * 
 */

package com.test;
import java.util.MissingFormatArgumentException;

import com.test.GenericMethod;
import com.test.Maximum;
public class Driver {

  public static void main(String[] args) {
		// TODO Auto-generated method stub

    //generic box exersizer
    try {
      GenericBox<Integer> test = new GenericBox<>();
      test.setT(10);

      GenericBox<String> test2 = new GenericBox<>();
      test2.setT("Hello World!");

      System.out.printf("The value of test which holds Integer is: %d, and the value of test2 which holds String is: %s "
      , test.getT(), test2.getT());
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }

    //generic method exersizer
    try {
      Integer[] intTest = {1, 2, 3, 4, 5, 6, 7};
      Double[] doublTest = {1.0, 2.0, 3.3, 4.6, 5.2, 6.1, 7.7};
      Character[] charTest = {'C', 'H', 'A', 'R', 'A'};

      System.out.println();
      System.out.println("Integer Array Contains: ");
      GenericMethod.printArray(intTest);
      System.out.println("Double Array Contains: ");
      GenericMethod.printArray(doublTest);
      System.out.println("Character Array Contains: ");
      GenericMethod.printArray(charTest);
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println();
      System.out.println("An array elements is trying to be accessed that does not exist.");
      e.printStackTrace();
    }

    //Maximum exersizer
    try {

      System.out.println();
      System.out.printf("Maximum of %d, %d, and %d is %d ", 5, 4, 3, Maximum.maximum(5, 4, 3));
      System.out.println();
      System.out.printf("Maximum of %d, %d, and %d is %d ", 740, 980, 1220, Maximum.maximum(740, 9804, 1220));
      //used to test error
      //System.out.printf("Maximum of %d, %d, and %d is %d ", 740, 980, Maximum.maximum(740, 9804, 1220));
      
    } catch (MissingFormatArgumentException e) {
      System.out.println();
      System.out.println("You did not include enough arguments that you used in the F string. ");
      e.printStackTrace();
    }
    


	}
  
}
