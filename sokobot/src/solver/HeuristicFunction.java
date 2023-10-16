package solver;


import java.awt.*;
import java.util.HashSet;
import java.util.Set;


public class HeuristicFunction {

    Set<Point> goalCoordinates;
    HeuristicFunction(Set<Point> goalCoordinates){
        this.goalCoordinates = goalCoordinates;
    }


    private int manhattanDistance(Set<Point> cratesCoordinates){
        //create storage of coordinates for the goals and crates
        int aggregatedDistance = 0;

        // now for every crates find the nearest distance to a goal

        for(Point crateLoc : cratesCoordinates){
            int min = 10000;
            for(Point goalLoc : goalCoordinates){
                int temp = (int) (Math.abs(crateLoc.getX()-goalLoc.getX()) +
                                  Math.abs(crateLoc.getY() -goalLoc.getY()));
                if (temp < min) min = temp;
            }
            aggregatedDistance += min;

        }

        return aggregatedDistance;
    }



    public int getHeuristicCost(Node node){
        char[][] itemData = node.getItemData();

        Set<Point> cratesCoordinates = new HashSet<>();

        for(int i = 0; i< itemData.length;i++){
            for(int j = 0; j<itemData[0].length;j++){
                if (itemData[i][j] == '$'){
                    cratesCoordinates.add(new Point(i,j));
                }
            }
        }
        return manhattanDistance(cratesCoordinates);
    }
}
