/**
 * @author Ashton Daniels
 * Date: 01/22/2024
 * JDK Version: 8
 * Description: Prompt the user to enter two numbers to calculate the GCD and return it to the user. 
 */
import java.util.Scanner;
import java.math.BigInteger;
public class myMain {

/**
 * 
 * @param a - first integer passed in from the user to calculate GCD of. 
 * @param b - second integer passed in from the user to calculate GCD of. 
 * @return - once the while loop has finished this will return the final GCD value. 
 * 
 * This function takes in two integers and calculates GCD implementing Euclid’s algorithm so it will not work for negative integers.
 * Utilized BigInteger class to experiment with this as I was discussing this with another classmate and had not used them before. 
 */
  private static BigInteger gcd(BigInteger a, BigInteger b) {
	    BigInteger current_gcd = BigInteger.valueOf(1);
		
			while (b.compareTo(BigInteger.ZERO) > 0) {
				//get remainder of a/b as it is fundamental for Euclid's
				BigInteger remainder = a.mod(b);

				//set a as the current value for b
				a = b;

				//set b to the current remainder value
				b = remainder;

				//set the current_gcd to a.
				current_gcd = a;

				//this process will repeat until b is 0 and then a will be the GCD
			}
		
	    return current_gcd;
	  }
	  /**
	   * 
	   * @param args - main method
	   * 
	   * prompt the user for two BigIntegers then check to make sure they are greater than 0
	   * if they are greater than 0 then call the above function to calculate GCD. 
	   */
	  public static void main(String args[]) {
		//added the try with to have the scanner close to avoid the error shown in vscode
	    try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Please enter two numbers to calculate the GCD between both: ");
			BigInteger a = scanner.nextBigInteger();
			BigInteger b = scanner.nextBigInteger();

			if (a.compareTo(BigInteger.ZERO) <= 0 || b.compareTo(BigInteger.ZERO) <= 0)
			{
				System.out.println("You entered a number of zero or less which is not allowed for the program. ");
			} else {
			 	System.out.println(gcd(a, b));
			}
		}
	  }
}
