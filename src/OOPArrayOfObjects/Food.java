package OOPArrayOfObjects;

public class Food {

    String name;

    Food(String name)
    {
        this.name = name;
    }

    //to String to allow the println statment to automatically print the object. 
    public String toString()
    {
        String myString = name;
        return myString;
    }
    
}
