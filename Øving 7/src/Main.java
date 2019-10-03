import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        try {
            //Oppgave 1:
            Graph g = new Graph();
            g.ny_ugraf(new BufferedReader(new FileReader("lg1.txt")));
            g.breadthFirstSearch(5);
            System.out.println(g.printGraph());

            //Oppgave 2:

            Graph g1 = new Graph();
            g1.ny_ugraf(new BufferedReader(new FileReader("L7g5.txt")));
            g1.printRes();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
