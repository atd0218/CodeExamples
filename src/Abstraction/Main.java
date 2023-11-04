package Abstraction;

public class Main {

    // abstract = abstract classes cannot be instantiated, but they can have a subclass
    //            abstract methods are declared without an implementation
    //            abstract classes add a layer of security to the application by controlling classes better. 

    public static void main(String[] args)
    {
        //Vehicle vehicle = new vehicle();
        Car car = new Car();

        car.go();
    }
    
}
