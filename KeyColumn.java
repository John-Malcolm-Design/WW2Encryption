package gmit;

import java.util.*;

public class KeyColumn implements Comparable<KeyColumn>{

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
	public char getKeyCharacter() {
		return keyCharacter;
	}

	public void setKeyCharacter(char keyCharacter) {
		this.keyCharacter = keyCharacter;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public List<Character> getChars() {
		return chars;
	}

	public void setChars(List<Character> chars) {
		this.chars = chars;
	}

	// Comparable Overrides
	@Override
	public int compareTo(KeyColumn o) {
		if (this.getKeyCharacter() > o.getKeyCharacter()) {
			return 1;
		} else if (this.getKeyCharacter() < o.getKeyCharacter()) {
			return -1;
		}
		return 0;		
	}
}
