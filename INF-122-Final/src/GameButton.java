import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class public GameButton extends JButton {
  private x;
  private y;

  public GameButton(String name, int x, int y) {
    super(name);
    this.x = x;
    this.y = y;
  }

  public getX() {
    return this.x;
  }

  public getY() {
    return this.y;
  }
}
