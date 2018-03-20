import javax.swing.*;

public class State {
	private static State state;
	private Player playerOne;
	private Player playerTwo;
	private GameBoard game;
	private int playerOneScore;
	private int playerTwoScore;
	private GUI gui;

	public State() {
		gui = GUI.getInstance();
	}

	public static State getInstance() {
		if (state == null) {
			state = new State();
		}
		return state;
	}

	public void gameOver() {
		if (playerOneScore > playerTwoScore) {
			playerOne.incrementWins();
			playerTwo.incrementLosses();
			JOptionPane.showMessageDialog(gui.gameContainer, "Game Over!\n" + playerOne.getName() + " wins!\n{ Total wins: " + playerOne.getNumberOfWins());
		} else if (playerOneScore < playerTwoScore) {
			playerTwo.incrementWins();
			playerOne.incrementLosses();
			JOptionPane.showMessageDialog(gui.gameContainer, "Game Over!\n" + playerTwo.getName() + " wins!\nTotal wins: " + playerTwo.getNumberOfWins());
		} else
		{
			playerOne.incrementTies();
			playerTwo.incrementTies();
			JOptionPane.showMessageDialog(gui.gameContainer, "Game Over!\n" + "It was a tie.");
		}
	}

	public void setPlayers(Player p1, Player p2) {
		this.playerOne = p1;
		this.playerTwo = p2;
	}

	public void setGame(GameBoard g) {
		this.game = g;
	}

	public GameBoard getGame() {
		return game;
	}

	public Player getPlayerOne() {
		return playerOne;
	}

	public Player getPlayerTwo() {
		return playerTwo;
	}

	public int getPlayerOneScore() {
		return playerOneScore;
	}

	public void setPlayerOneScore(int playerOneScore) {
		this.playerOneScore = playerOneScore;
	}

	public int getPlayerTwoScore() {
		return playerTwoScore;
	}

	public void setPlayerTwoScore(int playerTwoScore) {
		this.playerTwoScore = playerTwoScore;
	}
	
	public void setPlayerScores(int p1score, int p2score) {
		playerOneScore = p1score;
		playerTwoScore = p2score;
	}
	
	public void resetScores()
	{
		playerOneScore = playerTwoScore = 0;
	}
}
