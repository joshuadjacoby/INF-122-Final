import javax.swing.*;
import java.util.ArrayList;

public class GridGameMoveLogic<T> {

	int rows;
	int columns;
	T defaultBoardItem ;

	public GridGameMoveLogic(int rows_num, int col_num, T defaultInt) {
		rows = rows_num;
		columns = col_num;
        defaultBoardItem = defaultInt;

	}

	public ArrayList getNorthSpaces(int rowInd, int colInd, JButton[][] gameboard) {
		ArrayList returnList = new ArrayList();

		for (int revInd = rowInd - 1; revInd >= 0; revInd--) {
			if (revInd >= 0 && ((GameButton)(gameboard[revInd][colInd])).getButtonValue() == defaultBoardItem) {
				int[] arr = new int[2];
				arr[1] = colInd;
				arr[0] = revInd;
				returnList.add(arr);
			}
		}

		return returnList;
	}

	public ArrayList getNorthEastSpaces(int rowInd, int colInd, JButton[][] gameboard) {
		ArrayList returnList = new ArrayList();
		int nX = colInd + 1;

		for (int revInd = rowInd - 1; revInd >= 0; revInd--) {

			if (revInd >= 0) {
				if (nX < columns && ((GameButton)(gameboard[revInd][nX])).getButtonValue() == defaultBoardItem) {
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

	public ArrayList getEastSpaces(int rowInd, int colInd, JButton[][] gameboard) {
		ArrayList returnList = new ArrayList();

		for (int ind = colInd; ind < columns; ind++) {
			if (ind < columns && ((GameButton)(gameboard[rowInd][ind])).getButtonValue()  == defaultBoardItem) {
				int[] arr = new int[2];
				arr[1] = ind;
				arr[0] = rowInd;
				returnList.add(arr);
			}

		}

		return returnList;

	}

	public ArrayList getSouthEastSpaces(int rowInd, int colInd, JButton[][] gameboard) {
		ArrayList returnList = new ArrayList();

		int nX = colInd + 1;
		for (int ind = rowInd + 1; ind < rows; ind++) {
			if (ind < rows) {


				if (nX < columns && ((GameButton)(gameboard[ind][nX])).getButtonValue()  == defaultBoardItem) {
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

	public ArrayList getSouthSpaces(int rowInd, int colInd, JButton[][] gameboard) {
		ArrayList returnList = new ArrayList();

		for (int revInd = rowInd + 1; revInd < rows; revInd++) {
			if (revInd < rows && ((GameButton)(gameboard[revInd][colInd])).getButtonValue() == defaultBoardItem) {
				int[] arr = new int[2];
				arr[1] = colInd;
				arr[0] = revInd;
				returnList.add(arr);
			}
		}

		return returnList;

	}

	public ArrayList getSouthWestSpaces(int rowInd, int colInd, JButton[][] gameboard) {
		ArrayList returnList = new ArrayList();
		int nX = colInd - 1;

		for (int revInd = rowInd + 1; revInd < rows; revInd++) {

			//System.out.println(rows + "    " + columns + "    " + revInd + "    " + nX + "    ");
			if (revInd < rows) {

				if (nX >=0 && ((GameButton)(gameboard[revInd][nX])).getButtonValue()  == defaultBoardItem) {
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

	public ArrayList getWestSpaces(int rowInd, int colInd, JButton[][] gameboard) {
		ArrayList returnList = new ArrayList();

		for (int ind = colInd - 1; ind >= 0; ind--) {

			if (ind < columns && ((GameButton)(gameboard[rowInd][ind])).getButtonValue()  == defaultBoardItem) {
				int[] arr = new int[2];
				arr[1] = ind;
				arr[0] = rowInd;
				returnList.add(arr);
			}

		}

		return returnList;

	}

	public ArrayList getNorthWestSpaces(int rowInd, int colInd, JButton[][] gameboard) {
		ArrayList returnList = new ArrayList();

		int nX = colInd - 1;

		for (int ind = rowInd - 1; ind >= 0; ind--) {
			if (ind >= 0) {
			}
			if (nX >= 0 && ((GameButton)(gameboard[ind][nX])).getButtonValue()  == defaultBoardItem) {
				int[] arr = new int[2];
				arr[1] = nX;
				arr[0] = ind;
				returnList.add(arr);
			}
			nX -= 1;
		}

		return returnList;
	}

}
