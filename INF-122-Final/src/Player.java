<<<<<<< Updated upstream

class Player{
	private int playerNum;
	private int score = 0;
	
	Player(){}
	
	void setPlayerNum(int num){
		this.playerNum = num;
	}
	
	int getPlayerNum(){
		return playerNum;
	}
	
	int updateScore(int newScore){//should be able to handle increasing/decreasing score
		this.score += newScore;
		return this.score;
	}
	
	int getScore(){
		return this.score;
	}
	
	
}
=======
import java.awt.*;

class Player{
	private String name;
    private int playerID;
    private int numberOfWins;
    private int numberOfLosses;
	
	Player(String name, int id){
	    this.name = name;
	    this.playerID = id;
	    this.numberOfWins = 0;
	    this.numberOfLosses = 0;
    }

	public String getName() {
		return name;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public int getNumberOfWins() {
		return numberOfWins;
	}

	public void incrementWins() {
		this.numberOfWins++;
	}

	public int getNumberOfLosses() {
		return numberOfLosses;
	}

	public void incrementLosses() {
		this.numberOfLosses++;
	}
}
>>>>>>> Stashed changes
