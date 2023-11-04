package methodOverriding;

public class Dog extends Animal{


    // we are overwriting this method from the animal class to give it a specific meaning for dog. 
    @Override
    void speak()
    {
        System.out.println("The dog goes BARK BARK!!");
    }


    
}
