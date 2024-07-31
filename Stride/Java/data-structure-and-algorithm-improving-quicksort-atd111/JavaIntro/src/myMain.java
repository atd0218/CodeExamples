/**
 * @author Ashton Daniels
 * Date: 01/25/2024
 * JDK Version: 8 
 * Description: Create a program that takes an array of ints from the user in unsorted order
 * and the program will sort them using the recursive random pivot quick sort algorithm.
 */
import java.util.Scanner;
import java.util.Random;

public class myMain {

    private static Random random = new Random();


    /**
     * 
     * @param a - array of ints passed in from user
     * @param l - lower end of the array to start with
     * @param r - maximum index of the array to start with
     * @return - value of j + 1
     * 
     * this method will partition the array and and create sub arrays
     * of numbers lower than pivot and numbers higher than pivot. This 
     * will be iterated through several times until all items are sorted
     */
    // private static int partition(int[] a, int l, int r) {
    //     //TODO: Implement Quicksort code here for a partition

    //     //set x to the value in position r of a array.
    //     int x = a[r];

    //     //set j equal to value of l passed in minus 1. 
    //     int j = l - 1;

    //     //start with i as value of l 
    //     //loop through while i < r
    //     //looking for numbers less than pivot and moving them
    //     for (int i = l; i < r; i++) {

    //         if (a[i] <= x) {
    //             //increment j by 1
    //             j += 1;

    //             //swap a[j] and a[i]
    //             int t = a[j];
    //             a[j] = a[i];
    //             a[i] = t;
    //         }
    //     }

    //     //final swaps of j+1 and r(highindex)
    //     int temp = a[j+1]; 
    //     a[j+1] = a[r]; 
    //     a[r] = temp; 

    //     return j+1;
    // }

    static int[] partition(int a[], int l, int r) {
        if (r - l <= 1) {
           if (a[r] < a[l]){
                int t = a[r];
                a[r] = a[l];
                a[l] = t;
           }        
        }
        int mid = l;
        int pivot = a[r];
        while (mid <= r) {
            if (a[mid]<pivot) {
            int t = a[l++];
            a[l++] = a[mid++];
            a[mid++] = t;
            }
            else if (a[mid]==pivot)
                mid++;
            else if (a[mid] > pivot){
                int t = a[l++];
                a[l++] = a[mid++];
                a[mid++] = t;
            }
            int i = l-1;
            int j = mid;
        }

        int m1 = l;
        int m2 = r;
        int[] m = {m1, m2};
        return m;
    }
     

    /**
     * 
     * @param a - array of ints passed in from user
     * @param l - low index of the array to quick sort
     * @param r - high index of the array to quick sort
     * 
     * generates a random pivot number uses that to determine the partition
     * and then recursively calls itself re doing each operation until the array 
     * is sorted. 
     */
    private static void randomizedQuickSort(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        //set a random number between the (r - l + 1) + l
        int k = random.nextInt(r - l + 1) + l;

        //swap a[l] and a[k]
        int t = a[l];
        a[l] = a[k];
        a[k] = t;
        
        //2 way partition success. 
        //int m = partition(a, l, r); - 2 way partition split

        // //call function recursively to do lower half on partitioned array
        // randomizedQuickSort(a, l, m - 1);

        // //call function recursively to do upper half of partitioned array
        // randomizedQuickSort(a, m + 1, r);

        //3 way partition attempt
        int[] m1 = partition(a, l, r);
        int[] m2 = partition(a, l, r);

        randomizedQuickSort(a, l, m1.length - 1);
        randomizedQuickSort(a, m2.length + 1, r);
    }

    /**
     * 
     * @param args - main method
     * 
     * prompt user for size of array and then array elements. 
     * call quicksort which will recursively run until the array is sorted.
     * print out each array element after sorting is completed. 
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //System.out.println("Please enter the size of the array you will be creating.");
        int n = scanner.nextInt();
        //System.out.println("Please enter the numbers in the array in an unsorted order.");
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        randomizedQuickSort(a, 0, n - 1);
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
