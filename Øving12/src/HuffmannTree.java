import java.util.*;

public class HuffmannTree {
    PriorityQueue<Node> priorityQueue;

    public HuffmannTree() {
        //The priorityQueue will work as a maxHeap
        this.priorityQueue = new PriorityQueue<>(new SortByFrequency());
    }

    public void makePriQueue(ArrayList<Node> a) {
        Collections.sort(a, new SortByFrequency());
        for (Node n : a) {
            priorityQueue.add(n);
        }
    }

    public void printItOut() {
        System.out.println(Arrays.toString(priorityQueue.toArray()));
    }

}