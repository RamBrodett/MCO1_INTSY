package solver;

import java.util.ArrayList;
import java.util.List;

public class SokoBot {

  // generate a frontier,, added queue at the mean time for bfs algo?
  QueueFrontier qf;
  // track wall?? to have a list of constraint of possible moves
  // --Maybe boolean : True if wall and false if not
  // 2d list for the map
  List<List<Boolean>> walls;

  public SokoBot(){
    qf = new QueueFrontier();
    walls = new ArrayList<>();

    // ROUGH PSEUDO CODE
    // read the map file
    // get height and width first??
    //set a nested for loop
    /*
        for i to range(height):
          -List<Boolean> row = new ArrayList<>();

          -for j to range(width):
              -- define here an if else statement
              --that if at this coordinate this character is not a wall then
              --append FALSE in row, TRUE otherwise

          -append row to walls
     */

  }

  public String solveSokobanPuzzle(int width, int height, char[][] mapData, char[][] itemsData) {
    /*
     * YOU NEED TO REWRITE THE IMPLEMENTATION OF THIS METHOD TO MAKE THE BOT SMARTER
     */
    /*
     * Default stupid behavior: Think (sleep) for 3 seconds, and then return a
     * sequence
     * that just moves left and right repeatedly.
     */


    try {

      Thread.sleep(3000);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return "lrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlr";
  }

}
