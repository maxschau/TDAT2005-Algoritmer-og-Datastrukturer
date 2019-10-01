import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Graph {
    int N;
    int E;

    Node[] node;

    public void ny_ugraf(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        node = new Node[N];
        for (int i = 0; i < N; i++) { //For each index we will create a new Node-object
            node[i] = new Node();
        }
        E = Integer.parseInt(st.nextToken());
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            Edge e = new Edge(node[to], node[from].edge1);
            node[from].edge1 = e;
        }
    }

    /*
    Method which makes a specific node ready to search.
    Give every node a distance of infinity
    The startnode gets distance of 0
     */
    public void initLast(Node s) {
        for (int i = node.length; i-- > 0;) {
            node[i].d = new Last();
        }
        ((Last)s.d).dist = 0;
    }

    /*
    Method to get the index of the Node which is sent in
     */
    private String findNode(Node n) {
        String res ="";
        for (int i = 0; i < node.length; i++) {
            if (node[i] == n) {
                return res + i;
            }
        }
        return res + " ";
    }

    public void topolog

    public void breadthFirstSearch(int i) {
        Node s = node[i];
        initLast(s); //Gets ready to search
        Queue queue = new Queue(N-1); //Creating a new queue
        queue.add(s); //Adds the start node to the queue
        while (!queue.empty()) {
            Node n = (Node)queue.nextInQueue(); //Gets the next node
            for (Edge e = n.edge1; e != null; e= e.next) {
                Last l = (Last)e.to.d;
                if(l.dist == l.infinity) {
                    l.dist = ((Last)n.d).dist +1;
                    l.last = n;
                    queue.add(e.to);
                }
            }
        }
    }
    public String printGraph() {
        String res = "Node:     Last:       Dist:" + "\n";
        for (int i = 0; i < node.length; i++) {
            res += i + "          " + findNode(((Last)node[i].d).getLast()) + "           " + ((Last)node[i].d).getDist() + "\n";
        }
        return res;
    }
}
