package ru.job4j.thread.producerconsumer;

public class Consumer<T> implements Runnable {
    private final SimpleBlockingQueue<T> queue;

    public Consumer(SimpleBlockingQueue<T> queue) {
        this.queue = queue;
    }

    public void run() {
        while (!queue.isEmpty() /*|| !Thread.currentThread().isInterrupted()*/) {
            queue.poll();
        }
    }
}
