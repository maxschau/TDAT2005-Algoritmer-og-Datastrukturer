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
    public void initLast(Node startnode) {
        for (int i = node.length; i-- > 0;) {
            node[i].d = new Last();
        }
        ((Last)startnode.d).dist = 0;
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

    /*
    A depth-first method which goes through a dept-first search for a specific node and returns the first node of the list once done
     */
    public Node df_topo(Node startnode, Node first) {
        Topo_lst nd = (Topo_lst)startnode.d;
        if (nd.visited) {
            return first;
        }
        nd.visited = true;
        for (Edge edge = startnode.edge1; edge != null; edge = edge.next) {
            first = df_topo(edge.to, first);
        }
        nd.next = first;
        return startnode;
    }

    public void printRes() {
        Node n = topoligisort();
        Topo_lst t = (Topo_lst) n.d;

        while (n != null) {
            System.out.print(findNode(n) + " ");
            if (t.next != null) {
                n = t.next;
                t = (Topo_lst) n.d;
            } else {
                return;
            }
        }

    }

    public Node topoligisort() {
        Node l = null;
        for (int i = N; i-- > 0;) { //Initializes the depth-first-search
            node[i].d = new Topo_lst();
        }
        for (int i = N; i -- > 0;) {
            l = df_topo(node[i], l);
        }
        return l; //Returns the first element
    }



    public void breadthFirstSearch(int i) {
        Node s = node[i];
        initLast(s); //Gets ready to search
        Queue queue = new Queue(N-1); //Creating a new queue
        queue.add(s); //Adds the start node to the queue
        while (!queue.empty()) {
            Node n = (Node)queue.nextInQueue(); //Gets the next node
            for (Edge e = n.edge1; e != null; e= e.next) {
                Last nextnode = (Last)e.to.d;
                if(nextnode.dist == nextnode.infinity) {
                    nextnode.dist = ((Last)n.d).dist +1;
                    nextnode.last = n;
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
