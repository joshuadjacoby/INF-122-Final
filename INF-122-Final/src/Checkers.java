import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Checkers extends GameBoard {

	private int winner = -1;
	private JLabel blackScoreLabel;
	private JLabel whiteScoreLabel;
	
    private JLabel playerTurnLabel;
    
    private int currentPlayer = 1; //1 == W,   2 == B
    private ArrayList currentPossibleMoves;
    private CheckersSpace currPressedSpace; // currPressedBtn
    private boolean itemchanged = false;

    private String moveFacing = "s";
    private String defaultItem = "";
    private boolean item_selected = false;

    private int p1Score = 12;
    private int p2Score = 12;

    Checkers (int row, int col, int starter, GUI gui){
        super(row, col, gui);

        setLayout(new GridLayout(row,col));
        initializeButtons();
        currentPlayer = starter;
        currentPossibleMoves = new ArrayList();
        //gridGameMoveLogic = new GridGameMoveLogic<String>(row, col, defaultItem);


    }

    protected void initializeButtons() {
        Color theColor = Color.RED;
        Color placeOnColor = Color.decode("#191919");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if( theColor.equals(placeOnColor) && i >= 0 && i <= 2){

                    setSpace(i,j,  new CheckersSpace(i,j,"", new CheckersPieceWhite()));
                }
                else if(theColor.equals(placeOnColor) && i >= 5 && i <= 7){
                    setSpace(i,j ,  new CheckersSpace(i,j,"", new CheckersPieceBlack()));

                }
                else{
                    setSpace(i,j , new CheckersSpace(i,j,""));
                }
                CheckersSpace tempSpace = (CheckersSpace) getSpace(i,j);

                tempSpace.addActionListener(this);
                tempSpace.setBgColor(theColor);
                tempSpace.setOriginalColor(theColor);

                theColor = _colorChanger(theColor);
                add(getSpace(i,j));
            }
            theColor = _colorChanger(theColor);


        }
    }
    
    protected void statsPanelInfo(JPanel gameStatsPanel)
    {
        TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Game Stats");
        gameStatsPanel.setBorder(title);
        gameStatsPanel.setLayout(new BoxLayout(gameStatsPanel, BoxLayout.Y_AXIS));

        // player turn panel
        JPanel playerTurnPanel = new JPanel();

        // player turn label
        playerTurnLabel = new JLabel();
        updatePlayerTurnLabel();
        title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Player Turn");
        title.setTitleJustification(TitledBorder.CENTER);
        playerTurnLabel.setBorder(title);
        playerTurnLabel.setFont(new Font("", Font.BOLD, 24));

        // add to player turn panel
        playerTurnPanel.add(playerTurnLabel);

        // player info
        JPanel playerInfoPanel = new JPanel();
        title = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Pieces Remaining");
        title.setTitleJustification(TitledBorder.CENTER);
        playerInfoPanel.setBorder(title);
        playerInfoPanel.setLayout(new BoxLayout(playerInfoPanel, BoxLayout.Y_AXIS));
        whiteScoreLabel = new JLabel(State.getInstance().getPlayerOne().getName() + " (WHITE) : " + p1Score);
        blackScoreLabel = new JLabel(State.getInstance().getPlayerTwo().getName() + " (BLACK) : " + p2Score);
        whiteScoreLabel.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));
        blackScoreLabel.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
        blackScoreLabel.setFont(new Font("", Font.BOLD, 24));
        whiteScoreLabel.setFont(new Font("", Font.BOLD, 24));
        blackScoreLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        whiteScoreLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        playerInfoPanel.add(whiteScoreLabel);
        playerInfoPanel.add(blackScoreLabel);
        

        gameStatsPanel.add(playerTurnPanel);
        gameStatsPanel.add(playerInfoPanel);
    }

    private void updatePlayerTurnLabel()
    {
        TitledBorder title;
        switch(winner)
        {
            case 0:
                playerTurnLabel.setText("NO WINNER");
                title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Winner");
                title.setTitleJustification(TitledBorder.CENTER);
                playerTurnLabel.setBorder(title);
                break;
            case 1:
                playerTurnLabel.setText(State.getInstance().getPlayerOne().getName() + " WINS!");
                title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Winner");
                title.setTitleJustification(TitledBorder.CENTER);
                playerTurnLabel.setBorder(title);
                break;
            case 2:
                playerTurnLabel.setText(State.getInstance().getPlayerTwo().getName() + " WINS!");
                title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Winner");
                title.setTitleJustification(TitledBorder.CENTER);
                playerTurnLabel.setBorder(title);
                break;
            default:
                if (currentPlayer==1) {
                    playerTurnLabel.setText(State.getInstance().getPlayerOne().getName() + " (WHITE)");
                } else {
                    playerTurnLabel.setText(State.getInstance().getPlayerTwo().getName() + " (BLACK)");
                }
                break;
        }
    }

    private String getOtherDirection(){

        if(moveFacing == "s"){
            return "n";
        }

        return "s";
    }

    private int getOtherPlayer(){

        if(currentPlayer == 1){
            return 2;
        }

        return 1;
    }
    
    private String getOtherPlayerKingVal(){

        if(currentPlayer == 1){
            return "whiteking";
        }

        return "blackking";
    }
