import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;

public class Main {
    static Edges edges;
    static Nodes nodes;
    static Locations locations;
    public static void main(String[] args) {
        MapRead mapRead = new MapRead();
        System.out.println("Reading nodes...");
        nodes = mapRead.readNodesFromFile(new File("C:\\Programmering\\NTNU\\Algoritmer og datastrukturer\\Øving 13\\src\\norden.txt"));
        System.out.println("Done reading nodes...");
        System.out.println("Reading edges...");
        edges = mapRead.readEdgesFromFile(new File("C:\\Programmering\\NTNU\\Algoritmer og datastrukturer\\Øving 13\\src\\kanter_norden.txt"));
        System.out.println("Done reading edges...");
        System.out.println("Reading locations...");
        locations = mapRead.readLocationsFromFile(new File("C:\\Programmering\\NTNU\\Algoritmer og datastrukturer\\Øving 13\\src\\interesse_norden.txt"));
        System.out.println("Done reading locations..");

        /*
                        Djikstra
         */

        int[] listDjikstra = mapRead.djikstrasAlgorithm(locations.getNode("Trondheim"), locations.getNode("Oslo"), edges.nodes);

        String res = "";
        for (int i : listDjikstra){
            res += nodes.nodes[i][0] + "," +nodes.nodes[i][1] + "\n";
        }
        //Writing res to file:
        try(PrintWriter out = new PrintWriter(new File("C:\\Programmering\\NTNU\\Algoritmer og datastrukturer\\Øving 13\\src\\resDjikstra.txt"))) {
            out.println(res);
        }catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("/* **************************************** */");

        /*
                        A*-algorithm
         */
        System.out.println("Starting A* ....");
        int[] listAStar = mapRead.AStarAlgorithm(locations.getNode("Trondheim"), locations.getNode("Oslo"), nodes , edges.nodes);
        //System.out.println(listAStar.length);
        String res1 = "";
        for (int i : listAStar) {
            res1 += nodes.nodes[i][0] + "," + nodes.nodes[i][1] + "\n";
        }
        //Writing res to file:
        try(PrintWriter out = new PrintWriter(new File("C:\\Programmering\\NTNU\\Algoritmer og datastrukturer\\Øving 13\\src\\resAStar.txt"))) {
            out.println(res1);
        }catch(Exception e) {
            e.printStackTrace();
        }


    }


}
