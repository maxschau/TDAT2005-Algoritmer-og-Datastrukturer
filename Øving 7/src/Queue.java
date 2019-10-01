public class Queue {
    Object[] tab;
    int start = 0;
    int end = 0;
    int amount = 0;

    public Queue(int size) {
        tab = new Object[size];
    }

    public void add(Object n) {
        if (!(full())) {
            tab[end] = n;
            end = (end + 1) % tab.length;
            amount++;
        }

    }

    public Object nextInQueue() {
        if (!(empty())) {
            Object e = tab[start];
            start = (start + 1) % tab.length;
            amount--;
            return e;
        } else {
            return null;
        }
    }

    public boolean empty() {
        return amount == 0 ? true : false;
    }

    public boolean full() {
        return amount == tab.length ? true : false;
    }

    public Object checkQueue() {
        if (!empty()) {
            return tab[start];
        } else {
            return null;
        }
    }
}
