/**
 * @name: Ashton Daniels
 * Date: 03/21/2024
 * Description: 
 * 
 * Artist.java
 * Added 4 new methods to this class using the SortedSet and Maps specifically TreeSet and TreeMap.
 * 
 * Added a new constructor to initilize the new variables that were created as TreeSet and TreeMap. 
 * 
 * ArtistExerciser.java
 * 
 * Class to run and test all of the methods created in the Artist.java class. 
 */

package com.javaoo.store.drivers;

import java.util.SortedSet;
import java.util.TreeSet;

import com.javaoo.store.Artist;

public class ArtistExerciser {

  public static void main(String[] args) {

    try {

      Artist hotPlate = new Artist("hotPlate");
      SortedSet<String> instruments1 = new TreeSet<>();
      instruments1.add("Piano");
      instruments1.add("Clarinet");
      instruments1.add("Hurdy Gurdy");
      instruments1.add("Tuba");
      hotPlate.addMember("Tom", instruments1);

      SortedSet<String> instruments2 = new TreeSet<>();
      instruments2.add("Piano");
      instruments2.add("Trombone");
      instruments2.add("Flute");
      instruments2.add("Percussion");
      hotPlate.addMember("Steve", instruments2);

      printMemberInstruments(hotPlate, "Tom");
      printMemberInstruments(hotPlate, "Steve");
      //used to test caught exception.
      //printMemberInstruments(hotPlate, "TestException");
      
    } catch (NullPointerException e) {
      // TODO: handle exception
      System.out.println("You passed in a name which does not exist.");
      e.printStackTrace();
    }
    

  }
  private static void printMemberInstruments (Artist artist, String memberName) {
    System.out.println("Here is a list of instruments " + memberName + " plays: ");
    for (String instruments: artist.getInstruments(memberName)) {

      System.out.println("\t" + instruments);

    }
  }
  
}
