package com.gyd.sort.heap;

import com.gyd.lib.SortData;

/**
 * Created by gongyidong on 2017/11/21.
 */
public class HeapData extends SortData {

    public int heapIndex; // 已排好序的索引位置
    public HeapData(int N, int max, int min, TYPE dataType) {
        super(N, max, min, dataType);
    }

    public HeapData(int N, int max, int min) {
        super(N, max, min);
    }

    public HeapData(int N, int max) {
        super(N, max);
    }

    public HeapData(SortData data) {
        super(data);
    }

}
