package com.stride.testingchallenge.basicschallenge;
/*
 * Author: Scott Liu
 * Filename: BasicsChallenge.java
 * Creates two BankTeller objects and tracks transactions
 */

public class BasicsChallenge{
	public static void main (String[] args) {
		//Defines list of customers
		String[] customerNames = {"Jennifer Figueroa",
			  "Heather Mcgee",
			  "Amanda Schwartz",
			  "Nicole Yoder",
			  "Melissa Hoffman",
			  "Beatrice Helman",
			  "Louis Sanders",
			  "Catherine Altman",
			  "Ralph Estees",
			  "Samantha Augustine",
			  "Peter Fredricks",
			  "David Alters"};
		
		//Instantiates two BankTeller objects
		BankTeller teller1 = new BankTeller();
		BankTeller teller2 = new BankTeller();
		
		//Initial for-loop to populate each teller with list of customers
		for(int i = 0; i < customerNames.length; i++) {
			//If statements alternate customers between tellers and add to list
			if(i % 2 == 0) {
				teller1.addName(customerNames[i]);
			}
			if(i % 2 != 0) {
				teller2.addName(customerNames[i]);
			}
		}
		
		//For-loop to calculate transactions
		for(int i = 0; i < customerNames.length; i++) {
			//Check if customer is 3rd person in line and withdraws $250
			if(i % 3 == 2) {
				//Checks if customer belongs to teller1 or teller2
				if(teller1.getName().contains(customerNames[i])) {
					teller1.addTransaction(customerNames[i] + " withdraws $250");
					teller1.withdrawMoney(250);
			
				}
				else {
					teller2.addTransaction(customerNames[i] + " withdraws $250");
					teller2.withdrawMoney(250);
				}
			}
			
			//Check if customer is 5th person in line and deposits $475
			else if(i % 5 == 4) {
				//Checks if customer belongs to teller1 or teller2
				if(teller1.getName().contains(customerNames[i])) {
					teller1.addTransaction(customerNames[i] + " deposits $475");
					teller1.depositMoney(475);
				}
				else {
					teller2.addTransaction(customerNames[i] + " deposits $475");
					teller2.depositMoney(475);
				}
			}
			
			//Every other customer in line deposits $100
			else {
				//Checks if customer belongs to teller1 or teller2
				if(teller1.getName().contains(customerNames[i])) {
					teller1.addTransaction(customerNames[i] + " deposits $100");
					teller1.depositMoney(100);
				}
				else {
					teller2.addTransaction(customerNames[i] + " deposits $100");
					teller2.depositMoney(100);
				}
			}
		}
		//Output all teller1 details
		System.out.println("Teller 1 customers: " + teller1.getName());
		System.out.println("End of day teller 1 drawer total: $" + teller1.getTotal());
		System.out.println("Teller 1 Transaction details:");
		for(int i = 0; i < teller1.getTransaction().size(); i++) {
			System.out.println(teller1.getTransaction().get(i));
		}
		
		//Spacing line for console output readability
		System.out.println("\n");
		
		//Output all teller2 details
		System.out.println("Teller 2 customers: " + teller2.getName());
		System.out.println("End of day teller 2 drawer total: $" + teller2.getTotal());
		System.out.println("Teller 2 Transaction details: ");
		for(int i = 0; i < teller2.getTransaction().size(); i++) {
			System.out.println(teller2.getTransaction().get(i));
		}
	}
}