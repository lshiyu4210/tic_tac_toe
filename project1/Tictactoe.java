// Author: Jiayi Hao, Shiyu Liu, Erjia Meng
import java.util.*;

public class Tictactoe {

	State state;
	NineBoard nineBoard;

	public Tictactoe() {
	}

	public void printBoard(String[] gameBoard) {
		System.out.println("   a   b   c");
		System.out.println("1  " + gameBoard[0] + " | " + gameBoard[1] + " | " + gameBoard[2] + " ");
		System.out.println("  ---+---+---");
		System.out.println("2  " + gameBoard[3] + " | " + gameBoard[4] + " | " + gameBoard[5] + " ");
		System.out.println("  ---+---+---");
		System.out.println("3  " + gameBoard[6] + " | " + gameBoard[7] + " | " + gameBoard[8] + " ");

	}

	public void startGame() {
		Scanner s = new Scanner(System.in);

		boolean xIsMachine = true;

		while (true) {
			System.out.println("Do you want to play X or O? ");
			String markChoice = s.next();

			if (markChoice.equals("X") || markChoice.equals("x")) {
				xIsMachine = false;
				// System.out.println("X");
				break;
			} else if (markChoice.equals("O") || markChoice.equals("o")) {
				xIsMachine = true;
				// System.out.println("O");
				break;
			} else {
				System.out.println("Invalid choice!");
				System.out.println("Please choose again.");
			}
		}

		state = new State(xIsMachine);

		boolean playerFlag = state.getXisMachine();
		boolean turnFlag = true;

		state.printState();
		while (state.getUtility() == -100) {
			if (turnFlag) {
				System.out.println("Next to play: X");
			} else {
				System.out.println("Next to play: O");
			}
			System.out.println();
			if (playerFlag) { // is machine
				System.out.println("I'm thinking...");
				// state = state.markBoard(Minimax.basicMinimax(state));
				state = state.markBoard(Minimax.alphaBetaMinimax(state));
			} else {
				System.out.println("Your move [col row]?");

				while (true) {

					String playerChoice = s.next();
					if (playerChoice.length() != 2 || letterToNumber(playerChoice.charAt(0)) == -1
							|| letterToNumber(playerChoice.charAt(1)) == -1) {
						System.out.println("Try again!");
						continue;
					}
					int playerChoiceToNumber = letterToNumber(playerChoice.charAt(0))
							+ letterToNumber(playerChoice.charAt(1)) * 3;
					if (state.getBoard()[playerChoiceToNumber].equals(" ")) {
						state = state.markBoard(playerChoiceToNumber);
						break;
					}
					System.out.println("Try again!");
				}

			}
			state.printState();
			playerFlag = !playerFlag;
			turnFlag = !turnFlag;
		}

		if (state.getUtility() == 0) {
			System.out.println("Draw!");
		} else if (state.getUtility() == 1) {
			if (state.getXisMachine()) {
				System.out.println("Winner: X");
			} else {
				System.out.println("Winner: O");
			}
		} else if (state.getUtility() == -1) {
			if (state.getXisMachine()) {
				System.out.println("Winner: O");
			} else {
				System.out.println("Winner: X");
			}
		}
	}

