import java.io.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.*;

public class HuffmannTree {
    //PriorityQueue<Node> priorityQueue;
    int[] frequencies = new int[255];
    ArrayList<Node> characters;
    ArrayList<Node> original = new ArrayList<>();
    ArrayList<Node> codeBank = new ArrayList<>();
    String originalWord ="";



    public HuffmannTree() {
        characters = new ArrayList<>();
    }

    public void readFromFile(File f) {
        try {
            FileReader fr = new FileReader(f);   //Creation of File Reader object
            BufferedReader br = new BufferedReader(fr);  //Creation of BufferedReader object
            int c = 0;
            while ((c = br.read()) != -1)         //Read char by Char
            {
                char character = (char) c;          //converting integer to char
                originalWord += character;
                if ((int) c >= 0 && (int) c <= 256) {
                    frequencies[(int) c]++;
                }
            }

        }catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void setFrequencies(int[] newValue) {
        this.frequencies = newValue;
    }

    public void createTree() {
        try {
            //Adding the nodes to the arraylist
            for (int i = 0; i < frequencies.length; i++) {
                if (frequencies[i] != 0) {
                    Node n = new Node((char) i, frequencies[i]);
                    characters.add(n);
                    original.add(n);
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
        return characters.get(0);
    }

    String resetOrder(ArrayList<Node> code) {
        String out = "";
        for (int i = 0; i < originalWord.length(); i++) {
            char c = originalWord.charAt(i);
            for (int j = 0; j < code.size(); j++) {
                if (code.get(j).character == c) {
                    out += code.get(j).binary;
                    break;
                }
            }
        }
        return out;
    }

    public String tryingToCompress() {
        System.out.println("Kan det gå da???");
        String out = "";
        for (Node n : codeBank) {
            out += n.binary;
        }
        return out;

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

    byte[] createByteArray(String res) {
        BitSet bs = new BitSet(res.length());
        for (int i = 0; i < res.length(); i ++) {
            if (res.charAt(i) == '1') {
                bs.set(i);
            } else if (res.charAt(i) == '0') {
                bs.clear(i);
            }
        }
        return bs.toByteArray();
    }

    byte[] createByteArray() {
        ArrayList<Byte> byteArrayList = new ArrayList<>();
        for (int i = 0; i < frequencies.length; i++) {
            if(frequencies[i] != 0) {
                byteArrayList.add((byte) i);
                byte[] freq = ByteBuffer.allocate(4).putInt(frequencies[i]).array();
                byteArrayList.add(freq[0]);
                byteArrayList.add(freq[1]);
                byteArrayList.add(freq[2]);
                byteArrayList.add(freq[3]);
            }
        }


        byte[] bytesArray = new byte[byteArrayList.size()];
        for (int i = 0; i < byteArrayList.size(); i++) {
            bytesArray[i] = byteArrayList.get(i);
        }
        return bytesArray;
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