import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public abstract class BoardSpace extends JButton {

    protected int boardRow;
    protected int boardColumn;
    protected String value;
    protected Color backgroundColor;

    protected GamePiece piece;
    private boolean hasPiece;

    // construct empty space
    public BoardSpace(int row, int col, String spaceValue)
    {
        super();
        boardRow = row;
        boardColumn = col;
        value = spaceValue;
        piece = null;
        hasPiece = false;
    }

    // construct space with piece
    public BoardSpace(int row, int col, String spaceValue, GamePiece gamePiece) {
        super();
        boardRow = row;
        boardColumn = col;
        value = spaceValue;
        piece = gamePiece;
        hasPiece = true;
    }

    // get position
    public int getPosX() { return boardRow; }
    public int getPosY() { return boardColumn; }

    // value getter & setter
    public void setValue(String value) {
        this.value = value;
    }
    public String getValue() { return value; }

    // space color getter & setter
    public void setBgColor(Color color)
    {
        this.backgroundColor = color;
        this.setBackground(color);
        this.repaint();
    }
    public Color getBgColor() { return backgroundColor; }

    // game piece getter, setter & clear
    public GamePiece getGamePiece() { return piece; }
    public void setGamePiece(GamePiece gamePiece)
    {
        piece = gamePiece;
        hasPiece = true;
        this.repaint();
    }
    public void clearGamePiece()
    {
        piece = null;
        hasPiece = false;
        this.repaint();
    }

    // hasPiece getter
    public boolean hasPiece() { return hasPiece; }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        paintPiece(g);
        this.repaint();
    }

    abstract void paintPiece(Graphics g);
}