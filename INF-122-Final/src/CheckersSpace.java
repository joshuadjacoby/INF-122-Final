import java.awt.*;

public class CheckersSpace extends BoardSpace {
    private boolean hasMarker;
    private Color markerColor;

    // construct empty space
    public CheckersSpace(int row, int col, String spaceValue) {
        super(row, col, spaceValue);
        hasMarker = false;
        markerColor = null;
    }

    // construct space with piece
    public CheckersSpace(int row, int col, String spaceValue, GamePiece gamePiece) {
        super(row, col, spaceValue, gamePiece);
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
            g.setColor(piece.getColor());
            g.fillOval(getWidth() / 2 - 30, getHeight() / 2 - 30, 60, 60);
        }
    }
}

class CheckersPieceBlack extends GamePiece {
    public CheckersPieceBlack()
    {
        super("black", Color.BLACK, 2);
    }
}

class CheckersPieceWhite extends GamePiece {
    public CheckersPieceWhite()
    {
        super("white", Color.WHITE, 1);
    }
}