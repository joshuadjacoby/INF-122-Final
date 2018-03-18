import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Checkers extends GameBoard {

    private int currentPlayer = 1; //1 == W,   2 == B
    private ArrayList currentPossibleMoves;
    private CheckersSpace currPressedSpace; // currPressedBtn
    private boolean itemchanged = false;

    private String moveFacing = "s";
    private String defaultItem = "";
    private boolean item_selected = false;
    private GridGameMoveLogic gridGameMoveLogic;

    private int p1Score = 12;
    private int p2Score = 12;

    Checkers (int row, int col, int starter){
        super(row, col);

        setLayout(new GridLayout(row,col));
        initializeButtons();
        currentPlayer = starter;
        currentPossibleMoves = new ArrayList();
        gridGameMoveLogic = new GridGameMoveLogic<String>(row, col, defaultItem);


    }

//    public boolean checkForWin()
//    {
//        //Change
//        return false;
//    }
//
    public void resetButtons()
    {
    	initializeButtons();
    
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
            	System.out.println(e);
            }


            try{
            	CheckersSpace sw1 = (CheckersSpace) getSpace(theSpace.getPosX() + 1, theSpace.getPosY() - 1);
                //GameButton sw1 =  getButtonAt(thebutton.getGridRowLoc() + 1, thebutton.getGridColLoc() -1);
            	if(sw1 != null && sw1.getGamePiece() == null){
                    tempList.add(sw1);
                }
            }
            catch(Exception e){
            	System.out.println(e);
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
	    	  System.out.println(e);
	      }
          
          
	        try{
	        	
	        	CheckersSpace nw1 = (CheckersSpace) getSpace(theSpace.getPosX() - 1, theSpace.getPosY() - 1);
		          //GameButton ne1 =  getButtonAt(thebutton.getGridRowLoc() - 1, thebutton.getGridColLoc() + 1);
		          if(nw1 != null && nw1.getGamePiece() == null){
		              tempList.add(nw1);
		          }
	        }
	        catch(Exception e){
	        	System.out.println(e);
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

//		        GameButton se1 =  getButtonAt(thebutton.getGridRowLoc() + 1, thebutton.getGridColLoc() + 1);
//		        GameButton se2 =  getButtonAt(thebutton.getGridRowLoc() + 2, thebutton.getGridColLoc() + 2);
	        	if(se1.getGamePiece() != null) {
	        		if(se2.getGamePiece() == null && (se1.getGamePiece().getOwnerNum() == otherPlayer || se1.getGamePiece().getName() == otherKing)){
			            tempList.add(se2);
			        }
	        	}
		
		        
		    }catch(Exception e){
		    	System.out.println(e);
		    }
		
		    try {
		    	CheckersSpace sw1 = (CheckersSpace) getSpace(theSpace.getPosX() + 1, theSpace.getPosY() - 1);
	        	CheckersSpace sw2 = (CheckersSpace) getSpace(theSpace.getPosX() + 2, theSpace.getPosY() - 2);
	        	
//		        GameButton sw1 = getButtonAt(thebutton.getGridRowLoc() + 1, thebutton.getGridColLoc() - 1);
//		        GameButton sw2 = getButtonAt(thebutton.getGridRowLoc() + 2, thebutton.getGridColLoc() - 2);
	        	if(sw1.getGamePiece() != null) {
	        		if(sw2 != null && (sw1.getGamePiece().getOwnerNum() == otherPlayer || sw1.getGamePiece().getName() == otherKing) && sw2.getGamePiece() == null){
			            tempList.add(sw2);
			        }
	        	}
	        	
		    }catch(Exception e){
		    	System.out.println(e);
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
	    	  System.out.println(e);
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
		    	System.out.println(e);
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
    }

    private void checkForOverWritesBasic(CheckersSpace spaceClicked, String direction){
        if(currPressedSpace.getPosY() + 1 < spaceClicked.getPosY()){
            //Going East

            if(direction == "s"){
//                try{
                	
                	CheckersSpace overWriteSpace = (CheckersSpace) getSpace(currPressedSpace.getPosX() + 1, currPressedSpace.getPosY() + 1);
                	overWriteSpace.clearGamePiece();
                	itemchanged = true;
                	
//                    ((GameButton<String>)gameBoard[currPressedBtn.getGridRowLoc() + 1][currPressedBtn.getGridColLoc() + 1]).setButtonValue("");
//                    gameBoard[currPressedBtn.getGridRowLoc() + 1][currPressedBtn.getGridColLoc() + 1].setText("");
//                    ((GameButton<String>)gameBoard[currPressedBtn.getGridRowLoc() + 1][currPressedBtn.getGridColLoc() + 1]).setOwner("");
                    
//                }catch(Exception e){
//
//                }
            }else if(direction == "n"){
//                try{
                	CheckersSpace overWriteSpace = (CheckersSpace) getSpace(currPressedSpace.getPosX() - 1, currPressedSpace.getPosY() + 1);
                	overWriteSpace.clearGamePiece();
                	itemchanged = true;
                	
//                    ((GameButton<String>)gameBoard[currPressedBtn.getGridRowLoc() - 1][currPressedBtn.getGridColLoc() + 1]).setButtonValue("");
//                    gameBoard[currPressedBtn.getGridRowLoc() - 1][currPressedBtn.getGridColLoc() + 1].setText("");
//                    ((GameButton<String>)gameBoard[currPressedBtn.getGridRowLoc() - 1][currPressedBtn.getGridColLoc() + 1]).setOwner("");
                    
//                }catch(Exception e){
//
//                }



            }
        }else if (currPressedSpace.getPosY() - 1 > spaceClicked.getPosY()){
        	//currPressedSpace.getPosY() + 1 < spaceClicked.getPosY()
        	//Going West
          if(direction == "s"){
//              try{
            	  CheckersSpace overWriteSpace = (CheckersSpace) getSpace(currPressedSpace.getPosX() + 1, currPressedSpace.getPosY() - 1);
	              overWriteSpace.clearGamePiece();
	              itemchanged = true;
//                  ((GameButton<String>)gameBoard[currPressedBtn.getGridRowLoc() + 1][currPressedBtn.getGridColLoc() - 1]).setButtonValue("");
//                  gameBoard[currPressedBtn.getGridRowLoc() + 1][currPressedBtn.getGridColLoc() - 1].setText("");
//                  ((GameButton<String>)gameBoard[currPressedBtn.getGridRowLoc() + 1][currPressedBtn.getGridColLoc() - 1]).setOwner("");
//                  itemchanged = true;
//              }catch(Exception e){
//
//              }



          }else if(direction == "n"){
//              try{
            	  CheckersSpace overWriteSpace = (CheckersSpace) getSpace(currPressedSpace.getPosX() - 1, currPressedSpace.getPosY() - 1);
	              overWriteSpace.clearGamePiece();
	              itemchanged = true;
	              
//                  ((GameButton<String>)gameBoard[currPressedBtn.getGridRowLoc() - 1][currPressedBtn.getGridColLoc() - 1]).setButtonValue("");
//                  gameBoard[currPressedBtn.getGridRowLoc() - 1][currPressedBtn.getGridColLoc() - 1].setText("");
//                  ((GameButton<String>)gameBoard[currPressedBtn.getGridRowLoc() - 1][currPressedBtn.getGridColLoc() - 1]).setOwner("");
//                  itemchanged = true;
//              }catch(Exception e){
//
//              }


          }
        	
        }
    	
    	
    }


    private void checkForOverWritesKing(CheckersSpace spaceClicked){
        checkForOverWritesBasic( spaceClicked, "n");
        checkForOverWritesBasic( spaceClicked, "s");


    }

    
    private void movePeiceToButton(CheckersSpace currAvailSpace){
        
    	GamePiece thePiece = currPressedSpace.getGamePiece();
    	currAvailSpace.setGamePiece(thePiece);
        currPressedSpace.clearGamePiece();
        currPressedSpace.setSelected(false);
        currPressedSpace.setBgColor(currPressedSpace.getOriginalColor());
        
        setSpace(currAvailSpace.getPosX(), currAvailSpace.getPosY(), currAvailSpace);
        setSpace(currPressedSpace.getPosX(), currPressedSpace.getPosY(), currPressedSpace);

        
        
        
        
//        gameBoard[currPressedBtn.getGridRowLoc()][currPressedBtn.getGridColLoc()].setText("");
//        Color acolor = ((GameButton<String>)gameBoard[currPressedBtn.getGridRowLoc()][currPressedBtn.getGridColLoc()]).getoriginalColor();
//        gameBoard[currPressedBtn.getGridRowLoc()][currPressedBtn.getGridColLoc()].setBackground(acolor);
//        ((GameButton<String>)gameBoard[currPressedBtn.getGridRowLoc()][currPressedBtn.getGridColLoc()]).setButtonValue("");
//        ((GameButton<String>)gameBoard[currPressedBtn.getGridRowLoc()][currPressedBtn.getGridColLoc()]).setOwner("");
//        gameBoard[currPressedBtn.getGridRowLoc()][currPressedBtn.getGridColLoc()].setSelected(false);

    }
    
    private void clearPrevMoveState(){
        currentPossibleMoves = new ArrayList();
        currPressedSpace = null;
        item_selected = false;
    }

        
    private class buttonListener implements ActionListener
    {
    	
        public void actionPerformed(ActionEvent e){
        	CheckersSpace currSpaceClicked = (CheckersSpace) e.getSource();
        	GamePiece currGamePiece = currSpaceClicked.getGamePiece();
        	
//          GameButton buttonClicked = (GameButton)e.getSource();
//          System.out.println(buttonClicked.getButtonValue() + "  " + buttonClicked.getGridRowLoc() + "    " + buttonClicked.getGridColLoc());

        	

        	
        	
          //Check to see is there is an item selected and item that was clicked now is the button that was selected,
          // then deselect the button and put item_selected = false;
        	
        	if(currGamePiece != null && item_selected == true && currGamePiece.getOwnerNum() == currentPlayer && currSpaceClicked.isSelected()) {
        		currSpaceClicked.setBgColor(currSpaceClicked.getOriginalColor());
        		currSpaceClicked.setSelected(false);
        		item_selected = false;
        		currPressedSpace = null;
        		return;
//              if(item_selected == true && (buttonClicked.getOwner()).equals(currentPlayer)  && buttonClicked.isSelected() ){
//              buttonClicked.setBackground(buttonClicked.getoriginalColor());
//              buttonClicked.setSelected(false);
//              item_selected = false;
//              currPressedBtn = null;
//              return;
//          }
        	}
        	if(currGamePiece != null && item_selected == false && currGamePiece.getOwnerNum() == currentPlayer) {
        		item_selected = true;
        		currSpaceClicked.setSelected(true);
        		currSpaceClicked.setBgColor(Color.BLUE);
        		String pieceName = currGamePiece.getName();
        		
        		if(pieceName == "black" || pieceName == "white") {
                  currentPossibleMoves.addAll(available1Dir1TileMoves(currSpaceClicked, moveFacing));
                  currentPossibleMoves.addAll(available1Dir2TileMoves(currSpaceClicked, moveFacing));
                  currPressedSpace = currSpaceClicked;
        			
        		}
        		if(pieceName == "blackking" || pieceName == "whiteking") {
                  //Calculate the possible moves when the user clicks the first piece'
                  currentPossibleMoves.addAll(available1Dir1TileMoves(currSpaceClicked, "n"));
                  currentPossibleMoves.addAll(available1Dir2TileMoves(currSpaceClicked, "n"));


                  currentPossibleMoves.addAll(available1Dir1TileMoves(currSpaceClicked, "s"));
                  currentPossibleMoves.addAll(available1Dir2TileMoves(currSpaceClicked, "s"));
                  currPressedSpace = currSpaceClicked;
        		}
        		
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

                    	currPressedSpace.getGamePiece().setName("whiteking");

                    }
                    else if((currPressedSpace.getGamePiece().getName()).equals("black") && currSpaceClicked.getPosX() == 0){
                    	currPressedSpace.getGamePiece().setName("blackking");
                        //Black made it to the top, king black
                    }
                    //This is a legal move!
                    //Do all of the changes to make the next move
                    if ((currPressedSpace.getGamePiece().getName()).equals("white") || (currPressedSpace.getGamePiece().getName()).equals("black")){
                        //Find out who is eaten
                        checkForOverWritesBasic(currSpaceClicked, moveFacing );
                        movePeiceToButton(currSpaceClicked);
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
                        movePeiceToButton(currSpaceClicked);
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
                        JOptionPane.showConfirmDialog(null, "Game Over. Player 1 wins");
                    }else if(p2Score == 0){
                        JOptionPane.showConfirmDialog(null, "Game Over. Player 2 wins");
                    }

                    return;
                }

            }
        }
    }

    @Override
    protected void initializeButtons() {
        Color theColor = Color.RED;
        Color placeOnColor = Color.GRAY;


        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                if( theColor == placeOnColor && i >= 0 && i <= 2){

                    setSpace(i,j,  new CheckersSpace(i,j,"", new CheckersPieceWhite()));
//                    gameBoard[i][j] = new GameButton<String>(i,j,"w", "w" );
//                    gameBoard[i][j].setText("w");
//                    ((GameButton)gameBoard[i][j]).setOwner("w");

                }
                else if(theColor == placeOnColor && i >= 5 && i <= 7){
                    setSpace(i,j ,  new CheckersSpace(i,j,"", new CheckersPieceBlack()));

//                    gameBoard[i][j] = new GameButton<String>(i,j, "b", "w");
//                    gameBoard[i][j].setText("b");
//                    ((GameButton)gameBoard[i][j]).setOwner("b");

                }
                else{
                    setSpace(i,j , new CheckersSpace(i,j,""));
//                    gameBoard[i][j] = new GameButton<String>(i,j,"", "");
//                    gameBoard[i][j].setText("");
                }
                getSpace(i,j).addActionListener(new Checkers.buttonListener());
                getSpace(i,j).setBgColor(theColor);
                getSpace(i,j).setOriginalColor(theColor);

                theColor = _colorChanger(theColor);
                add(getSpace(i,j));
            }
            theColor = _colorChanger(theColor);


        }


    }

    private Color _colorChanger(Color currColor){
        Color tColor = currColor;
        if(tColor == Color.RED){
            tColor = Color.GRAY;
        }else {
            tColor = Color.RED;
        }

        return tColor;
    }


}
