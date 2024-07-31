/**
 * @name: Ashton Daniels
 * Date: 03/22/2024
 * Description: 
 * 
 * BookExerciser.java
 * 
 * This is the class that was created for the lab to hanlde all of the work. 
 * 
 * Steps 1:
 * 
 * 1. Create a .txt file and add Name, Author, and Price on each field and repeat for as many books as you would like. 
 * 2. in main store the location of the book as well as create a book placeholder to store a list of your books. 
 * Then write a for each loop to loop through the list of books and print out the title. 
 * 3. In the readBooksFromFile this takes in a file name and reads the file which is converted from the bytes by FileInputStream 
 * up to a line by line read using LineNumberedReader. 
 * Then it loops through the file and stores the Title, Author, and Price and uses those to create a new Book and add it to the 
 * books array list. 
 * 
 * books.txt
 * 
 * txt file to store book informaiton stored in order of Title, Author, and price on one line each. 
 */

package com.javaoo.store.drivers;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import com.javaoo.store.Book;

public class BookExerciser {

  public static void main(String[] args) {
     
    String location = "/Users/uashtoda/Documents/StriDE/Java/java2-lab-inputoutput-atd111/src/com/javaoo/store/drivers/books.txt";
    List<Book> books = readBooksFromFile(location);

    for (Book book : books) {
      System.out.println(book.getTitle());
    }
  }

  @SuppressWarnings("null")
  public static List<Book> readBooksFromFile(String name) {

    List<Book> books = null;

    try (FileInputStream byteInput = new FileInputStream(name);
         InputStreamReader charInput = new InputStreamReader(byteInput);
         LineNumberReader finalInput = new LineNumberReader(charInput);)
    {
      books = new ArrayList<>();
      String line;
      while ((line = finalInput.readLine()) != null) {
          System.out.printf("%d: %s%n", finalInput.getLineNumber(), line);
          String title = line; 
          System.out.println(title);
          String author = finalInput.readLine();
          System.out.println(author);
          double price = Double.parseDouble(finalInput.readLine());
          System.out.println(price);
          System.out.printf("Book: [Title: %s, Author: %s, Price: $%.2f%n]", title, author, price);
          books.add(new Book(title, price, 5, author, "Test", "Non-Fiction"));
      }
    }
    catch (IOException e) 
    {
        e.printStackTrace();
    }
      
    return books;
  }

}
