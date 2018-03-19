import javax.swing.*;
import java.awt.event.ActionEvent;

public abstract class GameBoard extends JPanel {
	protected int rows;
	protected int cols;
    protected GUI gui;
    protected State state;
	private Player[] players;
	private BoardSpace[][] gameBoard;

	public GameBoard(int row, int col, GUI gui) {
		gameBoard = new BoardSpace[row][col];
		this.gui = gui;
        state = State.getInstance();
		this.rows = row;
		this.cols = col;
	}

    protected void setSpace(int x, int y, BoardSpace space)
    {
        gameBoard[x][y] = space;
    }

    protected BoardSpace getSpace(int x, int y) { return gameBoard[x][y]; }

    protected GamePiece getPieceAt(int x, int y)
    {
	    if (getSpace(x,y).hasPiece())
        {
            return getSpace(x,y).getGamePiece();
        }
        else
            return null;
    }

	protected abstract void initializeButtons();
    protected abstract void statsPanelInfo(JPanel gameStatsPanel);

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JComboBox cb = (JComboBox)e.getSource();

        }
    }

    private class GameBoardButtonListener extends ButtonListener {
        public void actionPerformed(ActionEvent e) {
            JButton buttonClicked = (JButton) e.getSource();
            
        }
    }
}