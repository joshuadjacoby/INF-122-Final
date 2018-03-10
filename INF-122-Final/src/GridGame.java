import javax.swing.*;

public class GridGame extends JPanel{
	private int[][] gameBoard;
	private int rows;
	private int columns;
	private Player[] players;
	
	public GridGame(int row, int col){
		gameBoard = new int[row][col];
		this.rows = row;
		this.columns = col;
	}
	

	public void setItemInPosition(int row, int column, int item) {
		gameBoard[row][column] = item;
		
	}
	
	public int getItemAt(int row, int col) {
		
		
		return gameBoard[row][col];
	}
	
	
	public int[][] getGameBoard(){
		
		return gameBoard;
	}
	
	//void place(GamePiece g){}
}
