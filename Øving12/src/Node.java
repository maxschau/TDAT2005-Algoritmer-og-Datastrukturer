public class Node {
    int frequency;
    char character;
    Node left;
    Node right;
    int value;
    String binary;

    public Node(char character, int frequency) {
        this.frequency = frequency;
        this.character = character;
        this.left = null;
        this.right = null;
    }

    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right= right;

    }

    public Node(char character, String binary) {
        this.character = character;
        this.binary = binary;
    }

    public boolean isParent() {
        if (right != null || left != null) {
            return true;
        } else {
            return false;
        }
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
        if (character != 0 && binary == null) {
            return "Char: " + character + " frequency: " +frequency;
        } else if (binary != null && frequency == 0) {
            return "Char: '" + character + "'  binary: " + binary;
        }
        else {
            return "Value: " + value;
        }

    }
}


