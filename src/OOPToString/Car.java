package OOPToString;

public class Car {

    String make = "ford";
    String model = "Mustang";
    String color = "red";
    int year = 2021;

    //method overwriting toString method

    public String toString()
    {
        String myString = make + " " + model + " " + color + " " + year;
        return myString;
    }
    
}
