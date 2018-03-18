import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;
import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class SNLSpace extends BoardSpace {
    private boolean hasMarker;
    private Color markerColor;
    Image snakeImg;
    Image ladderImg;
    Image diceImg;

    // construct empty space
    public SNLSpace(int row, int col, String spaceValue) {
        super(row, col, spaceValue);
        
        try {
        	snakeImg = ImageIO.read(getClass().getResource("images/snake.png"));
        	ladderImg = ImageIO.read(getClass().getResource("images/stairs.png"));
        	diceImg = ImageIO.read(getClass().getResource("images/dice.png"));
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
                setIcon(new ImageIcon(snakeImg));
            }
            
            if(getGamePiece().getName().equals("L"))
            {
                setIcon(new ImageIcon(ladderImg));
            }
            
            if(getGamePiece().getName().equals("D"))
            {
                setIcon(new ImageIcon(diceImg));
            }
            
            
            if (getGamePiece().getName().equals("P1,P2")) {
            	g.setColor(Color.WHITE);
            	g.fillOval(getWidth() / 4 - 30, getHeight() / 4 - 30, 60, 60);
            	g.setColor(Color.BLACK);
            	g.fillOval(getWidth() * 3/4 - 30, getHeight() * 3/4 - 30, 60, 60);
            	this.repaint();
            }
            if (getGamePiece().getName().equals("P1")) {
            	g.setColor(Color.BLACK);
            	g.fillOval(getWidth()/2 - 30, getHeight()/2 - 30, 60, 60);
            }
            
            if (getGamePiece().getName().equals("P2")) {
            	g.setColor(Color.WHITE);
            	g.fillOval(getWidth()/2 - 30, getHeight()/2 - 30, 60, 60);
            }
		}
    	
    }
}

class Snake extends GamePiece {	
	public Snake() {
		super("S", null, 1);
	}
}

class Ladder extends GamePiece {	
	public Ladder() {
		super("L", null, 1);
	}
}

class DiceBtn extends GamePiece {	
	public DiceBtn() {
		super("D", null, 1);
	}
}
    
    
class SNLPieceBlack extends GamePiece {
    public SNLPieceBlack()
    {
        super("P1", null, 1);
    }
}

class SNLPieceWhite extends GamePiece {
    public SNLPieceWhite()
    {
        super("P2", null, 2);
    }
}

class SNLBothPieces extends GamePiece {
    public SNLBothPieces()
    {
        super("P1,P2", null, 2);
    }
}

class SNLSnake extends GamePiece {
    public SNLSnake()
    {
        super("red", Color.RED, 1);
    }
}
