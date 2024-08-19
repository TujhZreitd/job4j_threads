package ru.job4j.pools;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RowColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public Sums() {
            this.rowSum = 0;
            this.colSum = 0;
        }

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] resultSums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            resultSums[i] = new Sums();
        }

        for (int i = 0; i < matrix.length; i++) {
            int rowSum = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                rowSum += matrix[i][j];
                int count = resultSums[j].getColSum();
                count += matrix[i][j];
                resultSums[j].setColSum(count);
            }
            resultSums[i].setRowSum(rowSum);
        }
        return resultSums;
    }

    public static Sums[] asyncSum(int[][] matrix) {
        int size = matrix.length;
        Sums[] resultSums = new Sums[size];
        try {
            for (int i = 0; i < size; i++) {
                resultSums[i] = sumOneRowAndCol(matrix, i).get();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException exp) {
            Thread.currentThread().interrupt();
            exp.printStackTrace();
        }
        return resultSums;
    }

    private static CompletableFuture<Sums> sumOneRowAndCol(int[][] matrix, int number) {
        return CompletableFuture.supplyAsync(
                () -> {
                    Sums sums = new Sums();
                    int sumRow = 0;
                    int sumCol = 0;
                    for (int i = 0; i < matrix.length; i++) {
                        sumRow += matrix[number][i];
                        sumCol += matrix[i][number];
                    }
                    sums.setRowSum(sumRow);
                    sums.setColSum(sumCol);
                    return sums;
                }
        );
    }
}