package solver;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Arrays;

public class PrioQueueFrontier {

    PriorityQueue<Node> priorityQueue;

    PrioQueueFrontier(){
        priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));
    }

    public void  addNode(Node node){
        priorityQueue.add(node);
    }

    public boolean containsState(char[][]itemData){
        for(Node node : priorityQueue){
            if (Arrays.deepEquals(itemData, node.getItemData())){
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty(){
        return priorityQueue.isEmpty();
    }

    public Node remove(){
        if(isEmpty()){
            throw new NoSuchElementException("Empty frontier");
        }else{
            return priorityQueue.poll();
        }
    }

}
