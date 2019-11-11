import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class HuffmannTree {
    //PriorityQueue<Node> priorityQueue;
    int[] frequencies = new int[256];
    ArrayList<Node> characters;


    public HuffmannTree() {
        characters = new ArrayList<>();
    }

    public void createTree(File f) {
        try {
            FileReader fr = new FileReader(f);   //Creation of File Reader object
            BufferedReader br = new BufferedReader(fr);  //Creation of BufferedReader object
            int c = 0;
            while ((c = br.read()) != -1)         //Read char by Char
            {
                char character = (char) c;          //converting integer to char
                if ((int) c >= 0 && (int) c <= 256) {
                    frequencies[(int) c]++;
                }
            }

            //Adding the nodes to the arraylist
            for (int i = 0; i < frequencies.length; i++) {
                if (frequencies[i] != 0) {
                    Node n = new Node((char) i , frequencies[i]);
                    characters.add(n);
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void fix_heap() {
        Collections.sort(characters, new SortByFrequency());
        while (characters.size() >= 2) {
            System.out.println(characters.toString());
            //If both nodes are not "parents":
            Node parent;
            if (characters.get(0).value == 0 && characters.get(1).value == 0) {
                parent = new Node(characters.get(0).frequency + characters.get(1).frequency, characters.get(0), characters.get(1));
            } else if (characters.get(0).value != 0 && characters.get(1).frequency != 0) {
                parent = new Node(characters.get(0).value + characters.get(1).frequency, characters.get(0), characters.get(1));
            } else if (characters.get(0).frequency != 0 && characters.get(1).value != 0) {
                parent = new Node(characters.get(0).frequency + characters.get(1).value, characters.get(0), characters.get(1));
            } else {
                parent = new Node(characters.get(0).value + characters.get(1).value, characters.get(0), characters.get(1));
            }
            characters.remove(0);
            characters.remove(0);
            characters.add(parent);
        }
        System.out.println(characters.toString());
    }

    public String printNodesSorted() {
        Collections.sort(characters, new SortByFrequency());
        String output = "";
        for (Node n : characters) {
            output += "Char: '" + n.character + "'    freq: " + n.frequency + "\n";
        }
        return output;
    }


}