	public void start9BoardGame() {
		Scanner s = new Scanner(System.in);

		boolean xIsMachine = true;

		while (true) {
			System.out.println("Do you want to play X or O? ");
			String markChoice = s.next();
			if (markChoice.equals("X") || markChoice.equals("x")) {
				xIsMachine = false;
				// System.out.println("X");
				break;
			} else if (markChoice.equals("O") || markChoice.equals("o")) {
				xIsMachine = true;
				// System.out.println("O");
				break;
			} else {
				System.out.println("Invalid choice!");
				System.out.println("Please choose again.");
			}
		}

		nineBoard = new NineBoard(xIsMachine);

		boolean playerFlag = nineBoard.getXisMachine();
		boolean turnFlag = true;
		boolean isFirstTurn = true;

		while (true) {

			nineBoard.printNineBoard();
			if (turnFlag) {
				System.out.println("Next to play: X");
			} else {
				System.out.println("Next to play: O");
			}
			System.out.println();

			if (playerFlag) { // is machine
				System.out.println("I'm thinking...");

				double[] action = Minimax.HalphaBetaMinimax(nineBoard, 0);
				nineBoard = nineBoard.markBoard((int) action[2], (int) action[1]);

				System.out.println("I played on board " + numberToLetter((int) action[2]).toUpperCase() + " move "
						+ numberToLetter((int) action[1]));

			} else { // is player

				if (nineBoard.nextBoard == -100) {
					String boardChoice, boxChoice;
					int boardChoiceToNumber, boxChoiceToNumber;
					System.out.println("Choose the board[col row].");
					while (true) {
						boardChoice = s.next();
						if (boardChoice.length() != 2 || letterToNumber(boardChoice.charAt(0)) == -1
								|| letterToNumber(boardChoice.charAt(1)) == -1) {
							System.out.println("Try again!");
							continue;
						}
						boardChoiceToNumber = letterToNumber(boardChoice.charAt(0))
								+ letterToNumber(boardChoice.charAt(1)) * 3;
						break;
					}

					System.out
							.println("You choose board " + boardChoice.toUpperCase() + ". Choose your move[col row].");

					while (true) {
						boxChoice = s.next();
						if (boxChoice.length() != 2 || letterToNumber(boxChoice.charAt(0)) == -1
								|| letterToNumber(boxChoice.charAt(1)) == -1) {
							System.out.println("Try again!");
							continue;
						}
						boxChoiceToNumber = letterToNumber(boxChoice.charAt(0))
								+ letterToNumber(boxChoice.charAt(1)) * 3;
						break;

					}

					nineBoard = nineBoard.markBoard(boardChoiceToNumber, boxChoiceToNumber);

				} else {
					String boxChoice;
					int boxChoiceToNumber;
					while (true) {
						System.out.println("You're going to play on board "
								+ numberToLetter(nineBoard.getNextBoard()).toUpperCase() + ". Your move[col row]?");
						boxChoice = s.next();
						if (boxChoice.length() != 2 || letterToNumber(boxChoice.charAt(0)) == -1
								|| letterToNumber(boxChoice.charAt(1)) == -1) {
							System.out.println("Try again!");
							continue;
						}
						boxChoiceToNumber = letterToNumber(boxChoice.charAt(0))
								+ letterToNumber(boxChoice.charAt(1)) * 3;
						if (nineBoard.largeBoard[nineBoard.getNextBoard()].getBox(boxChoiceToNumber) == " ") {
							nineBoard = nineBoard.markBoard(nineBoard.getNextBoard(), boxChoiceToNumber);
							break;
						}

						System.out.println("Try again!");
					}
				}
			}
			if (isFirstTurn == false) {
				if (nineBoard.getUtility() == 1) {
					nineBoard.printNineBoard();
					if (nineBoard.getXisMachine()) {
						System.out.println("Winner: X");
					} else {
						System.out.println("Winner: O");
					}
					break;
				} else if (nineBoard.getUtility() == -1) {
					nineBoard.printNineBoard();
					if (nineBoard.getXisMachine()) {
						System.out.println("Winner: O");
					} else {
						System.out.println("Winner: X");
					}
					break;
				} else if (nineBoard.getUtility() == 0) {
					System.out.println("Draw");
					break;
				}
			}

			isFirstTurn = false;
			playerFlag = !playerFlag;
			turnFlag = !turnFlag;

		}

	}

	public int letterToNumber(char letter) {
		letter = Character.toLowerCase(letter);
		if (letter == 'a' || letter == '1')
			return 0;
		if (letter == 'b' || letter == '2')
			return 1;
		if (letter == 'c' || letter == '3')
			return 2;
		return -1;
	}

	public String numberToLetter(int number) {
		String letter = "";
		if (number % 3 == 0) {
			letter += "a";
		}
		if (number % 3 == 1) {
			letter += "b";
		}
		if (number % 3 == 2) {
			letter += "c";
		}
		letter += number / 3 + 1;
		return letter;
	}

	public static void main(String args[]) {
		System.out.println("Choose your game:");
		System.out.println("1. Basic 3x3 Tic-tac-toe");
		System.out.println("2. Nine-board Tic-tac-toe");
		System.out.println("Your Choice?");

		Scanner s = new Scanner(System.in);

		Tictactoe ttt = new Tictactoe();
		while (true) {
			String gameChoice = s.next();
			if (gameChoice.equals("1")) {
				System.out.println("Starting Basic 3x3 Tic-tac-toe");
				ttt.startGame();
				break;
			} else if (gameChoice.equals("2")) {
				System.out.println("Starting Nince-board Tic-tac-toe");
				ttt.start9BoardGame();
				break;
			} else {
				System.out.println("Invalid choice!");
				System.out.println("Please choose again.");
			}
		}
	}

}
