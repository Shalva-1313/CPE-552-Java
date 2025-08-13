/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.Scanner;

public class Main {

	/**
	 * @param usrStr - user input
	 * @returnm	Returns the number of non-whitespace characters in the given string
	 */
	public static int getNumOfNonWSCharacters(String usrStr)
	{
		int count = 0;
		for(int i = 0; i<usrStr.length(); i++)
		{
			char target = ' ';
			if(usrStr.charAt(i) != target)
			{
				count++;
			}
		}
		
		//int nonWS = usrStr.length() - count;
		//return nonWS;
		return count;
	}
	
	/**
	 * 
	 * @param usrStr
	 * @return Returns the number of words in the given string
	 */
	public static int getNumOfWords(String usrStr)
	{
		int count = 0;
		for(int i = 0; i<usrStr.length(); i++)
		{
			char target = ' ';
			if(usrStr.charAt(i) == target) //if we are at a blank space
			{
				if(usrStr.charAt(i-1) != target) //if the previous character is the end of a word
				{
					count++;
				}
			}
		}
		count++; //you reach the end of the string so increment for the last word that did not have a space after it
		return count;
	}
	
	
	/**
	 * 
	 * @param toFind
	 * @param usrStr
	 * @return the number of times toFind appears in usrStr. Note: this code 
	 * does not account for capitalization if the word is the first in the sentence
	 */
	public static int findText(String toFind, String usrStr)
	{
	    
	    if(usrStr.contains(toFind))
		{
			int count = 0; //keeps track of how many times toFind is in usrStr
	        int index = 0; //initialize index variable
	        //index is set to the first occurrence of toFind in userStr 
	        while ((index = usrStr.indexOf(toFind, index)) != -1) 
	        {
	            count++;    
	            index += toFind.length();   //increase index so search continues after this occurrence of toFind
	        }
	        return count;
		}
		else
		{
			return 0; //toFind does not exist in usrStr
		}
	}
	
	/**
	 * 
	 * @param usrStr
	 * @return Returns a modified string where all exclamation marks are replaced with periods
	 */
	public static String replaceExclamation(String usrStr)
	{

		String copy = usrStr; //copy is the edited version of usrStr we will return
		for(int i = 0; i<usrStr.length(); i++)
		{
			char target = '!';
			if(usrStr.charAt(i) == target) 
			{
				copy = usrStr.replace('!', '.'); //replace exclamation points with periods
			}
		}
		
		return copy;
	}
	
	/**
	 * 
	 * @param usrStr
	 * @return Returns a modified string where consecutive spaces are reduced to a single space.
	 */
	
	public static String shortenSpace(String usrStr)
	{
		//Remove leading and trailing white spaces and replaces one or more white space characters in a row with just one space. 
		String newStr = usrStr.trim().replaceAll("\\s+", " "); 
		return newStr;
	}
	
	public static void main(String[] args) {
		boolean keepGoing = true;
		Scanner scnr = new Scanner(System.in);
		/* Type your code here. */
		
		System.out.println("Enter a sample text: \n");
		String userText = scnr.nextLine();
		System.out.println("You entered: " + userText + "\n");
		
		while(keepGoing)
		{
			System.out.println("MENU");
			System.out.println("c - Number of non-whitespace characters\n"
					+ "w - Number of words\n" + "f - Find text\n" + "r - Replace all !'s\n"
					+ "s - Shorten spaces\n" + "q - Quit\n");
			
			System.out.println("Choose an option: ");
			String userOption = scnr.next();
			
			if(userOption.equals("c"))	//I manually counted 37 non-whitespace characters not 36.
			{	
				int answer = getNumOfNonWSCharacters(userText);
				System.out.println("Number of non-whitespace characters: \n" + answer);
				System.out.println();
			}
			else if (userOption.equals("w"))	//find number of words in text
			{
				int answer = getNumOfWords(userText);
				System.out.println("Number of words: \n" + answer);
				System.out.println();
			}
			else if (userOption.equals("f"))	//enter phrase to find in text
			{
				scnr.nextLine();
				System.out.println("Enter a word or phrase to be found:\n");
				String phrase = scnr.nextLine();
				int answer = findText(phrase, userText);
				System.out.printf("\'%s\' instances: %d\n", phrase, answer);
				System.out.println();
			}
			else if (userOption.equals("r"))	//replace exclamation points
			{
				String answer = replaceExclamation(userText);
				System.out.println("Edited text: \n" + answer);
				System.out.println();
			}
			else if (userOption.equals("s"))	//get rid of extra spaces
			{
				String answer = shortenSpace(userText);
				System.out.println("Edited text: \n" + answer);
				System.out.println();
			}
			else if (userOption.equals("q")) //user wants to quit program
			{
				keepGoing = false;
				scnr.close();
			}
		}

	}

}
