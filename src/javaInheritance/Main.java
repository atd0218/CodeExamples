package javaInheritance;

public class Main {

    public static void main(String[] args)
    {
        //inheritance = the process where one class acquires
        //              the attributes and methods of another.
        // the extend keyword must be used when creating a class to inherit the methods and attributes from another class. 

        Car car = new Car();

        car.go();

        Bicycle bike = new Bicycle();

        bike.stop();

        System.out.println(car.doors);
        System.out.println(bike.pedal);
    }
    
}
