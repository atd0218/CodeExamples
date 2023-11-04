package Interfaces;

public class Fish implements Prey,Predator {

    public void flee()
    {
        System.out.println("I am a small fish swim fast");
    }

    public void hunt()
    {
        System.out.println("I will eat you small fishy");
    }
    
}
