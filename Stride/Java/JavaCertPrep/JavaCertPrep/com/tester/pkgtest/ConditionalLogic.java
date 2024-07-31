package com.tester.pkgtest;

import java.util.Scanner;

public class ConditionalLogic {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

//        preAndPostDifference();
//        booleanLogicShortCircuitOps();
//        booleanBitwiseOps();
//        compareStrings();
//
//        System.out.println("Please provide a certifiaction for the film: ");
//        int cert = sc.nextInt();
//
//        System.out.println("Please provide me your age for verification: ");
//        int age = sc.nextInt();
//
//        boolean admit = admitToFilm(cert, age);
//        System.out.println(admit);

        switchVowelOrConsonant();

    }

    public static void preAndPostDifference() {

        int x = 5;
        int y = 10;

        System.out.println("The value of x is " + x);
        System.out.println("The value of ++X is " + ++x);
        System.out.println("The value of x++ is " + x++);
        System.out.println("The value of x is " + x);

        System.out.println("The value of y is " + y);
        System.out.println("The value of --y is " + --y);
        System.out.println("The value of y-- is " + y--);
        System.out.println("The value of y is " + y);



    }

    public static void booleanLogicShortCircuitOps() {

        boolean b = false;
        int x = 3;

        if (x < 0 && (b=true)) {}
        System.out.println(b);

        if (x > 0 || (b=true)) {
        }
        System.out.println(b);

    }
    public static void booleanBitwiseOps() {

        boolean b = false;
        int x = 3;

        if (x < 0 & (b=true)) {}
        System.out.println(b);

        if (x > 0 | (b=true)) {
        }
        System.out.println(b);

    }

    public static void compareStrings() {

        System.out.println("Please enter a string");
        String s1 = sc.nextLine();

        System.out.println("Please enter another string");
        String s2 = sc.nextLine();

        System.out.println(s1 == s2);

        System.out.println(s1.equals(s2));

    }

    public static boolean admitToFilm(int x, int y) {
        if (x > y) {
            return false;
        }
        else {
            return true;
        }
    }

    public static void switchVowelOrConsonant () {

        System.out.println("Please enter a character");
        char letter = sc.next().charAt(0);

        if ( ( (letter >= 'a') && (letter <= 'z')) || ( (letter >= 'A') && (letter <= 'Z') ) ) {
            if (letter == 'A' || letter == 'E' ||letter == 'I' ||letter == 'O' ||letter == 'U'
                    || letter == 'a' ||letter == 'e' ||letter == 'i' ||letter == 'o' ||letter == 'u' ) {
                System.out.println(letter + " is a vowel");
            }
            else if ((letter >= 'A') && (letter <= 'Z')) {
                String l = Integer.toHexString(letter);
                System.out.println(l);
            }
            else {
                System.out.println(letter + " is a consonant");
            }
        }

    }

    public static void ifMonth() {

    }

    public static void ifGrade() {

    }

    public static void switchMathOperation() {

    }

    public static void ifTemperature () {

    }

    public static void switchDaysInMonth () {

    }

}
