package solver;
import java.awt.*;
import java.util.*;
import java.util.List;

public class SokoBot {

  // Generate a  QUEUE Frontier for bfs
  PriorityQueue<Node> frontier;
  HeuristicFunction hf;
  Set<Point> goalCoordinates;

  // Dimension of the map
  int height;
  int width;

  // Sokobot Constructor
  public SokoBot(){
    frontier = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));
    goalCoordinates = new HashSet<>();
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
    this.goalCoordinates = getGoalCoordinates(mapData, height, width);
    hf = new HeuristicFunction(goalCoordinates);

    // Creates the initial state
    Node initialState = new Node(mapData,itemsData,"",null);

    initialState.addCostToNode(hf.getHeuristicCost(initialState));

    // To keep Track of visited nodes
    Set<Node> visited = new HashSet<>();

    // Adds the initial state to frontier to explore state
    frontier.add(initialState);

    //  looping exploration of states available in frontier
    while(!frontier.isEmpty()){

      // Gets the first element in frontier (FIFO format)
      Node current_State = frontier.remove();
      visited.add(current_State);

      //Checks if the current state is already the goal state.
      if(current_State.isGoalState()){
        return current_State.getActions();
      }

      //Gets the valid moves for the state
      ArrayList<Character> currStatePossibleMoves = current_State.getValidMoves();

      for (char move: currStatePossibleMoves) {

        Node child = new Node(mapData,
                succState(move,current_State.getItemData(),
                        current_State.getLocX(),
                        current_State.getLocY()),current_State.getActions() + move,
                current_State);

        if(!visited.contains(child)){
          child.addCostToNode(hf.getHeuristicCost(child));// getting cost consumes time - to cut off time
                                                          // only add when it is not visited.
          frontier.add(child); //adds the node to the priority queue, already compares which to prioritize first
          //visited.add(child); // list the nodes as visited.
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

    int newXLoc = locX;
    int newYLoc = locY;

    switch(action){
      case 'u' -> { //upward movement
        newXLoc--;
      }
      case 'd' ->{ //downward movement
        newXLoc++;
      }
      case 'l' ->{ // leftward movement
        newYLoc--;
      }
      case 'r'-> { //rightward movement
        newYLoc++;
      }
    }

    if(itemData[newXLoc][newYLoc] != '$') newState[newXLoc][newYLoc] = '@';
    else{
      int newCrateXLoc = newXLoc + (newXLoc-locX);
      int newCrateYLoc = newYLoc + (newYLoc-locY);
      newState[newCrateXLoc][newCrateYLoc] = '$';
      newState[newXLoc][newYLoc] = '@';
    }
    // clears the previous position of the agent.
    newState[locX][locY] = ' ';
    return newState;
  }

  public Set<Point> getGoalCoordinates(char[][]mapData, int height, int width){
    Set<Point> goalCoordinates = new HashSet<>();
    for(int i = 0; i< height;i++){
      for(int j = 0; j<width;j++){
        if(mapData[i][j]=='.') {
          goalCoordinates.add(new Point(i,j));
        }
      }
    }
    return goalCoordinates;
  }

}
