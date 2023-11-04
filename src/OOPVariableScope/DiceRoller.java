package OOPVariableScope;

import java.util.Random;

public class DiceRoller {
    
    // these have to be set as global variables so they can be used by both of the below methods. 
    Random random;
    int number;

    DiceRoller()
    {
        // if you create the variables in here you will need to pass them into the roll method if you want to use them. 
        random = new Random();
        roll();
    }

    void roll()
    {
        number = random.nextInt(6)+1;
        System.out.println(number);
    }
}
