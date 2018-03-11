import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener {
	String gameChoice;
	GridGame game;
	JPanel introPanel;

	public GUI(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 200, 300, 100);
		gameChoice = "Tic-Tac-Toe";
		introPanel = new JPanel();
		chooseGame();
		setVisible(true);
	}

	private void chooseGame() {
		String gameNames[] = {"Tic-Tac-Toe", "Checkers", "Othello", "Snakes and Ladders"};
		JComboBox gameList = new JComboBox(gameNames);
		gameList.addActionListener(this);
		JTextArea gameText = new JTextArea("What game would you like to play?");
		JButton gameSelectButton = new JButton("Let's go!");
		gameSelectButton.addActionListener(this);
		introPanel.add(gameText);
		introPanel.add(gameList);
		introPanel.add(gameSelectButton);
		add(introPanel);
	}

	private void startGame() {
		remove(introPanel);
		setBounds(300, 200, 300, 300);
		add(game);
		validate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JComboBox) {
			JComboBox cb = (JComboBox)e.getSource();
			gameChoice = cb.getSelectedItem().toString();
		} else if (e.getSource() instanceof JButton) {
			game = GameFactory.getInstance().makeGame(gameChoice);
			startGame();
		}
	}
}
