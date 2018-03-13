import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class TicTacToe extends GridGame {
	private int player = 1;
	private GUI gui;

	public TicTacToe(int row, int col, GUI gui) {
		super(row, col);
		this.gui = gui;
		setLayout(new GridLayout(row,col));
		initializeButtons();
	}
	
	protected void initializeButtons()
	{
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				gameBoard[i][j] = new GameButton(i, j, "");
				gameBoard[i][j].setText("");
				gameBoard[i][j].addActionListener(new buttonListener());
				add(gameBoard[i][j]);	
			}
		}
	}

	public void processLogic() {
	}

	public void resetButtons()
	{
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				gameBoard[i][j].setText("");
			}
		}
	}

	private class buttonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JButton buttonClicked = (JButton)e.getSource();
			if (buttonClicked.getText().equals("")) {
				if (player == 1) {
					buttonClicked.setText("X");
					player = 2;
				}
				else if (player == 2) {
					buttonClicked.setText("O");
					player = 1;
				}
				else {
					;
				}
			}
			if(checkForWin() == true)
			{
				gui.gameOver();
			}
			
		}
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
		if (gameBoard[r1][c1].getText().equals(gameBoard[r2][c2].getText()) && !gameBoard[r1][c1].getText().equals(""))
			return true;
		else
			return false;
	}
}
