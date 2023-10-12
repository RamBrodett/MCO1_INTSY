package solver;


/* --- this is a rough idea -- suggest edits or new idea in making this code work
  Structure im trying to create is a bfs algo that starting from an initial state we will
  explore that possible states that we can achieve from applying a valid action. we will
  make use of a frontier to keep track of the oder of the state we are to explore, utilize
  a "while frontier is not empty" loop, create neighboring nodes by applying valid actions
  making a network. From there we will check if the current node being evaluated (visited)
  is the goal state (all creates are on goal marks).
 */

public class SokoBot {

  // generate a frontier, added queue at the mean time for bfs algo?
  QueueFrontier qf;

  // List<List<Boolean>> walls;

  public SokoBot(){
    qf = new QueueFrontier();
  }

  /*
    @param width and height is the dimension of the map
    @param mapData contains the structure of map meaning it only contains either '#',(empty space),'.'
    which tells us if that coordinate is accessible by the player(player can move through that space)
    ** only # is not accessible **
    @param itemsData contains the following data (these are the only moving components)
            - @ : player
            - $ : crate (a crate is movable even if in a goal space already, cannot be moved
                         when an item or wall is blocking its way of direction)
   */
  public String solveSokobanPuzzle(int width, int height, char[][] mapData, char[][] itemsData) {


    /*
     * YOU NEED TO REWRITE THE IMPLEMENTATION OF THIS METHOD TO MAKE THE BOT SMARTER
     */
    /*
     * Default stupid behavior: Think (sleep) for 3 seconds, and then return a
     * sequence
     * that just moves left and right repeatedly.
     */

    //LET'S DO MULTIPLE APPROACH FOR TESTING
    String path;
    path = bfsApproach(width,height,mapData,itemsData);


    try {

      Thread.sleep(3000);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return "lrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlr";
  }

  public String bfsApproach(int width, int height, char[][] mapData, char[][] itemsData){

    // starting location of the character -- we need this to know if the actions we will
    // apply to get the next state is valid and optimal?
    int startingPlaceX,startingPlaceY;

    for(int i = 0; i<height; i++){
      for(int j = 0; j<width; j++){
        if(itemsData[i][j]=='@'){
          startingPlaceX = i;
          startingPlaceY = j;
        }
      }
    }

    Node initialState = new Node(itemsData,"",null);

    qf.add(initialState);

    return "NONE";
  }

  public char[][] deepcloneItems(char[][]itemsData){
    // get the row length and get the column length
    char[][] clone = new char[itemsData.length][itemsData[0].length];
    for(int i = 0; i < itemsData.length; i++){
      System.arraycopy(itemsData[i],0,clone,0,itemsData[0].length);
    }
    return clone;
  }

  public boolean isMoveValid(int locX, int locY,char[][] mapData,char[][] itemsData){

    return false;
  }


}
