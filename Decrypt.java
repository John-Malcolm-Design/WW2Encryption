package gmit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Decrypt {

	// Class Members
	private static Map<String, String> polybiusDecrypt = new HashMap<String, String>();
	private static ArrayList<String> decryptedChars = new ArrayList<String>();
	private static ColumnarTransposition decryptCT;
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

	// Methods
	public static void decryptFile(String key) throws IOException {
		decryptCT = new ColumnarTransposition(key);
		File file = new File("cipher.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		initialiseDecryptSquare();
		decryptCT.initializeMatrix();

		String line = null;
		int j = 0;
		messageSize = 0;

		System.out.println("Last character of each column after initial transposition");
		while ((line = br.readLine()) != null) {
			decryptCT.fillMatrix(line, j);
			messageSize = messageSize + line.length();
			j++;
		}
		br.close();

		decryptCT.setCorrectIndexValues();
		decryptCT.reverseTranspose();
		fillDecryptedCharsArray();
		System.out.println(messageSize);
	}

	public static void fillDecryptedCharsArray() {
		int numberOfRows = messageSize / decryptCT.getMatrix().size();
		int count = 0;
		while (count < numberOfRows) {
			for (int i = 0; i < decryptCT.getMatrix().size() -1; i++) {
				Character keyOne= decryptCT.getMatrix().get(i).getChars().get(count);
				Character keyTwo = decryptCT.getMatrix().get(++i).getChars().get(count);

				String currentKey = keyOne.toString() + keyTwo.toString();
				decryptedChars.add(polybiusDecrypt.get(currentKey));
			}	
			count++;
		}

		ArrayList<Character> lastRow = new ArrayList<Character>();
		if (messageSize % decryptCT.getMatrix().size() != 0) {
			int p = 0;
			while (decryptCT.getMatrix().get(p).getChars().size() == (numberOfRows +1) && p < decryptCT.getMatrix().size()) {
				lastRow.add(decryptCT.getMatrix().get(p).getChars().get(count));
				p++;

			}
			
			for (int k = 0; k < lastRow.size(); k++) {
				System.out.println(lastRow.get(k));
				
				Character keyOne= lastRow.get(k);
				Character keyTwo = lastRow.get(++k);

				String currentKey = keyOne.toString() + keyTwo.toString();
				decryptedChars.add(polybiusDecrypt.get(currentKey));
			}
		}

		System.out.println("Size of last row: " + lastRow.size());

	}

	private static void initialiseDecryptSquare() {
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
