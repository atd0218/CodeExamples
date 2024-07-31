package com.tester.pkgtest.leetcodetests;
import java.lang.String;

/**
 * Ashton
 * Leetcode Practice
 *
 * takes a string containing numbers from 1 to 26 if the number is bigger than 10 there is a # after the number.
 * each number corresponds to an alphabet letter and you must print out the letter.
 */
public class DecryptStringFromAlphabetToIntegerMap {

        public static String freqAlphabets(String s) {

            //used string builder to be able to append to the string.
            StringBuilder result = new StringBuilder();

            //set n to the length of the string passed in
            int n = s.length();

            //set i to 0 to begin with
            int i = 0;

            //while loop stops when i = n
            while (i < n) {

                /*
                check to see if 2 spaces from now is still less than n and that the character two places
                from now is a # if so this is a double digit number.
                 */
                if (i + 2 < n && s.charAt(i + 2) == '#') {
                    //store the number
                    int num = Integer.parseInt(s.substring(i, i + 2));
                    //take that number and add it starting at a - 1 to not include a twice and print that
                    //character. for example if the num was 5 then char would be e
                    result.append((char) ('a' + num - 1));

                    //add 3 to i since you have accounted for the next 3 spaces
                    i += 3;

                  //this else is if there is a single digit number
                } else {
                    int num = Integer.parseInt(s.substring(i, i + 1));
                    result.append((char) ('a' + num - 1));
                    i += 1;
                }
            }
            return result.toString();

        }

    public static void main(String[] args) {
        String s = "10#11#12";

        System.out.println(freqAlphabets(s));
    }
}
