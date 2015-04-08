package gmit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Encrypt {

	private static Map<String, String> polybiusEncrypt = new HashMap<String, String>();
	private static ArrayList<Character> encryptedChars = new ArrayList<Character>();
	private static ColumnarTransposition encryptCT;

	// Getters
	public static ColumnarTransposition getEncryptCT() {
		return encryptCT;
	}

	public static ArrayList<Character> getEncryptedChars() {
		return encryptedChars;
	}

	// Methods
	public static void encryptFile(String key, String fileUri) throws IOException {
		encryptCT = new ColumnarTransposition(key);
		File file = new File(fileUri);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		encryptCT.initializeMatrix();
		Encrypt.initialiseEncryptSquare();

		String line = null;

		while ((line = br.readLine()) != null) {
			Encrypt.encryptLine(line);
		}
		br.close();
		
		encryptCT.fillMatrix();
		encryptCT.transpose();
		encryptCT.writeToFile();
	}

	public static void encryptLine(String line) {
		// Splits into Words, wherever there are spaces.
		String[] words = line.split(" ");

		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			for (int j = 0; j < word.length(); j++) {
				char letter = word.charAt(j);
				encryptLetter(letter, j);
			}
		}
	}

	public static void encryptLetter(char letter, int j) {
		String current = polybiusEncrypt.get(Character.toString(letter).toUpperCase().trim());
		encryptedChars.add(current.charAt(0));
		encryptedChars.add(current.charAt(1));
	}

	// Polybius Square Initialization using HashMaps
	public static void initialiseEncryptSquare() {
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
