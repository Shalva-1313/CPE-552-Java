/*
 * Name: Shasha Alvares
 * Date 2/20/25
 * Description: Homework Assignment 3, simulate a dice game and output user's score based on the numbers they rolled. 
 */

import java.util.Scanner;
import java.util.Arrays;

public class LabProgram{
    public static void main(String[] args) {
    
    Scanner scnr = new Scanner(System.in);
    int diceValues[] = new int[5];
    int highScore = 0;
    
    // Fill array with five values from input
    for(int i=0; i<diceValues.length; ++i) {
    diceValues[i] = scnr.nextInt();
    }   
    scnr.close();
    
    // Place values in ascending order
    Arrays.sort(diceValues);
    
    // Find high score and output
    highScore = findHighScore(diceValues);
    System.out.println("High score: " + highScore);
    }
    
    // Find high score
    public static int findHighScore(int dice[]) {
        int max = 0;
        int goal = 3;
        
        //sets max to the highest value out of all the functions
        //and return the highest value as the high score
        if(max < checkSingles(dice, goal)) 
        {
            max = checkSingles(dice, goal); //score is sum of goal mentions
        }
        if(max < checkThreeOfKind(dice))
        {
            max = checkThreeOfKind(dice); //score 30
        }
        if(max < checkFullHouse(dice))
        {
            max = checkFullHouse(dice); //score 35
        }
        if(max < checkFourOfKind(dice))
        {
            max = checkFourOfKind(dice); //score 40
        }
        if(max < checkStraight(dice))
        {
            max = checkStraight(dice); //score 45
        }
        if(max < checkFiveOfKind(dice))
        {
            max = checkFiveOfKind(dice); //score 50
        }

        return max;
    }
    
    // Add all occurrences of goal value
    public static int checkSingles(int dice[], int goal) {
    	int sum = 0;
    	// Loop through the array to find occurrences of the target number
        for (int i = 0; i < dice.length; i++) {
            if (dice[i] == goal) {
                sum += dice[i]; // Add the goal number to the sum each time it is found in the array
            }
        }
        return sum; // Return the total sum of occurrences of the goal number
    }
    
    // Check for three of a kind (score = 30)
    public static int checkThreeOfKind(int dice[]) {
        /*
         *  Array to count occurrences of each dice number (1 to 6). Index 0 is not used, and each index 1-6 represents
         *  the number that is rolled. The value stored in that index is the number of times that specific number was rolled. 
         *  For example if count[3] = 2, then the number 3 was rolled twice by the user. 
         */
        int[] counts = new int[dice.length+2]; // 5+2=7 so indices 1 to 6 inclusive are accounted for 
        
        // Count the occurrences of each number
        for (int i = 0; i < dice.length; i++) {
            counts[dice[i]]++;
        }

        // Check for three of a kind
        for (int i = 1; i < counts.length; i++) {
            if (counts[i] == 3) {
                return 30; // Return 30 if three of a kind is found
            }
        }
        // If no three of a kind is found, return -1
        return -1;
    }
    
    
    // Check for four of a kind (score = 40)
    public static int checkFourOfKind(int dice[]) {
    	/*
         *  Array to count occurrences of each dice number (1 to 6). Index 0 is not used, and each index 1-6 represents
         *  the number that is rolled. The value stored in that index is the number of times that specific number was rolled. 
         *  For example if count[3] = 2, then the number 3 was rolled twice by the user. 
         */
        int[] counts = new int[dice.length+2]; 
        
        // Count the occurrences of each number
        for (int i = 0; i < dice.length; i++) {
            counts[dice[i]]++;
        }

        // Check for four of a kind
        for (int i = 1; i < counts.length; i++) {
            if (counts[i] == 4) {
                return 40; // Return 40 if three of a kind is found
            }
        }
        // If no four of a kind is found, return -1
        return -1;
    }
    
    // Check for five of a kind (score = 50)
    public static int checkFiveOfKind(int dice[]) {
    	/*
         *  Array to count occurrences of each dice number (1 to 6). Index 0 is not used, and each index 1-6 represents
         *  the number that is rolled. The value stored in that index is the number of times that specific number was rolled. 
         *  For example if count[3] = 2, then the number 3 was rolled twice by the user. 
         */
        int[] counts = new int[dice.length+2]; 
        
        // Count the occurrences of each number
        for (int i = 0; i < dice.length; i++) {
            counts[dice[i]]++;
        }

        // Check for five of a kind
        for (int i = 1; i < counts.length; i++) {
            if (counts[i] == 5) {
                return 50; // Return 50 if five of a kind is found
            }
        }
        // If no five of a kind is found, return -1
        return -1;
    }
    
    // Check for full house (score = 35)
    public static int checkFullHouse(int dice[]) {
    	/*
         *  Array to count occurrences of each dice number (1 to 6). Index 0 is not used, and each index 1-6 represents
         *  the number that is rolled. The value stored in that index is the number of times that specific number was rolled. 
         *  For example if count[3] = 2, then the number 3 was rolled twice by the user. 
         */
        int[] counts = new int[dice.length+2]; 
        
        // Count the occurrences of each number
        for (int i = 0; i < dice.length; i++) {
            counts[dice[i]]++;
        }

        // Check for three of a kind and then for two of a kind
        for (int i=1; i < counts.length; i++) {
            if (counts[i] == 3) {
                for (int j = 1; j < counts.length; j++){
                    if (counts[j] == 2){
                        return 35; // Return 35 if three and two of a kind are found
                    }
                }
            }
        }
        // If no three and two of a kind are found, return -1
        return -1;
    }
    
    // Check for straight (score = 45)
    public static int checkStraight(int dice[]) {
        
    	// Check for 1+2+3+4+5
        if (dice[0] == 1 && dice[1] == 2 && dice[2] == 3 && dice[3] == 4 && dice[4] == 5) {
        	return 45; // Return 45 if straight of 1-5 is found
        }
       
        // Check for 2+3+4+5+6
        if (dice[0] == 2 && dice[1] == 3 && dice[2] == 4 && dice[3] == 5 && dice[4] == 6) {
            return 45; // Return 45 if straight of 2-6 is found
        }
       
     // If no straight is found, return -1
    return -1;
    }
}