import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class TicTacToe extends GameBoard {
	private int player = 1;
	private GUI gui;

	public TicTacToe(int row, int col, GUI gui) {
		super(row, col);
		this.gui = gui;
		setLayout(new GridLayout(0,col));
		initializeButtons();
	}
	
	protected void initializeButtons()
	{
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				setSpace(i,j,new TicTacToeSpace(i, j, ""));
				getSpace(i,j).setBgColor(Color.WHITE);
				getSpace(i,j).addActionListener(new TicTacToeButtonListener());
				add(getSpace(i,j));
			}
		}
	}

	public void processLogic() {
	}

	public void resetButtons()
	{
//		for (int i = 0; i < rows; i++) {
//			for (int j = 0; j < cols; j++) {
//				gameBoard[i][j].setText("");
//			}
//		}
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


	private class TicTacToeButtonListener extends ButtonListener {
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
                else {

                }
            }
            if(checkForWin())
            {
                gui.gameOver();
            }
		}
	}

}
