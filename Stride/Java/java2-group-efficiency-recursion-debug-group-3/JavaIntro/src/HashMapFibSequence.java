import java.util.Scanner;
import java.util.HashMap;

public class HashMapFibSequence {
    /**
       This program computes Fibonacci numbers using a recursive method.
    */
       public static void main(String[] args)
       {
          Scanner in = new Scanner(System.in);
          System.out.print("Enter n: ");
          int n = in.nextInt();
          in.close();
    
          HashMap<Integer, Long> fibNumbers = new HashMap<>();
          // setting up the base cases
          fibNumbers.put(1, 1L);
          fibNumbers.put(2, 1L);
          System.out.printf("fib(%d)=%d\n", n, fib(n, fibNumbers));
       }
    
       /**
          Computes a Fibonacci number.
          @param n an integer
          @return the nth Fibonacci number
       */
       public static long fib(int n, HashMap<Integer, Long> fibNumbers)
       {
          if (fibNumbers.containsKey(n)) {
             return fibNumbers.get(n);
          }
          System.out.printf("Entering fib: n = %d\n", n);
          long returnVal = fib(n - 2, fibNumbers) + fib(n - 1, fibNumbers);
          fibNumbers.put(n, returnVal);
          System.out.printf("Exiting fib: n = %d return value = %d\n", n, returnVal);
          return returnVal;
        }
}
