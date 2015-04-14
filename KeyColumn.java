package gmit;

import java.util.*;

/*
 * KEYCOLUMN CLASS
 * 
 * This class is used to create the columns in the matrix.
 * Included an ArrayList for the characters an index variable a key character variable.
 * 
 * All methods are Big O: Best = O(N). Worst = 0(N) (see footnote*)
 */

public class KeyColumn {

	// Class Members
	private char keyCharacter;
	private int index;
	private List<Character> chars = new ArrayList<Character>();

	// Default Constructor
	public KeyColumn(char keyCharacter, int index) {
		this.keyCharacter = Character.toLowerCase(keyCharacter);
		this.index = index;
	}

	// Getters & Setters
	
	// Get Key Character
	
	public char getKeyCharacter() {
		return keyCharacter;
	}

	// Set Key Character
	public void setKeyCharacter(char keyCharacter) {
		this.keyCharacter = keyCharacter;
	}
	
	// Get Index
	public int getIndex() {
		return index;
	}

	// Set Index
	public void setIndex(int index) {
		this.index = index;
	}

	// Get Character ArrayList
	public List<Character> getChars() {
		return chars;
	}

	// Set Character ArrayList
	public void setChars(List<Character> chars) {
		this.chars = chars;
	}
}

//Footnote: *
//As a generalization a for loop that is implicitly O(N) as 
//the statment inside the loop executes exactly the same amount
//of times as the control variable i (Equivelant to N).
//A for loop inside a for loop is generally O(N^2).