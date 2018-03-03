public class ArrayQueueModuleTest {
    public static void main(String[] args) {
        ArrayQueueModule qqq = new ArrayQueueModule();
        for (int i = 0; i < 10; i++) {
            qqq.enqueue(i + 1);
            String arr = qqq.toStr();
            System.out.println();
        }
        while (!qqq.isEmpty()) {
            System.out.println(qqq.size() + " " + qqq.element() + " " + qqq.dequeue());
        }
        for (int i = 0; i < 5; i++) {
            qqq.enqueue(10 * (i + 1));
        }
        qqq.clear();
        qqq.push(10);
        qqq.peek();
        qqq.remove();
        System.out.println(qqq.isEmpty());
    }
}
