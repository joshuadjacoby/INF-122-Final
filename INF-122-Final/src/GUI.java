import javax.swing.*;

public class GUI {

	public GUI() {

	}

	public void run() {
		JFrame window = new JFrame("Tic-Tac-Toe");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().add(new TicTacToe());
		window.setBounds(300,200,300,300);
		window.setVisible(true);
	}
}
