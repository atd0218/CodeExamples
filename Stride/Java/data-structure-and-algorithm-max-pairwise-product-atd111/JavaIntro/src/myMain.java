/**
 * @author Ashton Daniels
 * Date: 01/22/2024
 * JDK Version: 8
 * Description: Create a class that will calculate the maxwise product of an array of integers
 * that are entered in by the user. 
 */
import java.util.Scanner;
public class myMain {

    /**
     * iterate through the length of the array starting at 0 and a nested loop starting at 1 and increasing
     * each time. Inside of the nested loop we are comparing the max of the current max value and the value
     * of the next two index positions multiplied by each other. Whichever is biggest will be reassigned
     * to max_product.
     * 
     * @param numbers - an array of numbers passed in from the user
     * @return - the max product value after multiplying the first and second item 
     * to find the largest product. 
     */
    static int getMaxPair(int[] inputNums) {
        int max_product = 0;
        int n = inputNums.length;

        //TODO: add code here that completes this assignment.
        //You need to find and return which is the highest value
        //when you multiply any two of the array items passed in.
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                max_product = Math.max(max_product,
                inputNums[i] * inputNums[j]);
            }
        }

        return max_product;
    }

    /**
     * Prompt the user twice 
     * 1. ask them how many integerst they will provide
     * 2. Prompt them to enter that many integers and store them in the numbers array. 
     * call the above function with the numbers array to calculate maxwise pair.
     * @param args - main method. 
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a number indicating the amount of integers you plan to provide.");
        int n = scanner.nextInt();
        System.out.println("Please enter " + n + " numbers.");
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        System.out.println(getMaxPair(nums));
    }
}
