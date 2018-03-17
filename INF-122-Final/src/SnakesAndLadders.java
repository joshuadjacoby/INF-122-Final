import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;


@SuppressWarnings("serial")
public class SnakesAndLadders extends GameBoard {
	private int turn = 1; // Game starts with Player 1's turn
	private Dice dice = new Dice();
	
	private HashMap<Integer, ArrayList<Integer>> positionOfPlayers = new HashMap<Integer, ArrayList<Integer>>(){};
	static HashMap<ArrayList<Integer>, ArrayList<Integer>> specialSpaces = new HashMap<ArrayList<Integer>, ArrayList<Integer>>();
	private GUI gui;
	
	public SnakesAndLadders(int row, int col, GUI gui){
		super(row, col);
		this.gui = gui;
	
		// Initializes player position at bottom left corner
	    positionOfPlayers.put(1,new ArrayList<Integer>(Arrays.asList(9, 0)));
	    positionOfPlayers.put(2,new ArrayList<Integer>(Arrays.asList(9, 0)));
	    
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
	    	(Arrays.asList(4, 9)), new ArrayList<Integer>(Arrays.asList(1, 6)));
	    specialSpaces.put(new ArrayList<Integer>
	    	(Arrays.asList(7, 0)), new ArrayList<Integer>(Arrays.asList(6, 1)));
	    specialSpaces.put(new ArrayList<Integer>
	    	(Arrays.asList(7, 8)), new ArrayList<Integer>(Arrays.asList(6, 8)));
	    specialSpaces.put(new ArrayList<Integer>
	    	(Arrays.asList(9, 7)), new ArrayList<Integer>(Arrays.asList(8, 6)));
	    
	    setLayout(new GridLayout(0, col));
	    initializeButtons();

		
	}
	
	protected void initializeButtons() {
		int result = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (i%2 == 1) {
					result = (int) (100 - (Math.ceil(i/2)*20) + 1 + j - 20);
				} else {
					result = (int) (100 - (i/2)*20 - j);
				}
				
				
				setSpace(i, j, new SNLSpace(i, j, ""));
				getSpace(i, j).setBgColor(new Color(160,160,160));
				
				
				getSpace(i, j).setText(Integer.toString(result));
				
				getSpace(i,j).addActionListener(new SNLButtonListener());
				add(getSpace(i,j));
			}
		}
		
		
		setSnakesandLadders();
		setPlayers();
		//add a special button for the dice
		SNLSpace button = new SNLSpace(-1, -1, "dice");
		button.setText("Roll Dice");
		button.addActionListener(new SNLButtonListener());
		add(button);
	}

	void setPlayers() {
		gameBoard[9][0].setText("1,2");
	}
	
	void setLadders(int startRow,int startCol, int endRow, int endCol) {
		if(startCol == endCol)//straight up
			for(int x = startRow; x >= endRow; x--)
				getSpace(x, startCol).setIcon(new ImageIcon("snakeHead.jpg"));
				// getSpace(x, startCol).setBgColor(new Color(0, 255, 0));
		else if(startCol < endCol)//up right
			for(int x = startRow, y = startCol;x >= endRow;x--,y++)
				getSpace(x, y).setBgColor(new Color(0, 255, 0));
		else if(startCol > endCol)//up left
			for(int x = startRow, y = startCol;x >= endRow;x--,y--)
				getSpace(x, y).setBgColor(new Color(0, 255, 0));
	}
	
	void setSnakes(int startRow, int startCol, int endRow,int endCol) {
		if(startCol == endCol)//straight down
			for(int x = startRow; x <= endRow; x++)
				getSpace(x, startCol).setBgColor(new Color(225, 0, 0));
		if(startCol < endCol)//up right
			for(int x = startRow, y = startCol;x <= endRow;x++,y++)
				getSpace(x, y).setBgColor(new Color(225, 0, 0));
		if(startCol > endCol)//up left
			for(int x = startRow, y = startCol;x <= endRow;x++,y--)
				getSpace(x, y).setBgColor(new Color(225, 0, 0));
	}
	
	public void setSnakesandLadders() {
		System.out.println("first");
		for(Map.Entry<ArrayList<Integer>, ArrayList<Integer>> coordinates : specialSpaces.entrySet()) {
			int startRow = coordinates.getKey().get(0);
			int startCol = coordinates.getKey().get(1);
			int endRow = coordinates.getValue().get(0);
			int endCol = coordinates.getValue().get(1);
			System.out.println(startCol + ", " + endCol);
			//set ladders 
			if(startRow > endRow) 
				setLadders(startRow,startCol,endRow,endCol);
			//set snakes
			else if(startRow < endRow)
				setSnakes(startRow,startCol,endRow,endCol);
		}		
}
	
		private void advanceGame() {
			int roll = dice.roll();
			System.out.println("Roll: " + roll);
			advancePlayer(turn,roll);
			turn = ((turn == 1) ? 2 : 1);
		}
		
		private void checkEndGame() {
			if(foundWinner())
				gui.gameOver();
	}
	
	public void processLogic() {
	}
	
	public void separatePieces() {
		int currPlayerRow = positionOfPlayers.get(turn).get(0);
		int currPlayerCol = positionOfPlayers.get(turn).get(1);
		if(gameBoard[currPlayerRow][currPlayerCol].getText().equals("1,2")) {
			gameBoard[currPlayerRow][currPlayerCol].setText(""+((turn == 1) ? 2 : 1));
		}	
		else
			gameBoard[currPlayerRow][currPlayerCol].setText("");
	}
	
	public void updateBoard() {
		int currPlayerRow = positionOfPlayers.get(turn).get(0);
		int currPlayerCol = positionOfPlayers.get(turn).get(1);
		System.out.println(currPlayerRow + " " + currPlayerCol);
		if(gameBoard[currPlayerRow][currPlayerCol].getText().equals(""))
			gameBoard[currPlayerRow][currPlayerCol].setText(""+((turn == 1) ? 1 : 2));
		else if(!gameBoard[currPlayerRow][currPlayerCol].getText().equals(""))
			gameBoard[currPlayerRow][currPlayerCol].setText("1,2");
		
	}
	
	public void advancePlayer(int player, int diceRoll){
	    ArrayList<Integer> currentPosition = positionOfPlayers.get(player);
	    System.out.println("Current position: " + Integer.toString(currentPosition.get(0)) + ", " + Integer.toString(currentPosition.get(1)));
	    separatePieces();
	    
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
	    updateBoard();
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

private class SNLButtonListener extends ButtonListener {
    public void actionPerformed(ActionEvent e) {
        SNLSpace spaceClicked = (SNLSpace) e.getSource();
        
        if (spaceClicked.getValue() == "dice") {
        	advanceGame();
        }
    }
}
}


