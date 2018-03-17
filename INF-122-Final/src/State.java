public class State {
	private static State state;
	private Player playerOne;
	private Player playerTwo;
	private GameBoard game;
	private int playerOneScore;
	private int playerTwoScore;
	private GUI gui = GUI.getInstance();

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
			gui.gameOver(playerOne.getName(), playerTwo.getName());
		} else if (playerOneScore < playerTwoScore) {
			playerTwo.incrementWins();
			playerOne.incrementLosses();
			gui.gameOver(playerTwo.getName(), playerOne.getName());
		} else
		{

		}
	}

	public void updatePlayers(Player p1, Player p2) {
		this.playerOne = p1;
		this.playerTwo = p2;
	}

	public void updateGame(GameBoard g) {
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
}
