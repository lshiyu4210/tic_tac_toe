// Author: Jiayi Hao, Shiyu Liu, Erjia Meng
public class Minimax {

    public static int basicMinimax(State init) {
        int[] action = getMax(init);
        // System.out.println(action[0]);
        return action[1];
    }

    private static int[] getMax(State s) {
        // System.out.println(s.printBoard());
        if (s.getXturn() != (s.getXisMachine() == true)) {
            System.out.println("Program Error");
        }
        int[] result = { -10, -1 };
        int utility = s.getUtility();
        if (utility != -100) {
            result[0] = utility;
            // System.out.println(s.printBoard());
            // System.out.println(utility+";");
            return result;
        } else {
            for (Integer i : s.getAction()) {
                State next = s.markBoard(i.intValue());
                int[] temp = getMin(next);
                if (temp[0] > result[0]) {
                    result[0] = temp[0];
                    result[1] = i;
                }
            }
            return result;
        }
    }

    private static int[] getMin(State s) {
        // System.out.println(s.printBoard());
        if (s.getXturn() != ((s.getXisMachine() == false))) {
            System.out.println("Program Error");
        }
        int[] result = { 10, -1 };
        int utility = s.getUtility();
        if (utility != -100) {
            result[0] = utility;
            // System.out.println(s.printBoard());
            // System.out.println(utility+";");
            return result;
        } else {
            for (Integer i : s.getAction()) {
                State next = s.markBoard(i.intValue());
                int[] temp = getMax(next);
                if (temp[0] < result[0]) {
                    result[0] = temp[0];
                    result[1] = i;
                }
            }
            return result;
        }
    }

    public static int alphaBetaMinimax(State init) {
        int[] action = getMax(init, -100, 100);
        // System.out.println(action[0]);
        return action[1];
    }

    private static int[] getMax(State s, int alpha, int beta) {
        // System.out.println(s.printBoard());
        if (s.getXturn() != (s.getXisMachine() == true)) {
            System.out.println("Program Error");
        }
        int[] result = { -10, -1 };
        int utility = s.getUtility();
        if (utility != -100) {
            result[0] = utility;
            // System.out.println(s.printBoard());
            // System.out.println(utility+";");
            return result;
        } else {
            for (Integer i : s.getAction()) {
                State next = s.markBoard(i.intValue());
                int[] temp = getMin(next, alpha, beta);
                if (temp[0] > result[0]) {
                    result[0] = temp[0];
                    result[1] = i;
                    alpha = Math.max(alpha, result[0]);
                }
                if (result[0] >= beta) {
                    return result;
                }
            }
            return result;
        }
    }

    private static int[] getMin(State s, int alpha, int beta) {
        // System.out.println(s.printBoard());
        if (s.getXturn() != ((s.getXisMachine() == false))) {
            System.out.println("Program Error");
        }
        int[] result = { 10, -1 };
        int utility = s.getUtility();
        if (utility != -100) {
            result[0] = utility;
            // System.out.println(s.printBoard());
            // System.out.println(utility+";");
            return result;
        } else {
            for (Integer i : s.getAction()) {
                State next = s.markBoard(i.intValue());
                int[] temp = getMax(next, alpha, beta);
                if (temp[0] < result[0]) {
                    result[0] = temp[0];
                    result[1] = i;
                    beta = Math.min(beta, result[0]);
                }
                if (result[0] <= alpha) {
                    return result;
                }
            }
            return result;
        }
    }

    public static int[] basicMinimax(NineBoard init) {
        int[] action = getMax(init);
        // System.out.println(action[0]);
        return action;
    }

