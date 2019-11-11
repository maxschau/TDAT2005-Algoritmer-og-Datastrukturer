import java.util.Comparator;

public class SortByFrequency implements Comparator<Node> {
    public int compare(Node a, Node b) {
        return a.frequency - b.frequency;
    }
}
