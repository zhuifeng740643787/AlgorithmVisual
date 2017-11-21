package com.gyd.lib;

import com.gyd.sort.selection.SelectionData;

import java.util.Arrays;

/**
 * Created by gongyidong on 2017/11/19.
 */
public class SortData {

    public enum TYPE {
        Default, // 默认
        NearlyOrdered, //近乎有序
        NearlyIdentical, // 近乎一致
    }

    protected int[] numbers;
    protected int N;

    private SortData() {
    }

    public SortData(int N, int max, int min, TYPE dataType) {
        if (N < 0) {
            throw new IllegalArgumentException("N 不能小于 0");
        }
        if (min > max) {
            throw new IllegalArgumentException("最小值不能大于最大值");
        }
        this.N = N;
        numbers = new int[N];

        if (dataType == TYPE.NearlyIdentical) {
            min = Math.max(min, 100);
            max = min + 10;
        }

        for (int i = 0; i < N; i++) {
            numbers[i] = (int) (Math.random() * (max - min)) + min;
        }

        if (dataType == TYPE.NearlyOrdered) {
            Arrays.sort(numbers);
            int swapTime = (int) (N * 0.02);
            for (int i = 0; i < swapTime; i++) {
                int a = (int) (Math.random() * N);
                int b = (int) (Math.random() * N);
                swap(a, b);
            }
        }

        init();
    }

    public SortData(int N, int max, int min) {
        this(N, max, min, TYPE.Default);
    }

    public SortData(int N, int max) {
        this(N, max, 1);
    }

    public SortData(SortData data) {
        N = data.N();
        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = data.get(i);
        }
    }

    public void init() {}
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

    public void setIndex(int index, int value) {
        if (index < 0 || index >= N()) {
            throw new ArrayIndexOutOfBoundsException("数组下标越界");
        }
        numbers[index] = value;
    }

}

