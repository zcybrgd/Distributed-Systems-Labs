package threads.factory;

import java.util.LinkedList;
import java.util.Queue;

public class MessageQueue implements Channel {
    private Queue<Object> queue = new LinkedList<>();
    private final int CAPACITY = 10; // buffer size

    @Override
    public synchronized void send(Object item) {
        while (queue.size() >= CAPACITY) {
            try { wait(); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        queue.add(item);
        notifyAll();  // Notify consumer that an item is available
    }

    @Override
    public synchronized Object receive() {
        while (queue.isEmpty()) {
            try { wait(); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        Object item = queue.poll();
        notifyAll();  // Notify producer that space is available
        return item;
    }
}
