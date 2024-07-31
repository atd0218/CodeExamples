package com.stride.testingchallenge.pastachallenge;
/**
 * Author: Scott Liu
 * Date: 02/23/2024
 */
public class BakedZiti extends Pasta{
	private String cookingMethod;
	private int cookTime;
	
	/**
	 * Default Constructor
	 */
	public BakedZiti() {
		
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
	public BakedZiti(String pasta, String pastaSauce, String dishName, 
			String mainIngredient, String cookingMethod, int cookTime) {
		super(pasta, pastaSauce, dishName, mainIngredient);
		this.setCookingMethod(cookingMethod);
		this.setCookTime(cookTime);
	}
	
	/**
	 * Retrieves the cooking method used for baked ziti.
	 * 
	 * @return The cooking method
	 */
	public String getCookingMethod() {
		return cookingMethod;
	}
	
	/**
	 * Sets the cooking method for the baked ziti.
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
	 * Sets the cooking time for the baked ziti.
	 * 
	 * @param cookTime Sets the time in minutes
	 */
	public void setCookTime(int cookTime) {
		this.cookTime = cookTime;
	}
	
	/**
	 * Prints cooking instructions for the baked ziti.
	 */
	public void printCookInstructions() {
		System.out.println("It is cooked by " + this.getCookingMethod() +
				" and takes " + this.getCookTime() + " minutes to make.");
	}
}
