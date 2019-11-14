import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;

public class Decompresser {
    byte[] bytes;
    ArrayList<Integer> numbers = new ArrayList<>();
    ArrayList<String> binaries = new ArrayList<>();
    int[] frequencies = new int[256];
    int lengthFreq;
    String binaryRep = "";
    int lenFreq = 0;
    Node parent;
    String res = "";




    public void readFromFile(File f) {
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            bytes = Files.readAllBytes(f.toPath());
            ArrayList<Integer> myInts = new ArrayList<>();
            //Read bytes:
            int length = 0;
            byte[] lengthArray = new byte[4];
            for (int i = 0; i < 4; i++) {
                lengthArray[i] = bytes[i];
            }

            length = ByteBuffer.wrap(lengthArray).getInt();

            for (int i = 4; i < length; i+=5) {
                //System.out.println(bytes[i]);
                //int sum = 0;
                byte[] freq = new byte[4];
                for (int j = 1; j < 5; j++) {
                    freq[j-1] = bytes[i+j];
                }
                int sum = ByteBuffer.wrap(freq).getInt();
                frequencies[bytes[i] &0xFF] = sum;
            }


            System.out.println("Freq decompressed");
            System.out.println(Arrays.toString(frequencies));
            //THIS WORKS!!!!!!!!!
            HuffmannTree huffmannTree = new HuffmannTree();
            huffmannTree.setFrequencies(frequencies);
            huffmannTree.createTree();
            parent = huffmannTree.fix_heap();
            huffmannTree.makeCodeBank(parent, "");
            //huffmannTree.printTree(parent);

            byte[] temp = Arrays.copyOfRange(bytes, length+4, bytes.length);
            BitSet bs = BitSet.valueOf(temp);


            for (int i = 0; i < bs.length(); i++) {
                if (bs.get(i)) { //If true
                    binaryRep += '1';
                } else {
                    binaryRep += '0';
                }
            }

            System.out.println("binaryRep: " + binaryRep);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    /*
    private char traverse(Node n, char c) {
        if (!(n.isParent())) {
            return n.character;
        }

    }
    */

    public void recreateFile() {
        Node n = parent;
        res = "";
        for (int i = 0; i < binaryRep.length(); i++) {
                if (binaryRep.charAt(i) == '0') {
                    n = n.left;
                }
                if (binaryRep.charAt(i) == '1') {
                    n = n.right;
                }
                if (!(n.isParent())) {
                    res += n.character;
                    n = parent;
                }

        }
        try(PrintWriter out = new PrintWriter(new File("C:\\Programmering\\NTNU\\Algoritmer og datastrukturer\\Øving12\\src\\res.txt"))) {
            out.println(res);
        }catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("---------------");
        System.out.println(res);

    }



}
