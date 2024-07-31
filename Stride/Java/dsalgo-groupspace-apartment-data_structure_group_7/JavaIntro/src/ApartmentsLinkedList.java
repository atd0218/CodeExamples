/**
 * @author data_structure_group_7
 * Team members: Ashton Daniels, Don Caplon, Kowshik Prasad Navilur
 * Description: Prompt user for apartments and add them to linked list
 * then sort list and display it sorted on rent. 
 * JDK Version: 8
 * Date: 02/05/2024
 */

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class ApartmentsLinkedList {

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
			System.out.println("Enter Rent Amount: \n");
			double rent = sc.nextDouble();
			System.out.println("Enter number of bedrooms: ");
			int bedrooms = sc.nextInt();

			Apartment pendToList = new Apartment(firstInput, aptNum, rent, bedrooms);
			aptList.add(pendToList);
		}
		//close scanner
		sc.close();

		 /*
		  * Sort the linked list of apartments on rent value
		  */
		Collections.sort(aptList);
		for(int i = 0; i < aptList.size(); i++) {
			System.out.println(aptList.get(i).toString(aptList.get(i)));
		}

	}

}
