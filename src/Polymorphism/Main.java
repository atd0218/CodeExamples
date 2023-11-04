package Polymorphism;

public class Main {

    public static void main(String[] args)
    {
        //polymorphism = greek word for poly-"many", morph-"form"
        //               the ability of an object to identify as more than one type
        // because vehicle is the parent class for all of the racer classes then we can have a vehicle array store all its child class objects. 

        Car car = new Car();
        Bicycle bike = new Bicycle();
        Boat boat = new Boat();

        Vehicle[] racers = {car, bike, boat};

        //enhanced for loop or for each loop
        for(Vehicle x : racers)
        {
            x.go();
        }
    }
    
}
