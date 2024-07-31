/**
 * @author Ashton Daniels
 * Date: 02/24/2023
 * JDK Version: 8
 * Description: Implement a binary search over an array that is inputted by the user.
 * Additionally, you can choose to look for multiple items in the array that was provided.
 */
import java.util.Scanner;
public class myMain {

	/**
	 * 
	 * @param a - array of integers populated by the user. 
	 * @param x - the term that you are looking for inside of the array.
	 * @return - return the number if it is found otherwise return -1
	 * 
	 * This method will loop through while the start of the array is less than the end. 
	 * in the loop it will establish an array middle and start looking for x there. 
	 * If x is found return x, if x is less than middle then eliminate greater half of array,
	 * if x is greater than middle then eliminate lesser half of array. Repeat until x is found
	 * else return -1.
	 */
	static int binarySearch(int[] a, int x) {
	        int left = 0, right = a.length;
	        //write your binary search code here
			while (left <= right) {
				int middleChecker = (int)(Math.floor(left + ((right - left) / 2)));

				if (x == a[middleChecker]) {
					return middleChecker;
				}
				else if (x < a[middleChecker]) {
					//set the right position to what middle was minus 1 in order to 
					//eliminate the right side. 
					right = middleChecker - 1;
				}
				else {
					/* set the left to what the middle was plus 1 in order to 
					 * eliminate the left side. 
					 */
					left = middleChecker + 1;
				}
			}
			return -1;
	        
	}
	/**
	 * 
	 * @param args - main method
	 * 
	 * prompt the user for the size of the array as well as the array items. 
	 * Then ask user how many items they are looking for and enter those. 
	 * Loop through amount of items user is looking for and attempt to find it
	 * else return -1.
	 */
	public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
			System.out.println("Please enter the size of your array: ");
	        int n = scanner.nextInt();
			System.out.println("Please enter " + n + " Array items: ");
	        int[] a = new int[n];
	        for (int i = 0; i < n; i++) {
	            a[i] = scanner.nextInt();
	        }
			System.out.println("Please enter the how many numbers you want to search for: ");
	        int m = scanner.nextInt();
			System.out.println("Enter " + m + " numbers to search for in the above array: ");
	        int[] b = new int[m];
	        for (int i = 0; i < m; i++) {
	          b[i] = scanner.nextInt();
	        }
	        for (int i = 0; i < m; i++) {
	            System.out.print(binarySearch(a, b[i]) + " ");
	        }
	}

}