    private static int[] getMax(NineBoard n) {
        // System.out.println(s.printBoard());
        // if(n.getXturn()!=(n.getXisMachine()==true)){System.out.println("Program
        // Error");}
        int[] result = { -10, -1, -1 };
        int utility = n.getUtility();
        if (utility != -100) {
            result[0] = utility;
            // System.out.println(s.printBoard());
            // System.out.println(utility+";");
            return result;
        } else {
            if (n.nextBoard == -100 || n.getAction().size() == 0) {
                n.nextBoard = -100;
                for (int[] i : n.getActionArray()) {
                    NineBoard next = n.markBoard(i[0], i[1]);
                    int[] temp = getMin(next);
                    if (temp[0] > result[0]) {
                        result[0] = temp[0];
                        result[1] = i[1];
                        result[2] = i[0];
                    }

                }
            } else {
                for (Integer i : n.getAction()) {
                    NineBoard next = n.markBoard(n.nextBoard, i.intValue());
                    int[] temp = getMin(next);
                    if (temp[0] > result[0]) {
                        result[0] = temp[0];
                        result[1] = i;
                        result[2] = n.nextBoard;
                    }
                }
            }
            return result;

        }
    }

    private static int[] getMin(NineBoard n) {
        // System.out.println(s.printBoard());
        // if(n.getXturn()!=((n.getXisMachine()==false))){System.out.println("Program
        // Error");}
        int[] result = { 10, -1, -1 };
        int utility = n.getUtility();
        if (utility != -100) {
            result[0] = utility;
            // System.out.println(s.printBoard());
            // System.out.println(utility+";");
            return result;
        } else {
            if (n.nextBoard == -100 || n.getAction().size() == 0) {
                n.nextBoard = -100;
                for (int[] i : n.getActionArray()) {
                    NineBoard next = n.markBoard(i[0], i[1]);
                    int[] temp = getMax(next);
                    if (temp[0] < result[0]) {
                        result[0] = temp[0];
                        result[1] = i[1];
                        result[2] = i[0];
                    }
                }
            } else {
                for (Integer i : n.getAction()) {
                    NineBoard next = n.markBoard(n.nextBoard, i.intValue());
                    int[] temp = getMax(next);
                    if (temp[0] < result[0]) {
                        result[0] = temp[0];
                        result[1] = i;
                        result[2] = n.nextBoard;
                    }
                }
            }
            return result;
        }
    }

    public static double[] HalphaBetaMinimax(NineBoard init, int depth) {
        double[] action = getMax(init, -100, 100, depth);
        // System.out.println(action[0]);
        return action;
    }

    private static double[] getMax(NineBoard n, double alpha, double beta, int depth) {
        // System.out.println(s.printBoard());
        // if(n.getXturn()!=(n.getXisMachine()==true)){System.out.println("Program
        // Error");}
        double[] result = { -10, -1, -1 };
        int utility = n.getUtility();
        if (utility != -100) {
            result[0] = utility;
            return result;
        }
        // } else {
        if (n.nextBoard == -100 || n.getAction().size() == 0) {
            n.nextBoard = -100;
            for (int[] i : n.getActionArray()) {
                NineBoard next = n.markBoard(i[0], i[1]);
                // evaluate it
                if (depth > 9) {
                    result[0] = next.evaluate(i[0]);
                    result[1] = i[1];
                    result[2] = i[0];
                    return result;
                } else {
                    double[] temp = getMin(next, alpha, beta, depth + 1);
                    if (temp[0] > result[0]) {
                        result[0] = temp[0];
                        result[1] = i[1];
                        result[2] = i[0];
                        alpha = Math.max(alpha, result[0]);
                    }
                    if (result[0] >= beta) {
                        return result;
                    }
                }
            }
        } else {
            for (Integer i : n.getAction()) {
                NineBoard next = n.markBoard(n.nextBoard, i.intValue());
                // evaluate it
                if (depth > 9) {
                    result[0] = next.evaluate(n.nextBoard);
                    result[1] = i.intValue();
                    result[2] = n.nextBoard;
                    return result;
                } else {
                    double[] temp = getMin(next, alpha, beta, depth + 1);
                    if (temp[0] > result[0]) {
                        result[0] = temp[0];
                        result[1] = i;
                        result[2] = n.nextBoard;
                        alpha = Math.max(alpha, result[0]);
                    }
                    if (result[0] >= beta) {
                        return result;
                    }
                }
            }
        }
        return result;

        // }
    }

