import java.util.*;

class SnakesAndLadders extends GridGame{
<<<<<<< HEAD
	public static final int BOARD_SIZE = 8;
	private int turn;
	private Dice dice;
	private int winner;
	private int[] positionOfPlayers = {0,0};
	private HashMap<Integer, Integer> specialSpaces;
	
	SnakesAndLadders(){
		super(BOARD_SIZE,BOARD_SIZE);
		dice = new Dice();
		specialSpaces = new HashMap<>();
=======
    private static final int BOARD_SIZE = 10;
	private int turn;
	private Dice dice;
	private int winner;
	private int[] positionOfPlayers;
	private HashMap specialSpaces;

	SnakesAndLadders(){
		super(BOARD_SIZE,BOARD_SIZE);
        dice = new Dice();
        positionOfPlayers = {0,0};
        specialSpaces = new Hashmap();
>>>>>>> e5994abf602309ea5c4508609172bb44ef8755be
	}

	int roll(){
		return dice.roll();
	}

	void advancePlayer(int player, int moves){
	}

	void checkForSnakes(int position){
	}

	void checkForLadders(int position){
	}

	void playerTurn(int move){

	}
}
