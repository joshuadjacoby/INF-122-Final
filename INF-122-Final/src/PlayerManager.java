import java.util.ArrayList;

public class PlayerManager {
	private ArrayList<Player> players;
	private static PlayerManager manager;
	private int playerIDCount;

	private PlayerManager() {
		players = new ArrayList<Player>();
		playerIDCount = 0;
	}

	public static PlayerManager getInstance() {
		if (manager == null) {
			manager = new PlayerManager();
		}
		return manager;
	}

	public void addPlayer(Player p) {
		players.add(p);
	}

	public Player findPlayer(String name) {
		for (Player p : players){
			if (p.getName() == name) {
				return p;
			}
		}
		return null;
	}

	public boolean playerExists(String name) {
		return findPlayer(name) != null;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public int getPlayerIDCount() {
		return playerIDCount;
	}

	public void incrementPlayerIDCount() {
		this.playerIDCount++;
	}
}
