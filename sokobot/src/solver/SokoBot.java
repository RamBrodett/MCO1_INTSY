package solver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SokoBot {

  // Generate a  QUEUE Frontier for bfs
  PrioQueueFrontier frontier;
  HeuristicFunction hf;


  // Dimension of the map
  int height;
  int width;

  // Sokobot Constructor
  public SokoBot(){
    frontier = new PrioQueueFrontier();
    hf  = new HeuristicFunction();
  }

  /*
    Solves the maze using searching algo.

    @param width and height is the dimension of the map
    @param mapData contains the structure of map meaning it only contains either '#',(empty space),'.'
                   which tells us if that coordinate is accessible by the player(player can
                   move through that space) ** only # is not accessible **
    @param itemsData contains the following data (these are the only moving components)
            - @ : player
            - $ : crate (a crate is movable even if in a goal space already, cannot be moved
                         when a crate or wall is blocking its way of direction)
   */
  public String solveSokobanPuzzle(int width, int height, char[][] mapData, char[][] itemsData) {
    this.height = height;
    this.width = width;

    // Creates the initial state
    Node initialState = new Node(mapData,itemsData,"",null);
    List<int[]> goalCoordinates = new ArrayList<>();

    for(int i = 0; i< initialState.getItemData().length;i++){
      for(int j = 0; j < initialState.getItemData()[0].length;j++){
        if(mapData[i][j]=='.') {
          goalCoordinates.add(new int[]{i,j});
        }
      }
    }

    initialState.addCostToNode(hf.getHeuristicCost(initialState, goalCoordinates));

    // To keep Track of visited nodes
    HashMap<Node,Boolean> visited = new HashMap<>();

    // Adds the initial state to frontier to explore state
    frontier.addNode(initialState);

    //  looping exploration of states available in frontier
    while(!frontier.isEmpty()){

      // Gets the first element in frontier (FIFO format)
      Node current_State = frontier.remove();

      //Checks if the current state is already the goal state.
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
        child.addCostToNode(hf.getHeuristicCost(child, goalCoordinates));

        if(!visited.containsKey(child)){
          frontier.addNode(child);
          visited.put(child,true);
        }
      }
    }

    return " "; // It will not move if solution is not found.
  }

  // Deep clones an array to refrain from altering previous state
  public char[][] deepcloneItems(char[][]arrSRC){
    // get the row length and get the column length
    char[][] clone = new char[height][width];
    for(int i = 0; i < arrSRC.length; i++){
      System.arraycopy(arrSRC[i],0,clone[i],0,arrSRC[0].length);
    }
    return clone;
  }

  // Produces the news state base on the action done.
  public char[][] succState(char action, char[][]itemData,
                            int locX, int locY){

    char[][] newState = deepcloneItems(itemData);

    switch(action){

      case 'u' -> { //upward movement
        if(itemData[locX-1][locY] != '$') {
          newState[locX-1][locY] = '@';
        }
        //moves the crate forward if there is a crate
        else{
          newState[locX-2][locY] = '$';
          newState[locX-1][locY] = '@';
        }
      }
      case 'd' ->{ //downward movement
        if(itemData[locX+1][locY] != '$') {
          newState[locX+1][locY] = '@';
        }
        //moves the crate forward if there is a crate
        else{
          newState[locX+2][locY] = '$';
          newState[locX+1][locY] = '@';
        }
      }
      case 'l' ->{ // leftward movement
        if(itemData[locX][locY-1] != '$') {
          newState[locX][locY-1] = '@';
        }
        //moves the crate forward if there is a crate
        else{
          newState[locX][locY-2] = '$';
          newState[locX][locY-1] = '@';
        }
      }
      case 'r'-> { //rightward movement
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
    // clears the previous position of the agent.
    newState[locX][locY] = ' ';
    return newState;
  }


}
