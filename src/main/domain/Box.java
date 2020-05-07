package domain;

/**
 * Created by oliviachisman on 5/4/20
 */
public class Box {

    private final int weight;
    private GridSpot rightEdge;
    private GridSpot leftEdge;
    private GridSpot topEdge;
    private GridSpot bottomEdge;

    public Box(int weight) {
        this.weight = weight;
    }

    public GridSpot getRightEdge() {
        return rightEdge;
    }

    public void setRightEdge(GridSpot rightEdge) {
        this.rightEdge = rightEdge;
    }

    public GridSpot getLeftEdge() {
        return leftEdge;
    }

    public void setLeftEdge(GridSpot leftEdge) {
        this.leftEdge = leftEdge;
    }

    public GridSpot getTopEdge() {
        return topEdge;
    }

    public void setTopEdge(GridSpot topEdge) {
        this.topEdge = topEdge;
    }

    public GridSpot getBottomEdge() {
        return bottomEdge;
    }

    public void setBottomEdge(GridSpot bottomEdge) {
        this.bottomEdge = bottomEdge;
    }

    @Override
    public String toString() {
        return (topEdge == null ? ".   ." : ".___.") + "\n" +
                (rightEdge == null ? " " : "|") + " " + weight + " " + (leftEdge == null ? " " : "|") + "\n" +
                (bottomEdge == null ? ".   ." : ".___.");
    }
}
