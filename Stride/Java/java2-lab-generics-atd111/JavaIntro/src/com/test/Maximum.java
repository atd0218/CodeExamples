package com.test;

public class Maximum {

  //<T extends Comparable<T>> this means any data type that wants
  //to use this mythod must implement the comparable interface. 
  public static <T extends Comparable<T>> T maximum(T x, T y, T z) {
    T max = x; 
    if (y.compareTo(max) > 0) {
      max = y;
    }
    if (z.compareTo(max) > 0) {
      max = z;
    }

    return max;
  }
  
}
