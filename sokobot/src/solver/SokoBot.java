package solver;
import java.util.ArrayList;
import java.util.HashMap;

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
    if (bfsApproach(mapData,itemsData) != null){
      return path =bfsApproach(mapData,itemsData);
    }


    try {

      Thread.sleep(3000);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return "lrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlrlr"; // if it finds no solution it will jiggle
    //common cause is that it was trap to  no possible moves anymore because of wrong moves to a wall
  }

  public String bfsApproach( char[][] mapData, char[][] itemsData){

    Node initialState = new Node(mapData,itemsData,"",null);

    // for visited nodes(State)
    int state_explored = 0;
    HashMap<Node,Boolean> visited = new HashMap<>();

    qf.add(initialState);

    while(!qf.isEmpty()){
      Node current_State = qf.remove();
      current_State.printItemdata();
      System.out.println();
      System.out.println();
      current_State.printMapdata();
      System.out.println("NEXT STATE");

      if(current_State.isGoalState()){
        return current_State.getActions();
      }
      
      ArrayList<Character> currStatePossibleMoves = current_State.getValidMoves();

      for (char move: currStatePossibleMoves) {

        Node child = new Node(mapData,
                              succState(move,current_State.getItemData(),
                              current_State.getLocX(),
                              current_State.getLocY()),current_State.getActions() + move,
                              current_State);
        if(!visited.containsKey(child)){
          qf.add(child);
          visited.put(child,true);
          state_explored++;
        }
      }
    }



    return null;
  }

  public char[][] deepcloneItems(char[][]arrSRC){
    // get the row length and get the column length
    char[][] clone = new char[arrSRC.length][arrSRC[0].length];
    for(int i = 0; i < arrSRC.length; i++){
      System.arraycopy(arrSRC[i],0,clone[i],0,arrSRC[0].length);
    }
    return clone;
  }

  public char[][] succState(char action, char[][]itemData,
                            int locX, int locY){

    char[][] newState = deepcloneItems(itemData);

    switch(action){
      case 'u' -> {
        if(itemData[locX-1][locY] != '$') {
          newState[locX-1][locY] = '@';
        }
        //moves the crate forward if there is a crate
        else{
          newState[locX-2][locY] = '$';
          newState[locX-1][locY] = '@';
        }
      }
      case 'd' ->{
        if(itemData[locX+1][locY] != '$') {
          newState[locX+1][locY] = '@';
        }
        //moves the crate forward if there is a crate
        else{
          newState[locX+2][locY] = '$';
          newState[locX+1][locY] = '@';
        }
      }
      case 'l' ->{
        if(itemData[locX][locY-1] != '$') {
          newState[locX][locY-1] = '@';
        }
        //moves the crate forward if there is a crate
        else{
          newState[locX][locY-2] = '$';
          newState[locX][locY-1] = '@';
        }
      }
      case 'r'-> {
        if(itemData[locX][locY+1] != '$') {
          newState[locX][locY+1] = '@';
        }
        //moves the crate forward if there is a crate
        else{
          newState[locX][locY+2] = '$';
          newState[locX][locY+1] = '@';
        }
      }
    }
    newState[locX][locY] = ' ';
    return newState;
  }


}
