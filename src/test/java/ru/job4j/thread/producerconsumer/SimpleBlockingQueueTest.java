package ru.job4j.thread.producerconsumer;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
}