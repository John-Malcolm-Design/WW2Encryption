package gmit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Encrypt {
	private static Map<String, String> polybiusEncrypt = new HashMap<String, String>();
	private static Map<String, String> polybiusDecrypt = new HashMap<String, String>();
	
	private static ArrayList<Character> encryptedChars = new ArrayList<Character>();
	private static ArrayList<Character> decryptedChars = new ArrayList<Character>();
	
	// Getters & Setters
	public static ArrayList<Character> getEncryptedChars() {
		return encryptedChars;
	}

	public static ArrayList<Character> getDecryptedChars() {
		return decryptedChars;
	}

	// Methods
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
	public static void initialiseSquare() {
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
