package Abstraction;

public abstract class Vehicle {

    // by creating a class with the keyword abstract it means you can't create an instance of it in a seperate class. 
    // sub classes however can still call and use methods from the parent class.
    // IN practice you would see this for classes that are to abstract or vague such as vehicle because there are different types of vehicles. 

    abstract void go();
    // you can't add a body to an abstract method
    // this forces us to implement this method in one of the child classes
    
}
