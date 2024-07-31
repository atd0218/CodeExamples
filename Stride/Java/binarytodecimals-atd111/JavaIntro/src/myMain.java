/**
 * @author Ashton Daniels
 * JDK Version: 8
 * Last Modified: 01/22/2024
 * Description: Create a binary to decimal converter as well as a octal to decimal converter. 
 */

 import java.util.ArrayList;
 import java.util.Scanner;

public class myMain {

	public static void main(String[] args) {

		 //variables
		 Scanner sc = new Scanner(System.in);
		 ArrayList<Integer> numbers = new ArrayList<Integer>();
		 ArrayList<Integer> octalNumbers = new ArrayList<Integer>();
		 int finalValue = 0;
		 int octalValue = 0;

		/**
		 * Ask user for a decimal input
		 */

		 System.out.println("Please enter a binary String");
		 String inputDecimal = sc.nextLine();

		/**
		 * Ask user for a decimal input
		 */

		 System.out.println("Please enter a octal String");
		 String inputOctal = sc.nextLine();
	 
		 /**
		  * Binary To Decimal Steps
		  * Iterate over the String backwards that was inputted and do several things
		  * 1. pull out each individual character of the string
		  * 2. check if that character is equal to 1 and if it is then take 1 times
		  * the exponential 2 value based on placement
		  * 3. iterate over the new numbers arraylist and add all numbers together
		  * 4. Print out the original input and the number
		  * numbers and add all the numbers together to give me the value. 
		  */
	 
		 for (int i = (inputDecimal.length() - 1), j = 0; i >= 0; i--, j++) {
			 char point = inputDecimal.charAt(i);
			 if (point == '1') {
				 int num = 1 * (int)(Math.pow(2, j));
				 numbers.add(num);
			 }
		 }
	 
		 for (int num : numbers) {
			 finalValue += num;
		 }
	 
		 System.out.println("The decimal value of binary number " + inputDecimal + " is " + finalValue);

		 /**
		  * Octal To Decimal Steps
		  * Iterate over the String backwards that was inputted and do several things
		  * 1. pull out each individual character of the string
		  * 2. check if that character is equal to 1 and if it is then take 1 times
		  * the exponential 8 value based on placement
		  * 3. iterate over the new numbers arraylist and add all numbers together
		  * 4. Print out the original input and the number
		  * numbers and add all the numbers together to give me the value. 
		  */
	 
		  for (int i = (inputOctal.length() - 1), j = 0; i >= 0; i--, j++) {
			Character point = inputOctal.charAt(i);
			int test = Character.getNumericValue(point);
			int num = test * (int)(Math.pow(8, j));
			octalNumbers.add(num);
		}
	
		for (int num : octalNumbers) {
			octalValue += num;
		}
	
		System.out.println("The decimal value of octal number " + inputOctal + " is " + octalValue);

	}

}