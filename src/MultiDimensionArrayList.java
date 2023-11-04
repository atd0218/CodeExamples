import java.util.ArrayList;

public class MultiDimensionArrayList {
    
    public static void main (String[] args) {

        // 2D ArrayList = a dynamic list of lists
        // You can change the size of these lists during runtime

        ArrayList<ArrayList<String>> groceries = new ArrayList();

        ArrayList<String> bakeryList = new ArrayList();
        bakeryList.add("Pasta");
        bakeryList.add("garlic bread");
        bakeryList.add("donuts");

        ArrayList<String> produceList = new ArrayList();
        produceList.add("Apple");
        produceList.add("Banana ");
        produceList.add("Spinach");

        ArrayList<String> junkList = new ArrayList();
        junkList.add("Soda");
        junkList.add("Candy");
        junkList.add("Ice Cream");


        groceries.add(bakeryList);
        groceries.add(produceList);
        groceries.add(junkList);

        //get one specific item in one list inside the parent list. 
        System.out.println(groceries.get(0).get(0));

        for (int i = 0; i < groceries.size(); i++) {

            System.out.println(groceries.get(i));
        }




    }
}
