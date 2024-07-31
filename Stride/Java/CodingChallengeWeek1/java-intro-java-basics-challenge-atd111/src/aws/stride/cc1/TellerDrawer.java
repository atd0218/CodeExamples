/**
 * Name: Ashton Daniels
 * FileName: TellerDrawer.java
 * Date: 01/10/2024
 * JDK Version: 8
 * Description: Teller Drawer Class to handle the logic of setting a balance and adding and removing from it.
 */


package aws.stride.cc1;

public class TellerDrawer {

    //constructors to call an instance of the constructor rather than the class itself. 
    public TellerDrawer(double balance) {
        setBalance(balance);
    }
    
    //attributes and getters and setters
    // String name;

    // public String getName() {
    //     return name;
    // }

    // public void setName(String name) {
    //     this.name = name;
    // }
    
    double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    //methods to add and subtract from the balance
    //since I created getters and setters I will handle setting the balance with those
    //since that is the case no return value is necessary for this. 
    public void addBalance(double x) {
        double currentBal = getBalance();
        double newBal = currentBal + x;
        setBalance(newBal);
    }

     public void subBalance(double x) {
        double currentBal = getBalance();
        double newBal = currentBal - x;
        setBalance(newBal);
    }

    
    
}
