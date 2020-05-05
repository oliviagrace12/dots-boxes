package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oliviachisman on 5/4/20
 */
public class Grid {

    // number of dots in the horizontal and vertical
    private int size;
    private Box[][] boxes;
    private boolean[][] availableHorizontalEdgeSpots;
    private boolean[][] availableVerticalEdgeSpots;

    public Grid(int size) {
        this.size = size;
        this.boxes = createBoxes();
        ;
        this.availableHorizontalEdgeSpots = createHorizonalEdgeSpots(size);
        this.availableVerticalEdgeSpots = createVerticalSpots(size);
    }

    private boolean[][] createVerticalSpots(int size) {
        boolean[][] vertEdge = new boolean[size - 1][size];

        return new boolean[0][];
    }

    private boolean[][] createHorizonalEdgeSpots(int size) {
        return new boolean[0][];
    }

    public void addVerticalEdge(int row, int col) {
        // todo
    }

    public void addHorizontalEdge(int row, int col) {
        // todo
    }

    private List<Integer> createEdgeSpots(int size) {
        List<Integer> ints = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            ints.add(i);
        }
        return ints;
    }

    private Box[][] createBoxes() {
        Box[][] boxes = new Box[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                boxes[row][col] = new Box();
            }
        }
        return boxes;
    }
}
