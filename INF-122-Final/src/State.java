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
			gui.gameOver(/*playerTwo.getName(), playerOne.getName()*/);
		} else if (playerOneScore < playerTwoScore) {
			playerTwo.incrementWins();
			playerOne.incrementLosses();
			gui.gameOver(/*playerTwo.getName(), playerOne.getName()*/);
		} else
		{
			playerOne.incrementTies();
			playerTwo.incrementTies();
			gui.gameOver(/*playerTwo.getName(), playerOne.getName()*/);
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
}
