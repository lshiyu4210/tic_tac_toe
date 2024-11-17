// Author: Jiayi Hao, Shiyu Liu, Erjia Meng
import java.util.ArrayList;

public class NineBoard {

	public State[] largeBoard;
	private boolean xTurn;
	private boolean xIsMachine;
	public int nextBoard;
	// public int depth;

	public NineBoard(boolean xIsMachine) {

		this.xTurn = true;
		this.xIsMachine = xIsMachine;
		this.nextBoard = -100;
		this.largeBoard = new State[9];
		for (int i = 0; i < largeBoard.length; i++)
			largeBoard[i] = new State(xIsMachine);
		// this.depth = 0;

	}

	public NineBoard(State[] largeBoard, int nextBoard, boolean xTurn, boolean xIsMachine) {

		this.largeBoard = largeBoard;
		this.xTurn = xTurn;
		this.xIsMachine = xIsMachine;
		this.nextBoard = nextBoard;
		// this.depth = depth;
	}

	public int getNextBoard() {
		return nextBoard;
	}

	public boolean getXisMachine() {
		return xIsMachine;
	}

	public NineBoard markBoard(int boardIndex, int boxIndex) {
		// mark there if the nextBoard is -100, we need 2!! inputs from the user to
		// markBoard
		// if (true) {
		if (isLegalMove(boardIndex)) {
			boolean copy = xTurn;
			State[] newBoard = new State[9];
			int newNextBoard = nextBoard;

			for (int i = 0; i < newBoard.length; i++)
				newBoard[i] = largeBoard[i].clone();

			if (xTurn) {
				newBoard[boardIndex] = newBoard[boardIndex].markBoard(boxIndex);
				newNextBoard = boxIndex;
				copy = false;
				for (int i = 0; i < newBoard.length; i++) {
					if (i != boardIndex)
						newBoard[i].switchSides();
				}
			} else {
				newBoard[boardIndex] = newBoard[boardIndex].markBoard(boxIndex);
				newNextBoard = boxIndex;
				copy = true;
				for (int i = 0; i < newBoard.length; i++) {
					if (i != boardIndex)
						newBoard[i].switchSides();
				}
			}
			NineBoard result = new NineBoard(newBoard, newNextBoard, copy, xIsMachine);
			return result;
		} else {
			System.out.println("Illegal move!");
			return new NineBoard(largeBoard, nextBoard, xTurn, xIsMachine);
		}
	}

