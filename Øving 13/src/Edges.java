public class Edges {
    Connection[] nodes;

    public Edges(int size) {
        this.nodes = new Connection[size];
    }


    void addEdge(Edge e) {
        if (nodes[e.from] == null) {
            nodes[e.from] = new Connection(e.to, e.runTime);
        } else {
            nodes[e.from].addConnection(e.to,e.runTime);
        }
    }
    void addNode(int index, int connection, int weight) {
        if (nodes[index] == null) {
            nodes[index] = new Connection(connection, weight);
        } else {
            nodes[index].addConnection(connection,weight);
        }
    }

}
