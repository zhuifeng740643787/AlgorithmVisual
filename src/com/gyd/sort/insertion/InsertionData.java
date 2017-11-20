package com.gyd.sort.insertion;

import com.gyd.lib.SortData;

/**
 * Created by gongyidong on 2017/11/17.
 */
public class InsertionData extends SortData {

    public int orderedIndex = 0; //已排好序的索引[0...orderIndex]
    public int compareCurrentIndex = -1; //当前要排序的索引
    public int insertIndex= -1; //要插入的索引位置

    public InsertionData(int N, int max, int min, TYPE dataType) {
        super(N, max, min, dataType);
    }
    public InsertionData(int N, int max, int min) {
        super(N, max, min);
    }

    public InsertionData(int N, int max) {
        this(N, max, 1);
    }

    public InsertionData(InsertionData data) {
        super(data);
    }

}

