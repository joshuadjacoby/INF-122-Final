import javax.swing.JButton;
import javax.swing.JPanel;

public abstract class GridGame extends JPanel{
	private int rows;
	private int columns;
	private Player[] players;
	protected GameButton[][] gameBoard;
	
	public GridGame(int row, int col) {
		gameBoard = new GameButton[row][col];
		this.rows = row;
		this.columns = col;
	}

	public void initializeButtons() {
	}

	public void setItemInPosition(int row, int column, int item) {
		gameBoard[row][column] = item;

	}

	public int getItemAt(int row, int col) {

		return gameBoard[row][col];
	}

	public int[][] getGameBoard() {

		return gameBoard;
	}

	public void updateBoard() {

	}

	//void place(GamePiece g){}
}
