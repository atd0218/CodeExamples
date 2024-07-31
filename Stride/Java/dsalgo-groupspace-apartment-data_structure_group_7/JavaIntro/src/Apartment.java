/**
 * @author data_structure_group_7
 * Team members: Ashton Daniels, Don Caplon, Kowshik Prasad Navilur
 * Description: Create the Apartment class and implement constructors and getters. 
 * Override the toString and compareTo.
 * JDK Version: 8
 * Date: 02/05/2024
 */

public class Apartment implements Comparable<Apartment>{
    private int streetAddress;
    public int getStreetAddress() {
        return streetAddress;
    }

    private int apartmentNumber;
    private double rentAmount;
    public double getRent() {
        return rentAmount;
    }

    private int numOfBedrooms;

    //constructor
    public Apartment(int streetAddress, int apartmentNumber, double rentAmount, int numOfBedrooms) {
        this.streetAddress = streetAddress;
        this.apartmentNumber = apartmentNumber;
        this.rentAmount = rentAmount;
        this.numOfBedrooms = numOfBedrooms;
    }

    //toString
    public StringBuilder toString(Apartment a) {
        StringBuilder sb = new StringBuilder();
        sb.append(a.streetAddress + " Apt number " + a.apartmentNumber + 
        "\n \t Rent $" + a.rentAmount +" " +  a.numOfBedrooms + " bedrooms");
        return sb;
    }

    //compareTo
    @Override
    public int compareTo(Apartment o) {

        double rent = o.getRent();
        //do some comparisons
        if (this.rentAmount > rent)
        {
            return 1;
        }
        else if(this.rentAmount == rent)
        {
            return 0;
        }else{
            return -1;
        }

    }
}