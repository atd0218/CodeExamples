//Imports
import java.util.Scanner;

public class UserInput {
    
    public static void main(String[] args){

        //Scanner - found in java utility library

        Scanner scanner = new Scanner(System.in);

        System.out.println("What is your name?");
        String name = scanner.nextLine();

        System.out.println("How old are you?");
        int age = scanner.nextInt();

        //because of the way scanner works you can't use another thing after nextInt until you flush the buffer so it is free to accept another input. 
            //scanner looks for \n to trigger it to empty the buffer but int does not so we have to do it manually. That is why a new line is showing in the program. 

        //option 1 to fix is to add a nextLine to flush the buffer
        scanner.nextLine();
        
        System.out.println("What is your favorite food?");
        String food = scanner.nextLine();

        System.out.println("Hello "+name);
        System.out.println("You are "+age +" years old!");
        System.out.println("You favorite food is "+food);        

    }
}
