[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/AkJhumXb)
# Intro
This is a group coding project. Each team will have a max of 3 members. Make sure to include the team name, team member in the javadoc comments.

## Task 1:
Tri has purchased buildings that contain apartments to rent. Create an Apartment class that contains fields for the street address, apartment number, monthly rent amount, and number of bedrooms for each Apartment that he owns. Include a constructor that assigns field values to the class, a toString() method that displays the Apartment values, and a compareTo() method that compares Apartment objects based on the rent value. Include any other methods that you think you might need. Save the file as __Apartment.java__

## Task 2:
Write an application that contains a LinkedList in which to store Apartment objects. Prompt the user for values for Apartments and add them to the LinkedList until a sentinel value is entered. Sort the Apartments by rent value, and then display the sorted list. Save the file as __ApartmentsLinkedList.java__ 

## Task 3:
Tri sometimes sells one of his buildings. Modify the ApartmentsLinkedList application so that after data entry is complete and the list of Apartment objects is displayed, the application prompts the user for a street address to eliminate. Remove all the Apartment objects at the specified street address, and display the list of Apartments again. Save the file as __ApartmentsLinkedList2.java__

### Sample output:

Enter street address for apartment or ZZZ to quit >> 9915<br>
Enter apartment number >> 1<br>
Enter rent >> 400<br>
Enter number of bedrooms >> 2<br>

Enter street address for apartment or ZZZ to quit >> 25506<br>
Enter apartment number >> 20<br>
Enter rent >> 800<br>
Enter number of bedrooms >> 4<br>

Enter street address for apartment or ZZZ to quit >> 9216<br>
Enter apartment number >> 30<br>
Enter rent >> 1000<br>
Enter number of bedrooms >> 6<br>
Enter street address for apartment or ZZZ to quit >> ZZZ

List of apartments<br>
9915 Apt number 1<br>
   Rent $400.0 2 bedrooms<br>
25506 Apt number 20<br>
   Rent $800.0 4 bedrooms<br>
9216 Apt number 30<br>
   Rent $1000.0 6 bedrooms<br>

Enter address to remove >> 9216<br>

List of apartments<br>
9915 Apt number 1<br>
   Rent $400.0 2 bedrooms<br>
25506 Apt number 20<br>
   Rent $800.0 4 bedrooms<br>
