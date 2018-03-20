import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class TicTacToe extends GameBoard {
	private int player = 1;
    private int winner = -1; //it's a tie if 0, 1 if player 1 won...

    private JLabel playerTurnLabel;

	public TicTacToe(int row, int col) {
		super(row, col);
		setLayout(new GridLayout(0,col));
		initializeButtons();
	}
	
	protected void initializeButtons()
	{
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				setSpace(i,j,new TicTacToeSpace(i, j, ""));
				getSpace(i,j).setBgColor(Color.WHITE);
				getSpace(i,j).addActionListener(this);
				add(getSpace(i,j));
			}
		}
	}

    protected void statsPanelInfo(JPanel gameStatsPanel)
    {
        TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Game Stats");
        gameStatsPanel.setBorder(title);
        gameStatsPanel.setLayout(new BoxLayout(gameStatsPanel, BoxLayout.Y_AXIS));

//        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 20, 20);
//        gameStatsPanel.setLayout(layout);



        // player turn panel
        JPanel playerTurnPanel = new JPanel();

        // player turn label
        playerTurnLabel = new JLabel();
        updatePlayerTurnLabel();
        playerTurnLabel.setFont(new Font("", Font.BOLD, 24));
        title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Player Turn");
        title.setTitleJustification(TitledBorder.CENTER);
        playerTurnLabel.setBorder(title);

        // player turn add to panel
        playerTurnPanel.add(playerTurnLabel);
        gameStatsPanel.add(playerTurnPanel);
    }

    private void updatePlayerTurnLabel()
    {
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
                if (player==1) {
                    playerTurnLabel.setText(State.getInstance().getPlayerOne().getName() + " (X)");
                } else {
                    playerTurnLabel.setText(State.getInstance().getPlayerTwo().getName() + " (O)");
                }
                break;
        }
    }

    public boolean isBoardFull() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!getSpace(i,j).hasPiece()) // if no piece on this space then it is not full
                    return false;
            }
        }
        return true;
    }

	public boolean checkForWin()
	{
		/**   Reference: the button array is arranged like this as the board
		 *      0 | 1 | 2
		 *      3 | 4 | 5
		 *      6 | 7 | 8
		 */
		// horizontal win check
		if( checkAdjacent(0,0,0,1) && checkAdjacent(0,1,0,2)) //no need to put " == true" because the default check is for true
			return true;
		else if( checkAdjacent(1,0,1,1) && checkAdjacent(1,1,1,2) )
			return true;
		else if ( checkAdjacent(2,0,2,1) && checkAdjacent(2,1,2,2))
			return true;

		// vertical win check
		else if ( checkAdjacent(0,0,1,0) && checkAdjacent(1,0,2,0))
			return true;
		else if ( checkAdjacent(0,1,1,1) && checkAdjacent(1,1,2,1))
			return true;
		else if ( checkAdjacent(0,2,1,2) && checkAdjacent(1,2,2,2))
			return true;

		// diagonal win check
		else if ( checkAdjacent(0,0,1,1) && checkAdjacent(1,1,2,2))
			return true;
		else if ( checkAdjacent(0,2,1,1) && checkAdjacent(1,1,2,0))
			return true;
		else
			return false;
	}

	public boolean checkAdjacent(int r1, int c1, int r2, int c2)
	{
	    if(getSpace(r1,c1).hasPiece() && getSpace(r2,c2).hasPiece())
        {
            if(getPieceAt(r1, c1).getName().equals(getPieceAt(r2, c2).getName()))
                return true;
        }
        return false;
	}

    public void actionPerformed(ActionEvent e) {
        TicTacToeSpace spaceClicked = (TicTacToeSpace) e.getSource();
        if (!spaceClicked.hasPiece()) {
            if (player == 1) {
                spaceClicked.setGamePiece(new TicTacToeX());
                player = 2;
            }
            else if (player == 2) {
                spaceClicked.setGamePiece(new TicTacToeO());
                player = 1;
            }
            updatePlayerTurnLabel();
        }
        if( checkForWin() )
        {
            if (player == 2) {
				winner=1;//System.out.println("Player1 Won");
				state.getPlayerOne().incrementWins();
				state.getPlayerTwo().incrementLosses();
			}

            if (player == 1) {
				winner=2;//System.out.println("Player2 Won");
				state.getPlayerTwo().incrementWins();
				state.getPlayerOne().incrementLosses();
			}

            updatePlayerTurnLabel();
            gui.gameOver();
        }
        else if (isBoardFull()) {
            winner=0;
			state.getPlayerOne().incrementTies();
			state.getPlayerTwo().incrementTies();
            updatePlayerTurnLabel();
            gui.gameOver();
        }
    }

}
