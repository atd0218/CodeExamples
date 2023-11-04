package ExceptionHandling;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    

    public static void main(String[] args)
    {
        //exception = an event that occurs during the execution of a program that
        //            disrupts the normal flow of instructions

        Scanner scanner = new Scanner(System.in);

        try
        {
            System.out.println("Enter a whole number to divide: ");
            int x = scanner.nextInt();

            System.out.println("Enter a whole number to divide by: ");
            int y = scanner.nextInt();

            int z = x/y;

            System.out.println("Result: " + z);
        }
        catch(ArithmeticException e)
        {
            System.out.println("You can't divide by 0");

        }
        catch(InputMismatchException e)
        {
            System.out.println("You must enter a number you dumb a**");
        }
        catch(Exception e) //lazy to do you want to make sure you are handling the exceptions you would expect. 
        {
            System.out.println("Something went wrong");
        }
        finally
        {
            // good to use to close any scanners or files that might be open.
            scanner.close();
        }
    }
}
