import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;


class Main{
    public static void main(String[] args) {

        HuffmannTree huffmannTree = new HuffmannTree();
        File f = new File("C:\\Programmering\\NTNU\\Algoritmer og datastrukturer\\Øving12\\src\\opg12.txt");
        huffmannTree.readFromFile(f);
        huffmannTree.createTree();
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
        System.out.println("Correct order:");
        System.out.println(bin);

        byte[] byteFromString = huffmannTree.createByteArray(bin);
        //System.out.println(Arrays.toString(byte1));

        String[] words = bin.split("(?<=\\G........)");
        byte[] bytesFreq = huffmannTree.createByteArray();
        System.out.println("Freq compressed:");
        System.out.println(Arrays.toString(huffmannTree.frequencies));


        try (DataOutputStream stream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("C:\\Programmering\\NTNU\\Algoritmer og datastrukturer\\Øving12\\src\\out.txt")))) {
            stream.writeInt(bytesFreq.length); //Length of frequencies array:
            stream.write(bytesFreq);        //The frequency array
            stream.write(byteFromString);
        } catch (Exception e) {
            e.printStackTrace();
        }


        File file1 = new File("C:\\Programmering\\NTNU\\Algoritmer og datastrukturer\\Øving12\\src\\out.txt");
        Decompresser decompresser = new Decompresser();
        decompresser.readFromFile(file1);











    }



}

