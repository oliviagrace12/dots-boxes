import domain.Grid;
import domain.Player;
import logic.MiniMaxAlgo;
import logic.PrunedMiniMaxAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by oliviachisman on 5/4/20
 */
public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Starting up dots and boxes game");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please enter a number for the size board you want: ");
        int size = Integer.parseInt(reader.readLine());
        Grid grid = new Grid(size);

        System.out.println("Please enter a number for the AI depth of search: ");
        int depth = Integer.parseInt(reader.readLine());
        PrunedMiniMaxAlgo miniMaxAlgo = new PrunedMiniMaxAlgo(depth);

        System.out.println("Starting board:");
        System.out.println(grid);

        String input;
        while (grid.getTurnsLeft() > 0){
            System.out.println("Please enter a move in the form of row,column");
            input = reader.readLine();
            String[] nums = input.split(",");

            grid.playMove(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]), Player.MAX);

            System.out.println("Your move: [Score >> You: " + grid.getMaxScore() + ", AI: " + grid.getMinScore()+ "]");
            System.out.println(grid);

            int[] aiMove = miniMaxAlgo.nextMove(grid);
            grid.playMove(aiMove[0], aiMove[1], Player.MIN);

            System.out.println("AI move: [Score >> You: " + grid.getMaxScore() + ", AI: " + grid.getMinScore()+ "]");
            System.out.println(grid);
        }

        System.out.println("WINNER: " + (grid.getMaxScore() > grid.getMinScore() ? "You" : "AI"));
        System.out.println("Final Score >> You: " + grid.getMaxScore() + ", AI: " + grid.getMinScore()+ "]");
    }
}
