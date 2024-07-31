package com.stride.testingchallenge.pastachallenge;
/**
 * Author: Scott Liu Date: 02/23/2024
 */
public class myMain {

	public static void main(String[] args) {
		
		// Creating instances of BakedZiti and ChickenAlfredo
		BakedZiti MondayMenu = new BakedZiti();
		ChickenAlfredo TuesdayMenu = new ChickenAlfredo();

		// Setting attributes for Baked Ziti object
		MondayMenu.setDishName("Baked Ziti");
		MondayMenu.setPasta("Ziti");
		MondayMenu.setPastaSauce("tomato");
		MondayMenu.setMainIngredient("Italian sausage");
		MondayMenu.setCookingMethod("oven baking");
		MondayMenu.setCookTime(35);
		
		// Setting attributes for Chicken Alfredo object
		TuesdayMenu.setDishName("Chicken Alfredo");
		TuesdayMenu.setPasta("fettuccini");
		TuesdayMenu.setPastaSauce("Alfredo");
		TuesdayMenu.setMainIngredient("chicken");
		TuesdayMenu.setCookingMethod("boiling in a pot");
		TuesdayMenu.setCookTime(20);
		
		// Display details of Baked Ziti
		MondayMenu.describePasta();
		System.out.println();
		MondayMenu.printCookInstructions();
		System.out.println();
		
		// Dsiplay details of Chicken Alfredo
		TuesdayMenu.describePasta();
		System.out.println();
		TuesdayMenu.printCookInstructions();
	}

}
