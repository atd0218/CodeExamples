package com.stride.testingchallenge.basicschallenge;
/*
 * Author: Scott Liu
 * Filename: BankTeller.java
 * Defines the BankTeller class with get/set methods for handling transactions
 */

import java.util.ArrayList;
//Using ArrayList to dynamically add/remove items to array

public class BankTeller{
	private double drawerTotal = 2500.0;
	private ArrayList<String> customers = new ArrayList<>();
	//Instantiate drawer total and customer list for each teller
	
	private ArrayList<String> transactionList = new ArrayList<>();
	//Bonus to track transactions made by teller
	
	public void depositMoney(double num) {
		this.drawerTotal = drawerTotal + num;
	}
	public void withdrawMoney(double num) {
		this.drawerTotal = drawerTotal - num;
	}
	//Methods to add or subtract from total in teller drawer
	
	public double getTotal() {
		return drawerTotal;
	}
	//Returns total in teller drawer
	
	public void addName(String name) {
		customers.add(name);
	}
	//Adds customer name to ArrayList of customers
	public ArrayList<String> getName(){
		return new ArrayList<String>(customers); 
	}
	//Returns list of customers
	
	public void addTransaction(String transaction) {
		transactionList.add(transaction);
	}
	//Adds transaction to list of teller transactions
	
	public ArrayList<String> getTransaction(){
		return new ArrayList<String>(transactionList);
	}
	//Returns list of teller transactions
}