import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Checkers extends GameBoard {

    private String currentPlayer = "w"; //1 == W,   2 == B
    private ArrayList currentPossibleMoves;
    private GameButton currPressedBtn;
    private boolean itemchanged = false;

    private String moveFacing = "s";
    private String defaultItem = "";
    private boolean item_selected = false;
    private GridGameMoveLogic gridGameMoveLogic;

    private int p1Score = 12;
    private int p2Score = 12;

    Checkers (int row, int col, String starter){
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
//    public void resetButtons()
//    {
//        //Change
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//
//                gameBoard[i][j].setText("");
//            }
//        }
//    }
//
//    private String getOtherDirection(){
//
//        if(moveFacing == "s"){
//            return "n";
//        }
//
//        return "s";
//    }
//
//    private String getOtherPlayer(){
//
//        if(currentPlayer == "w"){
//            return "b";
//        }
//
//        return "w";
//    }
//    private String getOtherPlayerKing(){
//
//        if(currentPlayer == "w"){
//            return "bk";
//        }
//
//        return "wk";
//    }
//
//    private ArrayList available1Dir1TileMoves(GameButton thebutton, String direction){
//        ArrayList tempList = new ArrayList();
//        if (direction == "s"){
//            try{
//                GameButton se1 =  getButtonAt(thebutton.getGridRowLoc() + 1, thebutton.getGridColLoc() + 1);
//                if(se1 != null && se1.getButtonValue() == defaultItem){
//                    tempList.add(se1);
//                }
//            }
//            catch(Exception e){
//            }
//
//
//            try{
//                GameButton sw1 =  getButtonAt(thebutton.getGridRowLoc() + 1, thebutton.getGridColLoc() -1);
//                if(sw1 != null && sw1.getButtonValue() == defaultItem){
//                    tempList.add(sw1);
//                }}
//            catch(Exception e){
//
//            }
//
//
//
//        }
//        if(direction == "n"){
//            try{
//                GameButton ne1 =  getButtonAt(thebutton.getGridRowLoc() - 1, thebutton.getGridColLoc() + 1);
//                if(ne1 != null && ne1.getButtonValue() == defaultItem){
//                    tempList.add(ne1);
//                }
//            }
//            catch(Exception e){
//
//            }
//
//            try{
//
//                GameButton nw1 =  getButtonAt(thebutton.getGridRowLoc() - 1, thebutton.getGridColLoc() -1);
//                if(nw1 != null && nw1.getButtonValue() == defaultItem){
//                    tempList.add(nw1);
//                }
//            }
//            catch(Exception e){
//
//            }
//
//
//        }
//
//        return tempList;
//    }
//
//    private ArrayList available1Dir2TileMoves(GameButton thebutton, String direction){
//        ArrayList tempList = new ArrayList();
//        String otherPlayer = getOtherPlayer();
//        String otherKing = getOtherPlayerKing();
//
//        if (direction == "s"){
//            try{
//                GameButton se1 =  getButtonAt(thebutton.getGridRowLoc() + 1, thebutton.getGridColLoc() + 1);
//                GameButton se2 =  getButtonAt(thebutton.getGridRowLoc() + 2, thebutton.getGridColLoc() + 2);
//
//                if(se2 != null && (se1.getButtonValue() == otherPlayer || se1.getButtonValue() == otherKing) && se2.getButtonValue() == defaultItem){
//                    tempList.add(se2);
//                }
//            }catch(Exception e){
//
//            }
//
//            try {
//                GameButton sw1 = getButtonAt(thebutton.getGridRowLoc() + 1, thebutton.getGridColLoc() - 1);
//                GameButton sw2 = getButtonAt(thebutton.getGridRowLoc() + 2, thebutton.getGridColLoc() - 2);
//
//                if (sw2 != null && (sw1.getButtonValue() == otherPlayer || sw1.getButtonValue() == otherKing) && sw2.getButtonValue() == defaultItem) {
//                    tempList.add(sw2);
//
//                }
//            }catch(Exception e){
//
//            }
//
//
//        }
//        if(direction == "n"){
//            try{
//                GameButton ne1 =  getButtonAt(thebutton.getGridRowLoc() - 1, thebutton.getGridColLoc() + 1);
//                GameButton ne2 =  getButtonAt(thebutton.getGridRowLoc() - 2, thebutton.getGridColLoc() + 2);
//                if(ne2 != null && (ne1.getButtonValue() == otherPlayer || ne1.getButtonValue() == otherKing)&& ne2.getButtonValue() == defaultItem){
//                    tempList.add(ne2);
//                }
//            }
//            catch(Exception e){}
//
//
//
//            try{
//                GameButton nw1 =  getButtonAt(thebutton.getGridRowLoc() - 1, thebutton.getGridColLoc() -1);
//                GameButton nw2 =  getButtonAt(thebutton.getGridRowLoc() - 2, thebutton.getGridColLoc() - 2);
//
//                if(nw2 != null && (nw1.getButtonValue() == otherPlayer || nw1.getButtonValue() == otherKing) && nw2.getButtonValue() == defaultItem){
//                    tempList.add(nw2);
//
//                }
//
//            }
//            catch(Exception e){}
//
//        }
//
//        return tempList;
//    }
//
//    private void updateScore(){
//        int b = 0;
//        int w = 0;
//        for (int i = 0; i < rows; i++) {
//
//            for (int j = 0; j < cols; j++) {
//                GameButton theButton =(GameButton<String>)gameBoard[i][j];
//                if(theButton.getOwner() == "w"){
//                    w+=1;
//                }
//                if(theButton.getOwner() == "b"){
//                    b+=1;
//                }
//            }
//        }
//
//        p1Score = w;
//        p2Score = b;
//    }
//
//    private void checkForOverWritesBasic(GameButton buttonClicked, String direction){
//        if(currPressedBtn.getGridColLoc() + 1 < buttonClicked.getGridColLoc()){
//            //Going East
//
//            if(direction == "s"){
//                try{
//                    ((GameButton<String>)gameBoard[currPressedBtn.getGridRowLoc() + 1][currPressedBtn.getGridColLoc() + 1]).setButtonValue("");
//                    gameBoard[currPressedBtn.getGridRowLoc() + 1][currPressedBtn.getGridColLoc() + 1].setText("");
//                    ((GameButton<String>)gameBoard[currPressedBtn.getGridRowLoc() + 1][currPressedBtn.getGridColLoc() + 1]).setOwner("");
//                    itemchanged = true;
//                }catch(Exception e){
//
//                }
//
//
//
//
//            }else if(direction == "n"){
//                try{
//                    ((GameButton<String>)gameBoard[currPressedBtn.getGridRowLoc() - 1][currPressedBtn.getGridColLoc() + 1]).setButtonValue("");
//                    gameBoard[currPressedBtn.getGridRowLoc() - 1][currPressedBtn.getGridColLoc() + 1].setText("");
//                    ((GameButton<String>)gameBoard[currPressedBtn.getGridRowLoc() - 1][currPressedBtn.getGridColLoc() + 1]).setOwner("");
//                    itemchanged = true;
//                }catch(Exception e){
//
//                }
//
//
//
//            }
//        }else if (currPressedBtn.getGridColLoc()- 1 > buttonClicked.getGridColLoc()){
//            //Going West
//            if(direction == "s"){
//                try{
//                    ((GameButton<String>)gameBoard[currPressedBtn.getGridRowLoc() + 1][currPressedBtn.getGridColLoc() - 1]).setButtonValue("");
//                    gameBoard[currPressedBtn.getGridRowLoc() + 1][currPressedBtn.getGridColLoc() - 1].setText("");
//                    ((GameButton<String>)gameBoard[currPressedBtn.getGridRowLoc() + 1][currPressedBtn.getGridColLoc() - 1]).setOwner("");
//                    itemchanged = true;
//                }catch(Exception e){
//
//                }
//
//
//
//            }else if(direction == "n"){
//                try{
//                    ((GameButton<String>)gameBoard[currPressedBtn.getGridRowLoc() - 1][currPressedBtn.getGridColLoc() - 1]).setButtonValue("");
//                    gameBoard[currPressedBtn.getGridRowLoc() - 1][currPressedBtn.getGridColLoc() - 1].setText("");
//                    ((GameButton<String>)gameBoard[currPressedBtn.getGridRowLoc() - 1][currPressedBtn.getGridColLoc() - 1]).setOwner("");
//                    itemchanged = true;
//                }catch(Exception e){
//
//                }
//
//
//            }
//        }
//    }
//
//    private void checkForOverWritesKing(GameButton buttonClicked){
//        checkForOverWritesBasic( buttonClicked, "n");
//        checkForOverWritesBasic( buttonClicked, "s");
//
//
//    }
//
//
//        private void movePeiceToButton(GameButton currAvailBtn){
//        String nextVal = (String) currPressedBtn.getButtonValue();
//
//
//        //The value should be depending on where they are placed
//        ((GameButton<String>)gameBoard[currAvailBtn.getGridRowLoc()][currAvailBtn.getGridColLoc()]).setButtonValue(nextVal);
//
//        String theText = ((GameButton<String>)gameBoard[currAvailBtn.getGridRowLoc()][currAvailBtn.getGridColLoc()]).getButtonValue();
//        gameBoard[currAvailBtn.getGridRowLoc()][currAvailBtn.getGridColLoc()].setText(theText);
//        ((GameButton<String>)gameBoard[currAvailBtn.getGridRowLoc()][currAvailBtn.getGridColLoc()]).setOwner(currentPlayer);
//
//
//
//        gameBoard[currPressedBtn.getGridRowLoc()][currPressedBtn.getGridColLoc()].setText("");
//        Color acolor = ((GameButton<String>)gameBoard[currPressedBtn.getGridRowLoc()][currPressedBtn.getGridColLoc()]).getoriginalColor();
//        gameBoard[currPressedBtn.getGridRowLoc()][currPressedBtn.getGridColLoc()].setBackground(acolor);
//        ((GameButton<String>)gameBoard[currPressedBtn.getGridRowLoc()][currPressedBtn.getGridColLoc()]).setButtonValue("");
//        ((GameButton<String>)gameBoard[currPressedBtn.getGridRowLoc()][currPressedBtn.getGridColLoc()]).setOwner("");
//        gameBoard[currPressedBtn.getGridRowLoc()][currPressedBtn.getGridColLoc()].setSelected(false);
//
//
//        //Check to see if more possible moves. If not then do all of the player changing stuff;
//
//    }
//
//    private void clearPrevMoveState(){
//        currentPossibleMoves = new ArrayList();
//        currPressedBtn = null;
//        item_selected = false;
//    }
//
//
//
    private class buttonListener implements ActionListener
    {
    	
        public void actionPerformed(ActionEvent e){
        	
        }
//            GameButton buttonClicked = (GameButton)e.getSource();
//            System.out.println(buttonClicked.getButtonValue() + "  " + buttonClicked.getGridRowLoc() + "    " + buttonClicked.getGridColLoc());
//
//
//            //Check to see is there is an item selected and item that was clicked now is the button that was selected,
//            // then deselect the button and put item_selected = false;
//            if(item_selected == true && (buttonClicked.getOwner()).equals(currentPlayer)  && buttonClicked.isSelected() ){
//                buttonClicked.setBackground(buttonClicked.getoriginalColor());
//                buttonClicked.setSelected(false);
//                item_selected = false;
//                currPressedBtn = null;
//                return;
//            }
//
//            //If nothing was selected then select a basic game peice
//            if(item_selected == false && (buttonClicked.getOwner()).equals(currentPlayer) ){
//                item_selected = true;
//                gameBoard[buttonClicked.getGridRowLoc()][buttonClicked.getGridColLoc()].setSelected(true);
//                gameBoard[buttonClicked.getGridRowLoc()][buttonClicked.getGridColLoc()].setBackground(Color.blue);
//
//                if ((buttonClicked.getButtonValue()).equals("w") || (buttonClicked.getButtonValue()).equals("b")){
//                    //Calculate the possible moves when the user clicks the first piece'
//                    currentPossibleMoves.addAll(available1Dir1TileMoves(buttonClicked, moveFacing));
//                    currentPossibleMoves.addAll(available1Dir2TileMoves(buttonClicked, moveFacing));
//                    currPressedBtn = (GameButton) gameBoard[buttonClicked.getGridRowLoc()][buttonClicked.getGridColLoc()];
//                }
//                if ((buttonClicked.getButtonValue()).equals("wk") || (buttonClicked.getButtonValue()).equals("bk")){
//                    //Calculate the possible moves when the user clicks the first piece'
//                    currentPossibleMoves.addAll(available1Dir1TileMoves(buttonClicked, "n"));
//                    currentPossibleMoves.addAll(available1Dir2TileMoves(buttonClicked, "n"));
//
//
//                    currentPossibleMoves.addAll(available1Dir1TileMoves(buttonClicked, "s"));
//                    currentPossibleMoves.addAll(available1Dir2TileMoves(buttonClicked, "s"));
//                    currPressedBtn = (GameButton) gameBoard[buttonClicked.getGridRowLoc()][buttonClicked.getGridColLoc()];
//                }
//                return;
//
//            }
//
//            //Check to see if there is an item selected, then check to see if the button they press was not the selected one and it is a valid move
//            //this creates an actual move
//            for(int a = 0; a < currentPossibleMoves.size();a++){
//                GameButton currAvailBtn = (GameButton)(currentPossibleMoves.get(a));
//                if(item_selected == true && buttonClicked.getButtonValue() == defaultItem
//                        && currAvailBtn.getGridRowLoc() == buttonClicked.getGridRowLoc()
//                        && currAvailBtn.getGridColLoc() == buttonClicked.getGridColLoc()){
//
//
//                    if((currPressedBtn.getButtonValue()).equals("w") && buttonClicked.getGridRowLoc() == rows -1){
//                        //White made it to the bottom of the board, King white
//
//                        currPressedBtn.setButtonValue("wk");
//
//                    }
//                    else if((currPressedBtn.getButtonValue()).equals("b") && buttonClicked.getGridRowLoc() == 0){
//                        currPressedBtn.setButtonValue("bk");
//                        //Black made it to the top, king black
//                    }
//                    //This is a legal move!
//                    //Do all of the changes to make the next move
//                    if ((currPressedBtn.getButtonValue()).equals("w") || (currPressedBtn.getButtonValue()).equals("b")){
//                        //Find out who is eaten
//                        checkForOverWritesBasic(buttonClicked, moveFacing );
//                        movePeiceToButton(currAvailBtn);
//                        clearPrevMoveState();
//
//
//
//
//                        if(itemchanged == true){
//                            ArrayList newOptions = available1Dir2TileMoves(buttonClicked, moveFacing);
//                            if(newOptions.size() != 0){
//                                currentPlayer = getOtherPlayer();
//                                moveFacing = getOtherDirection();
//
//                            }
//                            itemchanged = false;
//                        }
//
//
//                        currentPlayer  = getOtherPlayer();
//                        moveFacing = getOtherDirection();
//                    }
//                    else if ((currPressedBtn.getButtonValue()).equals("wk") || (currPressedBtn.getButtonValue()).equals("bk")){
//                        checkForOverWritesKing(buttonClicked);
//                        movePeiceToButton(currAvailBtn);
//                        clearPrevMoveState();
//
//                        if(itemchanged == true){
//                            ArrayList newOptionsN = available1Dir2TileMoves(buttonClicked, "n");
//                            ArrayList newOptionsS = available1Dir2TileMoves(buttonClicked, "s");
//                            if(newOptionsN.size() != 0 || newOptionsS.size() != 0){
//                                currentPlayer = getOtherPlayer();
//                                moveFacing = getOtherDirection();
//                            }
//                            itemchanged = false;
//                        }
//
//
//                        currentPlayer  = getOtherPlayer();
//                        moveFacing = getOtherDirection();
//
//                    }
//
//                    //Check to see if game is over
//                    updateScore();
//                    if(p1Score == 0){
//                        JOptionPane.showConfirmDialog(null, "Game Over. Player 1 wins");
//                    }else if(p2Score == 0){
//                        JOptionPane.showConfirmDialog(null, "Game Over. Player 2 wins");
//                    }
//
//                    return;
//                }
//
//            }
//        }
    }

    @Override
    protected void initializeButtons() {
        Color theColor = Color.RED;
        Color placeOnColor = Color.BLACK;


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
                getSpace(i,j).setOriginalColor(Color.BLACK);

                theColor = _colorChanger(theColor);
                add(getSpace(i,j));
            }
            theColor = _colorChanger(theColor);


        }


    }

    private Color _colorChanger(Color currColor){
        Color tColor = currColor;
        if(tColor == Color.RED){
            tColor = Color.BLACK;
        }else {
            tColor = Color.RED;
        }

        return tColor;
    }


}
