package ru.job4j.pools;

import java.util.Objects;

public class Sums {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Sums obj = (Sums) o;
        return rowSum == obj.rowSum && colSum == obj.colSum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowSum, colSum);
    }
}