package com.stride.testingchallenge.war;
/**
 * Author: Scott Liu
 * Date: 02/19/2024
 */

import java.util.Random;
import java.util.ArrayList;

public class War {
	
	public static void main(String[] args) {
		//Initialize random utility
		Random rand = new Random();
		//Initialize and populate arraylist of suit characters
		ArrayList<Character> suits = new ArrayList<Character>();
		suits.add('s');
		suits.add('h');
		suits.add('d');
		suits.add('c');
		
		//Max and min values for random calculations
		int valueMax = 13, valueMin = 1;
		//Picks a value from (0-3)
		int cpuSuitNum = rand.nextInt(4);
		//Retrieve the value from the suits arraylist
		char cpuSuit = suits.get(cpuSuitNum);
		//Ensures values are from 1-13 inclusive
		int cpuCardValue = rand.nextInt(valueMax - valueMin + 1) + valueMin;
		//Create the CPU card object and output
		Card cpuCard = new Card(cpuSuit, cpuCardValue);
		System.out.println("The CPU drew:");
		cpuCard.printCard();
		
		//Picks a value from (0-3)
		int playerSuitNum = rand.nextInt(4);
		//Retrieve the value from the suits arraylist
		char playerSuit = suits.get(playerSuitNum);
		//Ensures values are from 1-13 inclusive
		int playerCardValue = rand.nextInt(valueMax - valueMin + 1) + valueMin;
		
		//Check for card equality and handles, see test case below
		if (cpuCard.isEqual(playerSuit, playerCardValue)) {
			ArrayList<Character> temp = suits;
			temp.remove(cpuSuitNum);
			int newPlayerSuitNum = rand.nextInt(3);
			playerSuit = temp.get(newPlayerSuitNum);
		}
		
		//Create and output the player card object
		Card playerCard = new Card(playerSuit, playerCardValue);
		System.out.println("The player drew:");
		playerCard.printCard();
		
		//Checks values of each card and determine higher is winner
		if(cpuCard.num > playerCard.num) {
			System.out.println("CPU wins!");
		} else if(cpuCard.num < playerCard.num) {
			System.out.println("Player wins!");
		} else {
			System.out.println("Cards values are equal, noone wins :(");
		}
		
		System.out.println();
		System.out.println("Testing case for equal cards:");
		//Test case for when cards are equal
		Card card1 = new Card('s', 1);
		//Assign card 2 suit number to 0 (index of spade in the arraylist)
		int card2SuitNum = 0;
		//This will return spade
		char card2Suit = suits.get(card2SuitNum);
		//Set value of card to 1
		int card2Num = 1;
		
		//Card 2 values are exactly same as card 1 and will trigger
		if (card1.isEqual(card2Suit, card2Num)) {
			System.out.println("Cards were equal, changing suits for one card");
			//Create a temporary array list
			ArrayList<Character> testTemp = suits;
			//Remove the repeating suit from list
			testTemp.remove(card2SuitNum);
			//New random number generated with lower range
			int newSuitNum = rand.nextInt(3);
			//New suit is taken, guaranteed to not be the same suit again
			card2Suit = testTemp.get(newSuitNum);
		}
		
		Card card2 = new Card(card2Suit, card2Num);
		card1.printCard();
		card2.printCard();
		
	}

}
