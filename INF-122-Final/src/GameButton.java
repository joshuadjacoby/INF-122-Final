import javax.swing.*;

@SuppressWarnings("serial")
public class GameButton extends JButton {
  private int x;
  private int y;

  public GameButton(String name, int x, int y) {
    super(name);
    this.x = x;
    this.y = y;
  }

  public GameButton(int x, int y) {
	    this.x = x;
	    this.y = y;
  }
  
  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }
}
