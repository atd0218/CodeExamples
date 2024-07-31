package com.test;

public class MathUtils {
  /* Setting this up as the collaborator for EasyMock testing and this class 
   * will implement the ICalc interface and its methods. 
   */
  private ICalc calc;

  public MathUtils(ICalc c) {
    this.calc = c;
  }

  public int add(int x, int y) {
    return this.calc.add(x, y);
  }

  public int multiply(int x, int y) {
    return this.calc.multiply(x, y);
  }

}
