package gmit;

import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class TestRunner {
	
	@Test
	public void TestLargeFileEncryption() throws IOException {
		
		
		ColumnarTransposition encryptCT = new ColumnarTransposition("Java");
		
		FileIn.encryptFile();
		encryptCT.initializeMatrix();
		encryptCT.fillMatrix();
		encryptCT.transpose();
		encryptCT.writeToFile();
		
		Decrypt.decryptFile();
		
		// Tests that certain characters in cipher text are equal to the expected characters.
		ArrayList<Character> encryptedChars = Encrypt.getEncryptedChars();
		assertThat(encryptedChars.get(0), is('G'));
		assertThat(encryptedChars.get(1), is('X'));
		assertThat(encryptedChars.get(2), is('A'));
		assertThat(encryptedChars.get(3), is('D'));
		assertThat(encryptedChars.get(encryptedChars.size() -1 ), is('D'));
		assertThat(encryptedChars.get(encryptedChars.size() -2 ), is('Y'));

		// Test Sorting Is Working Correctly for encryption
		assertThat(encryptCT.getMatrix().get(0).getKeyCharacter(), is('a'));
		assertThat(encryptCT.getMatrix().get(1).getKeyCharacter(), is('a'));
		assertThat(encryptCT.getMatrix().get(2).getKeyCharacter(), is('j'));
		assertThat(encryptCT.getMatrix().get(3).getKeyCharacter(), is('v'));
		
		// Test that character duplication is not causing any issues
		// e.g - There are two "a"s in the keyword java.
		// Therefore the first "a" should be equal to index 1 and the second
		// "a" should be equal to index 3
		assertThat(encryptCT.getMatrix().get(0).getIndex(), is(1));
		assertThat(encryptCT.getMatrix().get(1).getIndex(), is(3));
		assertThat(encryptCT.getMatrix().get(2).getIndex(), is(0));
		assertThat(encryptCT.getMatrix().get(3).getIndex(), is(2));
		
		// Test that reverse transpose on matrix for decryption is working correctly
		assertThat(Decrypt.getDecryptCT().getMatrix().get(0).getChars().get(0), is('G')); //
		assertThat(Decrypt.getDecryptCT().getMatrix().get(1).getChars().get(0), is('X')); //  C
		assertThat(Decrypt.getDecryptCT().getMatrix().get(2).getChars().get(0), is('A')); // 
		assertThat(Decrypt.getDecryptCT().getMatrix().get(3).getChars().get(0), is('D')); //  H
		assertThat(Decrypt.getDecryptCT().getMatrix().get(0).getChars().get(1), is('D')); // 
		assertThat(Decrypt.getDecryptCT().getMatrix().get(1).getChars().get(1), is('G')); //  A
		assertThat(Decrypt.getDecryptCT().getMatrix().get(2).getChars().get(1), is('A')); // 
		assertThat(Decrypt.getDecryptCT().getMatrix().get(3).getChars().get(1), is('A')); //  P	
		assertThat(Decrypt.getDecryptCT().getMatrix().get(0).getChars().get(2), is('V')); // 
		assertThat(Decrypt.getDecryptCT().getMatrix().get(1).getChars().get(2), is('G')); //  T
		assertThat(Decrypt.getDecryptCT().getMatrix().get(2).getChars().get(2), is('D')); // 
		assertThat(Decrypt.getDecryptCT().getMatrix().get(3).getChars().get(2), is('F')); //  E
		assertThat(Decrypt.getDecryptCT().getMatrix().get(0).getChars().get(3), is('G')); // 
		assertThat(Decrypt.getDecryptCT().getMatrix().get(1).getChars().get(3), is('F')); //  R
//		assertThat(Decrypt.getDecryptCT().getMatrix().get(2).getChars().get(3), is('D')); //      
//		assertThat(Decrypt.getDecryptCT().getMatrix().get(3).getChars().get(3), is('Z')); //  SPACE
		
	}
}
