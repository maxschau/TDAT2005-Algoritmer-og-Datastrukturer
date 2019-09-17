public class Node {
    public String element;
    public Node next;

    public Node(String e) {
        this.element = e;
    }

    public String getElement() {
        return element;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
