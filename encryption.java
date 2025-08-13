import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;


public class encryption {
	
	public static void encrypt(String inputfile, String outputfile, String keyword)
	{
		/*storing each char (which has already been made uppercase) from the 5 letter keyword*/
		char char1 = keyword.charAt(0);
		char char2 = keyword.charAt(1);
		char char3 = keyword.charAt(2);
		char char4 = keyword.charAt(3);
		char char5 = keyword.charAt(4);
		char[] storedChars = {char1, char2, char3, char4, char5};
		
		FileInputStream fileByteStream = null; // File input stream
	    Scanner inFS = null;  // Scanner object 
	    PrintWriter outFS = null; //use to write to output file
	     
	    // Try to open file, throw error if file not found or if there is some other kind of issue
	    try
	    {
		    fileByteStream = new FileInputStream(inputfile);
		    inFS = new Scanner(fileByteStream);
	        outFS = new PrintWriter(new FileOutputStream(outputfile));
	         
	        int indexVal = 0;
	        while(inFS.hasNextLine())
	        {	//user input stored in variable line
	        	String line = inFS.nextLine();
	        	//go through each individual char of the word stored in line
	            for (int i = 0; i < line.length(); i++)
	            {
	            	//c is the char at current index
	            	char c = line.charAt(i);
	            	 //ensures we ignore spaces " "
	                   if (Character.isLetter(c)) {
	                	   //stores the char from the keyword that will be used to shift the corresponding
	                	   //char from the input file
	                       char keyChar = storedChars[indexVal % 5];
	                       //assigned the correct ascii value to k for key in the equations below
	                       int shift = keyChar - 'A';
	                        
	                       //c is from the input file so it could be uppercase
	                       if (Character.isUpperCase(c)) 
	                       {
	                           c = (char) ('A' + (c - 'A' + shift) % 26);
	                       } 
	                       //if c from the input file is lowercase
	                       else 
	                       {
	                           c = (char) ('a' + (c - 'a' + shift) % 26);
	                       }
	                       //increment to the next char in the keyword
	                       indexVal++;
	                   }
	                   //if we encounter a space " ", we just write it to the output file
	                   outFS.print(c);
	            }
	               outFS.println();
	        	 
	        }
	        //Need to close Scanner and printwriter so info gets pushed to outputfile from buffer  
	        inFS.close();
	        outFS.close();
	    }
	    catch(FileNotFoundException e)
	    {
	    	System.out.println("File not found");
	    }
	    catch(Exception e)
	    {
	    	System.out.println("Unable to open file due to unexpected issue");
	    }
}
	
