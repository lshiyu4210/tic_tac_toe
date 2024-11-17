// Author: Jiayi Hao, Shiyu Liu, Erjia Meng
public class Line {

	private String[] positions;
	private int score;
	private int index;
	private boolean full;

	public Line(int index) {

		this.positions = new String[3];
		this.score = 0;
		this.index = index;
		this.full = false;
		this.positions[0] = " ";
		this.positions[1] = " ";
		this.positions[2] = " ";
	}

	public void setPosition(int i, String s) {

		if (positions[i].equals(" "))
			positions[i] = s;
		else
			System.out.println(positions[i] + "is" + "Error!");

	}

	public String[] getLines() {
		String[] rtnLines = new String[3];
		rtnLines = positions.clone();
		return rtnLines;
	}

	public void printLine(int i) {
		System.out.println(positions[0] + "  " + positions[1] + "  " + positions[2]);
	}

	public String getWinner() {

		if (positions[0].equals("x") && positions[1].equals("x") && positions[2].equals("x"))
			return "x";
		else if (positions[0].equals("o") && positions[1].equals("o") && positions[2].equals("o"))
			return "o";
		else
			return " ";

	}

	public boolean isFull() {
		if (positions[0] != " " && positions[1] != " " && positions[2] != " ")
			return true;
		else
			return false;
	}

	public Line clone() {
		Line result = new Line(index);
		result.positions = positions.clone();
		result.score = score;
		result.full = full;
		return result;
	}

	public static void main(String[] args) {
		// Line a = new Line(3);
		// Line b = a.clone();
		// System.out.println(a==b);
		// int test = 2/3;
		// System.out.println(test);
	}

}
