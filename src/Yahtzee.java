/**
 * CS 1027B 
 * Assignment #1
 * @author Jordan Buckindale
 * Student #251246279
 *  Date: February 20th 2022
 */
import java.util.*;

// This class represents the Yahtzee game where 5 dice are rolled and the scoring is performed.
public class Yahtzee {

	// method to set private variables.
	private Dice[] dice;

	// method that initializes the dice array and roll them for random values.
	public Yahtzee() { 
		
		this.dice = new Dice[5];
		// iterates through array and sets dice values.
		for (int i = 0; i < dice.length; i++) {
			dice[i] = new Dice();
			dice[i].roll(); 
		}
	}	
	
	// method that initializes the dice array with the given argument.
	public Yahtzee(Dice[] dice) {
		// initializes the dice array to given argument.
		this.dice = dice;	
	}
	
	// method that counts how many dice show each of the possible values from 1-6 and record  in an integer array. 
	public int[] getValueCount() {
		
		// creates new array.
		int[] valueCount = new int[6]; 
		
		// gets values of dice and adds to new array with a count of each same element.
		for (int i = 0; i < dice.length; i++) {
			valueCount[dice[i].getValue()-1] = valueCount[dice[i].getValue()-1] + 1;
		}
		// return array with counters.
		return valueCount;
	}
	
	// method that sets the sum of all the dice values into variable. (private helper method)
	private int getSumOfArray(Dice[] dice) { 
		
		// initialize the variable.
		int sumOfArray = 0;

		// gets values of dice and adds to new array with sum of each same element.
		for(int i = 0; i<dice.length; i++) {
			sumOfArray += dice[i].getValue();
		}
		// return value. 
		return sumOfArray;
	}
	
	// method that sets values of score options.
	private void getScoreOfSameSidedDice(int[] results, int[] valueCount, int sumOfArray) {
		
		// iterates through the value count array.
		for(int i = 0; i < valueCount.length; i++) {
			
			//multiplies number of dice of specific value by value of dice
			results[i] = valueCount[i] * (i+1);
			
			// 3 of a kind (score option)
			if(valueCount[i] >= 3) {
				results[6] = sumOfArray;
			}
			// 4 of a kind (score option)
			if(valueCount[i] >= 4) {
				results[7] = sumOfArray;
			}
			// yahtzee (score option)
			if(valueCount[i] == 5) {
				results[11] = 50;
			}
			
		// full house (score option)	
		if(results[6] != 0){
	           for (int j = 0; j<valueCount.length; j++){
	               if(valueCount[j] == 2){
	               results[8] = 25;	
	               }
	           }
		}
		// Chance (score option)
		results[12] = sumOfArray;
		}
	}
		
	// method that sets other score options. (private helper method)
	private void getStraightScore(int[] results, int[] valueCount) {
		
		// initializes variables.
		int straightScore = 0;
		
		// iterates through value count array and checks for consecutive dice. 
		for(int i = 0; i < valueCount.length; i++) {
			
			// checks for missing dice in dice.
			if(valueCount[i] == 0) {
				straightScore = 0;
			}
			else if (valueCount[i] >= 1) {
				straightScore ++;
			}
			// checks and sets score for small straight. (score option)
			if(straightScore >= 4) {
			results[9] = 30;
			}
			
			// checks and sets score for large straight. (score option)
			if(straightScore == 5) {
				results[10] = 40;
			}
		}
	}
	
	// method that creates an integer array with 13 elements and records the score options. 
	public int[] getScoreOptions() {
		
		// sets variables.
		int[] results = new int[13];
		Dice[] dice = this.dice;
		
		// sets score options for indexes 0 through 6.
		int[]valueCount = getValueCount(); 				// indexes 0-6 
		
		// sets score options for index 12.
		int sumOfArray = getSumOfArray(dice); 			// index 12
		
		// sets score options from index 6, 7, 8, and 9.
		getScoreOfSameSidedDice(results, valueCount, sumOfArray); //index 6, 7, 8, 11
		getStraightScore(results, valueCount); // index 9, 10
		
		// returns array with scores.
		return results;
	}
	
	// method that determines the maximum value from the array, and the index at which the maximum is found.
	public int[] score() {
		
		// sets variable with array of scores.
		int[] results = getScoreOptions();
		
		// creates a array to store max value and index.
		int[] maxCoordinatesArray = new int[2];

		// iterates through and finds the max value and index and puts into array.
		for(int i = 0; i<results.length; i++) {
			
			if(results[i] > maxCoordinatesArray[0]) {
				
				// sets max value to first index of array.
				maxCoordinatesArray[0] = results[i];
				
				// sets max value index to second index of array.
				maxCoordinatesArray[1] = i;
			}
		}
		// returns the array with values.
		return maxCoordinatesArray;
	}
	
	// method that compares the given Yahtzee object with the "this" object to see if they are equal.
	public boolean equals(Yahtzee other) {
		
		// returns boolean values.
		return Arrays.equals(this.getValueCount(), other.getValueCount());
	}
	
	// method to return a string of the dice values formatted a specific way.
	public String toString() {
		
		String z = "Dice: {";
        for(int i =0; i<4; i++){
            z += dice[i].getValue() + ", ";
        }
        z += dice[4].getValue() + "}";
        
        // returns string.
        return z;
        
	}
}