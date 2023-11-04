import java.util.ArrayList;

public class arrayList {
    
    public static void main(String[] args) {

        //ArrayList = a resizable array
        //            Elements can be added and removed after compilation phase
        //            these can only store reference data types

        ArrayList<String> food = new ArrayList<String>();

        //add items to array list
        food.add("pizza");
        food.add("hamburger");
        food.add("hotdog");

        // // update items in array list
        food.set(0, "Sushi");

        // //delete items in array list
        food.remove(2);

        //clear array list
        food.clear();

        for (int i = 0; i < food.size(); i++) {

            System.out.println(food.get(i));
        }
        
    }
}
