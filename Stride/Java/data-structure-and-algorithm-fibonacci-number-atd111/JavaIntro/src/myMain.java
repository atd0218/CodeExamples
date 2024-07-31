/**
 * @author Ashton Daniels
 * JDK Version: 8
 * Date: 01/23/2024
 * Description: Program that asks user for an integer and calculates the Fibonacci number. 
 */

import java.util.ArrayList;
import java.util.Scanner;

public class myMain {

	/**
	 * 
	 * @param n - Number passed in for the user to calculate the Fibonacci number for.
	 * @return - return back the Fibonacci number to be displayed to the user. 
	 * 
	 * Create an arraylist and add 0 and 1 for the first calculation. 
	 * loop through the array the amount of times the user specified.
	 * inside the loop populate the arraylist with the FIB sequence and display it.
	 * calculate the final Fib Number as the final step and return it.
	 */
	private static long calc_fib(int n) {
	//TODO: Write Fibonacci logic here 

		ArrayList<Integer> F = new ArrayList<Integer>();

		F.add(0);
		F.add(1);

		//added n+1 as it will show you the number in the sequence that way.
		for (int i = 2; i < n + 1; i++) {
			//get the two previous values and add them together
			//then add them to the ArrayList
			F.add(F.get(i - 1) + F.get(i - 2));
		}

		/*
		 * For the final calculation take the number one index and two indexes
		 * behind the number the user passed in and add them together. 
		 */
		int FibNum = (F.get(n - 1) + F.get(n - 2));

		System.out.println("the Fibonacci sequence for " + n + " is: " + F);

		return FibNum;
	}
	/**
	 * 
	 * @param args - main method
	 * prompt user or a number to calculate the Fibonacci number. 
	 * call the above function to calculate said number and display it back to the user. 
	 */
	  public static void main(String args[]) {
	    Scanner in = new Scanner(System.in);
		System.out.println("Please enter the number I should calculate the Fibonnaci for.");
	    int n = in.nextInt();
	    System.out.println("The Fibonacci number is: " + calc_fib(n));
	  }

	/**
	 * While performing testing on this I noticed that once you get to 47 you are now to large for integer and get 
	 * wierd results. In order to use this for bigger numbers you will need to implement this using a larger data type.
	 */
}
