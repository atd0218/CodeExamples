/**
 * @author Ashton Daniels
 * JDK Version: 8
 * Date: 01/23/2024
 * Description: Program that asks user for an integer and calculates the Fibonacci number and returns the last digit. 
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
	 * calculate the final Fib Number as the final step
     * take the above calc and modulus by 10 to single out the final digit then return it.
	 */
    private static int getFibonacciLastDigit(int n) {
        //TODO: Calculate Fibonacci and return just the last digit

        ArrayList<Integer> F = new ArrayList<Integer>();

		F.add(0);
		F.add(1);

		for (int i = 2; i < n; i++) {
			//get the two previous values and add them together
			//then add them to the ArrayList
			F.add(F.get(i - 1) + F.get(i - 2));
		}

		/*
		 * For the final calculation take the number one index and two indexes
		 * behind the number the user passed in and add them together. 
		 */
		int FibNum = (F.get(n - 1) + F.get(n - 2));

        //to get the last digit you simply just need to take the modulus of 10 to single
        //out the ones position
		int last = FibNum % 10;

		//System.out.println("the Fibonacci sequence for " + n + " is: " + F);

		return last;
    }
    /**
	 * 
	 * @param args - main method
	 * prompt user or a number to calculate the Fibonacci number. 
	 * call the above function to calculate said number and display back the last digit to the user. 
	 */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //System.out.println("Please enter the number to return the last digit of the Fibonacci number.");
        int n = scanner.nextInt();
        int c = getFibonacciLastDigit(n);
        System.out.println(c);
    }
}
