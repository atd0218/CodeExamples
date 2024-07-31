package com.javaoo.calculators;

public class ScientificCalculator {

	public static final double PI = 3.14159;
	
	private double holdValue;
	
	public double exp(double x) {
		double result = Math.pow(x, x);
		return result;
	}
	
	public double log(double x) {
		double result = Math.log(x);
		return result;
	}
	
	public void putValueIntoMemory(double x) {
		holdValue = x;
	}
	
	public double getValueFromMemory() {
		return holdValue;
	}
}
