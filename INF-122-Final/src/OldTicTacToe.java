import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToe extends GridGame
{
	JButton buttons[] = new JButton[9];
	int alternate = 0;//if this number is a even, then put a X. If it's odd, then put an O

	public TicTacToe()
	{
		setLayout(new GridLayout(3,3));
		initializeButtons();
	}

	public void initializeButtons()
	{
		for(int i = 0; i <= 8; i++)
		{
			buttons[i] = new JButton();
			buttons[i].setText("");
			buttons[i].addActionListener(new buttonListener());

			add(buttons[i]); //adds this button to JPanel (note: no need for JPanel.add(...)
			//because this whole class is a JPanel already
		}
	}
	public void resetButtons()
	{
		for(int i = 0; i <= 8; i++)
		{
			buttons[i].setText("");
		}
	}

	// when a button is clicked, it generates an ActionEvent. Thus, each button needs an ActionListener. When it is clicked, it goes to this listener class that I have created and goes to the actionPerformed method. There (and in this class), we decide what we want to do.
	private class buttonListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e)
		{

			JButton buttonClicked = (JButton)e.getSource(); //get the particular button that was clicked
			if(alternate%2 == 0)
				buttonClicked.setText("X");
			else
				buttonClicked.setText("O");

			if(checkForWin() == true)
			{
				JOptionPane.showConfirmDialog(null, "Game Over.");
				resetButtons();
			}

			alternate++;

		}

		public boolean checkForWin()
		{
			/**   Reference: the button array is arranged like this as the board
			 *      0 | 1 | 2
			 *      3 | 4 | 5
			 *      6 | 7 | 8
			 */
			//horizontal win check
			if( checkAdjacent(0,1) && checkAdjacent(1,2) ) //no need to put " == true" because the default check is for true
				return true;
			else if( checkAdjacent(3,4) && checkAdjacent(4,5) )
				return true;
			else if ( checkAdjacent(6,7) && checkAdjacent(7,8))
				return true;

				//vertical win check
			else if ( checkAdjacent(0,3) && checkAdjacent(3,6))
				return true;
			else if ( checkAdjacent(1,4) && checkAdjacent(4,7))
				return true;
			else if ( checkAdjacent(2,5) && checkAdjacent(5,8))
				return true;

				//diagonal win check
			else if ( checkAdjacent(0,4) && checkAdjacent(4,8))
				return true;
			else if ( checkAdjacent(2,4) && checkAdjacent(4,6))
				return true;
			else
				return false;


		}

		public boolean checkAdjacent(int a, int b)
		{
			if ( buttons[a].getText().equals(buttons[b].getText()) && !buttons[a].getText().equals("") )
				return true;
			else
				return false;
		}

	}
}
