package solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class StackFrontier {
    ArrayList<Node> frontier;
    public StackFrontier(){
        frontier = new ArrayList<>();
    }

    public void add(Node node){
        this.frontier.add(node);
    }

    public boolean containsState(char[][]itemData){
        for(Node node : frontier){
            if (Arrays.deepEquals(itemData, node.getItemData())){
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty(){
        return frontier.isEmpty();
    }

    public Node remove(){
        if(isEmpty()){
            throw new NoSuchElementException("Empty frontier");
        }else{
            return frontier.remove(frontier.size()-1);
        }
    }



}
