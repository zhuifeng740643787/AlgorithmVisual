package com.gyd.sort.bubble;

import com.gyd.lib.SortData;

/**
 * Created by gongyidong on 2017/11/17.
 */
public class BubbleData extends SortData{

    public int orderedIndex = N; //已排好序的索引[orderedIndex...N)
    public int currentIndex = -1; //当前要排序的索引
    public int maxIndex = -1; //最大值的索引

    public BubbleData(int N, int max, int min, TYPE dataType) {
        super(N, max, min, dataType);
    }

    public BubbleData(int N, int max, int min) {
        super(N, max, min);
    }

    public BubbleData(int N, int max) {
        this(N, max, 1);
    }

    public BubbleData(BubbleData data) {
        super(data);
    }

}
