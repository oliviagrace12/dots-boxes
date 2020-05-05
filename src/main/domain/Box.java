package domain;

/**
 * Created by oliviachisman on 5/4/20
 */
public class Box {

    private Edge rightEdge;
    private Edge leftEdge;
    private Edge upEdge;
    private Edge downEdge;

    public Edge getRightEdge() {
        return rightEdge;
    }

    public void setRightEdge(Edge rightEdge) {
        this.rightEdge = rightEdge;
    }

    public Edge getLeftEdge() {
        return leftEdge;
    }

    public void setLeftEdge(Edge leftEdge) {
        this.leftEdge = leftEdge;
    }

    public Edge getUpEdge() {
        return upEdge;
    }

    public void setUpEdge(Edge upEdge) {
        this.upEdge = upEdge;
    }

    public Edge getDownEdge() {
        return downEdge;
    }

    public void setDownEdge(Edge downEdge) {
        this.downEdge = downEdge;
    }
}
