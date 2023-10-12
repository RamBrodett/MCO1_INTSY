package solver;

/*
    This node class represents a state in the game.
    @var itemData - is the reference of the current coordinates of movable items
    @var parent is the reference of node's parent for tracking the path,
    @var actions represents the string moves taken to reach
    that state.
 */

import java.util.ArrayList;

public class Node {
    private int height;
    private int width;
    private String actions;
    private char[][] itemData;
    private int locX;
    private  int locY;
    private char[][] mapData;
    private Node parentNode;

    public Node(char[][]mapData, char[][]itemsData, String actions, Node parentNode){
        /*
            Idea here is to copy all the actions done so far, copy the
            latest state after implementation of actions, save a pointer to
            previous state for backtracking in frontier.
         */

        //String of the actions done to reach this state.
        this.height = itemsData.length;
        this.width = itemsData[0].length;

        this.actions = actions;

        //MAIN STRUCTURE OF STATE
        this.itemData = itemsData;
        this.mapData = mapData;

        //Parent of this node state
        this.parentNode = parentNode;


        for(int i = 0; i<height; i++){
            for(int j = 0; j<width; j++){
                if(itemsData[i][j]=='@'){
                    locX = i;
                    locY = j;
                }
            }
        }
    }

    // Checks every crate seen in the map structure and validates
    // if it is in the goal states.
    public boolean isGoalState(){
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                if (itemData[i][j] == '$' && mapData[i][j] != '.'){
                    return false;
                }
            }
        }
        return true;
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

    public ArrayList<Character> getValidMoves(){

        ArrayList<Character> validMoves = new ArrayList<>();
        //check up
        if(mapData[locX-1][locY]!='#'){

            if ((mapData[locX-1][locY] == ' '|| mapData[locX-1][locY] == '.')&&
                    itemData[locX-1][locY] != '$'){
                validMoves.add('u');
            }

            //  if the next position has a box, check if the next upper location
            //  is free to be possible to move
            if( itemData[locX-1][locY] != '$' &&
                    (mapData[locX-2][locY] == ' '|| mapData[locX-2][locY] == '.')&&
                            itemData[locX-2][locY] != '$'){
                validMoves.add('u');
            }
        }

        //check down
        if(mapData[locX+1][locY]!='#'){

            if ((mapData[locX+1][locY] == ' '|| mapData[locX-1][locY] == '.')&&
                    itemData[locX+1][locY] != '$'){
                validMoves.add('d');
            }

            //  if the next position has a box, check if the next upper location
            //  is free to be possible to move
            if( itemData[locX+1][locY] == '$' &&
                    (mapData[locX+2][locY] == ' '|| mapData[locX+2][locY] == '.') &&
                            itemData[locX+2][locY] != '$'){
                validMoves.add('d');
            }
        }

        //check left
        if(mapData[locX][locY-1]!='#'){

            if ((mapData[locX][locY-1] == ' '|| mapData[locX][locY-1] == '.')&&
                    itemData[locX][locY-1] != '$'){
                validMoves.add('l');
            }

            //  if the next position has a box, check if the next upper location
            //  is free to be possible to move
            if( itemData[locX][locY-1] == '$' &&
                    (mapData[locX][locY-2] == ' '|| mapData[locX][locY-2] == '.')&&
                    itemData[locX][locY-2] != '$'){
                validMoves.add('l');
            }
        }

        //check right
        if(mapData[locX][locY+1]!='#'){

            if ((mapData[locX][locY+1] == ' '|| mapData[locX][locY+1] == '.')&&
                    itemData[locX][locY+1] != '$'){
                validMoves.add('r');
            }

            //  if the next position has a box, check if the next upper location
            //  is free to be possible to move
            if( itemData[locX][locY+1] == '$' &&
                    (mapData[locX][locY+2] == ' '|| mapData[locX][locY+2] == '.')&&
                            itemData[locX][locY+2] != '$'){
                validMoves.add('r');
            }
        }

        return validMoves;
    }

    public int getLocX() {
        return locX;
    }

    public int getLocY() {
        return locY;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node) {
            Node other = (Node) obj;
            return this.locX == other.locX && this.locY == other.locY &&
                    java.util.Arrays.deepEquals(this.itemData, other.itemData);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return java.util.Arrays.deepHashCode(itemData) + locX + locY;
    }

/*
    public void printItemdata(){

        System.out.println("--------------------------------------");
        for (int i =0; i<itemData.length;i++){
            for (int j =0; j<itemData[0].length;j++)
                System.out.print(itemData[i][j]);
            System.out.println();
        }
        System.out.println("--------------------------------------");
    }
 */
}

