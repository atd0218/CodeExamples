package com.test;
public class GenericMethod {

  //generic method to print the array in a certain way. 
  //it is static so it is independent of the class. 
  public static<E> void printArray(E[] inputArray) {

    int counter = 1;
    for (E e : inputArray) {
      System.out.printf("Item %d is: %s %n", counter, e);
      counter++;
    }

  }
  
}
