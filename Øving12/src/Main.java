
import java.io.*;
import java.util.*;


class Main{
    public static void main(String[] args) {

        HuffmannTree huffmannTree = new HuffmannTree();
        File f = new File("C:\\Programmering\\NTNU\\Algoritmer og datastrukturer\\Øving12\\src\\test.txt");
        huffmannTree.createTree(f);
        Node parent = huffmannTree.fix_heap();
        huffmannTree.makeCodeBank(parent, "");
        //huffmannTree.printTree(parent);
        ArrayList<Node> bank = huffmannTree.codeBank;

        for (Node n : bank) {
            System.out.println(n.toString());
        }



    }



}

