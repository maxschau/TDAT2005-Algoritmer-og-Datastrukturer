import java.util.Random;
import java.util.HashMap;

public class Oppgave2 {
    private static int length = 5000000;
    public static void main(String[] args) {
        HashTable h = new HashTable(length);
        int[] t = new int[length];
        Random rand = new Random();
        for (int i = 0; i < t.length; i++) {
            t[i] = rand.nextInt(50000000) +1;
        }
        long startTime = System.nanoTime();
        for (int i = 0; i < t.length; i++) {
            h.addToTable(t[i]);
        }
        long endTime = System.nanoTime();
        System.out.println("Time in ms for my astonishing algorithm: " + (endTime - startTime)*Math.pow(10,-6));

        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        long startTime1 = System.nanoTime();
        for (int i = 0; i < t.length; i++) {
            hashMap.put("h", t[i]);
        }
        long endTime1 = System.nanoTime();
        System.out.println("Time for Java's bad algorithm: " + (endTime1 - startTime1)*Math.pow(10,-6));




        System.out.println(h.getCounter());
        System.out.println(h.getSizeOfTable());
        System.out.println(h.getAmtOfElements());
        System.out.println(h.getLoadFactor());


    }
}
