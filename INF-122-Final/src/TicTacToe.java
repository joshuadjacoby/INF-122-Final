import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class public TicTacToe {
  public void initializeButtons {
    for(int i = 0; i <= 8; i++)
		{
			buttons[i] = new JButton();
			buttons[i].setText("");
			buttons[i].addActionListener(new buttonListener());

			add(buttons[i]); //adds this button to JPanel (note: no need for JPanel.add(...)
			//because this whole class is a JPanel already
		}
  }

  public void processLogic() {
    
  }
}
