public class ArrayQueue extends AbstractQueue {
    private int head;
    private Object[] elements = new Object[5];

    protected void doEnqueue(Object el) {
        ensureCapacity(size + 1);
        elements[(size + head) % elements.length] = el;
    }

    protected void doDequeue() {
        elements[head] = null;
        head = (head + 1) % elements.length;
    }

    private void ensureCapacity(int capacity) {
        if (capacity <= elements.length) {
            return;
        }
        elements = arrayCopy(2 * capacity);
        head = 0;
    }

    private Object[] arrayCopy(int len) {
        Object[] newArr = new Object[len];
        if (head + size <= elements.length) {
            System.arraycopy(elements, head, newArr, 0, size);
        } else {
            System.arraycopy(elements, head, newArr, 0, elements.length - head);
            System.arraycopy(elements, 0, newArr, elements.length - head, (head + size) % elements.length);
        }
        return newArr;
    }

    protected Object doElement() {
        return elements[head];
    }

    protected void doClear() {
        elements = new Object[5];
        head = 0;
    }

    public Object[] toArray() {
        return arrayCopy(size);
    }

    protected Queue doFilterAndMap() {
        return new ArrayQueue();
    }
}
