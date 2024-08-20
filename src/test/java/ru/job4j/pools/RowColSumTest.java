package ru.job4j.pools;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class RowColSumTest {
    @Test
    public void whenMatrixSizeTwo() {
        int[][] matrix = {{1, 2}, {3, 4}};
        Sums[] result = RowColSum.sum(matrix);
        Sums[] expected = new Sums[matrix.length];
        for (int i = 0; i < expected.length; i++) {
            expected[i] = new Sums();
        }
        expected[0].setRowSum(3);
        expected[0].setColSum(4);
        expected[1].setRowSum(7);
        expected[1].setColSum(6);
        assertThat(expected).isEqualTo(result);
    }

    @Test
    public void whenMatrixSizeThree() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Sums[] result = RowColSum.sum(matrix);
        Sums[] expected = new Sums[matrix.length];
        for (int i = 0; i < expected.length; i++) {
            expected[i] = new Sums();
        }
        expected[0].setRowSum(6);
        expected[0].setColSum(12);
        expected[1].setRowSum(15);
        expected[1].setColSum(15);
        expected[2].setRowSum(24);
        expected[2].setColSum(18);
        assertThat(expected).isEqualTo(result);
    }

    @Test
    public void whenMatrixSizeTwoWithAsync() {
        int[][] matrix = {{1, 2}, {3, 4}};
        Sums[] result = RowColSum.asyncSum(matrix);
        Sums[] expected = new Sums[matrix.length];
        for (int i = 0; i < expected.length; i++) {
            expected[i] = new Sums();
        }
        expected[0].setRowSum(3);
        expected[0].setColSum(4);
        expected[1].setRowSum(7);
        expected[1].setColSum(6);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenMatrixWithAsync() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Sums[] result = RowColSum.asyncSum(matrix);
        Sums[] expected = new Sums[matrix.length];
        for (int i = 0; i < expected.length; i++) {
            expected[i] = new Sums();
        }
        expected[0].setRowSum(6);
        expected[0].setColSum(12);
        expected[1].setRowSum(15);
        expected[1].setColSum(15);
        expected[2].setRowSum(24);
        expected[2].setColSum(18);
        assertThat(result).isEqualTo(expected);
    }
}