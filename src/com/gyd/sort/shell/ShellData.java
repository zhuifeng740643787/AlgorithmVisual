package com.gyd.sort.shell;

import com.gyd.lib.SortData;

/**
 * Created by gongyidong on 2017/11/20.
 */
public class ShellData extends SortData {

    public ShellData(int N, int max, int min, TYPE dataType) {
        super(N, max, min, dataType);
    }

    public int distance = 1;//间隔
    public int currentIndex = -1; //当前要排序的索引
    public int insertIndex= -1; //要插入的索引位置

    public ShellData(int N, int max, int min) {
        super(N, max, min);
    }

    public ShellData(int N, int max) {
        this(N, max, 1);
    }

    public ShellData(ShellData data) {
        super(data);
    }



}
