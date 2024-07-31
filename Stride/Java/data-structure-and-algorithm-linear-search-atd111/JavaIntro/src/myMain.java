/**
 * @author Ashton Daniels
 * Date: 01/25/2024
 * JDK Version: 8
 * Description: Program that takes a predefined array and search term and
 * uses the Linear Search algorithm to check if the search term is in the array. 
 */
public class myMain {

	/**
	 * 
	 * @param args - main method
	 * 
	 * create an int array and manually add the integers to it
	 * create a key variable as the term to search for. 
	 * Call the LinearSearch method and store the value returned in found. 
	 * If found equals -1 then inform the user the key was not found. Otherwise
	 * provide the index back to the user that contains that key. 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//create array and variable to search for
		int[] arr = {1, 2, 3, 4, 5, 15, 7, 8, 9, 10};
		int key = 7;


		int found = LinearSearch(arr, 0, arr.length, key);

		if (found == -1) {
			System.out.println("\nYou entered a number that was not present in the array.\n");
		}
		else {
			System.out.println("\n The number you are after is at index " + found + "\n");
		}

	}

	/**
	 * 
	 * @param arr - arr defined in main method
	 * @param l - low index of the array
	 * @param r - high index of the array
	 * @param key - term to look for in the array
	 * @return - -1 if not found otherwise return index position
	 * 
	 * LinearSearch works buy looking at the current value at the low index and comparing it 
	 * to the key. If they are the same then l is returned which is the index the value is
	 * located at. Otherwise it calls the method recursively reducing the size of the array to 
	 * look through each time. If it goes through the entire array without finding the term then
	 * -1 is returned. 
	 */
	static int LinearSearch(int[] arr, int l, int r, int key) {

		try {
			if (arr[l] == key) {
				return l;
			}
			else {
				return LinearSearch(arr, l + 1, r, key);
			}	
		} catch (ArrayIndexOutOfBoundsException e) {
			return -1;
		}
		
	}
}
