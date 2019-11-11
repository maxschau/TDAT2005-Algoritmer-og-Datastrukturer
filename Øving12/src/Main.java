
import java.io.*;
import java.util.*;


class Main{
    public static void main(String[] args) {

        HuffmannTree huffmannTree = new HuffmannTree();
        File f = new File("C:\\Programmering\\NTNU\\Algoritmer og datastrukturer\\Øving12\\src\\test.txt");
        huffmannTree.createTree(f);
        //System.out.println(huffmannTree.printNodesSorted());
        huffmannTree.fix_heap();


    }



}

