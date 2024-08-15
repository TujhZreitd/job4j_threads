package ru.job4j.thread.producerconsumer;

import org.junit.jupiter.api.Test;
import ru.job4j.synch.SingleLockList;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

class SimpleBlockingQueueTest {
   /* @Test
    void simpleBlockingQueue() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        List<Integer> addList = List.of(1, 2, 3, 4, 5);
        Thread producer = new Thread(new Producer<Integer>(queue, addList));
        Thread consumer = new Thread(new Consumer<Integer>(queue));
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();

        assertThat(rsl).hasSize(2).containsAll(Set.of(1, 2));
    }*/

    /*@Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread producer = new Thread(
                () -> {
                    IntStream.range(0, 5).forEach(
                            queue::offer
                    );
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {

                            buffer.add(queue.poll());

                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer).containsExactly(0, 1, 2, 3, 4);
    }*/

}