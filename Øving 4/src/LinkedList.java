public class LinkedList {
    public static Node head;
    public Node tail;
    private int amountOfElements = 0;

    public int getAmountOfElements() {
        return amountOfElements;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() { return tail;}


    public void pushFront(int value) {
        //If the list is empty
        if (amountOfElements == 0) {
            Node n = new Node(value);
            head = n;
            tail = n;
            n.next = head;
        } else {
            Node n = new Node(value);
            Node temp = head;
            n.next = temp;
            head = n;
            tail.next = head;
        }
        amountOfElements++;
    }

    public void pushBack(int value) {
        if (amountOfElements == 0) {
            pushFront(value);
        } else {
            Node n = new Node(value);
            tail.next = n;
            tail = n;
            tail.next = head;
            amountOfElements++;
        }
    }

    public Node fjern(Node n) {
        Node last = null;
        Node thisOne = head;
        while(thisOne != null && thisOne !=n) {
            last = thisOne;
            thisOne = thisOne.next;
        }
        if (thisOne != null) {
            if(last != null) last.next = thisOne.next;
            else head = thisOne.next;
            thisOne.next = null;
            amountOfElements--;
            return thisOne;
        } else return null;
    }

    public Node findNr(int nr) {
        Node thisOne = head;
        if (nr < amountOfElements) {
            for (int i = 0; i < nr; i++) {
                thisOne = thisOne.next;
            }
            return thisOne;
        } else return null;
    }

    public void slettAlle() {
        head = null;
        amountOfElements = 0;
    }

    public boolean contains() {
        if (amountOfElements == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void print(){
        Node temp = head;
        if(amountOfElements<0){
            System.out.print("List is empty");
        }else{
            while (temp.next != head) {
                System.out.print(temp.element + " ");
                temp = temp.next;
                if (temp.next == head) {
                    System.out.println(temp.element);
                }
            }
        }
    }



    public void pointers() {
        Node temp = head;
        while (temp.next != head) {
            System.out.println(temp.getElement() + " points to " + temp.next.getElement());
            temp = temp.next;
        }
        System.out.println(temp.getElement() + " points to " + temp.next.getElement());
    }

}
