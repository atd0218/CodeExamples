package com.javaoo.store.drivers;

import java.util.Date;

import com.javaoo.store.Artist;
import com.javaoo.store.Book;
import com.javaoo.store.CD;
import com.javaoo.store.ClassicalCD;
import com.javaoo.store.Inventory;
import com.javaoo.store.Item;

/**
 * @name Ashton Daniels
 * date: 02/21/2024
 * JDK Version: 8
 * Description: 
 * 
 * inventoryDriver.java
 * 
 * This class holds my main method which I use to instantiate inventory objects 
 * into an array of Items which is a different class. Then I call the inventory class using static syntax 
 * in order to print out each inventory item neatly in my Items array. 
 * 
 * The time complexity of initializing the inventory array with items is O(n), where n is the number of 
 * items in the array. This is because each item is individually assigned to a specific index in the array, 
 * which takes constant time.
 * 
 * The space complexity of the inventory array is O(1) because the array has a fixed size of 50 and does not grow 
 * with the number of items added to it. The space complexity of creating each individual item object is also O(1) 
 * because each item object takes up a constant amount of memory regardless of the number of items in the inventory.
 * 
 * 
 * Artist.java
 * 
 * This class was created to store the details about an artist such as the Name. This class can then be called and 
 * instantiated by the driver class in order to create Artist objects to go with that inventory item. 
 * 
 * The time complexity of the Artist class is O(1) for both the constructor and the getters/setters. This is because 
 * regardless of the size of the input, the operations performed in these methods will take a constant amount of time.
 * 
 * The space complexity of the Artist class is O(1) as well. This is because the class only contains a fixed number of 
 * instance variables (name, memberNames, memberInstruments) that do not depend on the size of the input. The size of 
 * the arrays memberNames and memberInstruments is fixed at 20, so the space complexity remains constant.
 * 
 * Book.java
 * 
 * This class was created to store the details about a Book such as the Title, price, quantity. This class can then be 
 * called and instantiated by the driver class in order to create Book objects to go with that inventory item. 
 * 
 * The time complexity of the Book class is O(1) for both getters and setters. This is because accessing or modifying the 
 * author, publisher, and category fields does not involve any loops or recursive operations, and the operations are constant 
 * time regardless of the size of the input.
 * 
 * The space complexity of the Book class is also O(1) because the amount of memory required to store the author, publisher, and 
 * category fields does not depend on the size of the input. Only a fixed amount of memory is allocated for each instance of the 
 * Book class, regardless of the number of books being created.
 * 
 * CD.java
 * 
 * This class was created to store the details about a Book such as the Title, price, quantity, Artist, releasedate. This class 
 * can then be called and instantiated by the driver class in order to create Book objects to go with that inventory item. 
 * 
 * The time complexity of the CD class is O(1) for both getters and setters. This is because accessing and setting the artist and 
 * release date variables are constant time operations, regardless of the size of the input.
 * 
 * The space complexity of the CD class is also O(1) because the amount of memory used by the class does not depend on the size of 
 * the input. The class only stores references to the artist and release date objects, which are constant size regardless of the 
 * input.
 * 
 * Inventory.java
 * 
 * This class was created to hold one method which will take in the Items[] from main and print the items out in a format that looks
 * good and is human readable. 
 * 
 * The time complexity of this code is O(n), where n is the number of items in the input array. This is because the code iterates 
 * through each item in the array once to print out the item details and calculate the total value of all items.
 * 
 * The space complexity is O(1) because the additional space used by the code is constant regardless of the size of the input array.
 * The variables count and sum are the only additional space used, and they do not depend on the size of the input array.
 * 
 * Item.java
 * 
 * This is the main item class that stores the in common attributes between all other items. This class is a parent to Book, CD,
 * and ClassicalCD. Each of the subclasses then have their own unique attributes but if price, quantity, and title are added to 
 * an item they must go through this parent class in order to do so. 
 * 
 * The time complexity of the getters and setters in the Item class is O(1) because accessing or modifying a single variable does 
 * not depend on the size of the input. 
 * 
 * The space complexity of the Item class is O(1) because the amount of memory used by an instance of the class does not depend on 
 * the size of the input. Each instance of the Item class will only store the title, price, and quantity variables, regardless of 
 * the size of the input.
 */

public class inventoryDriver {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        Item[] myInventory = new Item[50];

        myInventory[0] = new Book("\"test1\"", 20.00, 1, "Author1", "Publisher1", "Category1");
        myInventory[1] = new Book("\"test2\"", 20.00, 1, "Author2", "Publisher2", "Category2");
        myInventory[2] = new Book("\"test3\"", 20.00, 1, "Author3", "Publisher3", "Category3");
        myInventory[3] = new Book("\"test4\"", 20.00, 1, "Author4", "Publisher4", "Category4");
        myInventory[4] = new Book("\"test5\"", 20.00, 1, "Author5", "Publisher5", "Category5");
        myInventory[5] = new CD("\"Going For The One\"", 12.95, 4, new Artist("YES"), new Date("07/07/1977"));
        myInventory[6] = new CD("\"Going Down The Country\"", 12.95, 10, new Artist("Bozos"), new Date("09/07/1987"));
        myInventory[7] = new CD("\"Polka Favorites\"", 2.95, 4, new Artist("Jimmy and the Yuppers"), new Date("07/07/1955"));
        String[] performers1 = {"Henry", "Liz", "Franz", "Arther", "Tom"};
        myInventory[8] = new ClassicalCD("\"testClassicalCD1\"", 20, 2, "Robbie", performers1, "Grandmas House", new Date("07/07/1955"));
        
        Inventory.produceReport(myInventory);
    }
}
