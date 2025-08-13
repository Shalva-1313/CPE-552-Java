import java.util.*;

//Name: Shasha Alvares
//Due Date: 5/9/25
//Assignment: Candy Crush Project 

public class CandyCrush {
	
	final static int size=9; //global variable to store the size of rows and columns
	static int moves=0; //global variable to store moves
	static int score=0;//global variable to store score
	static Random random = new Random(12345); //globally available random that has been seeded once so we don't get the same game setup each time
	
	public enum Candy{ 	//enum class holding the six values displayed on the gameboard
		
		//the chars below have ascii codes 33-38 inclusively. 
		//Those codes will be used to decide on the character filled into the board
		EXCLAMATION("!"),
		HASHTAG("#"),
		DOUBLEQUOTE("\""),
		DOLLAR("$"),
		PERCENTAGE("%"),
		AMPERSAND("&");
		
		String symbol;
		
		Candy(String symbol) { //constructor so we can get symbol the symbol 
			this.symbol = symbol;
		}
		
		public String getSymbol() {
			return symbol;
		}
	}
	
	//Initialize game board at the start of the game
	public static void initializeBoard(Candy gameboard[][]) {
		System.out.println("\nInitializing board...\n");
		
		for(int i =0; i<size;i++) { //iterate through each row
			for(int j =0; j<size; j++) { //iterate through each col
				int randCandy = random.nextInt(6); //generates values between 0-5
				Candy candyArray [] = Candy.values(); //stores the array of candy values so we can index into them
				gameboard[i][j] = candyArray[randCandy]; //assigns enum name at that index in the array of enums
			}
		}
	}
	
	public static void shuffleGameBoard(Candy gameboard[][]) {
		for(int i =0; i<size;i++) { //iterate through each row
			for(int j =0; j<size; j++) { //iterate through each col
				int randCandy = random.nextInt(6); //generates values between 0-5
				Candy candyArray [] = Candy.values(); //stores the array of candy values so we can index into them
				gameboard[i][j] = candyArray[randCandy]; //assigns enum name at that index in the array of enums
			}
		}
	}
	
	public static void displayGameBoard(Candy gameboard[][]) {
		//print row number
		System.out.print("    ");
		for(int i = 0; i<size;i++) {
			System.out.print(i+1 + " ");
		}
		System.out.print(" \n");
		System.out.println("  +-------------------+");
		
		//print gameboard that was previously created/edited
		for(int i =0; i<size;i++) { //iterate through each row
			//print row letter labels and left vertical bar
			char rowLabel = (char)('A' + i);
			System.out.print(rowLabel + " | ");

			for(int j =0; j<size; j++) { //iterate through each col
				System.out.print(gameboard[i][j].getSymbol() + " "); //need getSymbol otherwise the enum names would print. 
			}
			System.out.print("| \n"); //print right vertical bar
		}
		System.out.println("  +-------------------+");
		System.out.printf("   Moves: %d  Score: %d\n", moves, score);
	}
	
	public static void horizontalMove(Candy gameboard[][], char letter, int userCol) {
		/* remember the row is fixed and column is changing to traverse the row */
		int userRow = letter-65; //letter's are all capital so this will leave us with values 0-8
		int userColArray = userCol-1; //array starts from 0, but userinput for col starts from 1. 
		int count=1; //keeps track of how many matching symbols there are to the starting symbol
		ArrayList<Integer> list = new ArrayList<>(); //store row numbers that we need to change 
		for(int i = userColArray-1; i>=0;i--) { //check left of current symbol until we reach the end of the gameboard
			if(gameboard[userRow][i].getSymbol() == gameboard[userRow][userColArray].getSymbol()) {
				count++;
				list.add(i);
			}
			else {
				//no matches 3 in a row
				break;
			}
		}

		for(int j = userColArray+1; j<size;j++) { //check right of current symbol until we reach the end of the gameboard
			if(gameboard[userRow][j].getSymbol() == gameboard[userRow][userColArray].getSymbol()) {
				count++;
				list.add(j);
			}
			else {
				break;//no matches of at least 3 in a row
			}
		}

		if(count<3) {
			System.out.println("Sorry, no match! No points awarded...");
		}
		if(count>=3) {
			score+=count;
			System.out.printf("Nice match!! You earned +%d points!\n", count);
			for(int horizontalCandy : list) {
				int randCandy = random.nextInt(6); //generates values between 0-5
				Candy candyArray [] = Candy.values(); //stores the array of candy values so we can index into them
				gameboard[userRow][horizontalCandy] = candyArray[randCandy]; //assigns enum name at that index in the array of enums 
			}
		}
		
		list.clear();
	}
	
