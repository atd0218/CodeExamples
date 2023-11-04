import java.util.Scanner;

public class WhileLoop {
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String name = "";
        String name2 = "";

        // THIS MIGHT NOT EVEN PERFORM ONCE AS CONDITION IS CHECKED FIRST
        while(name.isBlank()) {

            System.out.print("Enter your name: ");
            name = scanner.nextLine();
        }

        System.out.println("Hello "+name);

        // THIS WILL ALWAYS RUN AT LEAST ONCE
        do{

            System.out.print("Enter your name: ");
            name2 = scanner.nextLine();

        }while(name.isBlank());

    }
}
