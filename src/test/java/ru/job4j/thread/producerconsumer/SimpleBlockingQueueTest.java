package ru.job4j.thread.producerconsumer;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.assertj.core.api.Assertions.*;

class SimpleBlockingQueueTest {
    @Test
    void simpleBlockingQueue() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        List<Integer> addList = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> result = new ArrayList<>();
        Thread producer = new Thread(new Producer<>(queue, addList));
        Thread consumer = new Thread(new Consumer<>(queue, result));
        producer.start();
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(result).hasSize(10).containsAll(Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        List<Integer> addList = List.of(0, 1, 2, 3, 4);
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        Thread producer = new Thread(
                () -> {
                    for (Integer el : addList) {
                        try {
                            queue.offer(el);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer).containsExactly(0, 1, 2, 3, 4);
    }
}