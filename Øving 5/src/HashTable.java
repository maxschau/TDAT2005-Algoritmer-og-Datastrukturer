
public class HashTable {
    private int sizeOfTable;
    //private Node[] hashTable;
    private int counter = 0;
    private int amtOfElements = 0;
    private double loadFactor = 0;
    private int PRIME = 11;

    private int[] hashTable;



    /*
    public HashTable(int n) {
        hashTable = new Node[n];
        sizeOfTable = hashTable.length;
    }
    */

    public HashTable(int n) {
        //hashTable = new int[n];
        hashTable = new int[(int)Math.pow(2, Math.ceil(Math.log(n)/Math.log(2)))]; //Gives the table an appropiate size
        for (int i = 0; i < hashTable.length; i++) {
            hashTable[i] = -1;
        }
        sizeOfTable = hashTable.length;
    }

    public int getAmtOfElements() {
        return amtOfElements;
    }

    public int getSizeOfTable() {
        return sizeOfTable;
    }

    public int getCounter() {
        return counter;
    }

    //Oppgave 1
    /*
    public int convertStringToInt(String s) {
        int sum= 0;
        int multiplicator = s.length();
        for (int i = 0; i < s.length(); i++) {
            sum += Character.getNumericValue(s.charAt(i)) * multiplicator;
            multiplicator--;
        }

        return sum;
    }
    */

    /*
    public void addToTable(String s) {
        int index = hashFunction(s, sizeOfTable);
        if (hashTable[index] == null) {
            hashTable[index] = new Node(s);
        } else {
            System.out.println(s + " is colliding with " + hashTable[index].getElement());
            counter++;
            Node curr = hashTable[index];
            while (curr.next != null) {
                curr = curr.getNext();
            }
            Node n = new Node(s);
            curr.setNext(n);
        }
        amtOfElements++;
    }
    */

    public void addToTable(int n) {
        if (isFull()) {
            return;
        }
        int index = hashFunction(n);
        //System.out.println("Index: "+ index);
        if (hashTable[index] == -1) {
            //No collision
            hashTable[index] = n;
        } else {
            //Collision
            counter++;
            int i = 1;
            int index2 = hashFunction2(n);

            while (true) {
                int newIndex = ((index2*i) + index) % (sizeOfTable);
                //int newIndex = (index+index2) % sizeOfTable;
                //System.out.println("New Index: " + newIndex);
                if (hashTable[newIndex] == -1) {
                    hashTable[newIndex] = n;
                    break;
                }
                i++;
            }
        }
        amtOfElements++;
    }

    public boolean isFull() {
        return amtOfElements == sizeOfTable;
    }

    public double getCollisionsPerPerson() {
        return (double)counter/(double)amtOfElements;
    }

    public double getLoadFactor() {
        return (double) amtOfElements / (double) sizeOfTable;
    }


    /*
    public int hashFunction(String s, int size) {
        int key = convertStringToInt(s);
        return key % size;
    }
    */

    public int hashFunction(int n) {
        return n % sizeOfTable;
    }

    public int hashFunction2(int n) {
        return PRIME -(n % PRIME);
    }


    public String toString() {
        String res = "";
        for (int i = 0; i < hashTable.length; i++) {
           // res = res.concat("Element " + i + ": " + hashTable[i].printList() + "\n");
        }
        return res;
    }
}
