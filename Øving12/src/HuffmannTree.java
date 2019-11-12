import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class HuffmannTree {
    //PriorityQueue<Node> priorityQueue;
    int[] frequencies = new int[256];
    ArrayList<Node> characters;
    ArrayList<Node> codeBank = new ArrayList<>();


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
                    Node n = new Node((char) i, frequencies[i]);
                    characters.add(n);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Returns the parent?
    public Node fix_heap() {


        Collections.sort(characters, new SortByFrequency());
        while (characters.size() >= 2) {
            Node parent;
            Node smallest = findSmallest();
            characters.remove(characters.indexOf(smallest));
            Node leastSmallest = findSmallest();
            characters.remove(characters.indexOf(leastSmallest));

            if (smallest.value == 0 && leastSmallest.value == 0) {
                parent = new Node(smallest.frequency + leastSmallest.frequency, smallest, leastSmallest);
            } else if (smallest.value != 0 && leastSmallest.frequency != 0) {
                parent = new Node(smallest.value + leastSmallest.frequency, smallest, leastSmallest);
            } else if (smallest.frequency != 0 && leastSmallest.value != 0) {
                parent = new Node(smallest.frequency + leastSmallest.value, smallest, leastSmallest);
            } else {
                parent = new Node(smallest.value + leastSmallest.value, smallest, leastSmallest);
            }
            /*
            if (characters.get(0).value == 0 && characters.get(1).value == 0) {
                parent = new Node(characters.get(0).frequency + characters.get(1).frequency, characters.get(0), characters.get(1));
            } else if (characters.get(0).value != 0 && characters.get(1).frequency != 0) {
                parent = new Node(characters.get(0).value + characters.get(1).frequency, characters.get(0), characters.get(1));
            } else if (characters.get(0).frequency != 0 && characters.get(1).value != 0) {
                parent = new Node(characters.get(0).frequency + characters.get(1).value, characters.get(0), characters.get(1));
            } else {
                parent = new Node(characters.get(0).value + characters.get(1).value, characters.get(0), characters.get(1));
            }
            */


            characters.add(parent);
        }
        //System.out.println(characters.toString());
        return characters.get(0);
    }

    private Node findSmallest() {
        ArrayList<Node> copy = characters;
        int index = -1;
        int smallest = Integer.MAX_VALUE;
        for (int i = 0; i < characters.size(); i++) {
            if (copy.get(i).isParent()) { //Check by value
                if (copy.get(i).value < smallest) {
                    index = i;
                    smallest = copy.get(i).value;
                }
            } else {
                if (copy.get(i).frequency < smallest) {
                    index = i;
                    smallest = copy.get(i).frequency;
                }
            }
        }
        return copy.get(index);
    }

    /*
    Need to keep track over the binary
     */
    public void makeCodeBank(Node n, String s) {
        if (n.left != null) {
            String s1 = s + '0';
            makeCodeBank(n.left, s1);
        }
        if (n.right != null) {
            String s1 = s + '1';
            makeCodeBank(n.right, s1);
        }
        if (n.left == null && n.right == null) {
            codeBank.add(new Node(n.character, s));
        }

    }


    public void printTree(Node n) {
        if (n.value == 0) {
            System.out.println("Char: " + n.character + "    Frequency: " + n.frequency);
        } else {
            System.out.println("Value: " + n.value);
        }
        if (n.left != null) {
            printTree(n.left);
        }
        if (n.right != null) {
            printTree(n.right);
        }

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