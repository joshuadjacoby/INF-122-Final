import javax.swing.*;

public abstract class GameBoard extends JPanel {
	protected int rows;
	protected int cols;
	private Player[] players;
	protected BoardSpace[][] gameBoard;

	public GameBoard(int row, int col) {
		gameBoard = new BoardSpace[row][col];
		this.rows = row;x
        this.cols = col;
    }

    protected void setSpace(int x, int y, BoardSpace space)
    {
        gameBoard[x][y] = space;
    }

    protected BoardSpace getSpace(int x, int y) { return gameBoard[x][y]; }

    protected GamePiece getPieceAt(int x, int y)
    {
        if (getSpace(,y).hasPiece())
        {
            return getSpace(x,y).getGamePiece();
        }
        else
            return null;
    }
    protected void setPieceAt(int x, int y, GamePieece item) {
        setSpace(x, y, getSpace(x,y).setGamePiece(item));

    }

	protected abstract void initializeButtons();

}