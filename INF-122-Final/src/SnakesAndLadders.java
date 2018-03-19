import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;


@SuppressWarnings("serial")
public class SnakesAndLadders extends GameBoard {
	private int turn = 1; // Game starts with Player 1's turn
	private Dice dice = new Dice();
	
	private HashMap<Integer, ArrayList<Integer>> positionOfPlayers = new HashMap<Integer, ArrayList<Integer>>(){};
	static HashMap<ArrayList<Integer>, ArrayList<Integer>> specialSpaces = new HashMap<ArrayList<Integer>, ArrayList<Integer>>();
	
	public SnakesAndLadders(int row, int col, GUI gui) {
		super(row, col, gui);
	
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
		SNLSpace button = new SNLSpace(0, 0, "dice");
		button.setText("Roll Dice");
//		button.setIcon(new ImageIcon(this.getClass().getResource("/images/button_next.jpg")));
//		getSpace(0, 0).setGamePiece(new DiceBtn());
		//button.setIcon(ImageIO.read(getClass().getResource("images/dice.png")))
		
		
		button.addActionListener(new SNLButtonListener());
		add(button);
		//gui.statsContainer.add(button);
	}

    protected void statsPanelInfo(JPanel statsPanel)
    {
        statsPanel.add(new JLabel("Custom TTT info"));
    }

	void setPlayers() {
		//gameBoard[9][0].setText("Start");
		getSpace(0,0).setText("Finish");
		//gameBoard[player1x][player1y].setGamePiece(new SNLPieceBlack());
		getSpace(9,0).setGamePiece(new SNLBothPieces());
		
	}
	
	void setLadders(int startRow,int startCol, int endRow, int endCol) {
		if(startCol == endCol)//straight up
			for(int x = startRow; x >= endRow; x--)
				//getSpace(x, startCol).setGamePiece(new Snake());
				//getSpace(x, startCol).setBgColor(new Color(0, 255, 0));
				getSpace(x, startCol).setGamePiece(new Ladder());
		else if(startCol < endCol)//up right
			for(int x = startRow, y = startCol;x >= endRow;x--,y++)
				//getSpace(x, y).setBgColor(new Color(0, 255, 0));
				getSpace(x, y).setGamePiece(new Ladder());
		else if(startCol > endCol)//up left
			for(int x = startRow, y = startCol;x >= endRow;x--,y--)
				//getSpace(x, y).setBgColor(new Color(0, 255, 0));
				getSpace(x, y).setGamePiece(new Ladder());
	}
	
	void setSnakes(int startRow, int startCol, int endRow,int endCol) {
		if(startCol == endCol)//straight down
			for(int x = startRow; x <= endRow; x++)
				//getSpace(x, startCol).setBgColor(new Color(225, 0, 0));
				getSpace(x, startCol).setGamePiece(new Snake());
		if(startCol < endCol)//up right
			for(int x = startRow, y = startCol;x <= endRow;x++,y++)
				// getSpace(x, y).setBgColor(new Color(225, 0, 0));
				getSpace(x, y).setGamePiece(new Snake());
		if(startCol > endCol)//up left
			for(int x = startRow, y = startCol;x <= endRow;x++,y--)
				//getSpace(x, y).setBgColor(new Color(225, 0, 0));
				getSpace(x, y).setGamePiece(new Snake());
	}
	
