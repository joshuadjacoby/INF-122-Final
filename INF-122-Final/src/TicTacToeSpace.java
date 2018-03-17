import java.awt.Graphics;

import javax.swing.Icon;

@SuppressWarnings("serial")
public class TicTacToeSpace extends BoardSpace{

	public TicTacToeSpace(int row, int col, String spaceValue) {
		super(row, col, spaceValue);
	}


    @Override
	void paintPiece(Graphics g) {
		//if X
    	
    	//if O
    	
	}
	
}

class TicTacToeX extends GamePiece {
	private Icon image;
	
	public TicTacToeX() {
		super("X", null, 1);
	
	}
}

class TicTacToeO extends GamePiece {
	private Icon image;
	
	public TicTacToeO() {
		super("O", null, 2);
	}
}