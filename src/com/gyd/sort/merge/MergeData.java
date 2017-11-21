package com.gyd.sort.merge;

import com.gyd.lib.SortData;

/**
 * Created by gongyidong on 2017/11/17.
 */
public class MergeData extends SortData {

    public int l = -1, r = -1; //要进行归并的区间[l...r]
    public int mergeIndex = -1; //当前要归并的索引

    public MergeData(int N, int max, int min, TYPE dataType) {
        super(N, max, min, dataType);
    }

    public MergeData(int N, int max, int min) {
        super(N, max, min);
    }

    public MergeData(int N, int max) {
        this(N, max, 1);
    }

    public MergeData(MergeData data) {
        super(data);
    }

}

