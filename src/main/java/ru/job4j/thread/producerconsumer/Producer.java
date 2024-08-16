package ru.job4j.thread.producerconsumer;

import java.util.List;

public class Producer<T> implements Runnable {
    private final SimpleBlockingQueue<T> queue;

    private final List<T> addList;

    public Producer(SimpleBlockingQueue<T> queue, List<T> addList) {
        this.queue = queue;
        this.addList = addList;
    }

    public void run() {
        for (T el : addList) {
            try {
                queue.offer(el);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
