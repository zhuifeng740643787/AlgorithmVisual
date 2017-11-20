package com.gyd.sort.selection;

import com.gyd.lib.SortData;

/**
 * Created by gongyidong on 2017/11/17.
 */
public class SelectionData extends SortData{

    public int orderedIndex = -1; //已排好序的索引
    public int compareCurrentIndex = -1; //当前要排序的索引
    public int minIndex = -1; //最小值的索引

    public SelectionData(int N, int max, int min) {
        super(N, max, min);
    }

    public SelectionData(int N, int max) {
        this(N, max, 1);
    }

    public SelectionData(SelectionData data) {
        super(data);
    }

}
