import java.util.StringJoiner;

//inv : n ≥ 0 ∀i = 1..n: a[i] ≠ null
public class ArrayQueueModule {
    private static int size, head;
    private static Object[] elements = new Object[5];

    //inv
    //Pred: el != null;
    //Post: n = n' + 1 && a[1] = el && ∀i = 2..n : a[i] = a[i - 1]'
    public static void push(Object el) {
        assert el != null;
        ensureCapacity(size + 1);
        if (head > 0) {
            elements[head - 1] = el;
            head--;
        } else {
            elements[elements.length - 1] = el;
            head = elements.length - 1;
        }
        size++;
    }

    //inv
    //Pred: size > 0
    //Post: R = a[n] && n = n' && ∀i = 1..n' : a[i] = a[i]'
    public static Object peek() {
        assert size > 0;
        return elements[(head + size - 1) % elements.length];
    }

    //inv
    //Pred: size > 0
    //Post: R = a[n'] && n = n' - 1 && ∀i = 1..n : a[i] = a[i]'
    public static Object remove() {
        assert size > 0;
        size--;
        return elements[(head + size) % elements.length];
    }

    //inv
    //Pred: el != null
    //Post: n = n' + 1 && a[n'] = el && ∀i = 1..n' : a[i] = a[i]'
    public static void enqueue(Object el) {
        assert el != null;
        ensureCapacity(size + 1);
        elements[(size + head) % elements.length] = el;
        size++;
    }

    private static void ensureCapacity(int capacity) {
        if (capacity <= elements.length) {
            return;
        }
        elements = arrayCopy(2 * capacity);
        head = 0;
    }

    private static Object[] arrayCopy(int len) {
        Object[] newArr = new Object[len];
        if (head + size <= elements.length) {
            System.arraycopy(elements, head, newArr, 0, size);
        } else {
            System.arraycopy(elements, head, newArr, 0, elements.length - head);
            System.arraycopy(elements, 0, newArr, elements.length - head, (head + size) % elements.length);
        }
        return newArr;
    }

    //inv
    //Pred: size > 0
    //Post: R = a[1] && n = n' && ∀i = 1..n' : a[i] = a[i]'
    public static Object element() {
        assert size > 0;
        return elements[head];
    }

    //inv
    //Pred: size > 0
    //Post: R = a[1] && n = n' - 1 && ∀i = 1..n' : a[i] = a[i]'
    public static Object dequeue() {
        assert size > 0;
        size--;
        Object p = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        return p;
    }

    //inv
    //Pred: true
    //Post: R = n && n = n' && ∀i = 1..n : a[i] = a[i]'
    public static int size() {

        return size;
    }

    //inv
    //Pred: true
    //Post: R = n > 0 && n = n' && ∀i = 1..n : a[i] = a[i]'
    public static boolean isEmpty() {

        return size == 0;
    }

    //inv
    //Pred: true
    //Post: n = 0 && a = ∅
    public static void clear() {
        elements = new Object[5];
        head = 0;
        size = 0;
    }

    //inv
    //Pred: true
    //Post: R = строка вида '[' a[1] ', ' ... ', ' a[n] ']' && n = n' && ∀i = 1..n : a[i] = a[i]'
    public static String toStr() {
        Object[] copy = arrayCopy(size);
        StringJoiner str = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < copy.length; i++) {
            str.add(copy[i].toString());
        }
        return str.toString();
    }
}