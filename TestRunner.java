package gmit;

import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class TestRunner {
	
	@Test
	public void TestLargeFileEncryption() throws IOException {
		
		FileIn.encryptFile();
		ColumnarTransposition ct = new ColumnarTransposition("Java");
		ct.initializeMatrix();
		ct.fillMatrix();
		ct.sortMatrix();
		ct.writeToFile();
		
		// Tests that first and last character in cipher text are equal to the expected characters.
		ArrayList<Character> encryptedChars = Encrypt.getEncryptedChars();
		assertThat(encryptedChars.get(0), is('G'));
		assertThat(encryptedChars.get(encryptedChars.size() -1 ), is('D'));

		// Test Sorting Is Working Correctly
		assertThat(ct.getMatrix().get(0).getKeyCharacter(), is('a'));
		assertThat(ct.getMatrix().get(1).getKeyCharacter(), is('a'));
		assertThat(ct.getMatrix().get(2).getKeyCharacter(), is('j'));
		assertThat(ct.getMatrix().get(3).getKeyCharacter(), is('v'));
		
		assertThat(encryptedChars.get(encryptedChars.size() %4 +1 ), is('X'));

		// Test that character duplication is not causing any issues
		// e.g - There are two "a"s in the keyword java.
		// Therefore the first "a" should be equal to index 1 and the second
		// "a" should be equal to index 3
		assertThat(ct.getMatrix().get(0).getIndex(), is(1));
		assertThat(ct.getMatrix().get(1).getIndex(), is(3));
		assertThat(ct.getMatrix().get(2).getIndex(), is(0));
		assertThat(ct.getMatrix().get(3).getIndex(), is(2));	
	}
}
