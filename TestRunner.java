package gmit;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

/*
 * TEST RUNNER CLASS
 * 
 * This class contains the main method which allows the user to enter a 
 * keyword and a file url and file name for encryption and decryption.
 * 
 * The second method is a JUNIT test method that has various assertations to check that
 * the program is encrypting/decrypting correctly. It uses the book war and peace to
 * perform these tests.
 */

public class TestRunner {

	// Tests Full Encryption & Decryption.
	// User can enter fileURL, keyword and name for the output ciphertext file.
	// For decryption user can just enter keyword and filename as the url is going to be known.
	public static void main(String[] args) throws IOException{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner( System.in );

		// Gets choice off user, 1 = Encrypt 2 = Decrypt
		int userChoice;
		do {
			System.out.println("ENCRYPTION // DECRYPTION");
			System.out.println("Please enter 1 to Encrypt or 2 to Decrypt.");

			// Input Validation
			while (!scanner.hasNextInt()) {
				System.out.println("That's not a valid option!");
				System.out.println("Enter 1 to Encrypt or 2 to Decrypt");
				scanner.next(); 
			}
			userChoice = scanner.nextInt();

		} while (userChoice != 1 && userChoice != 2);

		// If the user choses encryption
		if (userChoice == 1) {

			// ArrayList used and boolean used when checking for duplicates
			ArrayList<Character> keywordArray = new ArrayList<Character>();
			boolean duplicateFlag;
			String keyword;

			do {
				duplicateFlag = false;
				System.out.println("Please enter keyword for encryption");
				System.out.println("Must be longer then 3 characters and contain no duplicate values.");
				keyword = scanner.next();

				// Creates arraylist with values from keyword and checks for duplicates
				for (char c : keyword.toLowerCase().toCharArray()) {
					if (keywordArray.contains(c)) {
						duplicateFlag = true;
					}
					keywordArray.add(c);
				}

				// Checks if there are duplicates or if the keyword is too short and warns user
				if (duplicateFlag == true || keyword.length() < 4) {
					System.out.println("Looks like you have entered an invalid keyword!");
					keywordArray.clear();
					duplicateFlag = true;
				}
			} while (duplicateFlag == true);

			// Gets File URL via the file url input method
			String fileUrl = fileUrlInput();

			// Promts for a name to give the cipher text file
			String fileName;
			System.out.println("Please enter a name for the ciphertext file.");
			fileName = scanner.next().toLowerCase();

			// Calls Encrypt function
			Encrypt.encryptFile(keyword, fileUrl, fileName);
			System.out.println("Encryption Complete!");

			// If the user choses decryption
		} else if (userChoice == 2) {
			String keyword, fileName;

			// Prompts for keyword
			System.out.println("Please enter keyword");
			System.out.println("If keyword is not correct output file will be jibberish!");
			keyword = scanner.next();

			// Prompts for name of file to decrypt
			System.out.println("Please name your decrypted file");
			fileName = scanner.next();

			// Gets File URL via the file url input method
			String fileUrl = fileUrlInput();

			// Calls decrypt function
			Decrypt.decryptFile(keyword, fileUrl, fileName);

			// Prints to console when decryption is complete
			System.out.println("Decryption Complete");
		}
	}

	// File URL input method with some basic validation
	public static String fileUrlInput(){
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner( System.in );
		boolean validFile;
		File f = null;
		String fileURL;

		do {
			// Resets flag
			validFile = true;

			// Promps for file url
			System.out.println("Please enter an absolute file URL");
			fileURL = scanner.next();

			f = new File(fileURL);

			// Checks too see if file url provided can be used in as a file input stream
			try {
				@SuppressWarnings({ "unused", "resource" })
				BufferedReader testBr = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			} catch (Exception e) {
				validFile = false;
			}

			// Tells user that the file url provided is invalid
			if (validFile == false) {
				System.out.println("Invalid file url!");
			}
			// Keeps looping until user enters a valid file url
		} while (validFile == false);
		return fileURL;
	}

	// Tests Full Encryption & Decryption.
	// Uses the book War & Peace and the keyword MaThIsOn.
	// Test uses JUnit and keyword can be changed.
	// File should not be changed as the JUnit tests
	// rely on expected values from the war and peace file provided.
	@Test
	public void TestLargeFileEncryption() throws IOException {	

		// Encrypt & decrypt methods calls
		Encrypt.encryptFile("johnmalc", "WarAndPeace-LeoTolstoy.txt", "warandpeace-encrypted");
		Decrypt.decryptFile("johnmalc", "warandpeace-encrypted.txt", "warandpeace-decrypted");

		// Test Variables
		Character[] expectedCipherTest = {'G', 'X', 'A', 'D', 'D', 'G', 'A', 'A', 'V', 'G', 'D', 'F', 'G', 'F', 'D', 'Z'};
		String[] decryptedFirstLineTest = {"C", "H", "A", "P", "T", "E", "R", " "};

		// Polybius Square encryption test
		for (int i = 0; i < expectedCipherTest.length -1; i++) {
			assertThat(Encrypt.getEncryptedChars().get(i), is(expectedCipherTest[i]));
		}

		// Reverse Transpostion test
		int count = 0;
		for (int i = 0; count < expectedCipherTest.length -1; i++) {
			for (int j = 0; j < Decrypt.getDecryptCT().getMatrix().size() && count < expectedCipherTest.length -1; j++) {
				assertThat(Decrypt.getDecryptCT().getMatrix().get(j).getChars().get(i), is(expectedCipherTest[count++]));
			}
		}

		// Final decrypted chars text 
		for (int i = 0; i < decryptedFirstLineTest.length; i++) {
			assertThat(Decrypt.getDecryptedChars().get(i), is(decryptedFirstLineTest[i]));
		}	
	}
}