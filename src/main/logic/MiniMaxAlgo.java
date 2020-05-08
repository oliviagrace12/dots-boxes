package logic;

import domain.Grid;
import domain.GridSpot;
import domain.Player;

/**
 * Created by oliviachisman on 5/7/20
 */
public class MiniMaxAlgo {

    private final int depth;

    public MiniMaxAlgo(int depth) {
        this.depth = depth;
    }

    public int[] nextMove(Grid grid) {
        Node next =  miniMax(grid, depth, false);

        return new int[]{next.row, next.col};
    }

    private Node miniMax(Grid grid, int depth, boolean isMaximizing) {
        if (depth == 0 || grid.getTurnsLeft() == 0) {
            return new Node(grid.getMaxScore() - grid.getMinScore());
        }

        if (isMaximizing) {
            Node maxNode = new Node(Integer.MIN_VALUE);
            for (int row = 0; row < grid.getSize(); row++) {
                for (int col = 0; col < grid.getSize(); col++) {
                    if (grid.isEdge(row, col)) {
                        GridSpot spot = grid.getGridSpots()[row][col];
                        if (spot == null) {
                            Grid gridCopy = grid.deepCopy();
                            gridCopy.playMove(row, col, Player.MAX);
                            Node childNode = miniMax(gridCopy, depth - 1, false);
                            if (childNode.eval > maxNode.eval) {
                                childNode.row = row;
                                childNode.col = col;
                                maxNode = childNode;
                            }
                        }
                    }
                }
            }
            return maxNode;
        } else {
            Node minNode = new Node(Integer.MAX_VALUE);
            for (int row = 0; row < grid.getSize(); row++) {
                for (int col = 0; col < grid.getSize(); col++) {
                    if (grid.isEdge(row, col)) {
                        GridSpot spot = grid.getGridSpots()[row][col];
                        if (spot == null) {
                            Grid gridCopy = grid.deepCopy();
                            gridCopy.playMove(row, col, Player.MAX);
                            Node childNode = miniMax(gridCopy, depth - 1, true);
                            if (childNode.eval < minNode.eval) {
                                childNode.row = row;
                                childNode.col = col;
                                minNode = childNode;
                            }
                        }
                    }
                }
            }
            return minNode;
        }
    }

    private int staticEval() {
        // todo
        return 0;
    }

    private class Node {
        Integer row;
        Integer col;
        Integer eval;

        public Node(int eval) {
            this.eval = eval;
        }

        public Node(int row, int col, int eval) {
            this.row = row;
            this.col = col;
            this.eval = eval;
        }
    }
}
