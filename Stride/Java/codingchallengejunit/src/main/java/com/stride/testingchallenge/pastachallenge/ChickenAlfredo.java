package com.stride.testingchallenge.pastachallenge;
/**
 * Author: Scott Liu
 * Date: 02/23/2024
 */
public class ChickenAlfredo extends Pasta{
	private String cookingMethod;
	private int cookTime;
	
	/**
	 * Default Constructor
	 */
	public ChickenAlfredo() {
		
	}
	
	/**
	 * Parameterized Constructor
	 * 
	 * @param pasta
	 * @param pastaSauce
	 * @param dishName
	 * @param mainIngredient
	 * @param cookingMethod
	 * @param cookTime
	 */
	public ChickenAlfredo(String pasta, String pastaSauce, String dishName, 
			String mainIngredient, String cookingMethod, int cookTime) {
		super(pasta, pastaSauce, dishName, mainIngredient);
		this.setCookingMethod(cookingMethod);
		this.setCookTime(cookTime);
	}
	
	/**
	 * Retrieves the cooking method used for chicken alfredo.
	 * 
	 * @return The cooking method
	 */
	public String getCookingMethod() {
		return cookingMethod;
	}
	
	/**
	 * Sets the cooking method for the chicken alfredo.
	 * 
	 * @param cookingMethod
	 */
	public void setCookingMethod(String cookingMethod) {
		this.cookingMethod = cookingMethod;
	}
	
	/**
	 * Retrieves the time required for cooking.
	 * 
	 * @return The cooking time in minutes.
	 */
	public int getCookTime() {
		return cookTime;
	}
	
	/**
	 * Sets the cooking time for the chicken alfredo.
	 * 
	 * @param cookTime Sets the time in minutes
	 */
	public void setCookTime(int cookTime) {
		this.cookTime = cookTime;
	}
	
	/**
	 * Prints cooking instructions for the chicken alfredo.
	 */
	public void printCookInstructions() {
		System.out.println("It is cooked by " + this.getCookingMethod() +
				" and takes " + this.getCookTime() + " minutes to make.");
	}

}
