import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class CheckersSpace extends BoardSpace {
    private boolean hasMarker;
    private Color markerColor;
    protected Color backgroundColor;
    protected Color originalColor;

    Image blackImg;
    Image whiteImg;
    Image blackKingImg;
    Image whiteKingImg;

    // construct empty space
    public CheckersSpace(int row, int col, String spaceValue) {
        super(row, col, spaceValue);
        hasMarker = false;
        markerColor = null;
        loadImages();
    }

    // construct space with piece
    public CheckersSpace(int row, int col, String spaceValue, GamePiece gamePiece) {
        super(row, col, spaceValue, gamePiece);
        hasMarker = false;
        markerColor = null;
        loadImages();
    }

    private void loadImages()
    {
        try {
            blackImg = ImageIO.read(getClass().getResource("images/blackpiece.png"));
            blackKingImg = ImageIO.read(getClass().getResource("images/blackking.png"));
            whiteImg = ImageIO.read(getClass().getResource("images/whitepiece.png"));
            whiteKingImg = ImageIO.read(getClass().getResource("images/whiteking.png"));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void setOriginalColor(Color color){
        this.originalColor = color;
    }
    public Color getOriginalColor(){
        return this.originalColor;
    }

    // hintMarker setters
    public void mark(Color color) {
        hasMarker = true;
        markerColor = color;
        this.repaint();
    }
    public void unmark() {
        hasMarker = false;
        markerColor = null;
        this.repaint();
    }
    public boolean isMarked() { return hasMarker; }

    public void paintPiece(Graphics g) {

        if (hasMarker) {
            g.setColor(markerColor);
            g.fillOval(getWidth() / 2 - 10, getHeight() / 2 - 10, 20, 20);
        } else if (hasPiece()){
            if(getGamePiece() instanceof CheckersPieceBlack)
            {
                setIcon(new ImageIcon(blackImg));
            }
            else if(this.getGamePiece() instanceof CheckersPieceWhite)
            {
                setIcon(new ImageIcon(whiteImg));
            }
            else if(this.getGamePiece() instanceof CheckersPieceBlackKing)
            {
                setIcon(new ImageIcon(blackKingImg));
            }
            else if(this.getGamePiece() instanceof CheckersPieceWhiteKing)
            {
                setIcon(new ImageIcon(whiteKingImg));
            }

        }
        else
            setIcon(null);
    }
}

class CheckersPieceBlack extends GamePiece {
    public CheckersPieceBlack()
    {
        super("black", Color.BLACK, 2);}
}

class CheckersPieceBlackKing extends GamePiece{
    public CheckersPieceBlackKing()
    {
        super("blackking", Color.BLACK, 2);
    }
}

class CheckersPieceWhite extends GamePiece {
    public CheckersPieceWhite()
    {
        super("white", Color.WHITE, 1);
    }
}

class CheckersPieceWhiteKing extends GamePiece{
    public CheckersPieceWhiteKing()
    {
        super("whiteking", Color.WHITE, 1);
    }
}