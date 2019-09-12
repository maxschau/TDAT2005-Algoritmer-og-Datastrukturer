public class Main {
    public static void main(String[] args) {
        LinkedList l = new LinkedList();
        for (int i = 1; i <= 41; i++) {
            l.pushBack(i);
        }

        //l.print();
        System.out.println(josephus(l));
    }

    private static int josephus(LinkedList l) {
        Node currentNode = l.head;

        while (l.contains()) {
            for (int i = 0; i < 2; i++) {
                currentNode = currentNode.next;
            }
            Node temp = currentNode.next;
            if (currentNode == l.getHead()) {
                l.head = temp;
            }
            l.print();
            l.fjern(currentNode);
            currentNode = temp;
        }
        return currentNode.element;
    }

}
