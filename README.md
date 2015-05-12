#ADFGVX Cypher - John Anderson
Data Structures Project - Rapid Encryption using the German WW1 ADFGVX Cypher
This application utilizes the power of ArrayLists, HashMaps and Comparators for rapid encryption and decryption. As well as the abstract data types listed above the application uses JUnit for unit testing and several custom objects as illustrated by the KeyColumn and ColumnarTransposition classes.

The average running time for the JUnit test is 2.7 seconds. That time includes reading from a file the book war and peace, full encryption, writing to file cipher text, reading in the cipher text file, full decryption, then writing decrypted text to a file and also the JUnit tests at the end. The average time of 2.7 based on 70 tests on my MacBook Pro, this includes file reading twice and file writing twice and would obviosly be a lot quicker without. Big O for each method can be found commented beside method declaration.

Included are the class files listed below, some text files used for testing the application and a jar file that can be run by opening your terminal and running the following commands:

Main Application:
$ java -jar encoder.jar
JUnit Test:
$ java -cp encoder.jar org.junit.runner.JUnitCore TestRunner
For the JUnit test to work the WarAndPeace-LeoTolstoy.txt file must be in the same location as the jar file!
Contents
TestRunner.java
Encrypt.java
Decrypt.java
ColumnarTransposition.java
KeyColumn.java
TransposeComparator.java
ReverseTransposeComparator.java
encoder.jar
WarAndPeace-LeoTolstoy.txt
TestRunner Class
This class contains the main method which allows the user to enter a keyword and a file url and file name for encryption and decryption. The second method is a JUNIT test method that has various assertations to check that the program is encrypting/decrypting correctly. It uses the book war and peace to perform these tests.

Encrypt Class
This class contains the HashMap used for the polybius square, the character ArrayList which will be filled with the encrypted characters from the polybius square and the columnar transosition object which holds the matrix and allows us to perform operations on like columnar transposition.

Decrypt Class
This class contains the HashMap used for the polybius square, the character ArrayList which will be filled with the decrypted characters from the polybius square and the columnar transosition object which holds the matrix and allows us to perform operations on like columnar transposition. There is also a variable for the message size which is needed to properly perform reverse columnar transposition.

ColumnarTransposition Class
This class contains a matrix object which is an array list of KeyColumns. The KeyColumns basically being abstracted arraylists with extra variables and methods. This class also cotains arraylists for both the sorted and and unsorted version of the keyword. This class contains methods for columnar transposition and also for printing to file cipher text and decrypted text. Columnar transpositions objects are created for both encryption and decryption this approach is OO and DRY and helps keep the code more understandable.

KeyColumn Class
This class is used to create the columns in the matrix. Included an ArrayList for the characters an index variable a key character variable.

TransposeComparator Class
Implement Comparator and used for columnar tranposition during encryption.

ReverseTransposeComparator Class
Implement Comparator and used for columnar tranposition during decryption.

ADFGVX Cipher History (from project brief)
The ADFGVX cypher was used by the German Army in WW1 from March 1918 to encrypt field communications during the Ludendorff Offensive (Kaiserschlacht). The cypher is so named because all messages are encrypted into codes from the small alphabet of ADFGVX to reduce operator error when sending Morse Code signals. Although an improvement on the ADFGX cypher used by the Germans up until 1918, the new cypher was broken by the French cryptanalyst Georges Painvin and proved decisive in repulsing the attack at Compiègne on June 11th 1918.
Todo's
Better Input Validation
Allow user to enter duplicates in keyword
Add GUI
More thorough Big O research/ documenting for methods.
License
MIT
