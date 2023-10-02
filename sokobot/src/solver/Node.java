package solver;

/*
    This node class represents a state in the game.
    @var state - is the reference of its coordinate(x,y)
    @var parent is the reference of node's parent for tracking the path,
    @var action represents the move taken to reach
    that state, can either be u(up),d(down),l(left),r(right).
 */

public class Node {
    private final int[] state;
    private final Node parent;
    private final char action;

    public Node(int[] state, Node parent, char action){
        this.state = new int[state.length];
        System.arraycopy(state,0,this.state,0,state.length);
        this.parent = parent;
        this.action = action;
    }

    public int[] getState(){
        return this.state;
    }

    public Node getParent(){
        return this.parent;
    }

    public char getAction(){
        return this.action;
    }

}
