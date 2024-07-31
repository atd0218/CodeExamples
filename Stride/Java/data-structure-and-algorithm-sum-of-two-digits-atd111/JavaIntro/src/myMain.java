/**
 * @author Ashton Daniels
 * Date: 01/22/2024
 * JDK Version: 8
 * Descripion: User enters two integers that will be passed into a sum function to be added and then returned
 * to the console. 
 */
import java.util.Scanner;

public class myMain {

	/**
	 * use this function to take two int and add them together. 
	 * @param first_digit
	 * @param second_digit
	 * @return sum of first two digits
	 */
	static int sumOfTwoDigits(int first_digit, int second_digit) {
          return first_digit + second_digit;
        }

	/**
	 * main method to ask the user for two ints and then print out the sum of those. 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
	        int a = s.nextInt();
	        int b = s.nextInt();
	        System.out.println(sumOfTwoDigits(a, b));
	}
}
