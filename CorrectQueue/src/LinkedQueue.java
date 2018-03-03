public class LinkedQueue extends AbstractQueue {
    private Node head, tail;

    protected void doEnqueue(Object el) {
        Node a = new Node(el, null, null);
        if (isEmpty()) {
            tail = a;
            head = a;
        } else {
            tail.next = a;
            a.prev = tail;
            tail = a;
        }
    }

    protected void doDequeue() {
        head = head.next;
        if (size == 1) {
            tail = null;
        }
    }

    protected Object doElement() {
        return head.value;
    }

    protected void doClear() {
        head = null;
        tail = null;
    }

    public Object[] toArray() {
        Object[] copy = new Object[size];
        Node n = head;
        for (int i = 0; i < size; i++) {
            copy[i] = n.value;
            n = n.next;
        }
        return copy;
    }

    protected Queue doFilterAndMap() {
        return new LinkedQueue();
    }
}
