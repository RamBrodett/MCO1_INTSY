package solver;

import java.util.ArrayList;
import java.util.List;

public class HeuristicFunction {

    private int manhattanDistance(char[][] itemData, char[][]mapData){
        //create storage of coordinates for the goals and crates
        int aggregatedDistance = 0;
        List<int[]> goalCoordinates = new ArrayList<>();
        List<int[]> cratesCoordinates = new ArrayList<>();

        for(int i = 0; i< itemData.length;i++){
            for(int j = 0; j<itemData[0].length;j++){
                if(mapData[i][j]=='.') {
                    int[] coordinate = {i,j};
                    goalCoordinates.add(coordinate);
                }
                if (itemData[i][j] == '$'){
                    int[] coordinate = {i,j};
                    cratesCoordinates.add(coordinate);
                }
            }
        }
        // now for every crates find the nearest distance to a goal

        for(int[] crateLoc : cratesCoordinates){
            int min = 10000;
            for(int[] goalLoc : goalCoordinates){
                int temp = Math.abs(crateLoc[0]-goalLoc[0]) + Math.abs(crateLoc[1]-goalLoc[1]);
                if (temp < min) min = temp;
            }
            aggregatedDistance += min;

        }

        return aggregatedDistance;
    }

    public int getHeuristicCost(Node node){

        return manhattanDistance(node.getItemData(), node.getMapData());
    }
}
