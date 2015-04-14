package gmit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * DECRYPT CLASS
 * 
 * This class contains the HashMap used for the polybius square, the character ArrayList which will be filled with
 * the decrypted characters from the polybius square and the columnar transosition object which holds the matrix 
 * and allows us to perform operations on like columnar transposition. There is also a variable for the message size which is needed
 * to properly perform reverse columnar transposition.
 * 
 * The Big O Notation is commented mostly inside other classes as this class just calls methods from other classes.
 * 
 */

public class Decrypt {

	// Used for polybius square
	private static Map<String, String> polybiusDecrypt = new HashMap<String, String>();
	// Used for decrypted characters
	private static ArrayList<String> decryptedChars = new ArrayList<String>();
	// Used for matrix and columnar transposition
	private static ColumnarTransposition decryptCT;
	// Used for reverse columnar transposition
	private static int messageSize;

	// Getters
	public static ColumnarTransposition getDecryptCT() {
		return decryptCT;
	}

	public static ArrayList<String> getDecryptedChars() {
		return decryptedChars;
	}

	public static Map<String, String> getPolybiusDecrypt() {
		return polybiusDecrypt;
	}

	// Master decryption method. This method created the neccesary objects and calls the
	// functions for each step of the encryption from start to finish.
	public static void decryptFile(String key, String fileUrl, String fileName) throws IOException {

		// New Columnar Transposition object
		decryptCT = new ColumnarTransposition(key);

		// File and buffered reader
		File file = new File(fileUrl);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

		// Initializes matrix
		decryptCT.initializeMatrix();

		// Initializes Polybius Square
		initPolybiusSquare();

		// Initialzes j which is used to loop through each column in matrix
		// and also the message size variable used for reverse columnar transposition
		int j = 0;
		messageSize = 0;

		// Splits file into lines and calls encrypt line method
		String line = null;
		while ((line = br.readLine()) != null) {
			// Fills the matrix with the cipher text from the file.
			// Fills matrix column by column using j as a control
			decryptCT.fillMatrix(line, j);

			// Updates message size variables line by line
			messageSize = messageSize + line.length();

			// Increments j which is used for controlling column we are filling
			j++;
		}
		br.close();

		// Sets correct index values as per sorted keyword
		decryptCT.setCorrectIndexValues();

		// Reverses transposition
		decryptCT.reverseTranspose();

		// Fills decrypted character array with the decrypted text from the polybius square
		fillDecryptedCharsArray();

		// Writes decrpted text to file
		decryptCT.writeToFileDecrypted(fileName);
	}

	// Further decrypts matrix using polybius square and then fills the character array 
	// with decrypted text
	// Big O: Best = O(N). Worst = 0(N)
	public static void fillDecryptedCharsArray() {

		// Calculates number of rows
		int numberOfRows = messageSize / decryptCT.getMatrix().size();

		// Initilizes count used for putting each character through polybius square
		int count = 0;

		// Arraylist used for each chacter in matrix if keyword size is not an even number.
		// This is needed as each 2 characters in cipher text maps to one real character
		ArrayList<Character> keysOddNumber = new ArrayList<Character>();	

		// Checks to see if keyword size is odd number 
		// If so adds the characters to a character arraylist
		if (decryptCT.getMatrix().size() % 2 != 0) {

			// While the count is less than the number of rows
			while (count < numberOfRows) {

				// Fills the arraylist with values from matrix
				for (int i = 0; i < decryptCT.getMatrix().size(); i++) {
					keysOddNumber.add(decryptCT.getMatrix().get(i).getChars().get(count));
				}	
				count++;

			}

			// Checks to see if there is an extra row
			// If there is an extra row these values are added to the character arraylist as per above.
			if (messageSize % decryptCT.getMatrix().size() != 0) {
				int p = 0;
				while (decryptCT.getMatrix().get(p).getChars().size() == (numberOfRows +1) && p < decryptCT.getMatrix().size()) {
					keysOddNumber.add(decryptCT.getMatrix().get(p).getChars().get(count));
					p++;
				}
			}

			// Maps each character pair in arraylist created above to decrypted character
			int count2 = 0;
			while (count2 < keysOddNumber.size()) {
				String currentKey = (keysOddNumber.get(count2).toString() + keysOddNumber.get(++count2).toString());
				decryptedChars.add(polybiusDecrypt.get(currentKey));
				count2++;
			}
		}

		// Code below runs if the keyowrd size is an even number
		else{
			
			// While the count is smaller than the number of rows
			while (count < numberOfRows) {
				
				// Fills a decrypted character arraylist after passing matrix through
				// polybius square.
				for (int i = 0; i < decryptCT.getMatrix().size() -1; i++) {
					Character keyOne= decryptCT.getMatrix().get(i).getChars().get(count);
					Character keyTwo = decryptCT.getMatrix().get(++i).getChars().get(count);
					String currentKey = keyOne.toString() + keyTwo.toString();
					decryptedChars.add(polybiusDecrypt.get(currentKey));
				}	
				count++;
			}
			
			// Checks to see if there is an extra row
			// If there is an extra row these values are added to the character arraylist as per above.
			ArrayList<Character> lastRow = new ArrayList<Character>();
			if (messageSize % decryptCT.getMatrix().size() != 0) {
				int p = 0;
				while (decryptCT.getMatrix().get(p).getChars().size() == (numberOfRows +1) && p < decryptCT.getMatrix().size()) {
					lastRow.add(decryptCT.getMatrix().get(p).getChars().get(count));
					p++;

				}
				
				// Adds text from last row through polybius square 
				// and to decrypted chars arrayslist
				for (int k = 0; k < lastRow.size(); k++) {
					Character keyOne= lastRow.get(k);
					Character keyTwo = lastRow.get(++k);

					String currentKey = keyOne.toString() + keyTwo.toString();
					decryptedChars.add(polybiusDecrypt.get(currentKey));
				}
			}
		}
	}

