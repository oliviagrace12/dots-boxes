package domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by oliviachisman on 5/4/20
 */
public class Grid {

    // number of dots in the horizontal and vertical
    private int size;
    private Random random;
    private GridSpot[][] gridSpots;
    private HashMap<Player, Integer> scores;

    public Grid(int size) {
        this.size = size * 2 + 1;
        this.random = new Random();
        this.gridSpots = buildGrid();
        this.scores = new HashMap<>();
        scores.put(Player.MAX, 0);
        scores.put(Player.MIN, 0);
    }

    public int getMaxScore() {
        return scores.get(Player.MAX);
    }

    public int getMinScore() {
        return scores.get(Player.MIN);
    }

    public void addEdge(int row, int col, Player player) {
        if (!isEdge(row, col) && row < size && col < size) {
            System.out.println("(" + row + ", " + col + ") is not an edge. Please choose an edge:");
        } else {
            gridSpots[row][col] = new GridSpot((row % 2 == 0 ? "---" : "|"), player);
        }
    }

    public void checkForBoxes() {
        scores.put(Player.MAX, 0);
        scores.put(Player.MIN, 0);
        for (int row = 0; row < size - 1; row++) {
            for (int col = 0; col < size; col++) {
                if (isFilledHorizEdge(row, col)) {
                    Player player = gridSpots[row][col].getPlayer();
                    if (ownsLowerLeft(row, col, player) && ownsLowerRight(row, col, player) && ownsBelow(row, col, player)) {
                        scores.put(player, scores.get(player) + getWeight(row, col));
                    }
                }
            }
        }
    }

    private Integer getWeight(int row, int col) {
        return gridSpots[row + 1][col].getBoxValue();
    }

    private boolean ownsBelow(int row, int col, Player player) {
        GridSpot lowerEdge = gridSpots[row + 2][col];
        return lowerEdge != null && lowerEdge.getPlayer() != null && lowerEdge.getPlayer().equals(player);
    }

    private boolean ownsLowerRight(int row, int col, Player player) {
        GridSpot rightEdge = gridSpots[row + 1][col + 1];
        return rightEdge != null && rightEdge.getPlayer() != null && rightEdge.getPlayer().equals(player);
    }

    private boolean ownsLowerLeft(int row, int col, Player player) {
        GridSpot leftEdge = gridSpots[row + 1][col - 1];
        return leftEdge != null && leftEdge.getPlayer() != null && leftEdge.getPlayer().equals(player);
    }

    private boolean isFilledHorizEdge(int row, int col) {
        return row % 2 == 0 && col % 2 != 0 && gridSpots[row][col] != null && isEdge(row, col);
    }

    private GridSpot[][] buildGrid() {
        GridSpot[][] gridSpots = new GridSpot[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (isDot(row, col)) {
                    gridSpots[row][col] = new GridSpot("*");
                } else if (isCenter(row, col)) {
                    int weight = getRandomWeight();
                    gridSpots[row][col] = new GridSpot(" " + weight + " ", weight);
                }
            }
        }
        return gridSpots;
    }

    private Integer getRandomWeight() {
        return random.nextInt(5) + 1;
    }

    private boolean isEdge(int row, int col) {
        return row % 2 == 0 ^ col % 2 == 0;
    }

    private boolean isDot(int row, int col) {
        return row % 2 == 0 && col % 2 == 0;
    }

    private boolean isCenter(int row, int col) {
        return row % 2 != 0 && col % 2 != 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("    ");
        for (int i = 0; i < size; i++) {
            builder.append(i).append(" ");
        }
        builder.append("\n    ");
        for (int i = 0; i < size; i++) {
            builder.append("--");
        }
        builder.append("\n");
        for (int row = 0; row < size; row++) {
            builder.append(row).append(" | ");
            for (int col = 0; col < size; col++) {
                if (gridSpots[row][col] == null) {
                    if (row % 2 == 0) {
                        builder.append("   ");
                    } else {
                        builder.append(" ");
                    }
                } else {
                    builder.append(gridSpots[row][col].getGridChar());
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }

}
