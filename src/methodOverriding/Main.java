package methodOverriding;

public class Main {
    
    //method overriding = Declaring a method in sub class,
    //                    which is already present in a parent class. 
    //                    done so that a child class can give its own implementation
    public static void main(String[] args)
    {
        Animal animal = new Animal();
         Dog dog = new Dog();

         // both classes have a speak method, but we overrode it for dogs to be more specific. 
         dog.speak();
         animal.speak();
        
    }
  
}
