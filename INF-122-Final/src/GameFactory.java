public class GameFactory {

	private static GameFactory gameFactory;

	public static GameFactory getInstance() {
		if (gameFactory == null) {
			gameFactory = new GameFactory();
		}
		return gameFactory;
	}

	public GridGame makeGame(String newGameType) {

		switch (newGameType) {
			case "Tic-Tac-Toe":
				return new TicTacToe(3, 3);
			case "Checkers":
			//	return new Checkers();
				break;
			case "Othello":
			//	return new Othello();
				break;
			case "Snakes and Ladders":
				return new SnakesAndLadders();
		}
		return null;
	}
}
