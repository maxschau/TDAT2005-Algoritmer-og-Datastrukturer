public class Node {
    int frequency;
    char character;
    Node left;
    Node right;

    public Node(char character, int frequency) {
        this.frequency = frequency;
        this.character = character;
        this.left = null;
        this.right = null;
    }

    public void incrementFrequency() {
        this.frequency++;
    }


    @Override
    public boolean equals(Object o) {
        Node n = (Node) o;
        if (n.character == character) return true;
        return false;
    }

    @Override
    public String toString() {
        return "Char: " + character + " frequency: " +frequency;
    }
}


