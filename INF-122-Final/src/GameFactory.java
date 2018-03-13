public class GameFactory {

	private static GameFactory gameFactory;

	public static GameFactory getInstance() {
		if (gameFactory == null) {
			gameFactory = new GameFactory();
		}
		return gameFactory;
	}

	public GridGame makeGame(String newGameType, GUI gui) {

		switch (newGameType) {
		case "Tic-Tac-Toe":
			return new TicTacToe(3, 3, gui);
		case "Checkers":
			break;
		case "Othello":
			return new Othello(8, 8, gui);
		case "Snakes and Ladders":
			return new SnakesAndLadders();
		}
		return null;
	}
}
