import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class MapRead {
    public Edges readEdgesFromFile(File f) {
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            int amount = Integer.parseInt(br.readLine().trim());
            Edges edges = new Edges(amount);
            for (int i = 0; i < amount; i++) {
                String[] tab = br.readLine().split("\\s");
                Edge edge = new Edge(Integer.parseInt(tab[0].trim()), Integer.parseInt(tab[1].trim()), Integer.parseInt(tab[2].trim()), Integer.parseInt(tab[3].trim()), Integer.parseInt(tab[4].trim()));
                edges.addEdge(edge);
            }
            return edges;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Nodes readNodesFromFile(File f) {
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            int amount = Integer.parseInt(br.readLine().trim());
            Nodes nodes = new Nodes(amount);
            for (int i = 0; i < amount; i++) {
                String[] tab = br.readLine().split("\\s");
                //System.out.println(Arrays.toString(tab));
               // System.out.println(tab.length);
                if (tab.length > 3) {
                    nodes.addPlace(Integer.parseInt(tab[0]), Double.parseDouble(tab[1]), Double.parseDouble(tab[3]));
                } else {
                    nodes.addPlace(Integer.parseInt(tab[0]), Double.parseDouble(tab[1]), Double.parseDouble(tab[2]));
                }

            }
            return nodes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public Locations readLocationsFromFile(File f) {
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            Locations locations = new Locations();
            int amount = Integer.parseInt(br.readLine().trim());
            for (int i = 0; i < amount; i++) {
                String[] tab = br.readLine().split("\\s");
                locations.addLocation(Integer.parseInt(tab[0]), tab[2].substring(1, tab[2].length() - 1)); //Substring to remove the " " from the location name
            }
            return locations;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int[] djikstrasAlgorithm(int fromNode, int toNode, Connection[] nodes) {
        long startTime = System.currentTimeMillis();

        int[] lengthFromStart = new int[nodes.length]; //Array to keep track over the lengt from the starting node to that index
        //System.out.println("CameFrom length: " + nodes.length);
        int[] cameFrom = new int[nodes.length]; //To keep track over the shortest route to that node

        LengthFromSource comp = new LengthFromSource(lengthFromStart); //To compare to nodes, comparing the distance from node to startnode
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(nodes.length, comp); //Priority queue to keep track over nodes

        //Initalizes every node by setting the distance to infinity
        for (int i = 0; i < nodes.length; i++) {
            lengthFromStart[i] = Integer.MAX_VALUE;
            //lengthFromStart[i] = 10000000;
            cameFrom[i] = Integer.MAX_VALUE;
            //cameFrom[i] = 10000000;
        }

        //Initializes
        lengthFromStart[fromNode] = 0; //
        cameFrom[fromNode] = -2;

        priorityQueue.add(fromNode);
/*
        int nodeIndex = 0;
        int totalLength = 0;
        int totalNodesUsed = 0;
        int getConnection;
        Connection connection;
        */
        int nodeIndex, totalLength, totalNodesUsed = 0, getConnection;
        Connection connection;

        while (!(priorityQueue.isEmpty())) { //AS long as the queue is not empty
            nodeIndex = priorityQueue.poll(); //removes the first element from the queue

            if (nodeIndex == toNode) { //If we have reached the goal-node we break out of the loop
                break;
            }

            connection = nodes[nodeIndex]; //The edge we are currently on
            totalNodesUsed++;

            while (connection != null) {
                totalLength = lengthFromStart[nodeIndex] + connection.length; //I
                getConnection = connection.connection; //finds next edte


                if (totalLength < lengthFromStart[getConnection]) { // the total length is less than the lengt from that node
                    lengthFromStart[getConnection] = totalLength; //Update the distance
                    cameFrom[getConnection] = nodeIndex;

                    if (toNode == getConnection) {
                        priorityQueue.remove(getConnection);
                    }
                    //Adds the lovest node connection to the queue
                    priorityQueue.add(getConnection);
                }
                connection = connection.next;
            }
        }

        int backNode = toNode;
        ArrayList<Integer> nodesUsed = new ArrayList<>();
        nodesUsed.add(0, toNode);

        while (cameFrom[backNode] != -2) {
            nodesUsed.add(0, backNode); //Adds the nodes to the arraylist
            backNode = cameFrom[backNode];
        }
        long totalTime = System.currentTimeMillis() - startTime;


        System.out.println("Djikstra:");
        System.out.println("Used a total of : " + totalNodesUsed);
        System.out.println("Noder i vei: ");
        System.out.println(nodesUsed.size());
        System.out.println("TotalTime of Djikstra:");
        System.out.println(totalTime);
        System.out.println("Totaltiden å kjøre:");
        System.out.println(Math.floor(lengthFromStart[toNode] / 100) + " sekunder, " + Math.floor(lengthFromStart[toNode] / 6000) + " minutter, eller " + Math.floor(lengthFromStart[toNode] / 36000) / 10 + " timer");


        //This is the route we are moving
        return nodesUsed.stream().mapToInt(i -> i).toArray();
    }

    public int[] AStarAlgorithm(int fromNode, int toNode, Nodes nodes, Connection[] connections) {
        long startTime = System.currentTimeMillis();


        int[] lengthFromStart = new int[connections.length];
        int[] cameFrom = new int[connections.length];
        int[] distanceTo = new int[connections.length]; //The estimated cost from node to the goal node


        LengthBetweenNodes comp = new LengthBetweenNodes(distanceTo, lengthFromStart); //Base the priority queue on the estimate
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(connections.length, comp);

        for (int i = 0; i < connections.length; i++) { //Initilaizes the different nodes with distance infinity
            lengthFromStart[i] = Integer.MAX_VALUE;
            //lengthFromStart[i] = 10000000;
            //cameFrom[i] = 10000000;
            cameFrom[i] = Integer.MAX_VALUE;
        }

        lengthFromStart[fromNode] = 0;
        cameFrom[fromNode] = -2;

        priorityQueue.add(fromNode);

        int nodeIndex = 0;
        int totalLength = 0;
        int totalNodesUsed = 0;
        int getConnection;
        Connection connection;

        while (!(priorityQueue.isEmpty())) {
            //System.out.println("While runs");
            nodeIndex = priorityQueue.poll();
            if (nodeIndex == toNode) { //Reached the goal Node
                break;
            }
            connection = connections[nodeIndex];
            totalNodesUsed++;

            while(connection != null) {
                totalLength = lengthFromStart[nodeIndex] + connection.length; //The total distance
                getConnection = connection.connection; //Next edge

                if (totalLength < lengthFromStart[getConnection]) {
                    lengthFromStart[getConnection] = totalLength;
                    cameFrom[getConnection] = nodeIndex;

                    if(distanceTo[getConnection] == 0) { //If the estimated distance is not calculated yet
                        distanceTo[getConnection] = nodes.calcDistance(getConnection, toNode); //Updating the estimate
                    }

                    if(toNode == getConnection) {
                        priorityQueue.remove(getConnection);
                    }
                    priorityQueue.add(getConnection);
                }

                connection = connection.next;
            }
        }
        System.out.println("While done");
        int backNode = toNode;
        ArrayList<Integer> usedNodes = new ArrayList<>();
        usedNodes.add(0, toNode);

        while(cameFrom[backNode] != -2) {
            usedNodes.add(0, backNode);
            backNode = cameFrom[backNode];
        }

        long totalTime = System.currentTimeMillis() - startTime;

        System.out.println("The magnificent A* algorithm used a total of: ");
        System.out.println(totalNodesUsed + " nodes");
        System.out.println("Noder i vei");
        System.out.println(usedNodes.size());
        System.out.println("Time of A*:");
        System.out.println(totalTime);
        System.out.println("Kjøretiden det tar: ");
        System.out.println(Math.floor(lengthFromStart[toNode] / 100) + " sekunder, " + Math.floor(lengthFromStart[toNode] / 6000) + " minutter, eller " + Math.floor(lengthFromStart[toNode] / 36000) / 10 + " timer");


        return usedNodes.stream().mapToInt(i->i).toArray();
    }
}