	public static void verticalMove(Candy gameboard[][], char letter, int userCol) {
		/* remember the col is fixed and row is changing to traverse the column */
		int userRow = letter-65; //letter's are all capital so this will leave us with values 0-8
		int userColArray = userCol-1; //array starts from 0, but userinput for col starts from 1. 
		int count=1; //keeps track of how many matching symbols there are to the starting symbol
		ArrayList<Integer> list = new ArrayList<>(); //store row numbers that we need to change 
		for(int i = userRow-1; i>=0;i--) { //check left of current symbol until we reach the end of the gameboard
			if(gameboard[i][userColArray].getSymbol() == gameboard[userRow][userColArray].getSymbol()) {
				count++;
				list.add(i);
			}
			else {
				break; 	//no matches 3 in a row
			}
		}

		for(int j = userRow+1; j<size;j++) { //check right of current symbol until we reach the end of the gameboard
			if(gameboard[j][userColArray].getSymbol() == gameboard[userRow][userColArray].getSymbol()) {
				count++;
				list.add(j);
			}
			else {
				break;	//no matches of at least 3 in a row
			}
		}

		if(count<3) {
			System.out.println("Sorry, no match! No points awarded...");
		}
		if(count>=3) {
			score+=count;
			System.out.printf("Nice match!! You earned +%d points!\n", count);
			for(int verticalCandy : list) {
				int randCandy = random.nextInt(6); //generates values between 0-5
				Candy candyArray [] = Candy.values(); //stores the array of candy values so we can index into them
				gameboard[verticalCandy][userColArray] = candyArray[randCandy]; //assigns enum name at that index in the array of enums 
			}
		}
		
		list.clear();
	}
	


	public static void main(String[] args) {
		
		Scanner scnr = new Scanner(System.in);
		Candy gameboard[][] = new Candy[size][size]; //generate 2D gameboard that is 9x9 in size and stores the enum values
		String userMoveChoice;
		String direction;
		boolean validChoice = false; //keeps track of if the user gives a move that can be done e.g. A8 and not M19
		boolean validDirection = false; //keeps track of if user enters V or H
		char letter='A'; //stores first letter as char from userMoveChoice. Initialized to placeholder
		int userCol=0; //stores the number of the userChoice as an int instead of string. Initialized to placeholder
		
		//Game start message to user
		System.out.println("-----------------------------------------------------------\n"
				+ "		Welcome to Candy Crush\n\n"
				+ "This program will randomly initialize your game board using\n"
				+ "a set of 6 characters (!, \", #, $, %, &) that a player will\n"
				+ "then attempt to match a combination of 3 or more characters\n"
				+ "to gain points in a finite set of moves or time duration.\n"
				+ "-----------------------------------------------------------");
		initializeBoard(gameboard);
		displayGameBoard(gameboard);
		moves++;

		//loop to keep the game going until the user runs out of moves
		while(moves < 11) {	
			System.out.printf("Enter location for move #%d (e.g., B7): ", moves);
			userMoveChoice = scnr.nextLine();
			validChoice = false; //reset for each new move
			validDirection = false; //reset for each new move
			
			//re-prompt user until they enter a valid move choice
			while(!validChoice)
			{
				//Check that input is valid
				letter = userMoveChoice.toUpperCase().charAt(0); //stores first letter as char
				String userColString = userMoveChoice.substring(1);  //stores the number which is either at index 1 if single digit or 1--> end if bigger
				userCol = Integer.valueOf(userColString); //stores the number of the userChoice as an int instead of string
				
				// Check for reshuffle option
				if(letter == 'R' && userCol == 0) {
				    System.out.println("Board Reshuffled...");
				    shuffleGameBoard(gameboard);
				    displayGameBoard(gameboard);
				    validChoice = false; // no need to enter direction (skip move choice for this round)
				    break; // break out of move input loop early
				}
				if(65 > (int)letter || (int)letter > 73)  {//if the letter is not between A-I ascii values of 65-73 inclusive
					System.out.print("Invalid entry. Pick letter A-I and number 1-9.\n");
					System.out.printf("Enter location for move #%d (e.g., B7): ", moves);
					userMoveChoice = scnr.nextLine();
					validChoice=false;
				}
				else if(0  >= userCol || userCol > 9) { //if the number is not between 1-9
					System.out.print("Invalid entry. Pick letter A-I and number 1-9.\n");
					System.out.printf("Enter location for move #%d (e.g., B7): ", moves);
					userMoveChoice = scnr.nextLine();
					validChoice=false;
				}
				else  {//letter and number entered are valid
					validChoice=true;
				}
			}
			
			if(!userMoveChoice.equals("R0")) { //user did not shuffle the board
				//reprompt user until they enter a valid direction
				while(!validDirection) {			
					System.out.print("Enter direction (V = Vertical, H = Horizontal): ");
					direction = scnr.nextLine();
					if(direction.toUpperCase().equals("V")) {
						verticalMove(gameboard, letter, userCol);
						displayGameBoard(gameboard);
						validDirection = true;
						break; // break out of move input loop early
					}
					else if(direction.toUpperCase().equals("H")) {
						horizontalMove(gameboard, letter, userCol);
						displayGameBoard(gameboard);
						validDirection = true;
						break; // break out of move input loop early
					}
					else {
						System.out.println("Invalid direction - enter V or H.");
						validDirection = false;
					}
				}
			}
			//increment moves to continue onto next round
			moves++;
		}
		
		//User finished all 10 rounds
		System.out.printf("Congratulations of your score of %d in 10 moves!", score);

	}

}