	public static void decrypt(String inputfile, String outputfile, String keyword)
	{
		/*storing each char (which has already been made uppercase) from the 5 letter keyword*/
		char char1 = keyword.charAt(0);		
		char char2 = keyword.charAt(1);		
		char char3 = keyword.charAt(2);
		char char4 = keyword.charAt(3);
		char char5 = keyword.charAt(4);
		char[] storedChars = {char1, char2, char3, char4, char5};
		
		//FileInputStream fileByteStream = null; // File input stream
	    //Scanner inFS = null;  // Scanner object 
	    //PrintWriter outFS = null; //use to write to output file
		
		try
		{
			FileInputStream fileByteStream = new FileInputStream(inputfile);
			Scanner inFS = new Scanner(fileByteStream);
			PrintWriter outFS = new PrintWriter(new FileOutputStream(outputfile));
	         
	         int indexVal = 0;
	         while(inFS.hasNextLine())
	         {	//user input stored in variable line
	        	 String line = inFS.nextLine();
	        	 //go through each individual char of the word stored in line
	             for (int i = 0; i < line.length(); i++)
	             {
	            	 //c is the char at current index
	            	 char c = line.charAt(i);
	            	 	//ensures we ignore spaces " "
	                    if (Character.isLetter(c)) {
	                    	//stores the char from the keyword that will be used to shift the corresponding
	                    	//char from the input file
	                        char keyChar = storedChars[indexVal % 5];
	                        //assigned the correct ascii value to k for key in the equations below
	                        int shift = keyChar - 'A';
	                        
	                        //c is from the input file so it could be uppercase
	                        if (Character.isUpperCase(c)) 
	                        {
	                            c = (char) ('A' + (26 + (c - 'A') - shift) % 26);
	                        } 
	                        //if c from the input file is lowercase
	                        else 
	                        {
	                            c = (char) ('a' + (26 + (c - 'a') - shift) % 26);
	                        }
	                        //increment to the next char in the keyword
	                        indexVal++;
	                    }
	                    //if we encounter a space " ", we just write it to the output file
	                    outFS.print(c);
	             }
	                outFS.println();
	         }
	         //Need to close Scanner and printwriter so info gets pushed to outputfile from buffer  
	         inFS.close();
	         outFS.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found");
		}
		catch(Error e)
		{
			System.out.println("Unable to output file due to unexpected issue");
		}
		
		//System.out.println((int)char1 - 65); for char1 = a it is converted to A and does result in 0 when we subtract 65
		System.out.printf("Now you can go the %s file and check the content of the file.\n ", outputfile);
		System.out.printf("Contents on the %s: ", outputfile);
		

		// Re-open output file for reading
		try {
		    FileInputStream fileByteStream = new FileInputStream(outputfile);
		    Scanner outReader = new Scanner(fileByteStream);

		    while (outReader.hasNextLine()) {
		        String line = outReader.nextLine();
		        System.out.println(line); // print each line from output file
		    }
            //closes scanner 
		    outReader.close();
		} catch (IOException e) {
		    System.out.println("Error reading the output file.");
		}
	
	}

	public static void main(String[] args) {
		System.out.println("Current working directory: " + System.getProperty("user.dir"));
		boolean reprompt = true;
		Scanner scnr = new Scanner(System.in);
	
		while(reprompt)
		{
			System.out.println("Would you like to encrypt or decrypt a file (E or D)? ");
			String answer = scnr.nextLine();
			if(answer.toUpperCase().equals("E"))
			{
				System.out.println("Enter the name of your input file you want to encrypt: ");
				String inputName = scnr.nextLine();
				System.out.println("Enter the name of the output file to write the ciphertext: ");
				String outputName = scnr.nextLine();
				System.out.println("Enter a 5-letter key to encrypt file: ");
				String keyword = scnr.nextLine();
				keyword = keyword.toUpperCase(); //sets keyword to correct ascii value
				while(keyword.length() != 5)
				{
					System.out.println("Keyword must be length 5");
					System.out.println("Enter a 5-letter key to encrypt file: ");
					keyword = scnr.nextLine();
					keyword = keyword.toUpperCase(); //sets keyword to correct ascii value
				}
				encrypt(inputName, outputName, keyword);
				reprompt = false;
			}
			else if(answer.toUpperCase().equals("D"))
			{
				System.out.println("Enter the name of your input file you want to decrypt: ");
				String inputName = scnr.nextLine();
				System.out.println("Enter the name of the output file to write the plaintext: ");
				String outputName = scnr.nextLine();
				System.out.println("Enter a 5-letter key to decrypt file: ");
				String keyword = scnr.nextLine();
				keyword = keyword.toUpperCase(); //sets keyword to correct ascii value
				while(keyword.length() != 5)
				{
					System.out.println("Keyword must be length 5");
					System.out.println("Enter a 5-letter key to encrypt file: ");
					keyword = scnr.nextLine();
					keyword = keyword.toUpperCase(); //sets keyword to correct ascii value
				}
				decrypt(inputName, outputName, keyword);
				reprompt = false;
			}
			else //answer is not valid
			{
				System.out.println("Invalid input try again. ");
				reprompt = true;
			}
		}
	}
    

}
