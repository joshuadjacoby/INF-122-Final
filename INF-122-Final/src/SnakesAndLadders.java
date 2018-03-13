import java.util.*;

class SnakesAndLadders {
	private static final int BOARD_SIZE = 10;
	private int turn = 1; // Game starts with Player 1's turn
	private Dice dice = new Dice();
	private HashMap<Integer, ArrayList<Integer>> positionOfPlayers = new HashMap<Integer, ArrayList<Integer>>(){};
	static HashMap<ArrayList<Integer>, ArrayList<Integer>> specialSpaces = new HashMap<ArrayList<Integer>, ArrayList<Integer>>();
			
	SnakesAndLadders(){
		//super(BOARD_SIZE, BOARD_SIZE);
	    // Initializes player position at bottom left corner
	    positionOfPlayers.put(1,new ArrayList<Integer>(Arrays.asList(9, 0)));
	    positionOfPlayers.put(2,new ArrayList<Integer>(Arrays.asList(9, 0)));
	    initializeButtons();
	
	    // Creates snakes	    
	    specialSpaces.put(new ArrayList<Integer>
	    	(Arrays.asList(0, 8)), new ArrayList<Integer>(Arrays.asList(3, 5)));
	    specialSpaces.put(new ArrayList<Integer>
	    	(Arrays.asList(1, 2)), new ArrayList<Integer>(Arrays.asList(4, 2)));
	    specialSpaces.put(new ArrayList<Integer>
	    	(Arrays.asList(3, 9)), new ArrayList<Integer>(Arrays.asList(4, 8)));
	    specialSpaces.put(new ArrayList<Integer>
	    	(Arrays.asList(5, 5)), new ArrayList<Integer>(Arrays.asList(7, 5)));
	    specialSpaces.put(new ArrayList<Integer>
	    	(Arrays.asList(7, 2)), new ArrayList<Integer>(Arrays.asList(8, 3)));	    
	
	    // Creates ladders
	    specialSpaces.put(new ArrayList<Integer>
	    	(Arrays.asList(3, 3)), new ArrayList<Integer>(Arrays.asList(1, 3)));
	    specialSpaces.put(new ArrayList<Integer>
	    	(Arrays.asList(4, 3)), new ArrayList<Integer>(Arrays.asList(1, 6)));
	    specialSpaces.put(new ArrayList<Integer>
	    	(Arrays.asList(7, 0)), new ArrayList<Integer>(Arrays.asList(6, 1)));
	    specialSpaces.put(new ArrayList<Integer>
	    	(Arrays.asList(7, 8)), new ArrayList<Integer>(Arrays.asList(6, 8)));
	    specialSpaces.put(new ArrayList<Integer>
	    	(Arrays.asList(9, 7)), new ArrayList<Integer>(Arrays.asList(8, 6)));
	}

	public void initializeButtons() {
		/*
	    for (int i = 0; i <= 10; i++) {
	    	for (int j = 0; j <= 10; j++) {
	    		environment.gameBoard[i] = new JButton();
	    		environment.gameBoard[i].setText("");
	    		environment.gameBoard[i].addActionListener(new ticTacToeListener());
	    	}
	    	
	      add(gameBoard[i]); //adds this button to JPanel (note: no need for JPanel.add(...)
	      //because this whole class is a JPanel already
	    }
	  */
	  }

	public void processLogic() {
	}

	public void advancePlayer(int player, int diceRoll){
	    ArrayList<Integer> currentPosition = positionOfPlayers.get(player);
	    System.out.println("Current position: " + Integer.toString(currentPosition.get(0)) + ", " + Integer.toString(currentPosition.get(1)));
	    
	    // If row is odd
	    if ((currentPosition.get(0)) % 2 == 1) { 
	      if ((9 - diceRoll) >= currentPosition.get(1)) { // If column + roll <= right edge of board
	      	currentPosition.set(1, currentPosition.get(1) + diceRoll);
	      	System.out.println("Advancing Player "+ turn + " to " + Integer.toString(currentPosition.get(0)) + ", " + Integer.toString(currentPosition.get(1)));
	      }
	      
	      else { // Moves carry over moves to the row above
	        int carryOverMoves = 9 - currentPosition.get(1);
	        int diceRollMovesLeft = diceRoll - carryOverMoves;
	        currentPosition.set(1, 9 - diceRollMovesLeft + 1);
	        currentPosition.set(0, currentPosition.get(0) - 1);
	        System.out.println("Advancing Player " + turn + " to " + Integer.toString(currentPosition.get(0)) + ", " + Integer.toString(currentPosition.get(1)));
	      }
	    }
	      
	    // If row is even
	    else {
	      if ((currentPosition.get(1) - diceRoll) >= 0) { // If column + roll <= left edge of board
	      	currentPosition.set(1,  currentPosition.get(1) - diceRoll);
	      	System.out.println("Advancing Player " + turn + " to " + Integer.toString(currentPosition.get(0)) + ", " + Integer.toString(currentPosition.get(1)));
	      }
	      
	      else { // Moves carry over moves to the row above
	        int carryOverMoves = diceRoll - currentPosition.get(1); 
	        currentPosition.set(1, carryOverMoves - 1);
	        currentPosition.set(0, currentPosition.get(0) - 1);
	        System.out.println("Advancing Player " + turn + " to " + Integer.toString(currentPosition.get(0)) + ", " + Integer.toString(currentPosition.get(1)));
	      }
	    }
	    currentPosition = checkSnakeOrLadder(currentPosition); // Moves the player if on a snake or ladder
	    positionOfPlayers.put(player, currentPosition); // Updates player position
	}

	
	public ArrayList<Integer> checkSnakeOrLadder(ArrayList<Integer> position){
    // Returns end position of a snake or ladder
	    if (specialSpaces.containsKey(position)) {
	    	System.out.println("snake/ladder found, new position: " + Integer.toString(specialSpaces.get(position).get(0)) + ", " + Integer.toString(specialSpaces.get(position).get(1)));
	    	return specialSpaces.get(position);
	    }
	    else {
	    	System.out.println("No snake/ladder found");
	    	return position;
	    }
	}
	
	public boolean foundWinner() {
		for (int i = 1; i <= 2; i++) {
			if(positionOfPlayers.get(i).get(0) == -1){
				System.out.println("Player " + Integer.toString(i) + " won!");
		    	return true;
			}
			else if((positionOfPlayers.get(i).get(0) == 0) && (positionOfPlayers.get(i).get(1) == 0)) {
				System.out.println("Player " + Integer.toString(i) + " won!");
		    	return true;
		    }
		}
		return false;
	}

	public void playGame(){
		while (!foundWinner()) {
			Scanner sc = new Scanner(System.in); // Replace scanner with click listening (when we connect to UI)
			System.out.println("\nPlayer "+turn+"'s turn");
			System.out.print("Enter any number: ");
			int i = sc.nextInt();
			int roll = dice.roll();
			System.out.print("rolled a "+ roll+"!\n");
			advancePlayer(turn, roll); // Advance player based on dice number 
			
			turn = ((turn == 1) ? 2 : 1);
			}
		}
}