import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener {
	private static GUI gui = null;
	private static final int introWidth = 740;
	private static final int introHeight = 380;
	private static final int gameWidth = 1500;
    private static final int gameHeight = 800;
	String gameChoice;
	JPanel introPanel;
	JPanel gameContainer;
	JPanel statsContainer;
	JTextField playerOneName;
	JTextField playerTwoName;
	PlayerManager pm;
	State s;

	public GUI(String title) {
		super(title);
		pm = PlayerManager.getInstance();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(introWidth, introHeight);
		setLocationRelativeTo(null);
		gameChoice = "Tic-Tac-Toe";
        try {
            introPanel = new JPanelWithBackground("src/images/space.jpg");
        } catch(IOException e){
            introPanel = new JPanel();
        }
        introPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 40, 20));
        introPanel.setLayout(new BoxLayout(introPanel, BoxLayout.PAGE_AXIS));
	}

	public static GUI getInstance() {
		if (gui == null) {
			gui = new GUI("G6 Games");
		}
		return gui;
	}

	public void start() {
		s = State.getInstance();
		chooseGame();
	}

    private void showIntroPanel(JPanel toRemove) {
        remove(toRemove);
        setSize(introWidth, introHeight);
        add(introPanel);
        introPanel.repaint();
        validate();
		setLocationRelativeTo(null);
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
		JLabel playerOneLabel = new JLabel("Player 1 Name");
		playerOneLabel.setForeground(Color.WHITE);
		playerOneName = new JTextField("Player 1");
		playerOnePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		playerOnePanel.add(playerOneLabel);
		playerOnePanel.add(playerOneName);

		JPanel playerTwoPanel = new JPanel();
		playerTwoPanel.setOpaque(false);
		playerTwoPanel.setLayout(new BoxLayout(playerTwoPanel, BoxLayout.PAGE_AXIS));
		JLabel playerTwoLabel = new JLabel("Player 2 Name");
		playerTwoLabel.setForeground(Color.WHITE);
		playerTwoName = new JTextField("Player 2");
		playerTwoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
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
		chooseGamePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		gameSelectButton.addActionListener(this);
		introPanel.add(titleText);
		introPanel.add(players);
		introPanel.add(chooseGamePanel);
		introPanel.add(gameSelectButton);
		add(introPanel);
		setVisible(true);
	}

	private void startGame(JPanel toRemove, GameBoard game) {
		remove(toRemove);
        s.setGame(game);
		startGameContainer(game);
	}

	private void startGameContainer(GameBoard game){
		gameContainer = new JPanel();
		gameContainer.setLayout(new BoxLayout(gameContainer, BoxLayout.X_AXIS));
		gameContainer.add(s.getGame());

		startStatsPanel(game);

		gameContainer.add(statsContainer);
		setSize(gameWidth, gameHeight);
		add(gameContainer);
		validate();
        setLocationRelativeTo(null);
	}

	private void startStatsPanel(GameBoard game)
	{
		statsContainer = new JPanel();
		statsContainer.setLayout(new BoxLayout(statsContainer, BoxLayout.PAGE_AXIS));

        // custom game stats
        JPanel gameStatsContainer = new JPanel();
        game.statsPanelInfo(gameStatsContainer);
        statsContainer.add(gameStatsContainer);

        // menu buttons
        JPanel menuButtons = new JPanel();
        menuButtons.setLayout(new BoxLayout(menuButtons, BoxLayout.LINE_AXIS));

        JButton restartButton = new JButton("Restart Game");
        restartButton.addActionListener(this);
        JButton quitButton = new JButton("Quit Game");
        quitButton.addActionListener(this);
        menuButtons.add(restartButton);
        menuButtons.add(quitButton);

        statsContainer.add(menuButtons);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JComboBox) {
			JComboBox cb = (JComboBox)e.getSource();
			gameChoice = cb.getSelectedItem().toString();
		} else if (e.getSource() instanceof JButton) {
            JButton buttonClicked = (JButton) e.getSource();

            if(buttonClicked.getText().equals("Restart Game"))
            {
                int reply = JOptionPane.showConfirmDialog(null, "Restart Game?", "Restart Game", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    startGame(gameContainer, GameFactory.getInstance().makeGame(gameChoice));
                }
            }
            else if(buttonClicked.getText().equals("Quit Game"))
            {
                int reply = JOptionPane.showConfirmDialog(null, "Quit Game and return to main menu?", "Quit Game", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    showIntroPanel(gameContainer);
                }
            }
            else {
                GameBoard game = GameFactory.getInstance().makeGame(gameChoice);
                Player playerOne;
                Player playerTwo;
                if (!pm.playerExists(playerOneName.getText())) {
                    playerOne = new Player(playerOneName.getText(), pm.getPlayerIDCount());
                    pm.addPlayer(playerOne);
                    pm.incrementPlayerIDCount();
                } else {
                    playerOne = pm.findPlayer(playerOneName.getText());
                }
                if (!pm.playerExists(playerTwoName.getText())) {
                    playerTwo = new Player(playerTwoName.getText(), pm.getPlayerIDCount());
					pm.addPlayer(playerTwo);
                    pm.incrementPlayerIDCount();
                } else {
                    playerTwo = pm.findPlayer(playerTwoName.getText());
                }
                s.setPlayers(playerOne, playerTwo);
                startGame(introPanel, game);
            }
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
