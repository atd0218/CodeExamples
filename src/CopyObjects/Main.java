package CopyObjects;

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

        Car car1 = new Car("Tesla", "Roadster", 2022);
        

        //copy values from car 1 to car 2

        //BAD EXAMPLE FIRST they are the same car with the same address. We just gave car 1 two different names
            //car2 = car1;

        //Better example
            // Car car2 = new Car("Bugati", "Veron", 2024);
            // car2.copy(car1);

        //other example using overloaded constructor in Car class

        Car car2 = new Car(car1);

        System.out.println(car1);
        System.out.println(car2);
        System.out.println();
        System.out.println(car1.getMake());
        System.out.println(car1.getModel());
        System.out.println(car1.getYear());
        System.out.println();
        System.out.println(car2.getMake());
        System.out.println(car2.getModel());
        System.out.println(car2.getYear());
        

    }
    
}
