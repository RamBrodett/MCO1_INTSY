package solver;

import java.util.NoSuchElementException;

public class QueueFrontier extends StackFrontier {
    public QueueFrontier() {
    }

    @Override
    public Node remove() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Empty frontier");
        } else {
            return this.frontier.remove(0);
        }
    }
}