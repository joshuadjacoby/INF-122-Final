import java.awt.*;

class Player{
	private String name;
    private int playerID;
    private int numberOfWins;
    private int numberOfLosses;
    private int numberOfTies;

	Player(String name, int id){
	    this.name = name;
	    this.playerID = id;
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

	public int getNumberOfTies() {
		return numberOfTies;
	}

	public void incrementTies() {
		this.numberOfTies++;
	}
}