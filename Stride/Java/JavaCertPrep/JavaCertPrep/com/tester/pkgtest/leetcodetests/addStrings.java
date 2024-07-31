package com.tester.pkgtest.leetcodetests;

public class addStrings {


        public String addStrings(String num1, String num2) {

            //create a stringbuilder which can store the result
            //StringBuilder is mutable so you can change the contents of the
            //object directly.
            StringBuilder result = new StringBuilder();

            //store a carry variable to handle carries when adding
            int carry = 0;

            //store an I an J variable starting at the end of the num strings
            int i = num1.length() - 1, j = num2.length() - 1;

            /*
            loop through while there are still numbers in both array.
            also loop until the carry is taken care of.

            set the sum value to carry initially

            first condition loops through I and adds all the numbers in I together

            second condition loops through J and adds all the numbers in J together


             */
            while (i >= 0 || j >= 0 || carry != 0) {
                int sum = carry;
                if (i >= 0) {
                    sum += num1.charAt(i--) - '0';
                }
                if (j >= 0) {
                    sum += num2.charAt(j--) - '0';
                }
                //the mod 10 is to see if there is any carry that is left over that needs to be added to the
                //next column.
                /*
                what happens here is if it is bigger than 10 say 13 sum%10 will keep the 3 in result
                and save the 1 for the next loop through.
                 */
                result.append(sum % 10);
                carry = sum / 10;
            }

            //built in string builder function to reverse the output then convert it to string.
            return result.reverse().toString();
        }

        public static void main(String[] args) {

            String num1 = "456";
            String num2 = "77";
            addStrings addStr = new addStrings();

            System.out.println(addStr.addStrings(num1, num2));

        }

    }
