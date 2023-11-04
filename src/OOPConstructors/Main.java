package OOPConstructors;

public class Main {

    //constructor = special method that is called when an object is instantiated (created)

    public static void main(String[] args)
    {
        //unique calling of the same Human class/object
        Human human = new Human("Ash", 26, 215.12); // the end is the constructor and this can allows us to call the same object multiple times with unique results.
        Human human2 = new Human("Tom", 28, 223.54); // the end is the constructor and this can allows us to call the same object multiple times with unique results.
        
        System.out.println(human.name);
        System.out.println(human.age);
        System.out.println(human.weight);
        System.out.println(human2.name);
        System.out.println(human2.age);
        System.out.println(human2.weight);

        //calling the Human methods for each Human object we referenced above. 

        System.out.println();
        human.drink();
        System.out.println();
        human.eat();
        System.out.println();
        human2.drink();
        System.out.println();
        human2.eat();
        
    }
    
}
