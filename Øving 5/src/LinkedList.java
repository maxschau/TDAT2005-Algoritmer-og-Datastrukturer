public class LinkedList {
    private static Node head;

    public static void insertNode(String data) {
        Node newNode = new Node(data);
        newNode.next = null;

        if (head == null) {
            head = newNode;
        } else {
            Node last = head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = newNode;
        }
    }

    public static Node getHead() {
        return head;
    }

    public static String printList()
    {
        String res = "";
        Node currNode = head;

        //System.out.print("LinkedList: ");

        // Traverse through the LinkedList
        while (currNode != null) {
            // Print the data at current node
            res = res.concat(currNode.element + " ");

            // Go to next node
            currNode = currNode.next;
        }
        return res;
    }

    public static boolean contains() {
        if (head == null) {
            return false;
        } else {
            return true;
        }
    }
}
