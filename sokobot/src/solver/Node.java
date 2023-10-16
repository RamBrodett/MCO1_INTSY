package solver;

/*
    This node class will represent a state in the game.
    @var itemData - is the reference of the current coordinates of movable items
    @var parent is the reference of node's parent for tracking the path,
    @var actions represents the string moves taken to reach
    that state.
 */

import java.util.ArrayList;

public class Node{
    private final int height;
    private final int width;
    private final String actions;
    private final char[][] itemData;
    private int locX;
    private  int locY;
    private final char[][] mapData;

    private int cost;


    public Node(char[][]mapData, char[][]itemsData, String actions){

        //Dimension of the state
        this.height = itemsData.length;
        this.width = itemsData[0].length;

        //String of the actions done to reach this state.
        this.actions = actions;

        //MAIN STRUCTURE OF STATE
        this.itemData = itemsData;
        this.mapData = mapData;

        this.cost = 0;

        //To get the height and width of the map.
        for(int i = 0; i<height; i++){
            for(int j = 0; j<width; j++){
                if(itemsData[i][j]=='@'){
                    locX = i;
                    locY = j;
                }
            }
        }
    }

    public void addCostToNode(int cost){
        this.cost = cost;
    }

    public char[][] getItemData() {
        return itemData;
    }

    public String getActions(){
        return actions;
    }

    public int getLocX() {
        return locX;
    }

    public int getLocY() {
        return locY;
    }

    public int getCost() {
        return cost;
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

    // Gets the valid moves
    public ArrayList<Character> getValidMoves(){

        char upMap1 = mapData[locX - 1][locY];
        char upItem1 = itemData[locX - 1][locY];

        char downMap1 = mapData[locX + 1][locY];
        char downItem1 = itemData[locX + 1][locY];

        char leftMap1 = mapData[locX][locY - 1];
        char leftItem1 = itemData[locX][locY - 1];

        char rightMap1 = mapData[locX][locY + 1];
        char rightItem1 = itemData[locX][locY + 1];


        ArrayList<Character> validMoves = new ArrayList<>();
        //check up
        if(upMap1!='#'){

            if ((upMap1 == ' '|| upMap1 == '.')&&
                    upItem1 != '$'){
                validMoves.add('u');
            }
            char upMap2 = mapData[locX - 2][locY];
            char upItem2 = itemData[locX - 2][locY];

            //  if the next position has a box, check if the next upper location
            //  is free to be possible to move
            if( upItem1 == '$' && (upMap2 == ' '||upMap2 == '.')&&
                    upItem2 != '$'){
                validMoves.add('u');
            }
        }

        //check down
        if(downMap1!='#'){

            if ((downMap1 == ' '|| downMap1 == '.')&&
                   downItem1 != '$'){
                validMoves.add('d');
            }

            char downMap2 = mapData[locX + 2][locY];
            char downItem2 = itemData[locX + 2][locY];

            //  if the next position has a box, check if the next upper location
            //  is free to be possible to move
            if( downItem1 == '$' &&
                    (downMap2 == ' '|| downMap2 == '.') &&
                            downItem2 != '$'){
                validMoves.add('d');
            }
        }

        //check left
        if(leftMap1!='#'){

            if ((leftMap1 == ' '|| leftMap1 == '.')&&
                    leftItem1 != '$'){
                validMoves.add('l');
            }

            char leftMap2 = mapData[locX][locY - 2];
            char leftItem2 = itemData[locX][locY - 2];

            //  if the next position has a box, check if the next upper location
            //  is free to be possible to move
            if( leftItem1 == '$' &&
                    (leftMap2 == ' '|| leftMap2 == '.')&&
                    leftItem2 != '$'){
                validMoves.add('l');
            }
        }

        //check right
        if(rightMap1!='#'){

            if ((rightMap1== ' '|| rightMap1 == '.')&&
                    rightItem1 != '$'){
                validMoves.add('r');
            }

            char rightMap2 = mapData[locX][locY + 2];
            char rightItem2 = itemData[locX][locY + 2];
            //  if the next position has a box, check if the next upper location
            //  is free to be possible to move
            if( rightItem1 == '$' &&
                    (rightMap2 == ' '|| rightMap2 == '.')&&
                            rightItem2 != '$'){
                validMoves.add('r');
            }
        }

        return validMoves;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node other) {
            return this.locX == other.locX && this.locY == other.locY &&
                    java.util.Arrays.deepEquals(this.itemData, other.itemData);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return java.util.Arrays.deepHashCode(itemData) + locX + locY;
    }


}

