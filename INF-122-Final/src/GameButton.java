import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class GameButton extends JButton {
  private int boardRow;
  private int boardColumn;
  private String value;
  private Color pieceColor;
  private boolean isPiece = false;
  private boolean marker = false;

  public GameButton(int row, int col, String buttonVal, Color color) {
    super();
    this.boardRow = row;
    this.boardColumn = col;
    this.value = buttonVal;
    this.pieceColor = color;
    this.isPiece = true;
  }
  
  public GameButton(int row, int col, String value) {
	  super();
	  this.boardRow = row;
	  this.boardColumn = col;
	  this.value = value;
	  this.setText(this.value);
  }

  public Color getColor() {
    return this.pieceColor;
  }

  public void setColor(Color color) {
    this.pieceColor = color;
    this.isPiece = true;
    this.repaint();
  }
  
  public void clearSpace() {
	  this.pieceColor = null;
	  this.isPiece = false;
	  this.marker = false;
	  this.repaint();
  }

  public int getGameX() {
    return boardRow;
  }

  public int getGameY() {
    return boardColumn;
  }

  public String getButtonValue() {
    return value;
  }

  public void setButtonValue(String value) {
    this.value = value;
  }
  
  public boolean isAPiece() {
	  return this.isPiece;
  }
  
  public boolean isMarked() {
	  return this.marker;
  }
  
  public void setMark() {
	  this.marker = true;
  }
  
  public void unMark() {
	  this.marker = false;
  }
  
  public void paintComponent(Graphics g)
  {
      if (!isPiece)
          super.paintComponent(g);
      else {
    	  if (marker) {
    		  super.paintComponent(g);
    		  g.setColor(this.pieceColor);
              g.fillOval(getWidth()/2 - 10, getHeight()/2 - 10, 20, 20);
    	  } else {
    		  super.paintComponent(g);
    		  g.setColor(this.pieceColor);
              g.fillOval(getWidth()/2 - 30, getHeight()/2 - 30, 60, 60);
    	  }
      }       
  }
}
