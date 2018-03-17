import javax.swing.*;

public abstract class GridGame extends JPanel{
	protected int rows;
	protected int cols;
	private Player[] players;
	protected JButton[][] gameBoard;
	
	public GridGame(int row, int col) {
		gameBoard = new GameButton[row][col];
		this.rows = row;
		this.cols = col;

	}
	public GameButton getButtonAt(int row, int column){

		System.out.println("GET BUTTON: row = " + row + " Col= " + column);
		if((row < rows && row >=0) && (column < cols && column >=0)){
			return (GameButton)gameBoard[row][column];
		}
		return null;
	}

	public JButton[][] getGameBoard() {
		return gameBoard;
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}


	//	public void setGameBoard(JButton[][] gameBoard) {
//		this.gameBoard = gameBoard;
//	}



	protected abstract void initializeButtons();

}
