package com.gyd.sort.quick;

import com.gyd.lib.SortData;

/**
 * Created by gongyidong on 2017/11/21.
 */
public class QuickData extends SortData {

    public int l, r;//进行排序的区间
    public int currentIndex;//当前索引位置
    public int currentPovit;//当前标定点
    public boolean[] fixedPovits;//已经放好位置的标定点
    public int lt, gt;// 大约v的区间范围[l...lt]<=v, [gt...r]>=v


    public QuickData(int N, int max, int min, TYPE dataType) {
        super(N, max, min, dataType);
    }

    public QuickData(int N, int max, int min) {
        super(N, max, min);
    }

    public QuickData(int N, int max) {
        super(N, max);
    }

    public QuickData(SortData data) {
        super(data);
    }

    @Override
    public void init() {
        super.init();
        fixedPovits = new boolean[N];
        for (int i = 0; i < N; i++) {
            fixedPovits[i] = false;
        }
    }
}
