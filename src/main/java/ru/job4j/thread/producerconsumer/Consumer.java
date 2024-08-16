package ru.job4j.thread.producerconsumer;

import java.util.List;

public class Consumer<T> implements Runnable {
    private final SimpleBlockingQueue<T> queue;

    private final List<T> result;

    public Consumer(SimpleBlockingQueue<T> queue, List<T> result) {
        this.queue = queue;
        this.result = result;
    }

    public void run() {
        while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
            try {
                result.add(queue.poll());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
