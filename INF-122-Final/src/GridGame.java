import javax.swing.JButton;
import javax.swing.JPanel;

public abstract class GridGame extends JPanel{
	protected int rows;
	protected int cols;
	private Player[] players;
	protected GameButton[][] gameBoard;
	
	public GridGame(int row, int col) {
		gameBoard = new GameButton[row][col];
		this.rows = row;
		this.cols = col;
	}

	public abstract initializeButtons() {
	}

}
