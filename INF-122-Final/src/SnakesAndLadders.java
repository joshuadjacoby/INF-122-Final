import java.util.*;
import javafx.util.Pair;

class SnakesAndLadders extends GridGame{
  private static final int BOARD_SIZE = 10;
	private int turn = 1;
	private Dice dice;
	private int winner;
	private HashMap<Integer, int[]> positionOfPlayers = new HashMap<Integer, int[]>();
	private HashMap<int[], int[]> specialSpaces = new HashMap<int[], int[]>();
	
			
			
	SnakesAndLadders(){
		super(BOARD_SIZE, BOARD_SIZE);
	    dice = new Dice();
	    // Initialize player position at bottom left corner
	    int[] player1Pos = new int[]{9, 0};
	    int[] player2Pos = new int[]{9, 0};
	    positionOfPlayers.put(1,player1Pos);
	    positionOfPlayers.put(2,player2Pos);
	    System.out.println("Setting player positions as bottom left");
	    initializeButtons();
	    System.out.println("Initializing buttons");
	
	    // Snakes
	    specialSpaces.put(new int[]{0, 8}, new int[]{3, 5});
	    specialSpaces.put(new int[]{1, 2}, new int[]{4, 2});
	    specialSpaces.put(new int[]{3, 9}, new int[]{4, 8});
	    specialSpaces.put(new int[]{5, 5}, new int[]{7, 5});
	    specialSpaces.put(new int[]{7, 2}, new int[]{8, 3});
	
	    // Ladders
	    specialSpaces.put(new int[]{3, 3}, new int[]{1, 3});
	    specialSpaces.put(new int[]{4, 3}, new int[]{1, 6});
	    specialSpaces.put(new int[]{7, 0}, new int[]{6, 1});
	    specialSpaces.put(new int[]{7, 8}, new int[]{6, 8});
	    specialSpaces.put(new int[]{9, 7}, new int[]{8, 6});
	    System.out.println("Adding special spaces");
	}

	/*
	public void initializeButtons() {
	    for (int i = 0; i <= 10; i++) {
	    	for (int j = 0; j <= 10; j++) {
	    		
	    		environment.gameBoard[i] = new JButton();
	    		environment.gameBoard[i].setText("");
	    		environment.gameBoard[i].addActionListener(new ticTacToeListener());
	    	}
	     

	      add(gameBoard[i]); //adds this button to JPanel (note: no need for JPanel.add(...)
	      //because this whole class is a JPanel already
	    
	    }
	  }
	  */

	public void processLogic() {
		
	}
	
	
	int roll(){
		int roll = dice.roll();
		System.out.println("Rolling dice --> " + Integer.toString(roll));
		return roll; // Change this back to dice.roll() after tests
		
	}

	void advancePlayer(int player, int diceRoll){
	    int[] currentPosition = positionOfPlayers.get(player);
	    
	    // If row is odd
	    if ((currentPosition[0]) % 2 == 1) { 
	      if ((9 - diceRoll) >= currentPosition[1]) { // If column + roll <= right edge of board
	      	currentPosition[1] += diceRoll;
	      	System.out.println("Advancing player " + turn + " to " + Integer.toString(currentPosition[0]) + ", " + Integer.toString(currentPosition[1]));
	      }
	      
	      else { // Moves carry over to the row above
	        int carryOverMoves = 9 - currentPosition[1]; 
	        currentPosition[1] = 9 - carryOverMoves + 1;
	        currentPosition[0] -= 1;
	        System.out.println("Advancing player " + turn + " to " + Integer.toString(currentPosition[0]) + ", " + Integer.toString(currentPosition[1]));
	      }
    }
      
    // If roll is even
    else {
      if ((currentPosition[1] - diceRoll) >= 0) { // If column + roll <= left edge of board
      	currentPosition[1] -= diceRoll;
      	System.out.println("Advancing player " + turn + " to " + Integer.toString(currentPosition[0]) + ", " + Integer.toString(currentPosition[1]));
      }
      
      else { // Moves carry over to the row above
        int carryOverMoves = diceRoll - currentPosition[1]; 
        currentPosition[1] = carryOverMoves - 1;
        currentPosition[0] -= 1;
        System.out.println("Advancing player " + turn + " to " + Integer.toString(currentPosition[0]) + ", " + Integer.toString(currentPosition[1]));
      }
      
    currentPosition = checkSnakeOrLadder(currentPosition);
    }
    positionOfPlayers.put(player, currentPosition);
	}

	int[] checkSnakeOrLadder(int[] position){
    // Returns end of a snake or ladder
	    if (specialSpaces.containsKey(position)) {
	    	System.out.println("Following snake/ladder to " + Integer.toString(specialSpaces.get(position)[0]) + ", " + Integer.toString(specialSpaces.get(position)[0]));
	      return specialSpaces.get(position);
	    }
	    else {
	    	System.out.println("No snake/ladder found");
	      return position;
	    }
	}
        
	void checkWinner() {
		System.out.println("Checking for a winner");
		for (int i = 1; i <= 2; i++) {
			if (positionOfPlayers.get(i).equals(new int[]{0, 0})) {
		    	System.out.println("Player " + Integer.toString(i) + " won!");
		    }
		}
	  }

	void playerTurn(){
		while (true) {
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter something here: ");
			int i = sc.nextInt();
			
			
			// 1) need to listen for click on dice button
			advancePlayer(turn, roll());     // 2) advance player based on dice number
		      
			if (turn == 1) {
				System.out.println("Player 2's turn is up!");
				turn = 2;
			}
			    
			else {
				System.out.println("Player 1's turn is up!");
				turn = 1;
			    }
			}
			// If winner, break
		}
	
//	public void printBoard() {
//		for (int i = 0; i < BOARD_SIZE; i++) {
//			for (int j = 0; j < BOARD_SIZE; j++) {
//				System.out.println();
//			}
//		}
//	}
	
	public static void main() {
		 SnakesAndLadders SNL = new SnakesAndLadders();
		 SNL.playerTurn();
		
	}
}