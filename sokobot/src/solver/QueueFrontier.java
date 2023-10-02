package solver;

import java.util.NoSuchElementException;

public class QueueFrontier extends StackFrontier{
    public QueueFrontier(){
        super();
    }

    public Node remove(){
        if(isEmpty()){
            throw new NoSuchElementException("Empty frontier");
        }else{
            return frontier.remove(0);
        }
    }


}
