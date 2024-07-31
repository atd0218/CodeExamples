import java.util.*;

public class FlowControl {
  //Name: Ashton Daniels
  //FileName: FlowControl.java
  //Description: Working with different flow control mechanisms
  //JDK Version: 8
  public static void main (String[] args) {
    //Start your code here

    //create the array variable to hold days in the week

    String [] daysInWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    //print out days of week using standard for loop
    System.out.println("Standard For Loop");
    for (int i = 0; i < daysInWeek.length; i++) {
      System.out.println(daysInWeek[i]);
    }

    //print out days of week using for each loop
    System.out.println("For Each Loop");
    for (String s : daysInWeek) {
      System.out.println(s);
    }

    //display the days of the week in reverse
    System.out.println("Reverse Days Of the week");
    for (int i = 6; i >= 0; i--) {
      System.out.println(daysInWeek[i]);
    }

    //WHILE LOOP

    //create a while loop to print out only even numbers between 1 and 20
    System.out.println("Print even numbers between 1 and 20");
    int counter = 0;
    while (counter++ <= 20) {
        if (counter % 2 == 0) {
          System.out.println(counter);
        }
    }

    //modify the above to use the continue statement
    System.out.println("Print even numbers between 1 and 20 using continue statement");
    counter = 0;
    while (counter++ <= 20) {
        if (counter % 2 == 0) {
          System.out.println(counter);
        }
        else if (counter % 2 != 0){
          continue;
        }
    }

    //print out all numbers between 1 and 100 except 50 through 60

    System.out.println("Print numbers 1 to 100 except 50 through 60");
    for (int i = 1; i <= 100; i++) {
      if (i >= 50 && i <= 60) {
        continue;
      }
      System.out.println(i);
    }

    //SWITCH STATEMENT 

    //print out the days and months with a while loop
    System.out.println("Switch Statment to list monthds and days");
    int c = 0;
    String [] monthNames = {"January", "February", "March", "April", 
    "May", "June", "July", "August", "September", "October", "November", "December"};
    int numOfDays = 0;
    while(c++ < 12) {
      switch (c) {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
          numOfDays = 31;
          break;
        case 2:
          numOfDays = 28;
          break;
        case 4:
        case 6:
        case 9: 
        case 11:
          numOfDays = 30;
          break;
      }
      System.out.println(monthNames[c - 1] + " has " + numOfDays + " days ");
    }


    //create a calendar and have it output to terminal a certain way
    System.out.println("Start of output for Challenge Excersize");
    System.out.print("   Sun ");
    System.out.print("  Mon ");
    System.out.print("  Tue ");
    System.out.print("  Wed ");
    System.out.print("  Thu ");
    System.out.print("  Fri ");
    System.out.print("  Sat" + "\n");
     int spaces = 5; 
     if (spaces < 0) 
         spaces = 6; 

     // Printing the calendar 
     for (int i = 0; i < spaces; i++) 
         System.out.print("      "); 
     for (int i = 1; i <= 31 ; i++) { 
         System.out.printf(" %4d ", i); 
        //apply a new line at the end of each 7 day period
         if (((i + spaces) % 7 == 0) 
             || (i == 31)) 
             System.out.println(); 
     } 


  }
}
