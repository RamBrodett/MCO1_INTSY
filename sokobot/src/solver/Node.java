package solver;

/*
    This node class represents a state in the game.
    @var itemData - is the reference of the current coordinates of movable items
    @var parent is the reference of node's parent for tracking the path,
    @var actions represents the string moves taken to reach
    that state.
 */

public class Node {
     private String actions;
    private char[][] itemData;
    private Node parentNode;

    public Node(char[][]itemsData, String actions, Node parentNode){
        /*
            Idea here is to copy all the actions done so far, copy the
            latest state after implementation of actions, save a pointer to
            previous state for backtracking in frontier.
         */

        //String of the actions done to reach this state.
        this.actions = actions;

        //MAIN STRUCTURE OF STATE
        this.itemData = itemsData;

        //Parent of this node state
        this.parentNode = parentNode;
    }


    public char[][] getItemData() {
        return itemData;
    }

    public String getActions(){
        return actions;
    }

    public Node getParentNode() {
        return parentNode;
    }
}
