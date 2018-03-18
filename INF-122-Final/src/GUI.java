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
	JPanel introPanel;
	JTextField playerOneName;
	JTextField playerTwoName;
	PlayerManager pm = PlayerManager.getInstance();
	State s = State.getInstance();

	public GUI(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(introWidth, introHeight);
		setLocationRelativeTo(null);
		gameChoice = "Tic-Tac-Toe";
		playerOne = "Player 1";
		playerTwo = "Player 2";
		try {
			introPanel = new JPanelWithBackground("src/images/space.jpg");
		} catch(IOException e){
			introPanel = new JPanel();
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
		add(s,getGame());
		validate();
	}

// 	public void gameOver(String winningPlayer, String losingPlayer) {
// 		remove(s.getGame());
// 		setSize(introWidth, introHeight);
// 		add(introPanel);
// 		validate();
	public void gameOver(/*String winningPlayer, String losingPlayer*/) {
		int reply = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Game Over!", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
        	remove(game);
        	game = GameFactory.getInstance().makeGame(gameChoice, this);
        	add(game);
        	validate();
        }
        else {
        	JOptionPane.showMessageDialog(null, "GOODBYE");
			remove(game);
			validate();
			setSize(700, 270);
			add(introPanel);
			introPanel.repaint();
			validate();
        }
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