//
    private ArrayList available1Dir1TileMoves(CheckersSpace theSpace, String direction){
        ArrayList tempList = new ArrayList();
        if (direction == "s"){
            try{
            	CheckersSpace se1 = (CheckersSpace) getSpace(theSpace.getPosX() + 1, theSpace.getPosY() + 1);
                //GameButton se1 =  getButtonAt(thebutton.getGridRowLoc() + 1, thebutton.getGridColLoc() + 1);
                if(se1 != null && se1.getGamePiece() == null){
                    tempList.add(se1);
                }
            }
            catch(Exception e){
            	//System.out.println(e);
            }


            try{
            	CheckersSpace sw1 = (CheckersSpace) getSpace(theSpace.getPosX() + 1, theSpace.getPosY() - 1);
                //GameButton sw1 =  getButtonAt(thebutton.getGridRowLoc() + 1, thebutton.getGridColLoc() -1);
            	if(sw1 != null && sw1.getGamePiece() == null){
                    tempList.add(sw1);
                }
            }
            catch(Exception e){
            	//System.out.println(e);
            }

        }
        if(direction == "n"){
          try{
        	  CheckersSpace ne1 = (CheckersSpace) getSpace(theSpace.getPosX() - 1, theSpace.getPosY() + 1);
	          //GameButton ne1 =  getButtonAt(thebutton.getGridRowLoc() - 1, thebutton.getGridColLoc() + 1);
	          if(ne1 != null && ne1.getGamePiece() == null){
	              tempList.add(ne1);
	          }
	      }
	      catch(Exception e){
	    	  //System.out.println(e);
	      }
          
          
	        try{
	        	
	        	CheckersSpace nw1 = (CheckersSpace) getSpace(theSpace.getPosX() - 1, theSpace.getPosY() - 1);
		          //GameButton ne1 =  getButtonAt(thebutton.getGridRowLoc() - 1, thebutton.getGridColLoc() + 1);
		          if(nw1 != null && nw1.getGamePiece() == null){
		              tempList.add(nw1);
		          }
	        }
	        catch(Exception e){
	        	//System.out.println(e);
	        }
        }
        return tempList;
        
    }

    private ArrayList available1Dir2TileMoves(CheckersSpace theSpace, String direction){
      ArrayList tempList = new ArrayList();
      int otherPlayer = getOtherPlayer();
      String otherKing = getOtherPlayerKingVal();
      
	    if (direction == "s"){
		    try{
	        	CheckersSpace se1 = (CheckersSpace) getSpace(theSpace.getPosX() + 1, theSpace.getPosY() + 1);
	        	CheckersSpace se2 = (CheckersSpace) getSpace(theSpace.getPosX() + 2, theSpace.getPosY() + 2);
	        	if(se1.getGamePiece() != null) {
	        		if(se2.getGamePiece() == null && (se1.getGamePiece().getOwnerNum() == otherPlayer || se1.getGamePiece().getName() == otherKing)){
			            tempList.add(se2);
			        }
	        	}
		
		        
		    }catch(Exception e){
		    	//System.out.println(e);
		    }
		
		    try {
		    	CheckersSpace sw1 = (CheckersSpace) getSpace(theSpace.getPosX() + 1, theSpace.getPosY() - 1);
	        	CheckersSpace sw2 = (CheckersSpace) getSpace(theSpace.getPosX() + 2, theSpace.getPosY() - 2);
	        	if(sw1.getGamePiece() != null) {
	        		if(sw2 != null && (sw1.getGamePiece().getOwnerNum() == otherPlayer || sw1.getGamePiece().getName() == otherKing) && sw2.getGamePiece() == null){
			            tempList.add(sw2);
			        }
	        	}
	        	
		    }catch(Exception e){
		    	//System.out.println(e);
		    }
		
		
		}
	    if(direction == "n"){
	      try{
	    	  CheckersSpace ne1 = (CheckersSpace) getSpace(theSpace.getPosX() - 1, theSpace.getPosY() + 1);
	    	  CheckersSpace ne2 = (CheckersSpace) getSpace(theSpace.getPosX() - 2, theSpace.getPosY() + 2);
	    	  if(ne1.getGamePiece() != null) {
	    		  if(ne2 != null && (ne1.getGamePiece().getOwnerNum() == otherPlayer || ne1.getGamePiece().getName() == otherKing) && ne2.getGamePiece() == null){
			            tempList.add(ne2);
			       }
	    	  }
	    	  
	      }
	      catch(Exception e){
	    	  //System.out.println(e);
	      }
	      
        try{
        	CheckersSpace nw1 = (CheckersSpace) getSpace(theSpace.getPosX() - 1, theSpace.getPosY() - 1);
	    	CheckersSpace nw2 = (CheckersSpace) getSpace(theSpace.getPosX() - 2, theSpace.getPosY() - 2);
        	
	    	if(nw1.getGamePiece() != null) {
	    		if(nw2 != null && (nw1.getGamePiece().getOwnerNum() == otherPlayer || nw1.getGamePiece().getName() == otherKing) && nw2.getGamePiece() == null){
		            tempList.add(nw2);
		       }
	    	}
	    	
	
	    }
		catch(Exception e){
		    	//System.out.println(e);
		    }

        	
        }
	    return tempList;
    	
    }
        

    private void updateScore(){
        int b = 0;
        int w = 0;
        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {
                CheckersSpace theSpace = (CheckersSpace) getSpace(i, j);
                GamePiece thePiece = theSpace.getGamePiece();
                
                if(thePiece != null) {
                	if(thePiece.getOwnerNum() == 1){
                        w+=1;
                    }
                    if(thePiece.getOwnerNum() == 2){
                        b+=1;
                    }
                }
                
            }
        }

        p1Score = w;
        p2Score = b;
        whiteScoreLabel.setText(State.getInstance().getPlayerOne().getName() + " (WHITE) : " + p1Score);
        blackScoreLabel.setText(State.getInstance().getPlayerOne().getName() + " (BLACK) : " + p2Score);
        updatePlayerTurnLabel();
    }

    private void checkForOverWritesBasic(CheckersSpace spaceClicked, String direction){
        if(currPressedSpace.getPosY() + 1 < spaceClicked.getPosY()){
            //Going East

            if(direction == "s"){
                try{
                	
                	CheckersSpace overWriteSpace = (CheckersSpace) getSpace(currPressedSpace.getPosX() + 1, currPressedSpace.getPosY() + 1);
                	overWriteSpace.clearGamePiece();
                	itemchanged = true;
                }catch(Exception e){

                }
            }else if(direction == "n"){
                try{
                	CheckersSpace overWriteSpace = (CheckersSpace) getSpace(currPressedSpace.getPosX() - 1, currPressedSpace.getPosY() + 1);
                	overWriteSpace.clearGamePiece();
                	itemchanged = true;

                }catch(Exception e){

                }



            }
        }else if (currPressedSpace.getPosY() - 1 > spaceClicked.getPosY()){
        	//currPressedSpace.getPosY() + 1 < spaceClicked.getPosY()
        	//Going West
          if(direction == "s"){
              try{
            	  CheckersSpace overWriteSpace = (CheckersSpace) getSpace(currPressedSpace.getPosX() + 1, currPressedSpace.getPosY() - 1);
	              overWriteSpace.clearGamePiece();
	              itemchanged = true;
              }catch(Exception e){

              }



          }else if(direction == "n"){
              try{
            	  CheckersSpace overWriteSpace = (CheckersSpace) getSpace(currPressedSpace.getPosX() - 1, currPressedSpace.getPosY() - 1);
	              overWriteSpace.clearGamePiece();
	              itemchanged = true;
              }catch(Exception e){

              }


          }
        	
        }
    	
    	
    }


    private void checkForOverWritesKing(CheckersSpace spaceClicked){
        checkForOverWritesBasic( spaceClicked, "n");
        checkForOverWritesBasic( spaceClicked, "s");


    }

    
    private void movePieceToButton(CheckersSpace currAvailSpace){
        
    	GamePiece thePiece = currPressedSpace.getGamePiece();
    	currAvailSpace.setGamePiece(thePiece);
        currPressedSpace.clearGamePiece();
        currPressedSpace.setSelected(false);
        currPressedSpace.setBgColor(currPressedSpace.getOriginalColor());
        
        setSpace(currAvailSpace.getPosX(), currAvailSpace.getPosY(), currAvailSpace);
        setSpace(currPressedSpace.getPosX(), currPressedSpace.getPosY(), currPressedSpace);
        
    }
    
    private void clearPrevMoveState(){
        currentPossibleMoves = new ArrayList();
        currPressedSpace = null;
        item_selected = false;
    }

    private void toggleAvailHint(ArrayList<CheckersSpace> thespaces){
        for(int ind = 0; ind < thespaces.size(); ind++ ){
            thespaces.get(ind).mark(Color.decode("#57BF24"));
        }

    }
    private void unToggleAvailHint(ArrayList<CheckersSpace> thespaces){
        for(int ind = 0; ind < thespaces.size(); ind++ ){
            thespaces.get(ind).unmark();
        }

    }

    private Color _colorChanger(Color currColor){
        Color tColor = currColor;
        if(tColor == Color.RED){
            tColor = Color.decode("#191919");
        }else {
            tColor = Color.RED;
        }

        return tColor;
    }


	public void actionPerformed(ActionEvent e) {
        CheckersSpace currSpaceClicked = (CheckersSpace) e.getSource();
        GamePiece currGamePiece = currSpaceClicked.getGamePiece();

        //Check to see is there is an item selected and item that was clicked now is the button that was selected,
        // then deselect the button and put item_selected = false;
        if(currGamePiece != null && item_selected == true && currGamePiece.getOwnerNum() == currentPlayer) {
            currSpaceClicked.setBgColor(currSpaceClicked.getOriginalColor());
            currSpaceClicked.setSelected(false);
            unToggleAvailHint(currentPossibleMoves);
            item_selected = false;
            currPressedSpace = null;
            return;
        }
        if(currGamePiece != null && item_selected == false && currGamePiece.getOwnerNum() == currentPlayer) {
            item_selected = true;
            currSpaceClicked.setSelected(true);
            currSpaceClicked.setBgColor(Color.BLUE);
            String pieceName = currGamePiece.getName();
            currentPossibleMoves = new ArrayList<Checkers>();

            if(pieceName == "black" || pieceName == "white") {
                currentPossibleMoves.addAll(available1Dir1TileMoves(currSpaceClicked, moveFacing));
                currentPossibleMoves.addAll(available1Dir2TileMoves(currSpaceClicked, moveFacing));
                currPressedSpace = currSpaceClicked;

            }
            if(pieceName == "blackking" || pieceName == "whiteking") {
                //Calculate the possible moves when the user clicks the first piece

                currentPossibleMoves.addAll(available1Dir1TileMoves(currSpaceClicked, "n"));
                currentPossibleMoves.addAll(available1Dir2TileMoves(currSpaceClicked, "n"));


                currentPossibleMoves.addAll(available1Dir1TileMoves(currSpaceClicked, "s"));
                currentPossibleMoves.addAll(available1Dir2TileMoves(currSpaceClicked, "s"));
                currPressedSpace = currSpaceClicked;
            }
            toggleAvailHint(currentPossibleMoves);
            return;
        }


        //Check to see if there is an item selected, then check to see if the button they press was not the selected one and it is a valid move
        //this creates an actual move
        for(int a = 0; a < currentPossibleMoves.size();a++){
            CheckersSpace currSpaceOption = (CheckersSpace)(currentPossibleMoves.get(a));
            if(item_selected == true && currSpaceClicked.getGamePiece() == null
                    && currSpaceOption.getPosX() == currSpaceClicked.getPosX()
                    && currSpaceOption.getPosY() == currSpaceClicked.getPosY()){


                if((currPressedSpace.getGamePiece().getName()).equals("white") && currSpaceClicked.getPosX() == rows -1){
                    //White made it to the bottom of the board, King white

                    currPressedSpace.setGamePiece(new CheckersPieceWhiteKing());

                }
                else if((currPressedSpace.getGamePiece().getName()).equals("black") && currSpaceClicked.getPosX() == 0){
                    currPressedSpace.setGamePiece(new CheckersPieceBlackKing());
                    //Black made it to the top, king black
                }
                //This is a legal move!
                //Do all of the changes to make the next move
                if ((currPressedSpace.getGamePiece().getName()).equals("white") || (currPressedSpace.getGamePiece().getName()).equals("black")){
                    //Find out who is eaten
                    checkForOverWritesBasic(currSpaceClicked, moveFacing );
                    movePieceToButton(currSpaceClicked);
                    unToggleAvailHint(currentPossibleMoves);
                    clearPrevMoveState();

                    if(itemchanged == true){
                        ArrayList newOptions = available1Dir2TileMoves(currSpaceClicked, moveFacing);
                        if(newOptions.size() != 0){
                            currentPlayer = getOtherPlayer();
                            moveFacing = getOtherDirection();
                        }
                        itemchanged = false;
                    }


                    currentPlayer  = getOtherPlayer();
                    moveFacing = getOtherDirection();
                }
                else if ((currPressedSpace.getGamePiece().getName()).equals("whiteking") || (currPressedSpace.getGamePiece().getName()).equals("blackking")){
                    checkForOverWritesKing(currSpaceClicked);
                    movePieceToButton(currSpaceClicked);
                    unToggleAvailHint(currentPossibleMoves);
                    clearPrevMoveState();

                    if(itemchanged == true){
                        ArrayList newOptionsN = available1Dir2TileMoves(currSpaceClicked, "n");
                        ArrayList newOptionsS = available1Dir2TileMoves(currSpaceClicked, "s");
                        if(newOptionsN.size() != 0 || newOptionsS.size() != 0){
                            currentPlayer = getOtherPlayer();
                            moveFacing = getOtherDirection();
                        }
                        itemchanged = false;
                    }


                    currentPlayer  = getOtherPlayer();
                    moveFacing = getOtherDirection();

                }

                //Check to see if game is over
                updateScore();
                if(p1Score == 0){
                    winner = 2;
                    state.getPlayerTwo().incrementWins();
                    state.getPlayerOne().incrementLosses();
                    updatePlayerTurnLabel();
                    gui.gameOver();
                }else if(p2Score == 0){
                    winner = 1;
					state.getPlayerOne().incrementWins();
					state.getPlayerTwo().incrementLosses();
                    updatePlayerTurnLabel();
                    gui.gameOver();
                }

                return;
            }

        }
	}


}