/**
 * @author data_structure_group_7
 * Team members: Ashton Daniels, Don Caplon, Kowshik Prasad Navilur
 * Description: Same as ApartmentLinkedList but adds the ability to remove an apartment from the list. 
 * JDK Version: 8
 * Date: 02/05/2024
 */

 import java.util.Collections;
 import java.util.LinkedList;
 import java.util.Scanner;
 
 public class ApartmentsLinkedList2 {
 
     public static void main(String[] args) {
         // TODO Auto-generated method stub
 
         //initilize the linked list
         LinkedList<Apartment> aptList = new LinkedList<>();
 
         /*
          * loop through asking user for values for apartments assign them to an object
          * and then add them to the linked lest until they press a value. 
          */
         Scanner sc = new Scanner(System.in);
         int firstInput = 108765;
         while(firstInput != 0) {
             System.out.print("Enter Street address or 0 to quit: \n");
             firstInput = sc.nextInt();
             if(firstInput == 0) {
                 break;
             }
             System.out.print("Enter apartment number: \n");
             int aptNum = sc.nextInt();
             System.out.print("Enter Rent Amount: \n");
             double rent = sc.nextDouble();
             System.out.print("Enter number of bedrooms: ");
             int bedrooms = sc.nextInt();
            
             //create new apartment object and add to linked list. 
             Apartment pendToList = new Apartment(firstInput, aptNum, rent, bedrooms);
             aptList.add(pendToList);
         }
 
          /*
           * Sort the linked list of apartments on rent value
           */
         Collections.sort(aptList);
         System.out.println("List of apartments");
         for(int i = 0; i < aptList.size(); i++) {
             System.out.println(aptList.get(i).toString(aptList.get(i)));
         }
        
         //prompt user for the street address to remove
         System.out.println("Enter the address to remove: ");
         int key = sc.nextInt();

         //close scanner
         sc.close();

        //call to remove that key. 
        aptList.removeIf(n -> (n.getStreetAddress() == key));
        System.out.println("List of apartments");
        for(int i = 0; i < aptList.size(); i++) {
            System.out.println(aptList.get(i).toString(aptList.get(i)));
        }
 
     }
 
 }
 