package gmit;

import java.util.*;

/*
 * KEYCOLUMN CLASS
 * 
 * This class is used to create the columns in the matrix.
 * Included an ArrayList for the characters an index variable a key character variable.
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
