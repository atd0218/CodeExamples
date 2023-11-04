import java.util.ArrayList;

public class forEach {
    
    public static void main(String[] args)
    {

        //for each: traversing technique to iterate through the elements in an array/collection
        //          less steps, more readable than a normal for loop
        //          less flexible than for loop
        // ArrayList is a type of collection 

        String[] animals = {"Cat", "dog", "Rat", "bird"};
        // ArrayList<String> animals = new ArrayList<String>();
        
        // animals.add("Cat");
        // animals.add("Dog");
        // animals.add("rat");
        // animals.add("Bird");

        for(String i : animals) 
        {

            System.out.println(i);
            
        }
        

    }
}
