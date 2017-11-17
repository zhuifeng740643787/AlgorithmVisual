package com.gyd.sort.selection;

/**
 * Created by gongyidong on 2017/11/17.
 */
public class SelectionData {

    private int[] numbers;
    private int N;

    public int orderedIndex = -1; //已排好序的索引
    public int compareCurrentIndex = -1; //当前要排序的索引
    public int minIndex = -1; //最小值的索引

    public SelectionData(int N, int max, int min) {
        if (N < 0) {
            throw new IllegalArgumentException("N 不能小于 0");
        }
        if (min > max) {
            throw new IllegalArgumentException("最小值不能大于最大值");
        }
        this.N = N;
        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = (int) (Math.random() * (max)) + min;
        }

    }

    public SelectionData(int N, int max) {
        this(N, max, 1);
    }

    public SelectionData(SelectionData data) {
        N = data.N();
        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = data.get(i);
        }
    }

    public int N() {
        return N;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public int get(int index) {
        if (index < 0 || index > N) {
            throw new ArrayIndexOutOfBoundsException("数组越界");
        }
        return numbers[index];
    }

    public void swap(int i1, int i2) {
        if (i1 < 0 || i1 > N || i2 < 0 || i2 > N) {
            throw new ArrayIndexOutOfBoundsException("数组越界");
        }
        if (i1 == i2) {
            return;
        }
        int tmp = numbers[i1];
        numbers[i1] = numbers[i2];
        numbers[i2] = tmp;
    }

}
