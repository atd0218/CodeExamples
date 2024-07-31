package com.tester.pkgtest;

import java.util.Scanner;

public class Operators {

    //initiate a scanner class
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Enter the first number:" );
        int x = sc.nextInt();

        System.out.println("Enter the second number: ");
        int y = sc.nextInt();

        logicalOps(x, y);
        simpleMaths(x,y);
        modulus(x,y);
        incrDecr();

    }

    public static void logicalOps(int x, int y) {

        if (x > 0 && y > 0) {
            System.out.println("Both numbers are positive");
        }
        else if (x < 0 && y < 0) {
            System.out.println("Both numbers are negative");
        }
        else if (x == 0 || y == 0) {
            System.out.println("One of the numbers is zero");
        }
        else if(x < 0 || y < 0) {
            System.out.println("One of the numbers is negative");
        }
        else {
            System.out.println("What do you want from me anyways");
        }

    }

    public static void simpleMaths(int x, int y) {

        int sum = x + y;

        int product = x * y;

        int difference = x - y;

        int quotient = x / y;

        System.out.println("The sum of " + x + " and " + y + " equals " + sum);

        System.out.println("The product of " + x + " and " + y + " equals " + product);

        System.out.println("The difference of " + x + " and " + y + " equals " + difference);

        System.out.println("The quotient of " + x + " and " + y + " equals " + quotient);

    }

    public static boolean modulus(int x, int y) {

        return (x % y == 0) ? true : false;

    }

    public static void incrDecr() {
        int x=0, w=0, y=0, z=0; x=4;
        System.out.println(++x);
        System.out.println(x++);
        System.out.println(x);
        System.out.println(--x);
        System.out.println(x--);
        System.out.println(x);
        w=20;
        x=10;
        y=-5;
        z=0;
        System.out.println( w == x && y != z );
        System.out.println( w == x || y != z );
        System.out.println( ! ( w == (x + z) ) );


    }
}