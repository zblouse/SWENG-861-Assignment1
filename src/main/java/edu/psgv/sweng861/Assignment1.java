package edu.psgv.sweng861;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This class reads a text file and prints out statistics on the file as well as the content of the file in reverse character order.
 * @author Zach Blouse
 *
 */
public class Assignment1 {

	/**
	 * main requires a single command line argument, the path to the file to process
	 * @param args[0] path of the file to read
	 */
	public static void main(String[] args) {
		//gathering input and validating
		if(args.length != 1) {
			System.out.println("Error: Invalid Arguments. Program requires filename as argument.");
			System.exit(1);
		}
		String fileName = args[0];
		String fullFile = readTextFile(fileName);
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the maximum number of characters to read: ");
		int maxCharacters = 0;
		try {
			maxCharacters = scanner.nextInt();
		} catch (Exception e) {
			System.out.println("Error: Invalid Entry.");
			System.exit(1);
		}
		String[] lines = fullFile.split("\n");
		//character count processing and maxCharacter validation
		int characters = 0;
		for(String line: lines) {
			characters += line.length();
		}
		if(characters > maxCharacters) {
			System.out.println("Error: File cannot contain more than the maximum number of characters");
			System.exit(1);
		}
		//Word and number processing
		int words = 0;
		int numbers =0;
		for(String line: lines) {
			String[] splitLine = line.split(" ");
			for(String section: splitLine) {

				if(isNumber(section)) {
					numbers++;
				} else if(isWord(section)) {
					words++;
				}
			}
		}
		//Character Processing
		int spaces = 0;
		int punctuation = 0;
		int upper = 0;
		int lower = 0;
		List<String> reversedStrings = new ArrayList<String>();
		for(String line: lines) {
			char[] lineChars = line.toCharArray();
			for(char character : lineChars) {
				if(Character.isWhitespace(character)) {
					spaces++;
				} else if(isPunctuation(character)) {
					punctuation++;
				} else if(Character.isUpperCase(character)) {
					upper++;
				} else if(Character.isLowerCase(character)) {
					lower++;
				}
			}
			//Reverse the line
			char reversedLine[] = new char[lineChars.length];
			int reverseLineCounter = 0;
			for(int lineCharCounter = lineChars.length -1; lineCharCounter >= 0; lineCharCounter-- ) {
				reversedLine[reverseLineCounter++] = lineChars[lineCharCounter];
			}
			reversedStrings.add(0, String.valueOf(reversedLine));
		}
		String reversedString = "";
		for(String string: reversedStrings) {
			reversedString += string;
		}
		//Format Output
		System.out.println("Words: " + words);
		System.out.println("Numbers: " + numbers);
		System.out.println("Chars (incl. Spaces): " + characters);
		System.out.println("Spaces: " + spaces);
		System.out.println("Punctuation: " + punctuation);
		System.out.println("Uppercase chars: " + upper);
		System.out.println("Lowercase chars: " + lower);
		System.out.println(reversedString.toLowerCase());
	}
	
	/**
	 * Reads in a text file to a string and adds new line characters
	 * @param fileName
	 * @return
	 */
	private static String readTextFile(String fileName) {
		String file = "";
		try {
			FileReader fileReader = new FileReader(fileName);
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
	 * Checks whether an input string is a word.
	 * @param stringToCheck String, the string to check
	 * @return boolean whether input is a word.
	 */
	private static boolean isWord(String stringToCheck) {
		Pattern wordPattern = Pattern.compile("^[A-Za-z!,?.,'\":-]+$");
		Pattern justPunctuation = Pattern.compile("^[!,?.,'\":-]+$");
		return wordPattern.matcher(stringToCheck).matches() && !justPunctuation.matcher(stringToCheck).matches();
	}
	
	/**
	 * Checks whether an input string is a number.
	 * @param stringToCheck String, the string to check
	 * @return boolean whether input is a number.
	 */
	private static boolean isNumber(String stringToCheck) {
		Pattern pattern = Pattern.compile("^[\\d]+$");
		return pattern.matcher(stringToCheck).matches();
	}
	
	/**
	 * Checks whether a char is a punctuation char
	 * @param character char, the character to check
	 * @return boolean, whether the character is a punctuation character
	 */
	private static boolean isPunctuation(char character) {
		List<Character> punctuation = Arrays.asList('!','?','.',',','\'','\"',';',':','-','(',')','{','}','<','>','[',']');
		return punctuation.contains(character);
	}
}
