package gmit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIn {
	
	public static void encryptFile() throws IOException {
		
		File file = new File("SampleFiles/WarAndPeace-LeoTolstoy.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

		Encrypt.initialiseEncryptSquare();

		String line = null;

		while ((line = br.readLine()) != null) {
			Encrypt.encryptLine(line);
		}
		br.close();
	}
}
