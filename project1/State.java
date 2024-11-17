// Author: Jiayi Hao, Shiyu Liu, Erjia Meng
import java.util.ArrayList;

public class State {

	private String[] gameBoard;
	private Line[] lines;
	// private int utility;
	private boolean xTurn;
	private boolean xIsMachine;
	// private String winner;

	public State(boolean xIsMachine) {

		this.lines = new Line[8];
		for (int i = 0; i < 8; i++) {
			lines[i] = new Line(i);
		}
		this.gameBoard = new String[9];
		for (int i = 0; i < gameBoard.length; i++)
			gameBoard[i] = " ";

		this.xTurn = true;
		this.xIsMachine = xIsMachine;
		// this.winner = " ";

	}

	public State(String[] gameBoard, Line[] lines, boolean xTurn, boolean xIsMachine) {

		this.gameBoard = gameBoard;
		this.lines = lines;
		this.xTurn = xTurn;
		this.xIsMachine = xIsMachine;
		// this.winner = " ";

	}

	public ArrayList<Integer> getAction() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < gameBoard.length; i++) {
			if (gameBoard[i].equals(" "))
				result.add(i);
		}
		return result;
	}

	public State markBoard(int i) {

		boolean copy = xTurn;
		String[] newBoard = gameBoard.clone();
		// Line[] newlines = lines.clone();
		Line[] newlines = new Line[8];
		for (int j = 0; j < 8; j++) {
			newlines[j] = lines[j].clone();
		}
		if (newBoard[i] != " ") {
			System.out.println("place to mark is occupied");
			System.out.println(printBoard());
		}

		if (xTurn) {
			newBoard[i] = "x";
			newlines = markLines(i, newlines, "x");
			copy = false;
		} else {
			newBoard[i] = "o";
			newlines = markLines(i, newlines, "o");
			copy = true;
		}
		State result = new State(newBoard, newlines, copy, xIsMachine);
		return result;
	}

	public String[] getBoard() {
		return gameBoard;
	}

	public Line getLines(int i) {
		return lines[i];
	}

	public String getBox(int i) {
		return gameBoard[i];
	}

	public Boolean getXturn() {
		return xTurn;
	}

	public Boolean getXisMachine() {
		return xIsMachine;
	}

	public String printBoard() {
		String result = "[";
		for (String s : gameBoard) {
			result += s;
		}
		return (result + "]");
	}

	public Line[] markLines(int i, Line[] l, String s) {

		switch (i) {
			case 0:
				l[0].setPosition(0, s);
				l[3].setPosition(0, s);
				l[6].setPosition(0, s);
				return l;
			case 1:
				l[0].setPosition(1, s);
				l[4].setPosition(0, s);
				return l;
			case 2:
				l[0].setPosition(2, s);
				l[5].setPosition(0, s);
				l[7].setPosition(0, s);
				return l;
			case 3:
				l[1].setPosition(0, s);
				l[3].setPosition(1, s);
				return l;
			case 4:
				l[1].setPosition(1, s);
				l[4].setPosition(1, s);
				l[6].setPosition(1, s);
				l[7].setPosition(1, s);
				return l;
			case 5:
				l[1].setPosition(2, s);
				l[5].setPosition(1, s);
				return l;
			case 6:
				l[2].setPosition(0, s);
				l[3].setPosition(2, s);
				l[7].setPosition(2, s);
				return l;
			case 7:
				l[2].setPosition(1, s);
				l[4].setPosition(2, s);
				return l;
			case 8:
				l[2].setPosition(2, s);
				l[5].setPosition(2, s);
				l[6].setPosition(2, s);
				return l;
			default:
				return l;
		}
	}

	public int getUtility() {
		int linesExamined = 0;
		String result = " ";
		for (int i = 0; i < lines.length; i++) {
			result = lines[i].getWinner();
			if (!result.equals(" ")) {
				break;
			}
			if (lines[i].isFull())
				linesExamined++;
		}
		if (linesExamined == 8)
			return 0;
		else if (xIsMachine) {
			if (result.equals("x")) {
				return 1;
			} else if (result.equals("o")) {
				return -1;
			}
		} else {
			if (result.equals("o")) {
				return 1;
			} else if (result.equals("x")) {
				return -1;
			}
		}
		return -100;
	}

	public void switchSides() {
		if (xTurn)
			xTurn = false;
		else
			xTurn = true;
	}

	public State clone() {

		State cloneState = new State(true);
		cloneState.gameBoard = gameBoard.clone();
		for (int i = 0; i < 8; i++) {
			cloneState.lines[i] = lines[i].clone();
		}
		cloneState.xTurn = xTurn;
		cloneState.xIsMachine = xIsMachine;

		return cloneState;
	}

	public void printState() {
		System.out.println("   a   b   c");
		System.out.println("1  " + gameBoard[0] + " | " + gameBoard[1] + " | " + gameBoard[2] + " ");
		System.out.println("  ---+---+---");
		System.out.println("2  " + gameBoard[3] + " | " + gameBoard[4] + " | " + gameBoard[5] + " ");
		System.out.println("  ---+---+---");
		System.out.println("3  " + gameBoard[6] + " | " + gameBoard[7] + " | " + gameBoard[8] + " ");

	}

	public static void main(String[] args) {
		// State test1 = new State(true);

		// test1.printState();
		// test1 = test1.markBoard(3);
		// test1 = test1.markBoard(5);
		// test1 = test1.markBoard(7);
		// test1 = test1.markBoard(1);
		// test1.printState();

	}
}
