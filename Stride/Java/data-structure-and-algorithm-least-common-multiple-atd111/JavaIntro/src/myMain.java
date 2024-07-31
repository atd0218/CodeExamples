/**
 * @author Ashton Daniels
 * Date: 
 */
import java.math.BigInteger;
import java.util.Scanner;
public class myMain {
/**
 * 
 * @param a - BigInteger value passed in by the user
 * @param b - BigInteger value passed in by the user
 * @return - returns the least common multiple for the two numbers
 * 
 * This method with perform this formula (a / gcd(a,b) * b) or vice versa if b is greater
 * Since I implemented the GCD as BigInteger I decided to continue with that here to avoid casting.
 */	
   private static BigInteger lcm(BigInteger a, BigInteger b) {
	    //TODO: Implement Least common multiple

		//returns 1 if a is greater, 0 if equal, or -1 if less than
		int compareValue = a.compareTo(b);

		if(compareValue == 1){
        	return (a.divide(gcd(a,b))).multiply(b);
		}else {
        	return (b.divide(gcd(a,b))).multiply(a);
		}


   }
	/**
	 * 
	 * @param a - first BigInteger passed in from the user to calculate GCD of. 
	 * @param b - second BigInteger passed in from the user to calculate GCD of. 
	 * @return - once the while loop has finished this will return the final GCD value. 
	 * 
	 * This function takes in two integers and calculates GCD implementing Euclid’s algorithm 
	 * so it will not work for negative integers.
	 * 
	 * Formula
	 * 
	 * get remainder of a / b
	 * 
	 * set a to what b currently is 
	 * set b to what the remainder is
	 * set the current_gcd to a
	 * 
	 * repeat this process until b is 0 and then a will be the GCD
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
	 * prompt user for two BigIntegers check to see if they are greater than 0 
	 * pass them to the LCM method to calculate the least common multiple. 
	 */
	public static void main(String args[]) {
		//added the try with to have the scanner close to avoid the error shown in vscode
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Please enter two numbers to calculate the LCM for: ");
			BigInteger a = scanner.nextBigInteger();
			BigInteger b = scanner.nextBigInteger();

			if (a.compareTo(BigInteger.ZERO) <= 0 || b.compareTo(BigInteger.ZERO) <= 0)
			{
				System.out.println("You entered a number of zero or less which is not allowed for the program. ");
			} else {
					System.out.println(lcm(a, b));
			}
		}
		}

}
