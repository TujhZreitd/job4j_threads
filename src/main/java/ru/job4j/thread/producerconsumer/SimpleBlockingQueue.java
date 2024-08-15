package ru.job4j.thread.producerconsumer;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    private final int sizeQueue;

    public SimpleBlockingQueue(int sizeQueue) {
        this.sizeQueue = sizeQueue;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public synchronized void offer(T value) {
        while (queue.size() >= sizeQueue) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.add(value);
        System.out.println("В очередь добавляется значение - " + value);
        System.out.print("Содержание очереди: ");
        for (T el : queue) {
            System.out.print(el + ", ");
        }
        System.out.println();
        notify();
    }

    public synchronized T poll() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T result = queue.poll();
        System.out.println("Из очереди извлечено значение - " + result);
        System.out.print("Содержание очереди: ");
        for (T el : queue) {
            System.out.print(el + ", ");
        }
        System.out.println();
        notify();
        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        List<Integer> addList = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Thread producer = new Thread(new Producer<>(queue, addList));
        Thread consumer = new Thread(new Consumer<>(queue));
        producer.start();
        consumer.start();
        /*producer.join();
        consumer.interrupt();
        consumer.join();*/
    }
}