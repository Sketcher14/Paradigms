import java.util.StringJoiner;

//inv : n ≥ 0 ∀i = 1..n: a[i] ≠ null
public class ArrayQueueADT {
    private int size, head;
    private Object[] elements = new Object[5];

    //inv
    //Pred: el != null; && queue != null
    //Post: n = n' + 1 && a[1] = el && ∀i = 2..n : a[i] = a[i - 1]'
    public static void push(ArrayQueueADT queue, Object el) {
        assert el != null;
        ensureCapacity(queue, queue.size + 1);
        if (queue.head > 0) {
            queue.elements[queue.head - 1] = el;
            queue.head--;
        } else {
            queue.elements[queue.elements.length - 1] = el;
            queue.head = queue.elements.length - 1;
        }
        queue.size++;
    }

    //inv
    //Pred: size > 0 && queue != null
    //Post: R = a[n] && n = n' && ∀i = 1..n' : a[i] = a[i]'
    public static Object peek(ArrayQueueADT queue) {
        assert queue.size > 0;
        return queue.elements[(queue.head + queue.size - 1) % queue.elements.length];
    }

    //inv
    //Pred: size > 0 && queue != null
    //Post: R = a[n'] && n = n' - 1 && ∀i = 1..n : a[i] = a[i]'
    public static Object remove(ArrayQueueADT queue) {
        assert queue.size > 0;
        queue.size--;
        return queue.elements[(queue.head + queue.size) % queue.elements.length];
    }


    //inv
    //Pred: el != null && queue != null
    //Post: n = n' + 1 && a[n'] = el && ∀i = 1..n' : a[i] = a[i]'
    public static void enqueue(ArrayQueueADT queue, Object el) {
        assert el != null;
        ensureCapacity(queue, queue.size + 1);
        queue.elements[(queue.size + queue.head) % queue.elements.length] = el;
        queue.size++;
    }

    private static void ensureCapacity(ArrayQueueADT queue, int capacity) {
        if (capacity <= queue.elements.length) {
            return;
        }
        queue.elements = arrayCopy(queue, 2 * capacity);
        queue.head = 0;
    }

    private static Object[] arrayCopy(ArrayQueueADT queue, int len) {
        Object[] newArr = new Object[len];
        if (queue.head + queue.size <= queue.elements.length) {
            System.arraycopy(queue.elements, queue.head, newArr, 0, queue.size);
        } else {
            System.arraycopy(queue.elements, queue.head, newArr, 0, queue.elements.length - queue.head);
            System.arraycopy(queue.elements, 0, newArr,
                    queue.elements.length - queue.head, (queue.head + queue.size) % queue.elements.length);
        }
        return newArr;
    }

    //inv
    //Pred: size > 0 && queue != null
    //Post: R = a[1] && n = n' && ∀i = 1..n' : a[i] = a[i]'
    public static Object element(ArrayQueueADT queue) {
        assert queue.size > 0;
        return queue.elements[queue.head];
    }

    //inv
    //Pred: size > 0 && queue != null
    //Post: R = a[1] && n = n' - 1 && ∀i = 1..n' : a[i] = a[i]'
    public static Object dequeue(ArrayQueueADT queue) {
        assert queue.size > 0;
        queue.size--;
        Object p = queue.elements[queue.head];
        queue.elements[queue.head] = null;
        queue.head = (queue.head + 1) % queue.elements.length;
        return p;
    }

    //inv
    //Pred: queue != null
    //Post: R = n && n = n' && ∀i = 1..n : a[i] = a[i]'
    public static int size(ArrayQueueADT queue) {

        return queue.size;
    }

    //inv
    //Pred: queue != null
    //Post: R = n > 0 && n = n' && ∀i = 1..n : a[i] = a[i]'
    public static boolean isEmpty(ArrayQueueADT queue) {

        return queue.size == 0;
    }

    //inv
    //Pred: queue != null
    //Post: n = 0 && a = ∅
    public static void clear(ArrayQueueADT queue) {
        queue.elements = new Object[5];
        queue.head = 0;
        queue.size = 0;
    }

    //inv
    //Pred: queue != null
    //Post: R = строка вида '[' a[1] ', ' ... ', ' a[n] ']' && n = n' && ∀i = 1..n : a[i] = a[i]'
    public static String toStr(ArrayQueueADT queue) {
        Object[] copy = arrayCopy(queue, queue.size);
        StringJoiner str = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < copy.length; i++) {
            str.add(copy[i].toString());
        }
        String ans = str.toString();
        return ans;
    }
}
