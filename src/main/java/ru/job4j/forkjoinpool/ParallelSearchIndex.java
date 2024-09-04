package ru.job4j.forkjoinpool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearchIndex<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final int from;
    private final int to;

    private final T element;

    public ParallelSearchIndex(T[] array, T element, int from, int to) {
        this.array = array;
        this.element = element;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if ((to - from + 1) <= 10) {
            return lineSearch();
        }
        int middle = (to - from + 1) / 2;
        ParallelSearchIndex<T> leftArray = new ParallelSearchIndex<>(array, element, from, middle);
        ParallelSearchIndex<T> rightArray = new ParallelSearchIndex<>(array, element, middle + 1, to);
        leftArray.fork();
        rightArray.fork();
        return Math.max(leftArray.join(), rightArray.join());
    }

    private int lineSearch() {
        int result = -1;
        for (int i = from; i <= to; i++) {
            if (element.equals(array[i])) {
                result = i;
                break;
            }
        }
        return result;
    }

    public static <T> int search(T[] array, T element) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelSearchIndex<>(array, element, 0, array.length - 1));
    }
}
