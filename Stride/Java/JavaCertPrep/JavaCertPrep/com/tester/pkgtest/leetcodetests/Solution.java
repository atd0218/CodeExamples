package com.tester.pkgtest.leetcodetests;

class Solution {

    /*
    Slow way to do this uses O(n^2) since it is a nested loop
     */
//    public static String firstPalindrome(String[] words) {
//
//        String backwardsWord [] = new String[words.length];
//
//        for (int i = 0; i < words.length; i++) {
//            backwardsWord[i]="";
//            for (int j = words[i].length() - 1; j >= 0; j--) {
//                backwardsWord[i] += words[i].charAt(j);
//            }
//            if (words[i].equals(backwardsWord[i])) {
//                return words[i];
//            }
//            else {
//                continue;
//            }
//
//        }
//        return "";
//


    /*
    more efficient way to do this as there is only an if statement and one for loop so its O(n)
     */
    public static String firstPalindrome(String[] words) {
        for (String word : words) {
            StringBuilder reversed = new StringBuilder(word).reverse();
            if (word.equals(reversed.toString())) {
                return word;
            }
        }
        return "";
    }

    public static void main(String[] args) {
        String words [] = {"abc","car","ada","racecar","cool"};
        firstPalindrome(words);
    }
}
