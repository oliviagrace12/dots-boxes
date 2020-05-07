import domain.Grid;
import domain.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by oliviachisman on 5/4/20
 */
public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Starting up dots and boxes game");

        Grid grid = new Grid(3);

        System.out.println(grid);

        String input;
        while (true) {
            System.out.println("Please enter a move in the form of row,column");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            input = reader.readLine();
            String[] nums = input.split(",");

            grid.addEdge(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]), Player.MAX);
            grid.checkForBoxes();

            System.out.println("Score >> MAX: " + grid.getMaxScore() + ", MIN: " + grid.getMinScore());
            System.out.println(grid);

        }

    }
}
