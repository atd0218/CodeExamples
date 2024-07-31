/**
 * @name: Ashton Daniels
 * Date: 03/22/2024
 * Description: 
 * 
 * CoffeeExerciser.java
 * 
 * This is my class which stores my main method that I use to declare an instance of coffee and set a temperature which is then
 * compared to 120 degrees and if it is hotter then throw a TooHotException. 
 * 
 * TooHotException.java
 * 
 * This class is my basic exception class that has a constructor that takes a message and passes it to the parent class Exception 
 * to be handled as Exception class would handle it when a message is passed. 
 * 
 * Coffee.java
 * 
 * My Coffee class contains a constructor to assign a temperature which then calls the setTemperature method where the check 
 * is made to see if temp is over 120 degrees. If so then throw the new TooHotException to be handled in main. 
 */

package exercises;

public class CoffeeExerciser {

  public static void main(String[] args) {

    Coffee Coffee = null;

    try {
      Coffee = new Coffee(110);
      
    } catch (TooHotException e) {
      System.out.println(e.getMessage());
    }
    finally {
      System.out.println("Coffee current temp is: " + Coffee.getTemperature() + " degrees!");
    }
    
    
  }
  
}
