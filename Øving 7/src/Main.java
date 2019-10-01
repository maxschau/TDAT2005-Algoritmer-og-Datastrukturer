import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        try {
            Graph g = new Graph();
            g.ny_ugraf(new BufferedReader(new FileReader("test.txt")));
            g.breadthFirstSearch(5);
            System.out.println(g.printGraph());
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
