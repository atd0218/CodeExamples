package com.tester.pkgtest.leetcodetests;

public class HappyNumberAlgorithm {


    /**
     * My solution which runs in N^2 time so there is most likely a more effective way to do this.
     * This solution was very slow and only beat 50% of users
     * @param n
     * @return
     */
//    public static boolean isHappy(Integer n) {
//
//            //convert n into a string
//
//            //read the string as a character to separate each one
//
//            //convert each character back into an int to be able to square it and test.
//
//            long start = System.currentTimeMillis();
//            int count = 0;
//
//            //first check if n is 1
//            if (n == 1) {
//                return true;
//            }
//
//            while (n != 1 && count <= 10) {
//
//                String size = n.toString();
//                Double tempResult = 0.0;
//
//                for (int i = 0; i < size.length(); i++) {
//                    tempResult += Math.pow(Integer.parseInt(String.valueOf(size.charAt(i))), 2);
//                }
//
//                if (tempResult.intValue() == 1) {
//                    long stop = System.currentTimeMillis();
//
//                    System.out.println("Time: " + (stop - start) + " ms");
//                    return true;
//                } else {
//                    n = tempResult.intValue();
//                    count++;
//                }
//
//
//            }
//            long stop = System.currentTimeMillis();
//
//            System.out.println("Time: " + (stop - start) + " ms");
//
//            return false;
//
//        }

    /**
     *
     * @param n
     * @return
     *
     * This solution found online completes faster than 99% of other solutions.
     * This solution uses two different pointers and recursion to call the square function
     * one pointer is 2 ahead and the other is 1 ahead. If there is a cycle of repeating numbers
     * then this is not a happy number, so it will execute then.
     */
    public static boolean isHappy(int n) {

        int slow = n;
        int fast = n;
        //while loop is not used here because initially slow and
        //fast pointer will be equal only, so the loop won't run.
        do {
        //slow moving one step ahead and fast moving two steps ahead
            long start = System.currentTimeMillis();
            slow = square(slow);
            fast = square(square(fast));
            long stop = System.currentTimeMillis();
            System.out.println("Time: " + (stop - start) + " ms");
        } while (slow != fast);

        //if a cycle exists, then the number is not a happy number
        //and slow will have a value other than 1

        return slow == 1;
    }

    //Finding the square of the digits of a number

    public static int square(int num) {

        int ans = 0;

        while(num > 0) {
            int remainder = num % 10;
            ans += remainder * remainder;
            num /= 10;
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(isHappy(19));
        System.out.println(isHappy(2));
        System.out.println(isHappy(10));
    }
}
