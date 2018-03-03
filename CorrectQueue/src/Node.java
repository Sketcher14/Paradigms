public class Node {
    Object value;
    Node next;
    Node prev;

    public Node(Object value, Node prev, Node next) {
        assert value != null;
        this.value = value;
        this.prev = prev;
        this.next = next;

    }
}
