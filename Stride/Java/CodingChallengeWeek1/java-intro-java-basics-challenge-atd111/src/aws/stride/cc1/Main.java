/**
 * Name: Ashton Daniels
 * FileName: Main.java
 * Date: 01/10/2024
 * JDK Version: 8
 * Description: Main class that will handle the testing for the program.
 */

package aws.stride.cc1;

import java.util.Arrays;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        //create 2 instances/objects of the TellerDrawer class
        TellerDrawer teller1 = new TellerDrawer(2500.00);
        TellerDrawer teller2 = new TellerDrawer(2500.00);

        // create the array of people that will be visiting the tellers
        String [] visitors = {"Jennifer Figueroa",
        "Heather Mcgee",
        "Amanda Schwartz",
        "Nicole Yoder",
        "Melissa Hoffman",
        "Beatrice Helman",
        "Louis Sanders",
        "Catherine Altman",
        "Ralph Estees",
        "Samantha Augustine",
        "Peter Fredricks",
        "David Alters"};

         //create an ArrayList to hold the visitors per teller
        ArrayList<String> teller1Visitors = new ArrayList<String>();
        ArrayList<String> teller2Visitors = new ArrayList<String>();

        //create a for loop to loop through all of the visitors and assign them
        //to the newly created ArrayLists to store their names
        for(int i = 0; i < visitors.length; i++) {
            if (i % 2 == 0) {
                teller1Visitors.add(visitors[i]);
            }
            else {
                teller2Visitors.add(visitors[i]);
            }
        }

        //print a list of teller visitors
        System.out.println("Teller 1 Visitors");
        System.out.println(teller1Visitors);

        System.out.println("Teller 2 Visitors");
        System.out.println(teller2Visitors);

        //create a for loop to loop through the visitors and handle the logic for withdrawals 
        //and deposits additionally print the individual transactions
        System.out.println();
        System.out.println("Printing transactions for each visitor");
        for(int i = 0; i < visitors.length; i++) {

            if (i % 2 == 0) {
                if (i % 3 == 0) {
                    teller1.subBalance(250.00);
                    System.out.println(visitors[i] + " Withdrew $250.00 from their account the current teller balance is "
                    + "$"+ teller1.getBalance());
                }
                else if (i% 5 == 0) {
                    teller1.addBalance(475.00);
                    System.out.println(visitors[i] + " Deposited $475.00 into their account the current teller balance is "
                    + "$"+ teller1.getBalance());
                }
                else {
                    teller1.addBalance(100.00);
                    System.out.println(visitors[i] + " Deposited $100.00 into their account the current teller balance is "
                    + "$"+ teller1.getBalance());
                }
            }
            else {
                if (i % 3 == 0) {
                    teller2.subBalance(250.00);
                    System.out.println(visitors[i] + " Withdrew $250.00 from their account the current teller balance is "
                    + "$"+ teller2.getBalance());
                }
                else if (i% 5 == 0) {
                    teller2.addBalance(475.00);
                    System.out.println(visitors[i] + " Deposited $475.00 into their account the current teller balance is "
                    + "$"+ teller2.getBalance());
                }
                else {
                    teller2.addBalance(100.00);
                    System.out.println(visitors[i] + " Deposited $100.00 into their account the current teller balance is "
                    + "$"+ teller2.getBalance());
                }
            }

        }

        //print out the balance for each teller at the end of the transactions
        System.out.println();
        System.out.println("Final Drawer Balances");
        System.out.println("Teller 1s final balance for the day: " + "$"+ teller1.getBalance());
        System.out.println("Teller 2s final balance for the day: " + "$"+ teller2.getBalance());
        
    }
}