	// Polybius Square Initialization
	// Big O: Best = O(N). Worst = 0(N)*
	private static void initPolybiusSquare() {
		polybiusDecrypt.put("AA", "P"); 
		polybiusDecrypt.put("AD", "H"); 
		polybiusDecrypt.put("AF", "0");
		polybiusDecrypt.put("AG", "Q"); 
		polybiusDecrypt.put("AV", "6"); 
		polybiusDecrypt.put("AX", "G"); 
		polybiusDecrypt.put("DA", "4");
		polybiusDecrypt.put("DD", "M"); 
		polybiusDecrypt.put("DF", "E");
		polybiusDecrypt.put("DG", "A");
		polybiusDecrypt.put("DV", "Y"); 
		polybiusDecrypt.put("DX", "1"); 
		polybiusDecrypt.put("FA", "L"); 
		polybiusDecrypt.put("FD", "2"); 
		polybiusDecrypt.put("FF", "N"); 
		polybiusDecrypt.put("FG", "O"); 
		polybiusDecrypt.put("FV", "D"); 
		polybiusDecrypt.put("FX", "F"); 
		polybiusDecrypt.put("GA", "X"); 
		polybiusDecrypt.put("GD", "K"); 
		polybiusDecrypt.put("GF", "R"); 
		polybiusDecrypt.put("GG", "3"); 
		polybiusDecrypt.put("GV", "V"); 
		polybiusDecrypt.put("GX", "C"); 
		polybiusDecrypt.put("VA", "J");
		polybiusDecrypt.put("VD", "9");
		polybiusDecrypt.put("VF", "U");
		polybiusDecrypt.put("VG", "T");
		polybiusDecrypt.put("VV", "8");
		polybiusDecrypt.put("VX", "I");
		polybiusDecrypt.put("XA", "S");
		polybiusDecrypt.put("XD", "5");
		polybiusDecrypt.put("XF", "Z");
		polybiusDecrypt.put("XG", "W");
		polybiusDecrypt.put("XV", "B");
		polybiusDecrypt.put("XX", "7");
		polybiusDecrypt.put("YA", "-");
		polybiusDecrypt.put("YD", ".");
		polybiusDecrypt.put("YF", "(");
		polybiusDecrypt.put("YG", ")");
		polybiusDecrypt.put("YX", ",");
		polybiusDecrypt.put("YV", "?");
		polybiusDecrypt.put("YY", "!");
		polybiusDecrypt.put("AY", ";");
		polybiusDecrypt.put("DY", ":");
		polybiusDecrypt.put("FY", "_");
		polybiusDecrypt.put("GY", "/");
		polybiusDecrypt.put("XY", "=");
		polybiusDecrypt.put("VY", "*");
		polybiusDecrypt.put("AZ", "\"");
		polybiusDecrypt.put("DZ", " "); 
		polybiusDecrypt.put("FZ", "&"); 
		polybiusDecrypt.put("GZ", "'"); 
		polybiusDecrypt.put("XZ", "|"); 
		polybiusDecrypt.put("VZ", "~"); 
		polybiusDecrypt.put("YZ", "#"); 
		polybiusDecrypt.put("ZA", "£");
		polybiusDecrypt.put("ZD", "Û");
		polybiusDecrypt.put("ZF", "$");
		polybiusDecrypt.put("ZG", "<");
		polybiusDecrypt.put("ZX", ">");
		polybiusDecrypt.put("ZV", "@");
		polybiusDecrypt.put("ZY", "`");
		polybiusDecrypt.put("ZZ", "^");

	}
}

//Footnote: *
//As a generalization a for loop that is implicitly O(N) as 
//the statment inside the loop executes exactly the same amount
//of times as the control variable i (Equivelant to N).
//A for loop inside a for loop is generally O(N^2).