    private static double[] getMin(NineBoard n, double alpha, double beta, int depth) {
        // System.out.println(s.printBoard());
        // if(n.getXturn()!=((n.getXisMachine()==false))){System.out.println("Program
        // Error");}
        double[] result = { 10, -1, -1 };
        int utility = n.getUtility();
        if (utility != -100) {
            result[0] = utility;
            return result;
        }
        // return result;
        // } else {
        if (n.nextBoard == -100 || n.getAction().size() == 0) {
            n.nextBoard = -100;
            for (int[] i : n.getActionArray()) {
                NineBoard next = n.markBoard(i[0], i[1]);
                // evaluate it
                if (depth > 9) {
                    result[0] = next.evaluate(i[0]);
                    result[1] = i[1];
                    result[2] = i[0];
                    return result;
                } else {
                    double[] temp = getMax(next, alpha, beta, depth + 1);
                    if (temp[0] < result[0]) {
                        result[0] = temp[0];
                        result[1] = i[1];
                        result[2] = i[0];
                        beta = Math.min(beta, result[0]);
                    }
                    if (result[0] <= alpha) {
                        return result;
                    }
                }
            }
        } else {
            for (Integer i : n.getAction()) {
                NineBoard next = n.markBoard(n.nextBoard, i.intValue());
                // evaluate it
                if (depth > 9) {
                    result[0] = next.evaluate(n.nextBoard);
                    result[1] = i.intValue();
                    result[2] = n.nextBoard;
                    return result;
                } else {
                    double[] temp = getMax(next, alpha, beta, depth + 1);
                    if (temp[0] < result[0]) {
                        result[0] = temp[0];
                        result[1] = i;
                        result[2] = n.nextBoard;
                        beta = Math.min(beta, result[0]);
                    }
                    if (result[0] <= alpha) {
                        return result;
                    }
                }
            }
        }
        return result;
        // }
    }

    public static void main(String[] args) {
        // String[] initArray = {"0","0","0","0","0","0","0","0","0"};
        // State testInital = new State(true);
        // testInital = testInital.markBoard(0);
        // testInital = testInital.markBoard(4);
        // testInital = testInital.markBoard(3);
        // testInital = testInital.markBoard(1);
        // testInital = testInital.markBoard(2);
        // testInital = testInital.markBoard(6);
        // testInital = testInital.markBoard(7);
        // System.out.println(testInital.printBoard());
        // System.out.print(basicMinimax(testInital));
        // NineBoard nineInital = new NineBoard(false);
        // nineInital = nineInital.markBoard(1, 0);
        // nineInital = nineInital.markBoard(0, 0);
        // nineInital = nineInital.markBoard(0, 2);
        // nineInital = nineInital.markBoard(2, 0);
        // nineInital = nineInital.markBoard(0, 1);
        // nineInital = nineInital.markBoard(1, 1);
        // nineInital = nineInital.markBoard(1, 2);
        // nineInital = nineInital.markBoard(2, 1);
        // nineInital = nineInital.markBoard(1, 3);
        // nineInital = nineInital.markBoard(3, 0);
        // nineInital = nineInital.markBoard(0, 3);
        // nineInital = nineInital.markBoard(3, 1);
        // nineInital = nineInital.markBoard(1, 4);
        // nineInital = nineInital.markBoard(4, 0);
        // nineInital = nineInital.markBoard(0, 4);
        // nineInital = nineInital.markBoard(4, 1);//o

        // nineInital = nineInital.markBoard(1, 5);//x

        // System.out.println("next board is " + nineInital.nextBoard);
        // System.out.println("next actions are " + nineInital.getAction().get(0));
        // System.out.println("next depth" + nineInital.depth);
        // System.out.println("current evluation is " + nineInital.evaluate(2));
        // System.out.println("current utility is " + nineInital.getUtility());
        // nineInital = nineInital.markBoard(5, 0);//o
        // nineInital = nineInital.markBoard(0, 5);//x
        // nineInital.printNineBoard();

        // double[] AIdecision = HalphaBetaMinimax(nineInital, 0);
        // System.out.println("on Board " + AIdecision[2] + " play " + AIdecision[1] + "
        // w/ score " + AIdecision[0]);

    }
}