	public ArrayList<Integer> getAction() {

		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < 9; i++) {
			if (largeBoard[nextBoard].getBox(i).equals(" "))// get actions from the board that can be allowed
				result.add(i); // to play on
		}
		return result;
	}

	public ArrayList<int[]> getActionArray() {
		ArrayList<int[]> result = new ArrayList<int[]>();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if ((largeBoard[i].getBox(j)).equals(" ")) {
					int[] temp = new int[2];
					temp[0] = i;
					temp[1] = j;
					result.add(temp);
				}
			}
		}
		return result;

	}

	public void printBoard() {

		for (int i = 0; i < largeBoard.length; i++) {
			System.out.println(largeBoard[i].printBoard());
		}

	}

	public void printNineBoard() {
		System.out.println("          A              B              C");
		System.out.println("      a   b   c      a   b   c      a   b   c  ");
		System.out.print("   1  " + largeBoard[0].getBox(0) + " | " + largeBoard[0].getBox(1) + " | "
				+ largeBoard[0].getBox(2) + " ");
		System.out.print("     " + largeBoard[1].getBox(0) + " | " + largeBoard[1].getBox(1) + " | "
				+ largeBoard[1].getBox(2) + " ");
		System.out.print("     " + largeBoard[2].getBox(0) + " | " + largeBoard[2].getBox(1) + " | "
				+ largeBoard[2].getBox(2) + " ");
		System.out.println();
		System.out.println("     ---+---+---    ---+---+---    ---+---+---");
		System.out.print("1  2  " + largeBoard[0].getBox(3) + " | " + largeBoard[0].getBox(4) + " | "
				+ largeBoard[0].getBox(5) + " ");
		System.out.print("     " + largeBoard[1].getBox(3) + " | " + largeBoard[1].getBox(4) + " | "
				+ largeBoard[1].getBox(5) + " ");
		System.out.print("     " + largeBoard[2].getBox(3) + " | " + largeBoard[2].getBox(4) + " | "
				+ largeBoard[2].getBox(5) + " ");
		System.out.println();
		System.out.println("     ---+---+---    ---+---+---    ---+---+---");
		System.out.print("   3  " + largeBoard[0].getBox(6) + " | " + largeBoard[0].getBox(7) + " | "
				+ largeBoard[0].getBox(8) + " ");
		System.out.print("     " + largeBoard[1].getBox(6) + " | " + largeBoard[1].getBox(7) + " | "
				+ largeBoard[1].getBox(8) + " ");
		System.out.print("     " + largeBoard[2].getBox(6) + " | " + largeBoard[2].getBox(7) + " | "
				+ largeBoard[2].getBox(8) + " ");
		System.out.println();

		System.out.println();
		System.out.println();

		System.out.print("   1  " + largeBoard[3].getBox(0) + " | " + largeBoard[3].getBox(1) + " | "
				+ largeBoard[3].getBox(2) + " ");
		System.out.print("     " + largeBoard[4].getBox(0) + " | " + largeBoard[4].getBox(1) + " | "
				+ largeBoard[4].getBox(2) + " ");
		System.out.print("     " + largeBoard[5].getBox(0) + " | " + largeBoard[5].getBox(1) + " | "
				+ largeBoard[5].getBox(2) + " ");
		System.out.println();
		System.out.println("     ---+---+---    ---+---+---    ---+---+---");
		System.out.print("2  2  " + largeBoard[3].getBox(3) + " | " + largeBoard[3].getBox(4) + " | "
				+ largeBoard[3].getBox(5) + " ");
		System.out.print("     " + largeBoard[4].getBox(3) + " | " + largeBoard[4].getBox(4) + " | "
				+ largeBoard[4].getBox(5) + " ");
		System.out.print("     " + largeBoard[5].getBox(3) + " | " + largeBoard[5].getBox(4) + " | "
				+ largeBoard[5].getBox(5) + " ");
		System.out.println();
		System.out.println("     ---+---+---    ---+---+---    ---+---+---");
		System.out.print("   3  " + largeBoard[3].getBox(6) + " | " + largeBoard[3].getBox(7) + " | "
				+ largeBoard[3].getBox(8) + " ");
		System.out.print("     " + largeBoard[4].getBox(6) + " | " + largeBoard[4].getBox(7) + " | "
				+ largeBoard[4].getBox(8) + " ");
		System.out.print("     " + largeBoard[5].getBox(6) + " | " + largeBoard[5].getBox(7) + " | "
				+ largeBoard[5].getBox(8) + " ");
		System.out.println();

		System.out.println();
		System.out.println();

		System.out.print("   1  " + largeBoard[6].getBox(0) + " | " + largeBoard[6].getBox(1) + " | "
				+ largeBoard[6].getBox(2) + " ");
		System.out.print("     " + largeBoard[7].getBox(0) + " | " + largeBoard[7].getBox(1) + " | "
				+ largeBoard[7].getBox(2) + " ");
		System.out.print("     " + largeBoard[8].getBox(0) + " | " + largeBoard[8].getBox(1) + " | "
				+ largeBoard[8].getBox(2) + " ");
		System.out.println();
		System.out.println("     ---+---+---    ---+---+---    ---+---+---");
		System.out.print("3  2  " + largeBoard[6].getBox(3) + " | " + largeBoard[6].getBox(4) + " | "
				+ largeBoard[6].getBox(5) + " ");
		System.out.print("     " + largeBoard[7].getBox(3) + " | " + largeBoard[7].getBox(4) + " | "
				+ largeBoard[7].getBox(5) + " ");
		System.out.print("     " + largeBoard[8].getBox(3) + " | " + largeBoard[8].getBox(4) + " | "
				+ largeBoard[8].getBox(5) + " ");
		System.out.println();
		System.out.println("     ---+---+---    ---+---+---    ---+---+---");
		System.out.print("   3  " + largeBoard[6].getBox(6) + " | " + largeBoard[6].getBox(7) + " | "
				+ largeBoard[6].getBox(8) + " ");
		System.out.print("     " + largeBoard[7].getBox(6) + " | " + largeBoard[7].getBox(7) + " | "
				+ largeBoard[7].getBox(8) + " ");
		System.out.print("     " + largeBoard[8].getBox(6) + " | " + largeBoard[8].getBox(7) + " | "
				+ largeBoard[8].getBox(8) + " ");
		System.out.println();
	}

	public boolean isLegalMove(int bi) {
		if (bi == nextBoard)
			return true;
		else if (nextBoard == -100)
			return true;
		else if (getAction().size() == 0 && bi != nextBoard)
			return true;
		else
			return false;

	}

	public int getUtility() {
		int boardExamined = 0;
		for (State s : largeBoard) {
			int currentUtility = s.getUtility();
			switch (currentUtility) {
				case -100:
					continue;
				case 1:
					return 1;
				case -1:
					return -1;
				case 0:
					boardExamined++;
			}
		}
		if (boardExamined == 9) {
			return 0;
		}
		return -100;
	}

	public double evaluate(int board) {

		if (largeBoard[board].getUtility() == 1) {
			return 1;
		} else if (willLose()) {
			return -1;
		} else {
			double score = getScore(board);
			return score;
		}
	}

	private boolean willLose() {
		for (int i : largeBoard[nextBoard].getAction()) {
			if (largeBoard[nextBoard].markBoard(i).getUtility() == -1) {
				return true;
			}
		}
		return false;
	}

	private double getScore(int board) {
		return ((winLines(board) - opponentWinLines(nextBoard)) / 8);
	}

	private int winLines(int board) {
		int numLines = 0;
		for (int i = 0; i < 8; i++) {
			String[] curLine = largeBoard[board].getLines(i).getLines();
			for (String s : curLine) {
				boolean ok2Count = false;
				if (xIsMachine) {
					if (s.equals("o")) {
						continue;
					}
					if (s.equals("x")) {
						ok2Count = true;
					}
				} else {
					if (s.equals("x")) {
						continue;
					}
					if (s.equals("o")) {
						ok2Count = true;
					}
				}
				if (ok2Count) {
					numLines++;
				}
			}
		}
		return numLines;
	}

	private int opponentWinLines(int nextBoard) {

		int numLines = 0;
		for (int i = 0; i < 8; i++) {
			String[] opLines = largeBoard[nextBoard].getLines(i).getLines();
			int xNum = 0;
			int oNum = 0;
			for (int j = 0; j < 3; j++) {
				if (opLines[j].equals("x"))
					xNum += 1;
				else if (opLines[j].equals("o"))
					oNum += 1;
				if (xIsMachine) {
					if (xNum > 0 && oNum == 0)
						numLines += 1;
				} else {
					if (oNum > 0 && xNum == 0)
						numLines += 1;
				}
			}
		}
		return numLines;
	}

	public static void main(String[] args) {

		// NineBoard testInitial = new NineBoard(true);
		// testInitial = testInitial.markBoard(0, 6);
		// testInitial = testInitial.markBoard(6, 1);
		// NineBoard testInitial2 = testInitial.markBoard(6, 2);
		// testInitial = testInitial.markBoard(1, 7);
		// testInitial = testInitial.markBoard(7, 5);
		// testInitial = testInitial.markBoard(5, 8);
		// testInitial = testInitial.markBoard(8, 0);
		// testInitial = testInitial.markBoard(0, 4);
		// testInitial = testInitial.markBoard(4, 7);
		// testInitial = testInitial.markBoard(7, 3);
		// testInitial = testInitial.markBoard(3, 1);
		// testInitial = testInitial.markBoard(1, 8);
		// testInitial = testInitial.markBoard(8, 2);
		// testInitial = testInitial.markBoard(2, 5);
		// testInitial = testInitial.markBoard(5, 7);
		// testInitial = testInitial.markBoard(7, 2);
		// testInitial = testInitial.markBoard(2, 1);
		// testInitial = testInitial.markBoard(1, 3);
		// testInitial = testInitial.markBoard(3, 6);
		// // testInitial = testInitial.markBoard(0, 6);
		// testInitial = testInitial.markBoard(6, 8);
		// testInitial = testInitial.markBoard(8, 7);
		// testInitial = testInitial.markBoard(7, 1);
		// testInitial = testInitial.markBoard(1, 5);
		// testInitial = testInitial.markBoard(5, 4);
		// testInitial = testInitial.markBoard(4, 1);

		// testInitial = testInitial.markBoard(1, 6);
		// testInitial = testInitial.markBoard(6, 8);
		// testInitial = testInitial.markBoard(8, 1);
		// testInitial = testInitial.markBoard(1, 4);

		// testInitial.printNineBoard();
		// System.out.println(testInitial.nextBoard);
		// System.out.println("depth is " + testInitial.depth);
		// System.out.println(testInitial.getAction());
		// ArrayList<int[]> array = testInitial.getActionArray();
		// for (int[] i : array){
		// System.out.println(i[0]+" "+i[1]);
		// }
		// testInitial.printNineBoard();

		// System.out.println(testInitial.getUtility());
		// System.out.println(testInitial.xTurn);
		// double[] AIdecision = Minimax.HalphaBetaMinimax(testInitial,0);
		// System.out.println("on Board " + AIdecision[2] + " play " + AIdecision[1] +
		// "w/ score " + AIdecision[0]);

	}

}
