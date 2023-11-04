package objectOrientedProgramIntro;

public class Main {

    public static void main(String[] args)
    {

        // object = an instance of a class that may contain attributes and methods
        // example: (phone, desk, computer, coffee cup)
        // the objects/new files must all be in the same folder in order to be called by the main class/method

        // creating an instance of the other file that you are calling from. you can call a class/object multiple times. 
        Car myCar1 = new Car();
        Car myCar2 = new Car();

        // printing out specific attributes from the car desinged in the other file
        System.out.println(myCar1.make);
        System.out.println(myCar1.model);
        System.out.println();
        System.out.println(myCar2.make);
        System.out.println(myCar2.model);

        //call the methods from the other class (java file)
        System.out.println();
        myCar1.drive();
        myCar1.brake();

        myCar2.drive();
        myCar2.brake();


    }
    
}
