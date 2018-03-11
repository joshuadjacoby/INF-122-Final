import javax.swing.*;

@SuppressWarnings("serial")
public class GameButton extends JButton {
  private int boardx;
  private int boardy;

  public GameButton(String name, int x, int y) {
    super(name);
    boardx = x;
    boardy = y;
  }

  public GameButton(int x, int y) {
	    boardx = x;
	    boardy = y;
  }
  
  public int getX() {
    return boardx;
  }

  public int getY() {
    return boardy;
  }
}
