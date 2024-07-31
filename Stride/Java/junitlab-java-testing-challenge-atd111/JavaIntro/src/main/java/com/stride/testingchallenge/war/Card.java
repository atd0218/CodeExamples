package com.stride.testingchallenge.war;
/*
 * Author: Scott Liu
 * Date: 02/19/2024
 */

public class Card {
	//Attributes for card will be suit character and number integer for value
	char suit;
	int num;
	
	//Default constructor will set card to ace of spades
	public Card() {
		suit = 'a';
		num = 1;		
	}
	
	//Constructor which will take suit and value inputs
	public Card(char suit, int value) {
		this.suit = suit;
		this.num = value;
	}
	
	//Set method for card value
	public void setValue(int n) {
		//Check for valid value (1 - 13)
		if (n > 13 || n < 1) {
			System.out.println("Card value of out of range, assigning 1 by default");
			this.num = 1;
		} else {
			this.num = n;
		}
	}
	
	//Print card method
	public void printCard() {
		String suit = null;
		//Takes the character and outputs in a more readable format
		switch(this.suit) {
			case 's':
				suit = "Spades";
				break;
			case 'h':
				suit = "Hearts";
				break;
			case 'd':
				suit = "Diamonds";
				break;
			case 'c':
				suit = "Clubs";
				break;
		}
		//Prints the num and suit of the card
		System.out.println(this.num + " of " + suit);
	}
	
	//Checks if cards are duplicated
	public boolean isEqual(char suit, int value) {
		//If a card has the same suit and value they are equal
		if(this.suit == suit && this.num == value) {
			return true;
		} else {
			return false;
		}
	}
}
 
