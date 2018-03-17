import java.awt.*;

class Player{
	private String name;
	private String password;
    private int num;
	private int score;
	private Color color;
	
	Player(String name, String password, int num, int score, Color color){
	    this.name = name;
	    this.password = password;
	    this.num = num;
	    this.score = score;
	    this.color = color;
    }
	
	void setPlayerNum(int num){
		this.num = num;
	}
	
	int getPlayerNum(){
		return num;
	}
	
	int updateScore(int newScore){//should be able to handle increasing/decreasing score
		this.score += newScore;
		return this.score;
	}
	
	int getScore(){
		return this.score;
	}
	
	
}
