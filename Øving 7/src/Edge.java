public class Edge {
    Edge next;
    Node to;

    public Edge(Node n, Edge next) {
        to = n;
        this.next = next;
    }
}
