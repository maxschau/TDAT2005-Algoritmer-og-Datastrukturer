
import java.io.*;
import java.util.*;


class Main{
    public static void main(String[] args) {
        ArrayList<Node> characters = new ArrayList<>();
        int[] frequencies = new int[256];
        HuffmannTree huffmannTree = new HuffmannTree();
        try {
            File f = new File("C:\\Programmering\\NTNU\\Algoritmer og datastrukturer\\Øving12\\src\\opg12.txt");

            FileReader fr=new FileReader(f);   //Creation of File Reader object
            BufferedReader br=new BufferedReader(fr);  //Creation of BufferedReader object
            int c = 0;
            while((c = br.read()) != -1)         //Read char by Char
            {
                char character = (char) c;          //converting integer to char
                if ((int) c >= 0 && (int) c <= 256) {
                    frequencies[(int) c] ++;
                }
            }



            for (int i = 0; i < frequencies.length; i++) {
                if (frequencies[i] != 0) {
                    Node n = new Node((char) i , frequencies[i]);
                    characters.add(n);
                }
            }
            Collections.sort(characters, new SortByFrequency());

            for (Node n : characters) {
                System.out.println("Char: " + n.character + "    frequency: " + n.frequency);
            }


        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /*
    public static Node getNode(char c, ArrayList<Node> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).character == c) {
                return list.get(i);
            }
        }
        return null;
    }
    */

    public static String printChars(ArrayList<Node> list) {
        Collections.sort(list, new SortByFrequency()); //Sorting the arraylist based on frequency
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            output += "Char: '" + list.get(i).character + "'   |    frequency: " + list.get(i).frequency + "\n";
        }
        return output;
    }
}

