import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class TicTacToeSpace extends BoardSpace{

    Image xImg;
    Image oImg;

	public TicTacToeSpace(int row, int col, String spaceValue) {
		super(row, col, spaceValue);

        try {
            xImg = ImageIO.read(getClass().getResource("images/x.png"));
            oImg = ImageIO.read(getClass().getResource("images/o.png"));
        } catch (Exception ex) {
            System.out.println(ex);
        }
	}


    @Override
	void paintPiece(Graphics g) {

    	if(hasPiece())
		{
            if(getGamePiece().getName().equals("X"))
            {
                setIcon(new ImageIcon(xImg));
            }
            else if(this.getGamePiece().getName().equals("O"))
            {
                setIcon(new ImageIcon(oImg));
            }
		}
    	
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