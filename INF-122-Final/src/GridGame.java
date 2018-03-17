import javax.swing.*;

public abstract class GridGame extends JPanel {
	protected int rows;
	protected int cols;
	private Player[] players;
	protected JButton[][] gameBoard;

	public GridGame(int row, int col) {
		gameBoard = new JButton[row][col];
		this.rows = row;
		this.cols = col;
	}

	protected abstract void initializeButtons();

}