public class Node {
    int frequency;
    char character;
    Node left;
    Node right;
    int value;

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

    public boolean isParent(Node n) {
        if (n.right != null || n.left != null) {
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
        if (character != 0) {
            return "Char: " + character + " frequency: " +frequency;
        } else {
            return "Value: " + value;
        }

    }
}


