import java.util.function.Function;
import java.util.function.Predicate;

//inv : n ≥ 0 ∀i = 1..n: a[i] ≠ null
public interface Queue {

    //inv
    //Pred: el != null
    //Post: n = n' + 1 && a[n'] = el && ∀i = 1..n' : a[i]' = a[i]
    void enqueue(Object el);

    //inv
    //Pred: size > 0
    //Post: R = a[1] && n = n' - 1 && ∀i = 1..n : a[i] = a[i + 1]'
    Object dequeue();

    //inv
    //Pred: size > 0
    //Post: R = a[1] && n = n' && ∀i = 1..n' : a[i]' = a[i]
    Object element();

    //inv
    //Pred: true
    //Post: R = n == 0 && n = n' && ∀i = 1..n : a[i]' = a[i]
    boolean isEmpty();

    //inv
    //Pred: true
    //Post: R = n && n = n' && ∀i = 1..n : a[i]' = a[i]
    int size();

    //inv
    //Pred: true
    //Post: n = 0 && a = ∅
    void clear();

    //inv
    //Pred: true
    //Post: R = a[1,..,n] && n = n' && ∀i = 1..n : a[i]' = a[i]
    Object[] toArray();

    //inv
    //Pred: Predicate != null
    //Post: R = queue[m], где q[i] ∈ Predicate && n = n' && ∀i = 1..n : a[i]' = a[i]
    Queue filter(Predicate<Object> p);

    //inv
    //Pred: Function != null
    //Post: R = queue[n], где q[i] = Function(a[i]) && n = n' && ∀i = 1..n : a[i]' = a[i]
    Queue map(Function<Object, Object> f);
}
