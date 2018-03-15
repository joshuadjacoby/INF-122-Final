import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Othello extends GridGame {
	private int BlackScore = 2;
	private int WhiteScore = 2;
	private Color playerColor = Color.BLACK;
	private Color opponentColor = Color.WHITE;
	private JLabel blackScoreLabel = new JLabel("Black: " + BlackScore);
	private JLabel whiteScoreLabel = new JLabel("White: " + WhiteScore);
	private GUI gui;

	public Othello(int row, int col, GUI gui) {
		super(row, col);
		this.gui = gui;
		setLayout(new GridLayout(0, col));
		initializeButtons();
		add(blackScoreLabel);
		add(whiteScoreLabel);
		allPossibleMoves();
	}

	protected void initializeButtons() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (i == rows / 2) {
					if (j == cols / 2) {
						gameBoard[i][j] = new GameButton(i, j, "", Color.BLACK);
					} else if (j == cols / 2 - 1) {
						gameBoard[i][j] = new GameButton(i, j, "", Color.WHITE);
					} else {
						gameBoard[i][j] = new GameButton(i, j, "");
					}
				} else if (i == rows / 2 - 1) {
					if (j == cols / 2) {
						gameBoard[i][j] = new GameButton(i, j, "", Color.WHITE);
					} else if (j == cols / 2 - 1) {
						gameBoard[i][j] = new GameButton(i, j, "", Color.BLACK);
					} else {
						gameBoard[i][j] = new GameButton(i, j, "");
					}
				} else {
					gameBoard[i][j] = new GameButton(i, j, "");
				}
		        gameBoard[i][j].setBackground(new Color(0,102,0));
		        gameBoard[i][j].addActionListener(new buttonListener());
		        add(gameBoard[i][j]);
		      }
		}
	}

	public void processLogic() {
	}

	public void resetButtons() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				gameBoard[i][j].setText("");
			}
		}
	}
	
	public void updateScore(Color player, int black, int white) {
		if (player == Color.BLACK) {
			WhiteScore = WhiteScore - white;
			BlackScore = BlackScore + black;
		} else {
			BlackScore -= black;
			WhiteScore += white;
		}
		blackScoreLabel.setText("Black: " + BlackScore);
		whiteScoreLabel.setText("White: " + WhiteScore);
	}
	
	public void allPossibleMoves() {
		ArrayList<GameButton> currentPlayerPieces = new ArrayList<GameButton>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (gameBoard[i][j].getColor() == this.playerColor) {
					currentPlayerPieces.add(gameBoard[i][j]);
				}
			}
		}
		for (int k = 0; k < currentPlayerPieces.size(); k++) {
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					int currentX = currentPlayerPieces.get(k).getGameX() + i;
					int currentY = currentPlayerPieces.get(k).getGameY() + j;
					while ( 0 <= currentX && currentX < this.rows && 0 <= currentY && currentY < this.cols ) {
						if (gameBoard[currentX][currentY].getColor() == this.opponentColor) {
							currentX += i;
							currentY += j;
							if ( 0 <= currentX && currentX < this.rows && 0 <= currentY && currentY < this.cols ) {
								if (!gameBoard[currentX][currentY].isAPiece() && !gameBoard[currentX][currentY].isMarked()) {
									gameBoard[currentX][currentY].setMark();
									gameBoard[currentX][currentY].setColor(this.playerColor);
								}
							}
						} else {
							break;
						}
					}
				}
			}
		}
	}
	
	public void clearHintMarks() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (gameBoard[i][j].isMarked()) {
					gameBoard[i][j].clearSpace();
				}
			}
		}
	}
	
	public boolean validMove(int x, int y) {
		boolean valid = false;
		if (gameBoard[x][y].isAPiece() && !gameBoard[x][y].isMarked()) {
			return false;
		}
		if (!gameBoard[x][y].isAPiece() && !gameBoard[x][y].isMarked()) {
			return false;
		}
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				int currentX = x + i;
				int currentY = y + j;
				while ( 0 <= currentX && currentX < this.rows && 0 <= currentY && currentY < this.cols ) {
					if (gameBoard[currentX][currentY].getColor() == this.opponentColor) {
						currentX += i;
						currentY += j;
						if (gameBoard[currentX][currentY].getColor() == this.playerColor && !gameBoard[currentX][currentY].isMarked()) {
							while (currentX != x + i || currentY != y + j) {
								currentX -= i;
								currentY -= j;
								gameBoard[currentX][currentY].setColor(this.playerColor);
								updateScore(this.playerColor, 1, 1);
							}
							valid = true;
						}
					} else {
						break;
					}
				}
			}
		}
		return valid;
	}

  private class buttonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      GameButton buttonClicked = (GameButton) e.getSource();
      if (validMove(buttonClicked.getGameX(), buttonClicked.getGameY())) {
    	  buttonClicked.setColor(playerColor);
    	  buttonClicked.unMark();
    	  if (playerColor.equals(Color.BLACK)) {
        	  updateScore(playerColor, 1, 0);
        	  playerColor = Color.WHITE;
        	  opponentColor = Color.BLACK;
          } else {
        	  updateScore(playerColor, 0, 1);
        	  playerColor = Color.BLACK;
        	  opponentColor = Color.WHITE;
          }
    	  clearHintMarks();
    	  allPossibleMoves();
    	  if (checkForWin()) {
    		  gui.gameOver();
    	  }
      }
    }

  }

  public boolean checkForWin() {
	  for (int i = 0; i < rows; i++) {
		  for (int j = 0; j < cols; j++) {
			  if (gameBoard[i][j].isMarked()) {
				  return false;
			  }
		  }
	  }
	  return true;
  }
}
