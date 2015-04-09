package gmit;

import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class TestRunner {

	@Test
	public void TestLargeFileEncryption() throws IOException {	

		Encrypt.encryptFile("qwertyuiopasd", "SampleFiles/WarAndPeace-LeoTolstoy.txt");
		Decrypt.decryptFile("qwertyuiopasd");

//		// Tests that certain characters in cipher text are equal to the expected characters.
//		ArrayList<Character> encryptedChars = Encrypt.getEncryptedChars();
//
//		// Below array is cipher text equivalent of CHAPTERI
//		Character[] testCharsCipher = {'G', 'X', 'A', 'D', 'D', 'G', 'A', 'A', 'V', 'G', 'D', 'F', 'G', 'F', 'V', 'X'};
//		String[] testCharsLiteral = {"C", "H", "A", "P", "T", "E", "R", "I"};
//		for (int i = 0; i < testCharsCipher.length -1; i++) {
//			assertThat(encryptedChars.get(i), is(testCharsCipher[i]));
//		}
//
//		// Test that reverse transpose on matrix for decryption is working correctly
//		int count = 0;
//		for (int i = 0; count < testCharsCipher.length -1; i++) {
//			for (int j = 0; j < Decrypt.getDecryptCT().getMatrix().size() && count < testCharsCipher.length -1; j++) {
//				assertThat(Decrypt.getDecryptCT().getMatrix().get(j).getChars().get(i), is(testCharsCipher[count++]));
//			}
//
//		}
//
//		// Test that decrypted text character array is being filled properly
//		for (int i = 0; i < testCharsLiteral.length; i++) {
//			assertThat(Decrypt.getDecryptedChars().get(i), is(testCharsLiteral[i]));
//		}
		
		// TESTING GOLD!!!
		
//		System.out.println("Old Size of Decrypted Char Array Vs New Size");
//		System.out.println(2626466 + " " + Decrypt.getDecryptedChars().size());
		
		System.out.println("Last character of each column after reverse transposition");
		System.out.println(Decrypt.getDecryptCT().getMatrix().get(0).getChars().get(Decrypt.getDecryptCT().getMatrix().get(0).getChars().size()-1));
		System.out.println(Decrypt.getDecryptCT().getMatrix().get(1).getChars().get(Decrypt.getDecryptCT().getMatrix().get(1).getChars().size()-1));
		System.out.println(Decrypt.getDecryptCT().getMatrix().get(2).getChars().get(Decrypt.getDecryptCT().getMatrix().get(2).getChars().size()-1));
		System.out.println(Decrypt.getDecryptCT().getMatrix().get(3).getChars().get(Decrypt.getDecryptCT().getMatrix().get(3).getChars().size()-1));
		System.out.println("LINE BREAK");
		
		System.out.println("Last 4 of decrypted text array");
		System.out.println(Decrypt.getDecryptedChars().get(Decrypt.getDecryptedChars().size()-4));
		System.out.println(Decrypt.getDecryptedChars().get(Decrypt.getDecryptedChars().size()-3));
		System.out.println(Decrypt.getDecryptedChars().get(Decrypt.getDecryptedChars().size()-2));
		System.out.println(Decrypt.getDecryptedChars().get(Decrypt.getDecryptedChars().size()-1));
		
	}
}
