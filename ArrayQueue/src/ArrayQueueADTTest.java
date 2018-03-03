public class ArrayQueueADTTest {
    public static void main(String[] args) {
        ArrayQueueADT qqq = new ArrayQueueADT();
        for (int i = 0; i < 10; i++) {
            qqq.enqueue(qqq,i + 1);
            String arr = qqq.toStr(qqq);
            System.out.println(arr);
        }
        while (!qqq.isEmpty(qqq)) {
            System.out.println(qqq.size(qqq) + " " + qqq.element(qqq) + " " + qqq.dequeue(qqq));
        }
        for (int i = 0; i < 5; i++) {
            qqq.enqueue(qqq, 10 * (i + 1));
        }
        qqq.clear(qqq);
        qqq.push(qqq, 10);
        qqq.peek(qqq);
        qqq.remove(qqq);
        System.out.println(qqq.isEmpty(qqq));
    }
}
