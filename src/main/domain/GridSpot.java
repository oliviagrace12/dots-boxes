package domain;

/**
 * Created by oliviachisman on 5/4/20
 */
public class GridSpot {

    private String gridChar;
    private Integer boxValue;
    private Player player;

    public GridSpot(String gridChar, Player player) {
        this.gridChar = gridChar;
        this.player = player;
    }

    public GridSpot(String gridChar) {
        this.gridChar = gridChar;
    }

    public GridSpot(String gridChar, Integer boxValue) {
        this.gridChar = gridChar;
        this.boxValue = boxValue;
    }

    public String getGridChar() {
        return gridChar;
    }

    public Integer getBoxValue() {
        return boxValue;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return gridChar;
    }
}
