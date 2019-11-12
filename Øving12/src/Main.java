
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
        /*
        for (Node n : bank) {
            System.out.println(n.toString());
        }
        */


        //ArrayList<Byte> bytes = new ArrayList<Byte>();
        String bin = huffmannTree.resetOrder(bank);
        //System.out.println(bin);
        String[] words = bin.split("(?<=\\G........)");
        byte[] bytes = new byte[words.length];


        int count = 0;
        for (String word : words) {
            byte b = (byte) Integer.parseInt(word);
            bytes[count] = b;
            count++;
        }

        try (FileOutputStream stream = new FileOutputStream("C:\\Programmering\\NTNU\\Algoritmer og datastrukturer\\Øving12\\src\\out.txt")) {
            stream.write(bytes);
            System.out.println("sucess");
        } catch (Exception e) {
            e.printStackTrace();
        }


        /*
        ArrayList<Byte> bytes = new ArrayList<>();
        String s = "";
        for (int i = 0; i < bin.length(); i++) {

        }
        */






    }



}

