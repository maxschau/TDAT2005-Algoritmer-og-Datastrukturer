
public class HashTable {
    private int sizeOfTable;
    private Node[] hashTable;
    private int counter = 0;
    private int amtOfElements = 0;


    public HashTable(int n) {
        hashTable = new Node[n];
        sizeOfTable = hashTable.length;
    }

    public int getSizeOfTable() {
        return sizeOfTable;
    }

    public int getCounter() {
        return counter;
    }

    public int convertStringToInt(String s) {
        int sum= 0;
        int multiplicator = s.length();
        for (int i = 0; i < s.length(); i++) {
            sum += Character.getNumericValue(s.charAt(i)) * multiplicator;
            multiplicator--;
        }

        return sum;
    }

    public void addToTable(String s) {
        int index = hashFunction(s, sizeOfTable);
        if (hashTable[index] == null) {
            hashTable[index] = new Node(s);
        } else {
            System.out.println(s + " is colliding with " + hashTable[index].getElement());
            counter++;
            Node curr = hashTable[index].getNext();
            while (curr != null) {
                curr = curr.getNext();
            }
            curr = new Node(s); //WILL THIS WORK????!!!!
            //I dont think so............
        }
        amtOfElements++;

    }

    public double getLoadFactor() {
        System.out.println("amt of elements: " + amtOfElements);
        System.out.println("Sizze of table: " + sizeOfTable);
        System.out.println("Collisions per person:" + ((double)counter/(double)amtOfElements));
        return (double) amtOfElements / (double) sizeOfTable;
    }



    public int hashFunction(String s, int size) {
        int key = convertStringToInt(s);
        return key % size;
    }

    public String toString() {
        String res = "";
        for (int i = 0; i < hashTable.length; i++) {
            //res = res.concat("Element " + i + ": " + hashTable[i].printList() + "\n");
        }
        return res;
    }
}
