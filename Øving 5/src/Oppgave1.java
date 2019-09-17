import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Oppgave1 {
    public static void main(String[] args) throws Exception {

        HashTable h = new HashTable(150);
        File file = new File("C:\\Programmering\\NTNU\\Algoritmer og datastrukturer\\Øving 5\\src\\navn.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) {
            h.addToTable(st);
        }
        System.out.println("Collisions: " + h.getCounter());
        System.out.println("Load-factor: " + h.getLoadFactor());
        System.out.println("Collisions per person: " + h.getCollisionsPerPerson());


    }
}
