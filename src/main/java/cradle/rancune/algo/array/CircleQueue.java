package cradle.rancune.algo.array;

/**
 * Created by Rancune@126.com on 2020/7/19.
 */
public class CircleQueue {
    private final String[] items;
    private int capacity = 0;
    private int head = 0;
    private int tail = 0;

    public CircleQueue(int capacity) {
        this.capacity = capacity;
        this.items = new String[capacity];
    }

    public boolean enqueue(String item) {
        if ((tail + 1) % capacity == head) {
            return false;
        }
        items[tail] = item;
        tail = (tail + 1) % capacity;
        return true;
    }

    public String dequeue() {
        if (head == tail) {
            return null;
        }
        String result = items[head];
        head = (head + 1) % capacity;
        return result;
    }
}
