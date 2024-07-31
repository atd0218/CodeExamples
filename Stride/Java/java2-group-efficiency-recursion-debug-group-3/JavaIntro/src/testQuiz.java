/**
 * Sun Lee
 * March 21, 2024
 * 
 * Simple program that uses deque to test whether a line of text inputted is a palindrome
 * The program reads a line, then outputs whether the input is a palindrome or not.
 */

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Deque;

public class testQuiz {

   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      String line = scanner.nextLine();
      testPalindrome(line);
      scanner.close();
   }

   private static void testPalindrome(String input) {
      
      // If input is null or empty, do not proceed further
      if (input == null || input.trim().isEmpty()) {
         System.out.println("Input is empty :(");
         return;
     }

      // Initialize boolean for whether the input is a palindrome or not
      boolean isPalindrome = false;

      /*
       * Get rid of all whitespaces and punctuation,
       * leaving only alphanumeric characters
       * with lowercase letters
       */
      String modInput = input.replaceAll(" ", "")
            .replaceAll("\\p{Punct}", "").toLowerCase();

      // Initialize deque of characters
      Deque<Character> deque = new LinkedList<>();

      // Loop through modified input and add characters to deque
      for (char c : modInput.toCharArray()) {
         deque.add(c);
      }

      // Special case for input that is one alphanumeric character
      if (deque.size() == 1) {
         isPalindrome = true;
      }

      /*
       * While deque is at least 2 characters, loop over the deque.
       * Compare and remove the first and last character.
       * If first and last are not the same character, input is not palindrome
       * and instantly break if-else loop.
       * Otherwise, if the first and last continue to match, the input is a
       * palindrome.
       */
      while (deque.size() > 1) {
         if (deque.removeFirst() != deque.removeLast()) {
            isPalindrome = false;
            break;
         } else {
            isPalindrome = true;
         }
      }

      // Print result of boolean after comparing characters and print out original
      // input
      if (isPalindrome) {
         System.out.printf("Yes, \"%s\" is a palindrome.%n", input);
      } else {
         System.out.printf("No, \"%s\" is not a palindrome.%n", input);
      }
   }
}