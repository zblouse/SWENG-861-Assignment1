package edu.psgv.sweng861;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * 
 * @author Zach Blouse
 *
 */
public class Assignment1 {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//input checking
		if(args.length != 2) {
			System.out.println("Error: Invalid Arguments. Program requires filename and number of characters to read.");
			System.exit(1);
		}
		String fileName = args[0];
		String fullFile = readTextFile(fileName);
		Integer maxCharacters = Integer.getInteger(args[1]);
		String[] lines = fullFile.split("\n");
		//character count processing and maxCharacter validation
		int characters = 0;
		for(String line: lines) {
			characters += line.length();
		}
		if(characters > maxCharacters) {
			System.out.println("Error: File cannot contain more than the maximum number of characters");
		}
		//Word and number processing
		int words = 0;
		int numbers =0;
		for(String line: lines) {
			String[] splitLine = line.split(" ");
			for(String section: splitLine) {
				if(isNumber(section)) {
					numbers++;
				} else {
					words++;
				}
			}
		}
	}
	
	/**
	 * Reads in a text file to a string and adds new line characters
	 * @param fileName
	 * @return
	 */
	private static String readTextFile(String fileName) {
		String file = "";
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);
			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				file += line + '\n';
			}
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Error: Unable to open file '" + fileName + "'");
			System.exit(1);
		} catch (IOException ex) {
			System.out.println("Error: Error reading file '" + fileName + "'");
			System.exit(1);
		}
		return file;
	}
	
	/**
	 * Checks whether an input string is a number.
	 * @param stringToCheck
	 * @return boolean whether input is a number.
	 */
	private static boolean isNumber(String stringToCheck) {
		Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
		return pattern.matcher(stringToCheck).matches();
	}
}
