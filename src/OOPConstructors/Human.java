package OOPConstructors;

public class Human {

    String name;
    int age;
    double weight;

    // constructor 
    Human(String name, int age, double weight)
    {
        // using this allows the attributes passed into a human object in the main method to be used. This keyword allows us to have unique values of the same class/object
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    //methods of the class/object
    void eat()
    {
        System.out.println(this.name+" is eating");
    }

    void drink()
    {
        System.out.println(this.name+" is drinking *burp*");
    }
    
}
