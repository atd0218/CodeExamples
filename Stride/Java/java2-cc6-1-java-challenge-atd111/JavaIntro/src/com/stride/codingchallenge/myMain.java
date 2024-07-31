package com.stride.codingchallenge;
/**
 *@author Ashton Daniels
 *date: 02/21/2024
 *JDK Version: 8
 *Description: myMain.java
 *This is my driver class which houses the main method. In the main method I initiate an
 *instance of BakedZiti as well as ChickenAlfredo objects and set their values using the constructor.
 *Then I make a call to the Generic class I have configured that handles displaying the output regardless if the class type is
 *different.
 *The time complexity of this code is O(1) because it will always take a constant amount of time to create instances of the
 *BakedZiti and ChickenAlfredo classes, call the displayOutput method, and print the output.
 *The space complexity is also O(1) because the amount of memory used by the code does not change with the size of the input.
 *Only a constant amount of memory is needed to store the instances of the BakedZiti and ChickenAlfredo classes, as well as
 *the DisplayClass instances
 *DisplayClass.java
 *This class was created as a Generic class so that I could pass in any type of Pasta or any of Pastas children to the class
 *and have it use that information to display the output that is required for this assignment.
 *The time complexity of this code is O(1) because regardless of the size of the input, the code will always execute in constant
 *time.
 *The space complexity is also O(1) because the amount of memory used by the code does not increase with the size of the input.
 *Pasta.java
 *This is the parent class made for this assignment which hosts the common properties of pasta as well as creates getters and
 *setters and a constructor to lock down this class so that it is only accessible in a controlled way.
 *The time complexity of the getters and setters in the Pasta class is O(1) because they directly access or set the value of a
 *single variable without any loops or recursive calls. The describe() method also has a time complexity of O(1) as it simply
 *prints out the values of the pasta sauce, dish name, and pasta variables.
 *The space complexity is O(1) because the class only has a fixed number of instance variables
 *(pasta, pastaSauce, dishName) and methods that do not create any additional data structures or variables that grow with the
 *input size.
 *BakedZiti.java
 *This is a subclass of Pasta that has special variables related to baked ziti such as cooking method and cooking time. This
 *class also has a constructor set as well as getters and setters to restrict how this class is accessed and changed.
 *The time complexity of this class is O(1) for both the describe() method and the getters and setters. This is because each
 *operation within these methods takes a constant amount of time to execute, regardless of the size of the input.
 *The space complexity of this class is also O(1) because the amount of memory used by the class does not increase with the
 *size of the input. The class only contains a few variables and methods, so the amount of memory required remains constant.
 *ChickenAlfredo.java
 *This is a subclass of Pasta that has special variables related to baked ziti such as cooking method and cooking time. This
 *class also has a constructor set as well as getters and setters to restrict how this class is accessed and changed.
 *The time complexity of this class is O(1) for the getters and setters as they directly access or set the values of the
 *variables without any loops or recursion. The describe() method also has a time complexity of O(1) as it simply prints out
 *the values of the cookTime and cookingMethod variables.
 *The space complexity of this class is O(1) as it only uses a constant amount of space for the variables cookingMethod and
 *cookTime, regardless of the size of the input.
 */
public class myMain {

    private myMain() {
        super();
    }

	public static void main(String[] args) {
		BakedZiti mondayMenu = new BakedZiti("Ziti", "Tomato Sauce with Italian Sausage", "Baked Ziti", "Oven Baking", 35);
		ChickenAlfredo tuesdayMenu = new ChickenAlfredo("Fettuccini", "Alfredo Sauce with Chicken", "Chicken Alfredo", "boiling in a pot", 20);
DisplayClass<BakedZiti> Ziti = new DisplayClass<>();

		System.out.println(Ziti.displayOutput(mondayMenu));
DisplayClass<ChickenAlfredo> Alfredo = new DisplayClass<>();
		System.out.println(Alfredo.displayOutput(tuesdayMenu));
	}

}