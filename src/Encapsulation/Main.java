package Encapsulation;

public class Main {

    public static void main(String[] args)
    {
        // encapsulation == attributes of a class will be hidden or private,
        //                  can be accessed only through methods (getters & setters)
        //                  You should make attirubetes private if you don't have a good reason for them being public.

        //option 1
        // Car car = new Car("Tesla", "Roadster", 2022);

        // System.out.println(car.getMake());
        // System.out.println(car.getModel());
        // System.out.println(car.getYear());

        //option 2

        Car car = new Car("Tesla", "Roadster", 2022);

        car.setYear(2024);

        System.out.println(car.getMake());
        System.out.println(car.getModel());
        System.out.println(car.getYear());
    }
    
}
