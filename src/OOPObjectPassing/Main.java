package OOPObjectPassing;

public class Main {

    public static void main(String[] args)
    {

        // creating an instance of the garage object in main method
        Garage garage = new Garage();

        // creating an instance of the car object in main method
        Car car1 = new Car("BMW");
        Car car2 = new Car("Tesla");
        
        // calling the park method in the garage class to "Park" the cars. 
        garage.park(car1);
        garage.park(car2);
    }
    
}
