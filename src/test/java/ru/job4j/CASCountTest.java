package ru.job4j;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CASCountTest {
    @Test
    public void whenWorkTwoThreads() throws InterruptedException {
        CASCount counter = new CASCount();
        Thread firstCount = new Thread(
                () -> {
                    for (int i = 0; i < 10000; i++) {
                        counter.increment();
                    }
                }
        );
        Thread secondCount = new Thread(
                () -> {
                    for (int i = 0; i < 10000; i++) {
                        counter.increment();
                    }
                }
        );
        firstCount.start();
        secondCount.start();
        firstCount.join();
        secondCount.join();
        assertThat(counter.get()).isEqualTo(20000);

    }

}