import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;


@SuppressWarnings("serial")
public class SnakesAndLadders extends GameBoard {
	private int turn = 1; // Game starts with Player 1's turn
	private Dice dice = new Dice();
	
	private int winner = -1; 
	private JLabel playerTurnLabel;
	private JLabel diceRollLabel;
	private JLabel snlAlertLabel;
	private JButton diceButton;
	int roll = 0;
	
	private HashMap<Integer, ArrayList<Integer>> positionOfPlayers = new HashMap<Integer, ArrayList<Integer>>(){};
	static HashMap<ArrayList<Integer>, ArrayList<Integer>> specialSpaces = new HashMap<ArrayList<Integer>, ArrayList<Integer>>();
	
	public SnakesAndLadders(int row, int col) {
		super(row, col);
	
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
	    	(Arrays.asList(7, 2)), new ArrayList<Integer>(Arrays.asList(9, 4)));	    
	
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
				
				getSpace(i,j).addActionListener(this);
				add(getSpace(i,j));
			}
		}
		
		setSnakesandLadders();
		setPlayers();
	}

    protected void statsPanelInfo(JPanel gameStatsPanel){
    	// game stats panel
        TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Game Stats");
        gameStatsPanel.setBorder(title);
        gameStatsPanel.setLayout(new BoxLayout(gameStatsPanel, BoxLayout.Y_AXIS));

        // player turn panel
        JPanel playerTurnPanel = new JPanel();

        // player turn label
        playerTurnLabel = new JLabel();
        updatePlayerTurnLabel();
        playerTurnLabel.setFont(new Font("", Font.BOLD, 24));
        title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Player Turn");
        title.setTitleJustification(TitledBorder.CENTER);
        playerTurnLabel.setBorder(title);

        // add to player turn panel
        playerTurnPanel.add(playerTurnLabel);

        // game status panel
        JPanel gameStatusPanel = new JPanel();
        gameStatusPanel.setLayout(new BoxLayout(gameStatusPanel, BoxLayout.Y_AXIS));
        diceRollLabel = new JLabel("Dice Roll: __");
        diceRollLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        diceRollLabel.setFont(new Font("", Font.BOLD, 18));
        snlAlertLabel = new JLabel();
        snlAlertLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        snlAlertLabel.setFont(new Font("", Font.BOLD, 20));
        snlAlertLabel.setVisible(false);
        gameStatusPanel.add(snlAlertLabel);
        gameStatusPanel.add(diceRollLabel);
        
        // dice button panel
        JPanel dicePanel = new JPanel();
        diceButton = new JButton("Roll Dice!");
        Image diceImg;
        try {
            diceImg = ImageIO.read(getClass().getResource("images/dice.png"));
            diceButton.setIcon(new ImageIcon(diceImg));
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        diceButton.setName("dice");
        diceButton.addActionListener(this);
        diceButton.setEnabled(true);
        dicePanel.add(diceButton);
        

        // player info
        JPanel playerInfoPanel = new JPanel();
        playerInfoPanel.setLayout(new BoxLayout(playerInfoPanel, BoxLayout.Y_AXIS));
        JLabel p1info = new JLabel(State.getInstance().getPlayerOne().getName() + " = BLACK");
        JLabel p2info = new JLabel(State.getInstance().getPlayerTwo().getName() + " = WHITE");
        p1info.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));
        p2info.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
        p1info.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        p2info.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        playerInfoPanel.add(p1info);
        playerInfoPanel.add(p2info);

        // add to game stats panel
        gameStatsPanel.add(playerTurnPanel);
        gameStatsPanel.add(gameStatusPanel);
        gameStatsPanel.add(dicePanel);
        gameStatsPanel.add(playerInfoPanel);
    }

    private void updatePlayerTurnLabel(){
        TitledBorder title;
        switch(winner)
        {

            case 0:
                playerTurnLabel.setText("NO WINNER");
                title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Winner");
                title.setTitleJustification(TitledBorder.CENTER);
                playerTurnLabel.setBorder(title);
                break;
            case 1:
                playerTurnLabel.setText(State.getInstance().getPlayerOne().getName() + " WINS!");
                title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Winner");
                title.setTitleJustification(TitledBorder.CENTER);
                playerTurnLabel.setBorder(title);
                break;
            case 2:
                playerTurnLabel.setText(State.getInstance().getPlayerTwo().getName() + " WINS!");
                title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Winner");
                title.setTitleJustification(TitledBorder.CENTER);
                playerTurnLabel.setBorder(title);
                break;
            default:
                if (turn==1) {
                    playerTurnLabel.setText(State.getInstance().getPlayerOne().getName());
                } else {
                    playerTurnLabel.setText(State.getInstance().getPlayerTwo().getName());
                }
                break;
        }
    }
    
    private void updateDiceRollLabel()
    {
    	diceRollLabel.setText("Dice Roll: " + roll);
    }
    
    private void updateSNLAlertLabel(char sOrL)
    {
    	switch(sOrL)
    	{
    	case 'S':
        	snlAlertLabel.setText("Snake!");
        	snlAlertLabel.setVisible(true);
    		break;
    	case 'L':
    		snlAlertLabel.setText("Ladder!");
        	snlAlertLabel.setVisible(true);
    		break;
    	default:
    		snlAlertLabel.setVisible(false);
    	}
    }

	void setPlayers() {
		//gameBoard[9][0].setText("Start");
		getSpace(0,0).setText("Finish");
		//gameBoard[player1x][player1y].setGamePiece(new SNLPieceBlack());
		getSpace(9,0).setValue("P1,P2");
		getSpace(9,0).setText("Start");
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
		// getSpace(9,0).clearGamePiece();
		for(Map.Entry<ArrayList<Integer>, ArrayList<Integer>> coordinates : specialSpaces.entrySet()) {
			int startRow = coordinates.getKey().get(0);
			int startCol = coordinates.getKey().get(1);
			int endRow = coordinates.getValue().get(0);
			int endCol = coordinates.getValue().get(1);
			//set ladders 
			if(startRow > endRow) 
				setLadders(startRow,startCol,endRow,endCol);
			//set snakes
			else if(startRow < endRow)
				setSnakes(startRow,startCol,endRow,endCol);
		}		
    }
	
	private void advanceGame() {
		roll = dice.roll();
		updateDiceRollLabel();
		updateSNLAlertLabel(' ');
		System.out.println();
		System.out.println("Roll: " + roll);
		System.out.println("Turn: " + turn);
		int player1x = getPlayer1Position().get(0);
		int player1y = getPlayer1Position().get(1);
		int player2x = getPlayer2Position().get(0);
		int player2y = getPlayer2Position().get(1);
		
		if (turn == 1) {
			if (getSpace(player1x,player1y).getValue().equals("P1,P2")) { // if spot with P1 & P2
				getSpace(player2x,player2y).clearGamePiece(); // clears SNLBothPieces()
				getSpace(player2x,player2y).setGamePiece(new SNLPieceWhite()); // fill space with P2's piece
				
			}
			else { // if P1 & P2 on separate spaces, just clear the cell
				getSpace(player1x,player1y).clearGamePiece();
			}
		}
		else { // if P2's turn
			if (getSpace(player2x,player2y).getValue().equals("P1,P2")) { // if spot with P1 & P2
				getSpace(player2x,player2y).clearGamePiece(); // clears SNLBothPieces()
				getSpace(player1x,player1y).setGamePiece(new SNLPieceBlack()); // fill space with P1's piece
			}
			else { // otherwise, just clear the cell
                getSpace(player2x,player2y).clearGamePiece();
			}
		}
		
		//win conditions
		if ((player1x == 0) && (player1y - roll <= 0) && (turn == 1)) {
			getSpace(0,0).setGamePiece(new SNLPieceBlack());
			getSpace(player1x, player1y).clearGamePiece();
			ArrayList<Integer> position = new ArrayList<Integer>();
			position.add(0);
			position.add(0);
			positionOfPlayers.put(1, position);
			foundWinner(1);
		}
		else if ((player2x == 0) && (player2y - roll <= 0) && (turn == 2)) {
			getSpace(0,0).setGamePiece(new SNLPieceWhite());
			getSpace(player2x, player2y).clearGamePiece();
			ArrayList<Integer> position = new ArrayList<Integer>();
			position.add(0);
			position.add(0);
			positionOfPlayers.put(2, position);
			foundWinner(2);
		}
		//if no one won, advance player
		else 
            advancePlayer(turn, roll);
		
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
		updatePlayerTurnLabel();
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

        if(getSpace(currPlayerRow,currPlayerCol).getValue().equals("")) // if nothing there, update with the current player
            getSpace(currPlayerRow,currPlayerCol).setValue(""+((turn == 1) ? "P1" : "P2"));
        else if (!getSpace(currPlayerRow,currPlayerCol).getValue().equals("")) // if something there, update with both
        	getSpace(currPlayerRow,currPlayerCol).setValue("P1,P2"); 
        else // if both on same spot, set the space to whoever's turn it's not
        	getSpace(currPlayerRow,currPlayerCol).setValue(""+((turn == 1) ? "P2" : "P1")); 
        
	}
	
	public void advancePlayer(int player, int diceRoll){
	    ArrayList<Integer> currentPosition = positionOfPlayers.get(player);
	    System.out.println("Current position: " + Integer.toString(currentPosition.get(0)) + ", " + Integer.toString(currentPosition.get(1)));
	    boolean together = false;
	    int notPlayer = (turn == 1) ? 2 : 1;
	    
	    if(currentPosition.equals(positionOfPlayers.get(notPlayer)))
	    	together = true;
	    	
	    updateBoard();
	    
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
	    
	    if(together) {
	    	if(turn == 1)
	    		getSpace(positionOfPlayers.get(notPlayer).get(0),positionOfPlayers.get(notPlayer).get(1)).setGamePiece(new SNLPieceWhite());
	    	else
	    		getSpace(positionOfPlayers.get(notPlayer).get(0),positionOfPlayers.get(notPlayer).get(1)).setGamePiece(new SNLPieceBlack());
	    }
	    //updateBoard();
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
	    	if(this.getPieceAt(position.get(0), position.get(1)).getName().equals("S"))
	    	{
	    		updateSNLAlertLabel('S');
	    	}
	    	else if(this.getPieceAt(position.get(0), position.get(1)).getName().equals("L"))
	    	{
	    		updateSNLAlertLabel('L');
	    	}
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
		winner = playerNum;
		if (winner == 1) {
			state.getPlayerOne().incrementWins();
			state.getPlayerTwo().incrementLosses();
		} else {
			state.getPlayerTwo().incrementWins();
			state.getPlayerOne().incrementLosses();
		}
		diceButton.setEnabled(false);
		gui.gameOver();
	}

    public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton && !(e.getSource() instanceof SNLSpace)) {
			JButton buttonClicked = (JButton) e.getSource();
			if(buttonClicked.getName().equals("dice"))
			{
				updatePlayerTurnLabel();
				advanceGame();
			}
		}
    }
}


