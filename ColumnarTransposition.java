package gmit;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ColumnarTransposition {

	private String key;
	private ArrayList<Character> keyArray = new ArrayList<Character>();
	private List<KeyColumn> matrix = new ArrayList<KeyColumn>();

	public ColumnarTransposition(String key) {
		this.key = key;
	}

	// Get Matrix
	public List<KeyColumn> getMatrix() {
		return matrix;
	}

	public void initializeMatrix()  {

		for (char c : key.toLowerCase().toCharArray()) {
			keyArray.add(c);
		}	

		for (int i = 0; i < keyArray.size(); i++) {
			matrix.add(new KeyColumn(keyArray.get(i), i));
		}		
	}

	public void fillMatrix() {
		int count = 0;
		while (count < (Encrypt.getEncryptedChars().size())) {
			for (int j = 0; j < keyArray.size() && count < (Encrypt.getEncryptedChars().size()); j++) {
				matrix.get(j).getChars().add(Encrypt.getEncryptedChars().get(count++));
			}
		}
	}

	public void fillMatrix(String line, int j) {
		int count = 0;
		while (count < line.length() -1) {
			matrix.get(j).getChars().add(line.charAt(count));
			count++;
		}
	}

	public void transpose() {
		Collections.sort(matrix, new transposeComparator());
	}

	public void reverseTranspose() {
		Collections.sort(matrix, new reverseTransposeComparator());
	}

	public void writeToFile() throws FileNotFoundException {
		PrintWriter out = new PrintWriter("cipher.txt");
		for (int i = 0; i < key.length(); i++) {
			for (int p = 0; p < matrix.get(i).getChars().size(); p++) {
				out.print(matrix.get(i).getChars().get(p));
			}	
			out.print("\n");
		}
		out.close();
	}
}
