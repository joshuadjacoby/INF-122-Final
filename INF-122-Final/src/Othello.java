import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Othello extends GameBoard {
	private int blackScore = 2;
	private int whiteScore = 2;

	private Color playerColor = Color.BLACK;
	private Color opponentColor = Color.WHITE;

	private JLabel blackScoreLabel = new JLabel("Black: " + blackScore);
	private JLabel whiteScoreLabel = new JLabel("White: " + whiteScore);
    private JLabel playerTurnLabel;

	public Othello(int row, int col, GUI gui) {
		super(row, col, gui);
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
						setSpace(i, j, new OthelloSpace(i, j, "", new OthelloPieceBlack()));
					} else if (j == cols / 2 - 1) {
                        setSpace(i, j, new OthelloSpace(i, j, "", new OthelloPieceWhite()));
					} else {
                        setSpace(i, j, new OthelloSpace(i, j, ""));
					}
				} else if (i == rows / 2 - 1) {
					if (j == cols / 2) {
                        setSpace(i, j, new OthelloSpace(i, j, "", new OthelloPieceWhite()));
					} else if (j == cols / 2 - 1) {
                        setSpace(i, j, new OthelloSpace(i, j, "", new OthelloPieceBlack()));
					} else {
                        setSpace(i, j, new OthelloSpace(i, j, ""));
					}
				} else {
                    setSpace(i, j, new OthelloSpace(i, j, ""));
				}
		        getSpace(i,j).setBgColor(new Color(0,102,0));
                getSpace(i,j).addActionListener(new OthelloButtonListener());
		        add(getSpace(i,j));
		      }
		}
	}

    protected void statsPanelInfo(JPanel gameStatsPanel)
    {
        TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Game Stats");

        gameStatsPanel.setBorder(title);

        // player turn
        JPanel playerTurnPanel = new JPanel();
        playerTurnLabel = new JLabel();
        updatePlayerTurnLabel();
        // player turn style
        title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Turn");
        title.setTitleJustification(TitledBorder.CENTER);
        playerTurnLabel.setBorder(title);
        playerTurnLabel.setFont(new Font("", Font.BOLD, 24));
        // player turn add to panel
        playerTurnPanel.add(playerTurnLabel);
        gameStatsPanel.add(playerTurnPanel);
    }

    private void updatePlayerTurnLabel()
    {
//        playerTurnLabel
//
//        if(winner > 0) {
//            playerTurnLabel.setText("PLAYER " + winner + " WINS!");
//            TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Winner");
//            title.setTitleJustification(TitledBorder.CENTER);
//            playerTurnLabel.setBorder(title);
//        }
//        else if (winner == 0)
//        {
//            playerTurnLabel.setText("NO WINNER");
//            TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Winner");
//            title.setTitleJustification(TitledBorder.CENTER);
//        }
//        else {
//            playerTurnLabel.setText("PLAYER " + player);
//        }
    }

//	public void resetButtons() {
//		for (int i = 0; i < rows; i++) {
//			for (int j = 0; j < cols; j++) {
//				getSpace(i,j).setText("");
//			}
//		}
//	}
	
	public void updateScore(Color player, int black, int white) {
		if (player == Color.BLACK) {
			whiteScore = whiteScore - white;
			blackScore = blackScore + black;
		} else {
			blackScore -= black;
			whiteScore += white;
		}
		blackScoreLabel.setText("Black: " + blackScore);
		whiteScoreLabel.setText("White: " + whiteScore);
	}
	
	public void allPossibleMoves() {
		ArrayList<OthelloSpace> currentPlayerPieces = new ArrayList<>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
                if (getSpace(i, j).hasPiece()) {
                    if (getPieceAt(i, j).getColor() == this.playerColor) {
                        OthelloSpace tempSpace = (OthelloSpace) getSpace(i, j);
                        currentPlayerPieces.add(tempSpace);
                    }
                }
			}
		}
		for (int k = 0; k < currentPlayerPieces.size(); k++) {
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					int currentX = currentPlayerPieces.get(k).getPosX() + i;
					int currentY = currentPlayerPieces.get(k).getPosY() + j;
					while ( 0 <= currentX && currentX < this.rows && 0 <= currentY && currentY < this.cols ) {
                        if (getSpace(currentX, currentY).hasPiece()) {
                            if (getPieceAt(currentX, currentY).getColor() == this.opponentColor) {
                                currentX += i;
                                currentY += j;
                                if (0 <= currentX && currentX < this.rows && 0 <= currentY && currentY < this.cols) {
                                    OthelloSpace tempSpace = (OthelloSpace) getSpace(currentX, currentY);
                                    if (!tempSpace.hasPiece() && !tempSpace.isMarked()) {
                                        tempSpace.mark(this.playerColor);
                                    }
                                }
                            }
                            else {
                                break;
                            }
                        }
                        else {
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
			    OthelloSpace tempSpace = (OthelloSpace) getSpace(i,j);
				if (tempSpace.isMarked()) {
                    tempSpace.unmark();
				}
			}
		}
	}
	
	public boolean validMove(int x, int y) {
		boolean valid = false;

        OthelloSpace tempSpace = (OthelloSpace) getSpace(x,y);
		if ((tempSpace.hasPiece() && !tempSpace.isMarked())
                || (!tempSpace.hasPiece() && !tempSpace.isMarked()))
		{
			return false;
		}

		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				int currentX = x + i;
				int currentY = y + j;

				while ( 0 <= currentX && currentX < this.rows && 0 <= currentY && currentY < this.cols ) {
                    tempSpace = (OthelloSpace) getSpace(currentX,currentY);
                    if (tempSpace.hasPiece()) {
                        if (getPieceAt(currentX, currentY).getColor() == this.opponentColor) {
                            currentX += i;
                            currentY += j;

                            if(0 <= currentX && currentX < this.rows && 0 <= currentY && currentY < this.cols) {
                                tempSpace = (OthelloSpace) getSpace(currentX, currentY);
                                if (tempSpace.hasPiece()) {
                                    if (getPieceAt(currentX, currentY).getColor() == this.playerColor && !tempSpace.isMarked()) {
                                        while (currentX != x + i || currentY != y + j) {
                                            currentX -= i;
                                            currentY -= j;
                                            getPieceAt(currentX, currentY).setColor(this.playerColor);
                                            updateScore(this.playerColor, 1, 1);
                                        }
                                        valid = true;
                                    }
                                }
                            }

                        } else {
                            break;
                        }
                    }
                    else {
                        break;
                    }
				}
			}
		}
		return valid;
	}



    public boolean checkForWin() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                OthelloSpace tempSpace = (OthelloSpace) getSpace(i,j);
                if (tempSpace.isMarked()) {
                    return false;
                }
            }
        }
        return true;
    }

    private class OthelloButtonListener extends ButtonListener {
        public void actionPerformed(ActionEvent e) {
            OthelloSpace spaceClicked = (OthelloSpace) e.getSource();

            if (validMove(spaceClicked.getPosX(), spaceClicked.getPosY())) {
                if (playerColor.equals(Color.BLACK)) {
                    spaceClicked.setGamePiece(new OthelloPieceBlack());
                    spaceClicked.unmark();
                    updateScore(playerColor, 1, 0);
                    playerColor = Color.WHITE;
                    opponentColor = Color.BLACK;
                } else {
                    spaceClicked.setGamePiece(new OthelloPieceWhite());
                    spaceClicked.unmark();
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
}
