import java.util.function.Predicate;
import java.util.function.Function;

public abstract class AbstractQueue implements Queue {
    protected int size;

    protected abstract void doEnqueue(Object el);

    public void enqueue(Object el) {
        assert el != null;
        doEnqueue(el);
        size++;
    }

    protected abstract void doDequeue();

    public Object dequeue() {
        assert size > 0;
        Object p = element();
        doDequeue();
        size--;
        return p;
    }

    protected abstract Object doElement();

    public Object element() {
        assert size > 0;
        return doElement();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    protected abstract void doClear();

    public void clear() {
        size = 0;
        doClear();
    }

    protected abstract Queue doFilterAndMap();

    public Queue filter(Predicate<Object> p) {
        Queue q = doFilterAndMap();
        for (int i = 0; i < size; i++) {
            Object a = dequeue();
            if (p.test(a)) {
                q.enqueue(a);
            }
            enqueue(a);
        }
        return q;
    }

    public Queue map(Function<Object, Object> f) {
        Queue q = doFilterAndMap();
        for (int i = 0; i < size; i++) {
            Object a = dequeue();
            q.enqueue(f.apply(a));
            enqueue(a);
        }
        return q;
    }
}
