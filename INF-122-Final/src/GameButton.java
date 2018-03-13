import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class GameButton<T> extends JButton {
  private int boardRow;
  private int boardColumn;
  private T value;
  private Color originalColor;

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  private String owner;



  public GameButton(String name, int row, int col, T buttonVal, String theowner) {
    super(name);
    boardRow = row;
    boardColumn = col;
    value = buttonVal;
    originalColor = null;
    owner = theowner;
  }


  public GameButton( int row, int col, T buttonVal, String theowner) {
    boardRow = row;
    boardColumn = col;
    value = buttonVal;
    owner = theowner;
  }


  public Color getoriginalColor() {
    return originalColor;
  }

  public void setoriginalColor(Color color) {
    this.originalColor = color;
  }

  public int getGridRowLoc() {
    return boardRow;
  }

  public int getGridColLoc() {
    return boardColumn;
  }

  public T getButtonValue() {
    return value;
  }

  public void setButtonValue(T value) {
    this.value = value;
  }
}

