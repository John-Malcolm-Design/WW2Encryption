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
 * ENCRYPT CLASS
 * 
 * This class contains the HashMap used for the polybius square, the character ArrayList which will be filled with
 * the encrypted characters from the polybius square and the columnar transosition object which holds the matrix 
 * and allows us to perform operations on like columnar transposition.
 * 
 * The Big O Notation is commented mostly inside other classes as this class just calls methods from other classes.
 * 
 */

public class Encrypt {
	
	// Used for polybius square
	private static Map<String, String> polybiusEncrypt = new HashMap<String, String>();
	// Used for encrypted characters
	private static ArrayList<Character> encryptedChars = new ArrayList<Character>();
	// Used for matrix and columnar transposition
	private static ColumnarTransposition encryptCT;

	// Getters
	public static ColumnarTransposition getEncryptCT() {
		return encryptCT;
	}

	public static ArrayList<Character> getEncryptedChars() {
		return encryptedChars;
	}

	// Master encryption method. This method created the neccesary objects and calls the
	// functions for each step of the encryption from start to finish.
	public static void encryptFile(String key, String fileUri, String fileName) throws IOException {

		// New Columnar Transposition object
		encryptCT = new ColumnarTransposition(key);

		// File and buffered reader
		File file = new File(fileUri);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

		// Initializes matrix
		encryptCT.initializeMatrix();

		// Initializes Polybius Square
		Encrypt.initPolybiusSquare();

		// Splits file into lines and calls encrypt line method
		String line = null;
		while ((line = br.readLine()) != null) {
			Encrypt.encryptLine(line);
		}
		br.close();

		// Fills the matrix with the cipher text from polybius square
		encryptCT.fillMatrix();

		// Transposes columns
		encryptCT.transpose();

		// Writes cipher text to file
		encryptCT.writeToFile(fileName);
	}

	// Big O: Best = O(log(n)). Worst = O(log(n))
	// The rational behind this being O(log(n)) is that it contains searching of 
	// a hashmap which would be O(log(n)) and would be the slowest of all the big O
	// notations for the various expressions in this method.
	public static void encryptLine(String line) {
		// Splits into Words, wherever there are spaces and puts words into array
		String[] words = line.split(" ");

		// Loops through array of words
		for (int i = 0; i < words.length; i++) {
			String word = words[i];

			// Loops through each word and calls encrypt letter function on each letter
			for (int j = 0; j < word.length(); j++) {
				char letter = word.charAt(j);
				encryptLetter(letter);
			}

			// Gets cipher text for space and adds to cipher text character arraylist
			String current = polybiusEncrypt.get(" ");
			encryptedChars.add(current.charAt(0));
			encryptedChars.add(current.charAt(1));
		}
	}

	// Encrypts each letter by getting the string from the polybius.
	// Big O: Best = O(N), Worst = O(N)
	public static void encryptLetter(char letter) {
		String current = polybiusEncrypt.get(Character.toString(letter).toUpperCase().trim());
		encryptedChars.add(current.charAt(0));
		encryptedChars.add(current.charAt(1));
	}

	// Polybius Square Initialization
	// Big O: Best = O(N). Worst = 0(N)*
	public static void initPolybiusSquare() {
		polybiusEncrypt.put("P", "AA");
		polybiusEncrypt.put("H", "AD");
		polybiusEncrypt.put("0", "AF");
		polybiusEncrypt.put("Q", "AG");
		polybiusEncrypt.put("G", "AX");
		polybiusEncrypt.put("6", "AV");
		polybiusEncrypt.put("4", "DA");
		polybiusEncrypt.put("M", "DD");
		polybiusEncrypt.put("E", "DF");
		polybiusEncrypt.put("A", "DG");
		polybiusEncrypt.put("1", "DX");
		polybiusEncrypt.put("Y", "DV");
		polybiusEncrypt.put("L", "FA");
		polybiusEncrypt.put("2", "FD");
		polybiusEncrypt.put("N", "FF");
		polybiusEncrypt.put("O", "FG");
		polybiusEncrypt.put("F", "FX");
		polybiusEncrypt.put("D", "FV");
		polybiusEncrypt.put("X", "GA");
		polybiusEncrypt.put("K", "GD");
		polybiusEncrypt.put("R", "GF");
		polybiusEncrypt.put("3", "GG");
		polybiusEncrypt.put("C", "GX");
		polybiusEncrypt.put("V", "GV");
		polybiusEncrypt.put("S", "XA");
		polybiusEncrypt.put("5", "XD");
		polybiusEncrypt.put("Z", "XF");
		polybiusEncrypt.put("W", "XG");
		polybiusEncrypt.put("7", "XX");
		polybiusEncrypt.put("B", "XV");
		polybiusEncrypt.put("J", "VA");
		polybiusEncrypt.put("9", "VD");
		polybiusEncrypt.put("U", "VF");
		polybiusEncrypt.put("T", "VG");
		polybiusEncrypt.put("I", "VX");
		polybiusEncrypt.put("8", "VV");
		polybiusEncrypt.put("-", "YA");
		polybiusEncrypt.put(".", "YD");
		polybiusEncrypt.put("(", "YF");
		polybiusEncrypt.put(")", "YG");
		polybiusEncrypt.put(",", "YX");
		polybiusEncrypt.put("?", "YV");
		polybiusEncrypt.put("!", "YY");
		polybiusEncrypt.put(";", "AY");
		polybiusEncrypt.put(":", "DY");
		polybiusEncrypt.put("_", "FY");
		polybiusEncrypt.put("=", "XY");
		polybiusEncrypt.put("*", "VY");
		polybiusEncrypt.put("\"", "AZ"); 
		polybiusEncrypt.put(" ", "DZ"); 
		polybiusEncrypt.put("&", "FZ"); 
		polybiusEncrypt.put("'", "GZ");
		polybiusEncrypt.put("|", "XZ"); 
		polybiusEncrypt.put("~", "VZ");
		polybiusEncrypt.put("/", "GY");
		polybiusEncrypt.put("#", "YZ");
		polybiusEncrypt.put("£", "ZA");
		polybiusEncrypt.put("$", "ZF");
		polybiusEncrypt.put("<", "ZG");
		polybiusEncrypt.put(">", "ZX");
		polybiusEncrypt.put("@", "ZF");
		polybiusEncrypt.put("~", "ZY");
		polybiusEncrypt.put("^", "ZZ");
	}
}

//Footnote: *
//As a generalization a for loop that is implicitly O(N) as 
//the statment inside the loop executes exactly the same amount
//of times as the control variable i (Equivelant to N).
//A for loop inside a for loop is generally O(N^2).