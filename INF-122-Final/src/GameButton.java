import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class GameButton<T> extends JButton {
  private int boardRow;
  private int boardColumn;
  private T value;
  private Color originalColor;



  public GameButton(String name, int row, int col, T buttonVal) {
    super(name);
    boardRow = row;
    boardColumn = col;
    value = buttonVal;
    originalColor = null;
  }


  public GameButton( int row, int col, T buttonVal) {
    boardRow = row;
    boardColumn = col;
    value = buttonVal;
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

