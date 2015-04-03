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
		while (count < (Encrypt.getEncryptedChars().size() - 1)) {
			for (int j = 0; j < keyArray.size() && count < (Encrypt.getEncryptedChars().size() - 1); j++) {
				matrix.get(j).getChars().add(Encrypt.getEncryptedChars().get(count++));
			}
		}
	}
	
	public void sortMatrix() {
		Collections.sort(matrix);
	}

	public void writeToFile() throws FileNotFoundException {
		PrintWriter out = new PrintWriter("cipher.txt");
		for (int i = 0; i < key.length(); i++) {
			for (int p = 0; p < matrix.get(0).getChars().size(); p++) {
				out.print(matrix.get(0).getChars().get(p));
			}		
		}
		out.close();
	}
}
