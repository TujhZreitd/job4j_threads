package ru.job4j.pools;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class RowColSumTest {
    @Test
    public void whenMatrixSizeTwo() {
        int[][] matrix = {{1, 2}, {3, 4}};
        RowColSum.Sums[] result = RowColSum.sum(matrix);
        RowColSum.Sums[] expected = new RowColSum.Sums[matrix.length];
        for (int i = 0; i < expected.length; i++) {
            expected[i] = new RowColSum.Sums();
        }
        expected[0].setRowSum(3);
        expected[0].setColSum(4);
        expected[1].setRowSum(7);
        expected[1].setColSum(6);
        assertThat(result[0].getRowSum()).isEqualTo(expected[0].getRowSum());
        assertThat(result[0].getColSum()).isEqualTo(expected[0].getColSum());
        assertThat(result[1].getRowSum()).isEqualTo(expected[1].getRowSum());
        assertThat(result[1].getColSum()).isEqualTo(expected[1].getColSum());
    }

    @Test
    public void whenMatrixSizeThree() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RowColSum.Sums[] result = RowColSum.sum(matrix);
        RowColSum.Sums[] expected = new RowColSum.Sums[matrix.length];
        for (int i = 0; i < expected.length; i++) {
            expected[i] = new RowColSum.Sums();
        }
        expected[0].setRowSum(6);
        expected[0].setColSum(12);
        expected[1].setRowSum(15);
        expected[1].setColSum(15);
        expected[2].setRowSum(24);
        expected[2].setColSum(18);
        assertThat(result[0].getRowSum()).isEqualTo(expected[0].getRowSum());
        assertThat(result[0].getColSum()).isEqualTo(expected[0].getColSum());
        assertThat(result[1].getRowSum()).isEqualTo(expected[1].getRowSum());
        assertThat(result[1].getColSum()).isEqualTo(expected[1].getColSum());
        assertThat(result[2].getRowSum()).isEqualTo(expected[2].getRowSum());
        assertThat(result[2].getColSum()).isEqualTo(expected[2].getColSum());
    }

    @Test
    public void whenMatrixSizeTwoWithAsync() {
        int[][] matrix = {{1, 2}, {3, 4}};
        RowColSum.Sums[] result = RowColSum.asyncSum(matrix);
        RowColSum.Sums[] expected = new RowColSum.Sums[matrix.length];
        for (int i = 0; i < expected.length; i++) {
            expected[i] = new RowColSum.Sums();
        }
        expected[0].setRowSum(3);
        expected[0].setColSum(4);
        expected[1].setRowSum(7);
        expected[1].setColSum(6);
        assertThat(result[0].getRowSum()).isEqualTo(expected[0].getRowSum());
        assertThat(result[0].getColSum()).isEqualTo(expected[0].getColSum());
        assertThat(result[1].getRowSum()).isEqualTo(expected[1].getRowSum());
        assertThat(result[1].getColSum()).isEqualTo(expected[1].getColSum());
    }

    @Test
    public void whenMatrixWithAsync() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RowColSum.Sums[] result = RowColSum.asyncSum(matrix);
        RowColSum.Sums[] expected = new RowColSum.Sums[matrix.length];
        for (int i = 0; i < expected.length; i++) {
            expected[i] = new RowColSum.Sums();
        }
        expected[0].setRowSum(6);
        expected[0].setColSum(12);
        expected[1].setRowSum(15);
        expected[1].setColSum(15);
        expected[2].setRowSum(24);
        expected[2].setColSum(18);
        assertThat(result[0].getRowSum()).isEqualTo(expected[0].getRowSum());
        assertThat(result[0].getColSum()).isEqualTo(expected[0].getColSum());
        assertThat(result[1].getRowSum()).isEqualTo(expected[1].getRowSum());
        assertThat(result[1].getColSum()).isEqualTo(expected[1].getColSum());
        assertThat(result[2].getRowSum()).isEqualTo(expected[2].getRowSum());
        assertThat(result[2].getColSum()).isEqualTo(expected[2].getColSum());
    }

}