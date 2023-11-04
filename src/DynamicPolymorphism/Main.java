package DynamicPolymorphism;

import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        //Dynamic Polymorphism
        // polymorphism = objects have multiple data types
        // dynamic = after compilation (during runtime)

        Scanner scanner = new Scanner(System.in);
        Animal animal;

        System.out.println("What animal do you want?");
        System.out.print("(1=cat) or (2=dog): ");
        int choice = scanner.nextInt();

        if(choice == 1)
        {
            animal = new Cat();
            animal.speak();
        }
        else if(choice == 2)
        {
            animal = new Dog();
            animal.speak();
        }
        else
        {
            animal = new Animal();
            animal.speak();
        }
        
    }
    
}
