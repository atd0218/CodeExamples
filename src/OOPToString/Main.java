package OOPToString;

public class Main {

    public static void main(String[] args)
    {

        //toString() = special method that all objects inherit
        //             that returns a string that "textually represents" an object.
        //             can be used both implicitly and explicitly

        Car car = new Car();

        //allows us to display all of the values in the car class
        System.out.println(car.make);
        System.out.println(car.model);
        System.out.println(car.color);
        System.out.println(car.year);

        // we overrode the toString method to return a string representation of all of the attributes of an object. In this case the car obejct/file
        //when we use print or println we also implicitally call the toString method
        System.out.println(car.toString());

        System.out.println(car);
    }
    
}
