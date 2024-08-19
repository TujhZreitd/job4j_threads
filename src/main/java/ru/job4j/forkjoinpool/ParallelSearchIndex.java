package ru.job4j.forkjoinpool;

import java.util.Arrays;
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
        int result = -1;
        if ((to - from + 1) <= 10) {
            T[] arrayResult = Arrays.copyOfRange(array, from, to + 1);
            return lineSearch(arrayResult, element);
        }
        int middle = (to - from + 1) / 2;
        ParallelSearchIndex<T> leftArray = new ParallelSearchIndex<>(array, element, from, middle);
        ParallelSearchIndex<T> rightArray = new ParallelSearchIndex<>(array, element, middle + 1, to);
        leftArray.fork();
        rightArray.fork();
        int result1 = leftArray.join();
        if (result1 != -1) {
            result = result1;
        }
        int result2 = rightArray.join();
        if (result2 != -1) {
            result = result2;
        }
        return result;
    }

    private int lineSearch(T[] array, T element) {
        int result = -1;
        for (int i = 0; i < array.length; i++) {
            if (element.equals(array[i])) {
                result = i;
                break;
            }
        }
        return result;
    }
}
