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
 * 
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
	// Big O: Best = 3*O(N). Worst = 2*0(N) + 0(N^2). O(my algo)
	// This method has two for loops which makes it O(N^2) of the bat.
	// The extra sort method at the bottom would make it O(N^3). Even 
	// if for some reason the keyword happened to be already alphebetized
	// the bubble sort would have to at run once. Worst case is the bubble 
	// sort would have to run more than once.
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

	// Fills Matrix for encrption with values from the polybius square (character array from encryption class)
	// Big O: Best = O(N). Worst = 0(N)
	// The statement inside the for loop runs as many times as there are characteres in the character array.
	public void fillMatrix() {
		int count = 0;
		while (count < (Encrypt.getEncryptedChars().size())) {
			for (int j = 0; j < keyArray.size() && count < (Encrypt.getEncryptedChars().size()); j++) {
				matrix.get(j).getChars().add(Encrypt.getEncryptedChars().get(count++));
			}
		}
	}
	
	// Fills Matrix with cipher text from file (decryption)
	// Big O: Best = O(N). Worst = 0(N)
	// The statement inside the for loop runs as many times as there are characteres in the cipher text file.
	public void fillMatrix(String line, int j) {
		int count = 0;

		while (count < line.length()) {
			matrix.get(j).getChars().add(line.charAt(count++));
		}
	}

	// Sets the correct index valeus for the KeyColumns during decryption
	// The index values should be that of the sorted keyword not the original.
	// After setting the index values to that of the sorted keyword we can 
	// do the reverse transposition by sorting numberically.
	// Big O: Best = O(N^2). Worst = 0(N^2)*
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
	// Big O: See method inside TransposeComparator class
	public void transpose() {
		Collections.sort(matrix, new TransposeComparator());
	}

	// Used for Columnar Transposition for decryption
	// Big O: See method inside ReverseTransposeComparator class
	public void reverseTranspose() {
		Collections.sort(matrix, new ReverseTransposeComparator());
	}

	// Writes cipher text to file
	// Big O: Best = O(N). Worst = 0(N)
	// The statement inside the for loops runs as many times as there are characteres in the matrix.
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
	// Big O: Best = O(N). Worst = 0(N)
	// The statement inside the for loop runs as many times as there are characteres in the arraylist.
	public void writeToFileDecrypted(String fileName) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(fileName + ".txt");
		for (int i = 0; i < Decrypt.getDecryptedChars().size(); i++) {
			out.print(Decrypt.getDecryptedChars().get(i));
		}
		out.close();
	}
}

// Footnote: *
// As a generalization a for loop that is implicitly O(N) as 
// the statment inside the loop executes exactly the same amount
// of times as the control variable i (Equivelant to N).
// A for loop inside a for loop is generally O(N^2).