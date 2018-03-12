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
