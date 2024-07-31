package com.javaoo.store;
public class Inventory {

    public static void produceReport(Item[] items) {
        int count = 1;
        double sum = 0.0;
        try {
            for(int i = 0; i <= items.length; i++) {
                System.out.println("Item " + count + "'s Title is: " + items[i].getTitle() + " the Price is: " + items[i].getPrice()
                + " and there is " + items[i].getQuantity() + " in stock");
                count++;
                sum += items[i].getPrice();
            }
        } catch (NullPointerException e) {
            System.out.println("The total count of items is: " + (count - 1));
            System.out.println("The total value of all items is: " + sum);
        }
    }
    
}
