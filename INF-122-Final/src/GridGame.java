
class GridGame{
	private int[][] gameBoard;
	private int rows;
	private int columns;
	private Player[] players;
	
	GridGame(int row, int col){
		gameBoard = new int[row][col];
		this.rows = row;
		this.columns = col;
	}
	
	//void place(GamePiece g){}
}
