package ru.job4j;

import net.jcip.annotations.ThreadSafe;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class CASCount {
    private final AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        int numberNow;
        int numberNext;
        do {
            numberNow = count.get();
            numberNext = numberNow + 1;
        } while (!count.compareAndSet(numberNow, numberNext));
        /*count.incrementAndGet();*/
    }

    public int get() {
        return count.get();
    }
}