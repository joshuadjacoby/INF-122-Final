import java.util.*;

class EelsandEscalators extends GridGame{
	private int turn;
	private Dice dice = new Dice();
	private int winner = 0;
	private int[] positionOfPlayers = {0,0};
	private HashMap specialSpaces = new Hashmap();
	
	EelsandEscalators(){
		super(8,8);
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
