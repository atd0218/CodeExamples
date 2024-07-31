package com.stride.testingchallenge.pastachallenge;
/**
 * Author: Scott Liu
 * Date: 02/23/2024
 */
public class Pasta {
	private String pasta; // Type of pasta used in the dish
	private String pastaSauce; // Sauce used in the dish
	private String dishName; // Name of the pasta dish
	
	//Added extra attribute for mainIngredient to match prompt output
	private String mainIngredient; // Main ingredient used in the dish
	
	/**
	 * Default Constructor
	 */
	public Pasta() {
		
	}
	
	/**
	 * Parameterized Constructor
	 * 
	 * @param pasta
	 * @param pastaSauce
	 * @param dishName
	 * @param mainIngredient
	 */
	public Pasta(String pasta, String pastaSauce, String dishName, String mainIngredient){
		super();
		this.setPasta(pasta);
		this.setPastaSauce(pastaSauce);
		this.setDishName(dishName);
		this.setMainIngredient(mainIngredient);
	}
	
	/**
	 * Retrieves the type of pasta used in the dish.
	 * 
	 * @return The type of pasta used
	 */
	public String getPasta() {
		return pasta;
	}
	
	/**
	 * Sets the type of pasta used in the dish.
	 * 
	 * @param pasta The type of pasta to be set
	 */
	public void setPasta(String pasta) {
		this.pasta = pasta;
	}
	
	/**
	 * Retrieves the sauce used in the dish.
	 * 
	 * @return The sauce used
	 */
	public String getPastaSauce() {
		return pastaSauce;
	}
	
	/**
	 * Sets the sauce used in the dish.
	 * 
	 * @param pastaSauce The sauce to be set
	 */
	public void setPastaSauce(String pastaSauce) {
		this.pastaSauce = pastaSauce;
	}
	
	/**
	 * Retrieves the dish name
	 * 
	 * @return The dish name
	 */
	public String getDishName() {
		return dishName;
	}
	
	/**
	 * Sets the dish name
	 * 
	 * @param dishName The name of the dish to be set
	 */
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	
	/**
	 * Retrieves the main ingredient used in the dish.
	 * 
	 * @return The main ingredient used
	 */
	public String getMainIngredient() {
		return mainIngredient;	
	}
	
	/**
	 * Sets the main ingredient of the dish
	 * 
	 * @param mainIngredient The main ingredient to be set
	 */
	public void setMainIngredient(String mainIngredient) {
		this.mainIngredient = mainIngredient;
	}
	
	/**
	 * Prints a description of the pasta dish.
	 */
	public void describePasta() {
		System.out.println("The pasta dish named " + this.getDishName() +
				" is made with " + this.getPasta() + " pasta and " +
				this.getPastaSauce() + " sauce with " + this.getMainIngredient() + ".");
	}
}
