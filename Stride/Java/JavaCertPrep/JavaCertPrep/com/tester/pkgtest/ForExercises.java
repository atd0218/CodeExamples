package com.tester.pkgtest;
import java.util.ArrayList;
import java.util.Scanner;
public class ForExercises {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

//        forLargestSmallest();
//        forHistogram();
        forAverage();

    }

    public static void forLargestSmallest() {

        System.out.println("Please enter the number of times the loop will run: ");
        int n = sc.nextInt();

        if (n < 0) {

            System.out.println("The number is not valid please enter a number greater than 0");

        } else {
            ArrayList<Integer> ints = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) {
                System.out.println("Enter a number: ");
                int temp = sc.nextInt();
                ints.add(temp);
            }
            int size = ints.size();
            int min = ints.get(0);
            int max = ints.get(0);

            for (int i = 0; i < size; i++) {
                if (ints.get(i) < min) {
                    min = ints.get(i);
                }
            }

            for (int i = 0; i < size; i++) {
                if (ints.get(i) > max) {
                    max = ints.get(i);
                }
            }

            System.out.println("The largest number is " + max);
            System.out.println("The smallest number is " + min);
        }

    }

    public static void forHistogram() {

        System.out.println("Please enter the number of rows ");
        int rows = sc.nextInt();

        System.out.println("Please enter the number of columns ");
        int cols = sc.nextInt();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

    }

    public static void forAverage() {

        System.out.println("Please enter the number of times the loop will run: ");
        int n = sc.nextInt();

        if (n < 0) {

            System.out.println("The number is not valid please enter a number greater than 0");

        } else if (n == 0) {
            System.out.println("Cant Divide by 0.");
        } else {
            ArrayList<Integer> ints = new ArrayList<Integer>();
            int sum = 0;
            for (int i = 0; i < n; i++) {
                System.out.println("Enter a number: ");
                int num = sc.nextInt();
                ints.add(num);
            }

            double average = ints.stream().mapToDouble(d -> d).average().orElse(0.0);
            System.out.println(average);
        }

    }

}