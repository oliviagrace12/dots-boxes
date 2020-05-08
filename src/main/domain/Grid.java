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
    private int turnsLeft;
    private Random random;
    private GridSpot[][] gridSpots;
    private HashMap<Player, Integer> scores;

    public Grid(int size) {
        this.size = size * 2 + 1;
        this.turnsLeft = 2 * size * (size + 1);
        this.random = new Random();
        this.gridSpots = buildGrid();
        this.scores = new HashMap<>();
        scores.put(Player.MAX, 0);
        scores.put(Player.MIN, 0);
    }

    public Grid deepCopy() {
        Grid gridCopy = new Grid((size - 1) / 2);
        gridCopy.scores.put(Player.MAX, this.scores.get(Player.MAX));
        gridCopy.scores.put(Player.MIN, this.scores.get(Player.MIN));
        copyGridSpots(gridCopy);

        return gridCopy;
    }

    private void copyGridSpots(Grid gridCopy) {
        for (int row = 0; row < this.size; row++) {
            for (int col = 0; col < this.size; col++) {
                if (isEdge(row, col)) {
                    GridSpot spot = gridSpots[row][col];
                    if (spot != null) {
                        gridCopy.gridSpots[row][col] = new GridSpot(spot.getGridChar(), spot.getPlayer());
                    }
                }
            }
        }
    }

    public int getMaxScore() {
        return scores.get(Player.MAX);
    }

    public int getMinScore() {
        return scores.get(Player.MIN);
    }

    public int getTurnsLeft() {
        return turnsLeft;
    }

    public void playMove(int row, int col, Player player) {
        addEdge(row, col, player);
        checkForBoxes(row, col, player);
    }

    public int getSize() {
        return size;
    }

    public GridSpot[][] getGridSpots() {
        return gridSpots;
    }

    public void setGridSpots(GridSpot[][] gridSpots) {
        this.gridSpots = gridSpots;
    }

    private void addEdge(int row, int col, Player player) {
        if (!isEdge(row, col) && row < size && col < size) {
            System.out.println("(" + row + ", " + col + ") is not an edge. Please choose an edge:");
        } else {
            gridSpots[row][col] = new GridSpot((row % 2 == 0 ? "---" : "|"), player);
            turnsLeft--;
        }
    }

    private void checkForBoxes(int row, int col, Player player) {
        if (isHorizontalEdge(row, col)) {
            if (row != 0) {
                if (isFilledAbove(row, col) && isFilledUpperRight(row, col) && isFilledUpperLeft(row, col)) {
                    scores.put(player, scores.get(player) + getWeightAbove(row, col));
                }
            }
            if (row != size - 1) {
                if (isFilledBelow(row, col) && isFilledLowerRight(row, col) && isFilledLowerLeft(row, col)) {
                    scores.put(player, scores.get(player) + getWeightBelow(row, col));
                }
            }
        } else {
            if (col != 0) {
                if (isFilledUpperLeft(row, col) && isFilledLowerLeft(row, col) && isFilledLeft(row, col)) {
                    scores.put(player, scores.get(player) + getWeightLeft(row, col));
                }
            }
            if (col != size - 1) {
                if (isFilledUpperRight(row, col) && isFilledLowerRight(row, col) && isFilledRight(row, col)) {
                    scores.put(player, scores.get(player) + getWeightRight(row, col));
                }
            }
        }
    }

    private Integer getWeightLeft(int row, int col) {
        return gridSpots[row][col - 1].getBoxValue();
    }

    private boolean isFilledLeft(int row, int col) {
        return gridSpots[row][col - 2] != null;
    }

    private Integer getWeightRight(int row, int col) {
        return gridSpots[row][col + 1].getBoxValue();
    }

    private boolean isFilledRight(int row, int col) {
        return gridSpots[row][col + 2] != null;
    }

    private Integer getWeightAbove(int row, int col) {
        return gridSpots[row - 1][col].getBoxValue();
    }

    private boolean isFilledUpperLeft(int row, int col) {
        return gridSpots[row - 1][col - 1] != null;
    }

    private boolean isFilledUpperRight(int row, int col) {
        return gridSpots[row - 1][col + 1] != null;
    }

    private boolean isFilledAbove(int row, int col) {
        return gridSpots[row - 2][col] != null;
    }

    private Integer getWeightBelow(int row, int col) {
        return gridSpots[row + 1][col].getBoxValue();
    }

    private boolean isFilledBelow(int row, int col) {
        return gridSpots[row + 2][col] != null;
    }

    private boolean isFilledLowerRight(int row, int col) {
        return gridSpots[row + 1][col + 1] != null;
    }

    private boolean isFilledLowerLeft(int row, int col) {
        return gridSpots[row + 1][col - 1] != null;
    }

    private boolean isHorizontalEdge(int row, int col) {
        return row % 2 == 0;
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

    public boolean isEdge(int row, int col) {
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
