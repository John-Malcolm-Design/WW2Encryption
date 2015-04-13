package gmit;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * COLUMNAR TRANSPOSITION CLASS
 * 
 * This class contains a matrix object which is an array list of KeyColumns.
 * The KeyColumns basically being abstracted arraylists with extra variables and methods.
 * This class also cotains arraylists for both the sorted and and unsorted version of the keyword.
 * This class contains methods for columnar transposition and also for printing to file cipher text and decrypted text.
 * Columnar transpositions objects are created for both encryption and decryption
 * this approach is OO and DRY and helps keep the code more understandable.
 */

public class ColumnarTransposition {

	// Class Members
	private String key;
	private ArrayList<Character> keyArray = new ArrayList<Character>();
	private ArrayList<Character> sortedKeyArray = new ArrayList<Character>();
	private List<KeyColumn> matrix = new ArrayList<KeyColumn>();

	public ColumnarTransposition(String key) {
		this.key = key;
	}

	// Get Matrix
	public List<KeyColumn> getMatrix() {
		return matrix;
	}

	// Initialize Matrix
	public void initializeMatrix()  {
		
		// Fills two arraylists with the keyword
		for (char c : key.toLowerCase().toCharArray()) {
			keyArray.add(c);
			sortedKeyArray.add(c);
		}	

		// Adds appropriate number of columns to the matrix
		// Uses the KeyColumn class for the columns
		for (int i = 0; i < keyArray.size(); i++) {
			matrix.add(new KeyColumn(keyArray.get(i), i));
		}		

		// Sorts one arraylist alphabetically 
		Collections.sort(sortedKeyArray);
	}

	// Encryption - Fills Matrix with values from the polybius square (character array from encryption class)
	public void fillMatrix() {
		int count = 0;
		while (count < (Encrypt.getEncryptedChars().size())) {
			for (int j = 0; j < keyArray.size() && count < (Encrypt.getEncryptedChars().size()); j++) {
				matrix.get(j).getChars().add(Encrypt.getEncryptedChars().get(count++));
			}
		}
	}

	// Decryption -  Fills Matrix with cipher text from file
	public void fillMatrix(String line, int j) {
		int count = 0;

		while (count < line.length()) {
			matrix.get(j).getChars().add(line.charAt(count));
			count++;
		}
	}

	// Sets the correct index valeus for the KeyColumns during decryption
	// The index values should be that of the sorted keyword not the original.
	// After setting the index values to that of the sorted keyword we can 
	// do the reverse transposition by sorting numberically.
	public void setCorrectIndexValues() {
		for (int i = 0; i < keyArray.size(); i++) {
			for (int j = 0; j < keyArray.size(); j++) {
				if (sortedKeyArray.get(i) == keyArray.get(j)) {
					matrix.get(i).setIndex(j);
					keyArray.set(j, null);
				}
			}
		}
	}

	// Used for Columnar Transposition for encryption
	public void transpose() {
		Collections.sort(matrix, new TransposeComparator());
	}

	// Used for Columnar Transposition for decryption
	public void reverseTranspose() {
		Collections.sort(matrix, new ReverseTransposeComparator());
	}

	// Writes cipher text to file
	public void writeToFile(String fileName) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(fileName + ".txt");
		for (int i = 0; i < key.length(); i++) {
			for (int p = 0; p < matrix.get(i).getChars().size(); p++) {
				out.print(matrix.get(i).getChars().get(p));
			}	
			out.print("\n");
		}
		out.close();
	}

	// Writes decrypted text to file
	public void writeToFileDecrypted(String fileName) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(fileName + ".txt");
		for (int i = 0; i < Decrypt.getDecryptedChars().size(); i++) {
			out.print(Decrypt.getDecryptedChars().get(i));
		}
		out.close();
	}
}