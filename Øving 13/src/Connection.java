import java.util.Comparator;

public class Connection {
    int connection;
    int length;
    Connection next;

    public Connection(int connection, int length) {
        this.connection = connection;
        this.length = length;
    }

    void addConnection(int connection, int weight) {
        if (next == null) {
            next = new Connection(connection, weight);
        } else {
            next.addConnection(connection, weight);
        }
    }

}

class LengthFromSource implements Comparator<Integer> {
    int[] lengthFromSource;

    LengthFromSource(int[] lengthFromSource) {
        this.lengthFromSource = lengthFromSource;
    }

    @Override
    public int compare(Integer node1, Integer node2){
        return lengthFromSource[node1] - lengthFromSource[node2];
    }
}

class LengthBetweenNodes implements Comparator<Integer> {
    int[] lengtFromSource;
    int[] distanceTo;

    LengthBetweenNodes(int[] lengtFromSource, int[] distanceTo) {
        this.lengtFromSource = lengtFromSource;
        this.distanceTo = distanceTo;
    }

    @Override
    public int compare(Integer node1, Integer node2) {
        return distanceTo[node1] + lengtFromSource[node1] - distanceTo[node2] - lengtFromSource[node2];
    }
}