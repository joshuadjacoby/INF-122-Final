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
	private static GUI gui;
	private static final int introWidth = 700;
	private static final int introHeight = 270;
	String gameChoice;
<<<<<<< Updated upstream
	GridGame game;
	JPanel introPanel;
=======
	JPanel introPanel;
	JTextField playerOneName;
	JTextField playerTwoName;
	PlayerManager pm = PlayerManager.getInstance();
	State s = State.getInstance();
>>>>>>> Stashed changes

	public GUI(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(introWidth, introHeight);
		setLocationRelativeTo(null);
		gameChoice = "Tic-Tac-Toe";
		try {
			introPanel = new JPanelWithBackground("src/images/space.jpg");
		} catch(IOException e){

		}
		introPanel.setLayout(new BoxLayout(introPanel, BoxLayout.PAGE_AXIS));
		chooseGame();
		setVisible(true);
	}

	public static GUI getInstance() {
		if (gui == null) {
			gui = new GUI("G6 Games");
		}
		return gui;
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
<<<<<<< Updated upstream
		JLabel playerOne = new JLabel("Player One");
		playerOne.setForeground(Color.WHITE);
		JTextField playerOneName = new JTextField();
		playerOnePanel.add(playerOne);
=======
		JLabel playerOneLabel = new JLabel("Player 1");
		playerOneLabel.setForeground(Color.WHITE);
		playerOneName = new JTextField("Player 1");
		playerOnePanel.add(playerOneLabel);
>>>>>>> Stashed changes
		playerOnePanel.add(playerOneName);

		JPanel playerTwoPanel = new JPanel();
		playerTwoPanel.setOpaque(false);
		playerTwoPanel.setLayout(new BoxLayout(playerTwoPanel, BoxLayout.PAGE_AXIS));
<<<<<<< Updated upstream
		JLabel playerTwo = new JLabel("Player Two");
		playerTwo.setForeground(Color.WHITE);
		JTextField playerTwoName = new JTextField();
		playerTwoPanel.add(playerTwo);
=======
		JLabel playerTwoLabel = new JLabel("Player 2");
		playerTwoLabel.setForeground(Color.WHITE);
		playerTwoName = new JTextField("Player 2");
		playerTwoPanel.add(playerTwoLabel);
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
		setBounds(300, 200, 300, 300);
		add(game);
		validate();
	}

	public void gameOver() {
		remove(game);
		setBounds(300, 200, 300, 100);
=======
		add(s.getGame());
		validate();
	}

	public void gameOver(String winningPlayer, String losingPlayer) {
		remove(s.getGame());
		setSize(introWidth, introHeight);
>>>>>>> Stashed changes
		add(introPanel);
		validate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JComboBox) {
			JComboBox cb = (JComboBox)e.getSource();
			gameChoice = cb.getSelectedItem().toString();
		} else if (e.getSource() instanceof JButton) {
			GameBoard game = GameFactory.getInstance().makeGame(gameChoice, this);
			Player playerOne;
			Player playerTwo;
			if (!pm.playerExists(playerOneName.getText())) {
				playerOne = new Player(playerOneName.getText(), pm.getPlayerIDCount());
				pm.incrementPlayerIDCount();
			} else {
				playerOne = pm.findPlayer(playerOneName.getText());
			}
			if (!pm.playerExists(playerTwoName.getText())) {
				playerTwo = new Player(playerTwoName.getText(), pm.getPlayerIDCount());
				pm.incrementPlayerIDCount();
			} else {
				playerTwo = pm.findPlayer(playerTwoName.getText());
			}
			s.updatePlayers(playerOne, playerTwo);
			s.updateGame(game);
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
