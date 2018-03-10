import java.util.*;

class SnakesAndLadders extends GridGame{
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
