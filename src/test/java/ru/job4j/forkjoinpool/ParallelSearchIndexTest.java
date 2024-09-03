package ru.job4j.forkjoinpool;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ParallelSearchIndexTest {
    @Test
    public void whenUseLineSearchIndex() {
        Integer[] array = {0, 1, 2, 3};
        int element = 2;
        int result = ParallelSearchIndex.search(new ParallelSearchIndex<>(array, element, 0, array.length - 1));
        assertThat(result).isEqualTo(2);
    }

    @Test
    public void whenUseParallelSearchIndex() {
        Integer[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        int element = 4;
        int result = ParallelSearchIndex.search(new ParallelSearchIndex<>(array, element, 0, array.length - 1));
        assertThat(result).isEqualTo(4);
    }

    @Test
    public void whenElementExists() {
        Integer[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        int element = 15;
        int result = ParallelSearchIndex.search(new ParallelSearchIndex<>(array, element, 0, array.length - 1));
        assertThat(result).isEqualTo(-1);
    }

    @Test
    public void whenSearchAnotherType() {
        Integer[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        String element = "10";
        int result = ParallelSearchIndex.search(new ParallelSearchIndex<>(array, element, 0, array.length - 1));
        assertThat(result).isEqualTo(-1);
    }

}