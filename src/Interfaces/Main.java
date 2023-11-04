package Interfaces;

public class Main {

    //interface = a template that can be applied to a class
    //            similiar to inheritance, but specifies what a class has/must do.
    //            classes can apply more than one interface, interitance is limited to 1 super and child class


    public static void main(String[] args)
    {
        Rabbit rabbit = new Rabbit();
        Hawk hawk = new Hawk();
        Fish fish = new Fish();

        rabbit.flee();
        hawk.hunt();
        fish.flee();
        fish.hunt();

    }
    
}
