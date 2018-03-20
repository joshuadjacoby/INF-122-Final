public class GameFactory {

	private static GameFactory gameFactory;

	public static GameFactory getInstance() {
		if (gameFactory == null) {
			gameFactory = new GameFactory();
		}
		return gameFactory;
	}

	public GameBoard makeGame(String newGameType) {
		State.getInstance().resetScores();
		
		switch (newGameType) {
		case "Tic-Tac-Toe":
			return new TicTacToe(3, 3);
		case "Checkers":
			return new Checkers(8, 8, 1);
		case "Othello":
			return new Othello(8, 8);
		case "Snakes and Ladders":
			return new SnakesAndLadders(10, 10);
		}
		return null;
	}
}
