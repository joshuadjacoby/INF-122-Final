import java.util.ArrayList;

public class LogicTester {
	public static void main(String[] args){
	    System.out.println("Hey");
	    
	    int[][] gb = new int[8][8];	    
	    
	    gb[1][2] = 1;
	    gb[2][2] = 2;
	    gb[3][2] = 2;
	    
	    
	    
	    
	    
	    
	    GridGame theGame = new GridGame(8, 8);
	    
	    //Spacesplaced that are north of [4][4]
	    theGame.setItemInPosition(3, 4, 1);
	    theGame.setItemInPosition(2, 4, 2);
	    theGame.setItemInPosition(1, 4, 2);
	    
	    
	  //Spacesplaced that are NorthEast of [4][4]
	    theGame.setItemInPosition(3, 5, 1);
	    theGame.setItemInPosition(2, 6, 2);
	    //theGame.setItemInPosition(1, 7, 2);
	    
	  //Spacesplaced that are east of [4][4]
	    theGame.setItemInPosition(4, 5, 1);
	    theGame.setItemInPosition(4, 7, 2);
	    //theGame.setItemInPosition(4, 5, 2);
	    
	    GameLogic logic = new GameLogic(8,8,0);
	    
	    ArrayList<int[]> freeSpacesN = logic.getNorthSpaces(4,4, theGame);
	    ArrayList<int[]> freeSpacesNE = logic.getNorthEastSpaces(4,4, theGame);
	    ArrayList<int[]> freeSpacesE = logic.getEastSpaces(4,4, theGame);

	    
	    
	    System.out.println("Checking spaces North");
	    for(int[] a : freeSpacesN) {
	    	System.out.println(a[0] + ", " + a[1]);
	    }
	    
	    System.out.println("");
	    System.out.println("Checking spaces NorthEast");
	    for(int[] a : freeSpacesNE) {
	    	System.out.println(a[0] + ", " + a[1]);
	    }
	    
	    System.out.println("");
	    System.out.println("Checking spaces East");
	    for(int[] a : freeSpacesE) {
	    	System.out.println(a[0] + ", " + a[1]);
	    }
	    
	    
	}


}
