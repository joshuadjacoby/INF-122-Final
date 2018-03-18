import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;
import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class SNLSpace extends BoardSpace {
    private boolean hasMarker;
    private Color markerColor;
    Image snakeHeadImg;
    Image ladderImg;

    // construct empty space
    public SNLSpace(int row, int col, String spaceValue) {
        super(row, col, spaceValue);
        
        try {
        	snakeHeadImg = ImageIO.read(getClass().getResource("images/snake.png"));
        	ladderImg = ImageIO.read(getClass().getResource("images/stairs.png"));
        }
        catch (Exception ex) {
        	System.out.println(ex);
        }
        hasMarker = false;
        markerColor = null;
    }

    // construct space with piece
    public SNLSpace(int row, int col, String spaceValue, GamePiece gamePiece) {
        super(row, col, spaceValue, gamePiece);
    }

    public boolean isMarked() { return hasMarker; }

    @Override
    void paintPiece(Graphics g) {
    	if(hasPiece())
		{
            if(getGamePiece().getName().equals("S"))
            {
                setIcon(new ImageIcon(snakeHeadImg));
            }
            
            if(getGamePiece().getName().equals("L"))
            {
                setIcon(new ImageIcon(ladderImg));
            }
		}
    }
}

class SnakeHead extends GamePiece {	
	public SnakeHead() {
		super("S", null, 1); // change S later
	}
}

class Ladder extends GamePiece {	
	public Ladder() {
		super("L", null, 1); // change S later
	}
}
    
    
class SNLPieceBlack extends GamePiece {
    public SNLPieceBlack()
    {
        super("black", Color.BLACK, 1);
    }
}

class SNLPieceWhite extends GamePiece {
    public SNLPieceWhite()
    {
        super("white", Color.WHITE, 2);
    }
}

class SNLSnake extends GamePiece {
    public SNLSnake()
    {
        super("red", Color.RED, 1);
    }
}
