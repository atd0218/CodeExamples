import java.util.Scanner;

public class LogicalOperatorsAndOrNot {
    
    public static void main(String[] args) {

        //Logical operators
        // && = AND (all conditions must be true)
        // || = OR (either condition must be true)
        // ! = NOT reverses boolean value of condition

        int temp = 35;

        // and example
        if(temp>30) {

            System.out.println("It is hot");
        }
        else if (temp >= 20 && temp <= 30) { 

            System.out.println("It is a good temp");
        }
        else {

            System.out.println("Its cold brrr");
        }

        // or example

        Scanner scanner = new Scanner(System.in);

        System.out.println("You are playing a Game! press lower case q or uppercase q to quit");
        String response = scanner.next();

        if(response.equals("q") || response.equals("Q")) {

            System.out.println("The game has ended goodbye!");

        }
        else {
            System.out.println("You are still playing the game!");
        }

        // ! example

        Scanner scanner2 = new Scanner(System.in);

        System.out.println("You are playing a Game! press lower case q or uppercase q to quit");
        String response2 = scanner2.next();

        if(response2.equals("q") || response2.equals("Q")) {

            System.out.println("The game has ended goodbye!");

        }
        else if (!response2.equals("q") || !response2.equals("Q")) {
            System.out.println("You are still playing the game!");
        }

    }
}
