
/**
 * CS 1027B 
 * Assignment #1
 * @author Jordan Buckindale
 * Student #251246279
 *  Date: February 20th 2022
 */

// class represents a single six-sided dice that will be used in the game.
public class Dice {
	
	// set private variables for dice class.
	private int value; 
	
	public Dice() { 
		// initialize value to -1.
		value = -1;
	}
 
	public Dice(int x) {
		// initialize value to given argument.
		value = x;
	}
	
	public void roll() { 
		// generate number between 0-6.
		value = RandomNumber.getRandomNumber(1, 6);
	}
	
	public int getValue() {
		// return dice value.
		return value;
	}
}
