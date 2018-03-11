import java.util.ArrayList;

public abstract class GameLogic {

	int rows;
	int columns;
	int defInt;

	public GameLogic(int x, int y, int defaultInt) {
		rows = y;
		columns = x;
		defInt = defaultInt;

	}

	public ArrayList getNorthSpaces(int rowInd, int colInd, GridGame gameboard) {
		ArrayList returnList = new ArrayList();

		for (int revInd = rowInd - 1; revInd >= 0; revInd--) {
			if (revInd >= 0 && gameboard.getItemAt(revInd, colInd) == defInt) {
				int[] arr = new int[2];
				arr[1] = colInd;
				arr[0] = revInd;
				returnList.add(arr);
			}
		}

		return returnList;
	}

	public ArrayList getNorthEastSpaces(int rowInd, int colInd, GridGame gameboard) {
		ArrayList returnList = new ArrayList();
		int nX = colInd + 1;

		for (int revInd = rowInd - 1; revInd >= 0; revInd--) {

			if (revInd >= 0) {
				if (nX < columns && gameboard.getItemAt(revInd, nX) == defInt) {
					int[] arr = new int[2];
					arr[1] = nX;
					arr[0] = revInd;
					returnList.add(arr);
				}

			}
			nX += 1;
		}

		return returnList;
	}

	public ArrayList getEastSpaces(int rowInd, int colInd, GridGame gameboard) {
		ArrayList returnList = new ArrayList();

		for (int ind = colInd; ind < columns; ind++) {
			if (ind < columns && gameboard.getItemAt(rowInd, ind) == defInt) {
				int[] arr = new int[2];
				arr[1] = ind;
				arr[0] = rowInd;
				returnList.add(arr);
			}

		}

		return returnList;

	}

	public ArrayList getSouthEastSpaces(int rowInd, int colInd, GridGame gameboard) {
		ArrayList returnList = new ArrayList();

		int nX = colInd + 1;
		for (int ind = rowInd + 1; ind < rows; ind++) {
			if (ind < rows) {
				if (nX < columns && gameboard.getItemAt(ind, nX) == defInt) {
					int[] arr = new int[2];
					arr[1] = nX;
					arr[0] = ind;
					returnList.add(arr);
				}
			}
			nX += 1;
		}

		return returnList;

	}

	public ArrayList getSouthSpaces(int rowInd, int colInd, GridGame gameboard) {
		ArrayList returnList = new ArrayList();

		for (int revInd = rowInd + 1; revInd < rows; revInd++) {
			if (revInd < rows && gameboard.getItemAt(revInd, colInd) == defInt) {
				int[] arr = new int[2];
				arr[1] = colInd;
				arr[0] = revInd;
				returnList.add(arr);
			}
		}

		return returnList;

	}

	public ArrayList getSouthWestSpaces(int rowInd, int colInd, GridGame gameboard) {
		ArrayList returnList = new ArrayList();
		int nX = colInd - 1;

		for (int revInd = rowInd + 1; revInd < rows; revInd++) {

			if (revInd < rows) {
				if (nX < columns && gameboard.getItemAt(revInd, nX) == defInt) {
					int[] arr = new int[2];
					arr[1] = nX;
					arr[0] = revInd;
					returnList.add(arr);
				}

			}
			nX -= 1;
		}

		return returnList;
	}

	public ArrayList getWestSpaces(int rowInd, int colInd, GridGame gameboard) {
		ArrayList returnList = new ArrayList();

		for (int ind = colInd - 1; ind >= 0; ind--) {
			if (ind < columns && gameboard.getItemAt(rowInd, ind) == defInt) {
				int[] arr = new int[2];
				arr[1] = ind;
				arr[0] = rowInd;
				returnList.add(arr);
			}

		}

		return returnList;

	}

	public ArrayList getNorthWestSpaces(int rowInd, int colInd, GridGame gameboard) {
		ArrayList returnList = new ArrayList();

		int nX = colInd - 1;

		for (int ind = rowInd - 1; ind >= 0; ind--) {
			if (ind >= 0) {
				if (nX >= 0 && gameboard.getItemAt(ind, nX) == defInt) {
					int[] arr = new int[2];
					arr[1] = nX;
					arr[0] = ind;
					returnList.add(arr);
				}
			}
			nX -= 1;
		}

		return returnList;
	}

}
