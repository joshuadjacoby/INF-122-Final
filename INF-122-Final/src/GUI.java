import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener {
	String gameChoice;
	GameBoard game;
	JPanel introPanel;
	String playerOne;
	String playerTwo;

	public GUI(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 270);
		setLocationRelativeTo(null);
		gameChoice = "Tic-Tac-Toe";
		playerOne = "Player 1";
		playerTwo = "Player 2";
		try {
			introPanel = new JPanelWithBackground("src/images/space.jpg");
		} catch(IOException e){

		}
		introPanel.setLayout(new BoxLayout(introPanel, BoxLayout.PAGE_AXIS));
		chooseGame();
		setVisible(true);
	}

	private void chooseGame() {
		String gameNames[] = {"Tic-Tac-Toe", "Checkers", "Othello", "Snakes and Ladders"};
		JComboBox gameList = new JComboBox(gameNames);
		gameList.addActionListener(this);
		JLabel titleText = new JLabel("G6 Games");
		titleText.setForeground(Color.WHITE);
		titleText.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		titleText.setFont(titleText.getFont().deriveFont(100f));

		JPanel players = new JPanel();
		players.setOpaque(false);
		players.setLayout(new BoxLayout(players, BoxLayout.LINE_AXIS));

		JPanel playerOnePanel = new JPanel();
		playerOnePanel.setOpaque(false);
		playerOnePanel.setLayout(new BoxLayout(playerOnePanel, BoxLayout.PAGE_AXIS));
		JLabel playerOneLabel = new JLabel("Player 1");
		playerOneLabel.setForeground(Color.WHITE);
		JTextField playerOneName = new JTextField("Player 1");
		playerOnePanel.add(playerOneLabel);
		playerOnePanel.add(playerOneName);

		JPanel playerTwoPanel = new JPanel();
		playerTwoPanel.setOpaque(false);
		playerTwoPanel.setLayout(new BoxLayout(playerTwoPanel, BoxLayout.PAGE_AXIS));
		JLabel playerTwoLabel = new JLabel("Player 2");
		playerTwoLabel.setForeground(Color.WHITE);
		JTextField playerTwoName = new JTextField("Player 2");
		playerTwoPanel.add(playerTwoLabel);
		playerTwoPanel.add(playerTwoName);

		players.add(playerOnePanel);
		players.add(playerTwoPanel);

		JPanel chooseGamePanel = new JPanel();
		chooseGamePanel.setOpaque(false);
		JLabel gameText = new JLabel("What game would you like to play?");
		gameText.setForeground(Color.WHITE);
		gameText.setFont(gameText.getFont().deriveFont(20f));
		JButton gameSelectButton = new JButton("Let's go!");
		gameSelectButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		chooseGamePanel.add(gameText);
		chooseGamePanel.add(gameList);
		gameSelectButton.addActionListener(this);
		introPanel.add(titleText);
		introPanel.add(players);
		introPanel.add(chooseGamePanel);
		introPanel.add(gameSelectButton);
		add(introPanel);
	}

	private void startGame() {
		remove(introPanel);
		add(game);
		validate();
	}

	public void gameOver(/*String winningPlayer, String losingPlayer*/) {
		remove(game);
		setSize(700, 270);
		add(introPanel);
		validate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JComboBox) {
			JComboBox cb = (JComboBox)e.getSource();
			gameChoice = cb.getSelectedItem().toString();
		} else if (e.getSource() instanceof JButton) {
			game = GameFactory.getInstance().makeGame(gameChoice, this);
			startGame();
		}
	}

	private class JPanelWithBackground extends JPanel {

		private Image backgroundImage;

		// Some code to initialize the background image.
		// Here, we use the constructor to load the image. This
		// can vary depending on the use case of the panel.
		public JPanelWithBackground(String fileName) throws IOException {
			backgroundImage = ImageIO.read(new File(fileName));
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			// Draw the background image.
			g.drawImage(backgroundImage, 0, 0, this);
		}
	}
}
