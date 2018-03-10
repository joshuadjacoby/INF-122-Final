<<<<<<< HEAD
import javax.swing.*;

public class GridGame extends JPanel{
	private int[][] gameBoard;
=======
import javax.swing.JButton;

import TicTacToe.buttonListener;

public abstract class GridGame {
>>>>>>> e5994abf602309ea5c4508609172bb44ef8755be
	private int rows;
	private int columns;
	private Player[] players;

	public GridGame(int row, int col) {
		gameBoard = new JButton[row][col];
		this.rows = row;
		this.columns = col;
		for (int i = 0; i <= 8; i++) {
			gameBoard[i][i] = new JButton();
			gameBoard[i][i].setText("");
			gameBoard[i][i].addActionListener(new buttonListener());

			add(gameBoard[i][i]); //adds this button to JPanel (note: no need for JPanel.add(...)
			//because this whole class is a JPanel already
		}
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
