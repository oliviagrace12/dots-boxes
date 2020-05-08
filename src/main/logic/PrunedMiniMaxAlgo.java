package logic;

import domain.Grid;
import domain.GridSpot;
import domain.Player;

/**
 * Created by oliviachisman on 5/7/20
 */
public class PrunedMiniMaxAlgo {

    private final int depth;

    public PrunedMiniMaxAlgo(int depth) {
        this.depth = depth;
    }

    public int[] nextMove(Grid grid) {
        Node next =  miniMax(grid, depth, false, Integer.MIN_VALUE, Integer.MAX_VALUE);

        return new int[]{next.row, next.col};
    }

    private Node miniMax(Grid grid, int depth, boolean isMaximizing, Integer alpha, Integer beta) {
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
                            Node childNode = miniMax(gridCopy, depth - 1, false, alpha, beta);
                            if (childNode.eval > maxNode.eval) {
                                childNode.row = row;
                                childNode.col = col;
                                maxNode = childNode;
                            }
                            alpha = Math.max(alpha, childNode.eval);
                            if (alpha >= beta) {
                                return maxNode;
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
                            gridCopy.playMove(row, col, Player.MIN);
                            Node childNode = miniMax(gridCopy, depth - 1, true, alpha, beta);
                            if (childNode.eval < minNode.eval) {
                                childNode.row = row;
                                childNode.col = col;
                                minNode = childNode;
                            }
                            beta = Math.min(beta, childNode.eval);
                            if (beta <= alpha) {
                                return minNode;
                            }
                        }
                    }
                }
            }
            return minNode;
        }
    }

    private class Node {
        Integer row;
        Integer col;
        Integer eval;

        Node(int eval) {
            this.eval = eval;
        }
    }
}
