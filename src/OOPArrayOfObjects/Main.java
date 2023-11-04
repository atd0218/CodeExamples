package OOPArrayOfObjects;

public class Main {

    public static void main(String[] args)
    {

        //arrays can be used to store a list of objects results
        //arrays must have their sizes pre defined or items assigned at initialization

        // create a unique food object because we created a constructor in the object allowing for it. 
        Food food1 = new Food("pizza");
        Food food2 = new Food("hamburger");
        Food food3 = new Food("hotdog");

        // how to create an array of objects.
        // you must call the object as the data type when wanting to store them.
        Food[] refrigerator = {food1,food2,food3};

        //or
        //refrigerator[0] = food1;
        //refrigerator[1] = food2;
        //refrigerator[2] = food3;

        // dont have to add .name after as I created a toString() method override to have it print the entire object. 
        System.out.println(refrigerator[0]);
        System.out.println(refrigerator[1]);
        System.out.println(refrigerator[2]);
    }
    
}