	public void setSnakesandLadders() {
		getSpace(9,0).clearGamePiece();
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
			int player1x = getPlayer1Position().get(0);
			int player1y = getPlayer1Position().get(1);
			
			int player2x = getPlayer2Position().get(0);
			int player2y = getPlayer2Position().get(1);
			
			if (turn == 1) {
				if (getSpace(player1x,player1y).getText().equals("P1,P2")) { // if spot with P1 & P2
					getSpace(player2x,player2y).clearGamePiece(); // clears SNLBothPieces()
                    getSpace(player2x,player2y).setGamePiece(new SNLPieceWhite()); // fill space with P2's piece
				}
				else { // otherwise, just clear the cell
					getSpace(player1x,player1y).clearGamePiece();
				}
			}
			else {
				if (getSpace(player1x,player1y).getText().equals("P1,P2")) { // if spot with P1 & P2
					getSpace(player2x,player2y).clearGamePiece(); // clears SNLBothPieces()
					getSpace(player2x,player2y).setGamePiece(new SNLPieceBlack()); // fill space with P1's piece
				}
				else { // otherwise, just clear the cell
                    getSpace(player2x,player2y).clearGamePiece();
				}
				
			}
			
			if ((player1x == 0) && (player1y - roll <= 0) && (turn == 1)) {
				getSpace(0,0).setGamePiece(new SNLPieceBlack()); //
				foundWinner(1);
			}
			else if ((player2x == 0) && (player2y - roll <= 0) && (turn == 2)) {
				getSpace(0,0).setGamePiece(new SNLPieceWhite()); //
				foundWinner(2);
			}
			else {
                advancePlayer(turn, roll);
            }
				
			
			player1x = getPlayer1Position().get(0);
			player1y = getPlayer1Position().get(1);
			
			player2x = getPlayer2Position().get(0);
			player2y = getPlayer2Position().get(1);
			if (turn == 1) {
				getSpace(player1x,player1y).setGamePiece(new SNLPieceBlack());
			}
            else if (turn == 2) {
                getSpace(player2x,player2y).setGamePiece(new SNLPieceWhite());
            }

            if ((player1x == player2x) && (player1y == player2y)) {
				getSpace(player1x,player1y).setGamePiece(new SNLBothPieces());
			}


			turn = ((turn == 1) ? 2 : 1);
	}
	
	public void processLogic() {
	}
	
		
	public void separatePieces() {
		int currPlayerRow = positionOfPlayers.get(turn).get(0);
		int currPlayerCol = positionOfPlayers.get(turn).get(1);
		if(getSpace(currPlayerRow,currPlayerCol).getValue().equals("P1,P2")) {
			getSpace(currPlayerRow,currPlayerCol).setValue(""+((turn == 1) ? "P2" : "P1"));
		}	
		else
			getSpace(currPlayerRow,currPlayerCol).setValue("");
	}
	
	public ArrayList<Integer> getPlayer1Position() {
		return positionOfPlayers.get(1);
	}
	
	public ArrayList<Integer> getPlayer2Position() {
		return positionOfPlayers.get(2);
	}
	
	public void updateBoard() {
		int currPlayerRow = positionOfPlayers.get(turn).get(0);
		int currPlayerCol = positionOfPlayers.get(turn).get(1);
		System.out.println(currPlayerRow + " " + currPlayerCol);

        if(getSpace(currPlayerRow,currPlayerCol).getValue().equals(""))
            getSpace(currPlayerRow,currPlayerCol).setValue(""+((turn == 1) ? "P1" : "P2"));
        else if(!getSpace(currPlayerRow,currPlayerCol).getValue().equals(""))
            getSpace(currPlayerRow,currPlayerCol).setValue("P1,P2");

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
	
	public void showSNLAlert() { // put this in panel
		if (turn == 1) {
			JOptionPane.showMessageDialog(null, "Black has landed on a snake or ladder! Follow it!");
		}
		else {
			JOptionPane.showMessageDialog(null, "White has landed on a snake or ladder! Follow it!");
		}
		
	}

	public ArrayList<Integer> checkSnakeOrLadder(ArrayList<Integer> position){
    // Returns end position of a snake or ladder
	    if (specialSpaces.containsKey(position)) { // position = <2,3>
	    	System.out.println("snake/ladder found, new position: " + Integer.toString(specialSpaces.get(position).get(0)) + ", " + Integer.toString(specialSpaces.get(position).get(1)));
	    	// showSNLAlert(); // move this to panel 
	    	return specialSpaces.get(position);
	    }
	    else {
	    	System.out.println("No snake/ladder found");
	    	return position;
	    }
	}
	
	public void foundWinner(int playerNum) {
		JOptionPane.showMessageDialog(null, "Player " + Integer.toString(playerNum) + " won!");

		gui.gameOver();
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


