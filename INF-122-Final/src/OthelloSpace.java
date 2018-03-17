import java.awt.*;

public class OthelloSpace extends BoardSpace {
    private boolean hasMarker;
    private Color markerColor;

    // construct empty space
    public OthelloSpace(int row, int col, String spaceValue) {
        super(row, col, spaceValue);
        hasMarker = false;
        markerColor = null;
    }

    // construct space with piece
    public OthelloSpace(int row, int col, String spaceValue, GamePiece gamePiece) {
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
        } else {
            g.setColor(piece.getColor());
            g.fillOval(getWidth() / 2 - 30, getHeight() / 2 - 30, 60, 60);
        }
    }
}

class OthelloPieceBlack extends GamePiece {
    public OthelloPieceBlack()
    {
        super("black", Color.BLACK, 1);
    }
}

class OthelloPieceWhite extends GamePiece {
    public OthelloPieceWhite()
    {
        super("white", Color.WHITE, 2);
    }
}