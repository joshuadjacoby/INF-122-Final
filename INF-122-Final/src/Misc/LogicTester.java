//import java.util.ArrayList;
//
//public class LogicTester {
//	public static void main(String[] args){
//	    System.out.println("Hey");
//	    
//	    int[][] gb = new int[8][8];	    
//	    
//	    gb[1][2] = 1;
//	    gb[2][2] = 2;
//	    gb[3][2] = 2;
//	    
//	    
//	    
//	    
//	    
//	    
//	    GridGame theGame = new GridGame(8, 8);
//	    
//	    //Spacesplaced that are north of [4][4]
//	    theGame.setItemInPosition(3, 4, 1);
//	    theGame.setItemInPosition(2, 4, 2);
//	    theGame.setItemInPosition(1, 4, 2);
//	    
//	    
//	  //Spacesplaced that are NorthEast of [4][4]
//	    theGame.setItemInPosition(3, 5, 1);
//	    theGame.setItemInPosition(2, 6, 2);
//	    //theGame.setItemInPosition(1, 7, 2);
//	    
//	  //Spacesplaced that are east of [4][4]
//	    theGame.setItemInPosition(4, 5, 1);
//	    theGame.setItemInPosition(4, 7, 2);
//	    //theGame.setItemInPosition(4, 5, 2);
//	    
//	    
//	  //Spacesplaced that are SouthEast of [4][4]
//	    //theGame.setItemInPosition(5, 5, 1);
//	    theGame.setItemInPosition(6, 6, 2);
//	    theGame.setItemInPosition(7, 7, 2);
//	    
//	    
//	    
//	    GridGameMoveLogic logic = new GridGameMoveLogic(8,8,0);
//	    
//	    ArrayList<int[]> freeSpacesN = logic.getNorthSpaces(4,4, theGame);
//	    ArrayList<int[]> freeSpacesNE = logic.getNorthEastSpaces(4,4, theGame);
//	    ArrayList<int[]> freeSpacesE = logic.getEastSpaces(4,4, theGame);
//	    ArrayList<int[]> freeSpacesSE = logic.getSouthEastSpaces(4,4, theGame);
//	    
//	    ArrayList<int[]> freeSpacesS = logic.getSouthSpaces(4,4, theGame);
//	    ArrayList<int[]> freeSpacesSW = logic.getSouthWestSpaces(4,4, theGame);
//	    ArrayList<int[]> freeSpacesW = logic.getWestSpaces(4,4, theGame);
//	    ArrayList<int[]> freeSpacesNW = logic.getNorthWestSpaces(4,4, theGame);
//
//
//
//
//	    
//	    
//	    System.out.println("Checking spaces North");
//	    for(int[] a : freeSpacesN) {
//	    	System.out.println(a[0] + ", " + a[1]);
//	    }
//	    
//	    System.out.println("");
//	    System.out.println("Checking spaces NorthEast");
//	    for(int[] a : freeSpacesNE) {
//	    	System.out.println(a[0] + ", " + a[1]);
//	    }
//	    
//	    System.out.println("");
//	    System.out.println("Checking spaces East");
//	    for(int[] a : freeSpacesE) {
//	    	System.out.println(a[0] + ", " + a[1]);
//	    }
//	    
//	    System.out.println("");
//	    System.out.println("Checking spaces SouthEast");
//	    for(int[] a : freeSpacesSE) {
//	    	System.out.println(a[0] + ", " + a[1]);
//	    }
//	    
//	    
//	    System.out.println("");
//	    System.out.println("");
//
//	    
//	    System.out.println("Checking spaces South");
//	    for(int[] a : freeSpacesS) {
//	    	System.out.println(a[0] + ", " + a[1]);
//	    }
//	    
//	    System.out.println("");
//	    System.out.println("Checking spaces SouthWest");
//	    for(int[] a : freeSpacesSW) {
//	    	System.out.println(a[0] + ", " + a[1]);
//	    }
//	    
//	    System.out.println("");
//	    System.out.println("Checking spaces West");
//	    for(int[] a : freeSpacesW) {
//	    	System.out.println(a[0] + ", " + a[1]);
//	    }
//	    
//	    System.out.println("");
//	    System.out.println("Checking spaces NorthWest");
//	    for(int[] a : freeSpacesNW) {
//	    	System.out.println(a[0] + ", " + a[1]);
//	    }
//	}
//
//
//}
