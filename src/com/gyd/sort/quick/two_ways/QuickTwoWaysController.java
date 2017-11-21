package com.gyd.sort.quick.two_ways;

import com.gyd.lib.AlgorithmController;
import com.gyd.lib.AlgorithmThread;
import com.gyd.lib.Helper;
import com.gyd.lib.SortData;
import com.gyd.sort.quick.QuickData;

import java.awt.*;

/**
 * Created by gongyidong on 2017/11/21.
 */
public class QuickTwoWaysController extends AlgorithmController {

    private QuickData data;
    private QuickTwoWaysView frame;

    public QuickTwoWaysController(int screenWidth, int screenHeight, int N) {
        super(screenWidth, screenHeight, N);
    }

    @Override
    public void initData() {
        data = new QuickData(N, screenHeight - 1, 1, QuickData.TYPE.NearlyOrdered);
    }

    @Override
    public void run() {
        // 事件分发线程
        EventQueue.invokeLater(() -> {
            frame = new QuickTwoWaysView("双路快速排序", screenWidth, screenHeight);
            frame.addKeyListener(new KeyboardLister());

            // 必须要放入一个线程中
            thread = new AlgorithmThread("双路快速排序", DELAY) {
                @Override
                public void run() {
                    super.run();
                }

                @Override
                public void handle() {
                    _run();
                }
            };
            // 启动线程
            thread.start();
        });

    }

    private void _run() {
        Helper.printArray(data.getNumbers(), data.N());
        setData(-1, -1, -1, -1, -1, -1);
        // 双路快速排序
        _quickSort2Ways(0, data.N() - 1);
        setData(0, data.N() - 1, -1, -1, -1, -1);
        Helper.printArray(data.getNumbers(), data.N());
    }

    // 对[l...r]进行快速排序
    private void _quickSort2Ways(int l, int r) {
        if (l > r) {
            return;
        }
        if (l == r) {
            setData(l, r, l, l, -1, -1);
            return;
        }
        // 获取分割数组的索引位置
        int pIndex = _partition(l, r);
        setData(l, r, pIndex, -1, -1, -1);
        // 对[l...pIndex-1]进行快速排序
        _quickSort2Ways(l, pIndex - 1);
        // 对[pIndex+1...r]进行快速排序
        _quickSort2Ways(pIndex + 1, r);

    }

    // 对[l...r]进行分割
    private int _partition(int l, int r) {
        data.swap(l, (int) (Math.random() * (r - l + 1)) + l);
        int v = data.get(l);
        // [l+1...i) <= v, (j...r]>=v
        int i = l + 1, j = r;
        setData(l, r, -1, l, -1, -1);
        while (true) {
            while (i <= j && data.get(i) < v) {
                i++;
                setData(l, r, -1, l, i, j);
            }
            while (j >= i && data.get(j) > v) {
                j--;
                setData(l, r, -1, l, i, j);
            }
            if (i > j) {
                break;
            }
            data.swap(i++, j--);
            setData(l, r, -1, l, i, j);
        }
        data.swap(l, j);
        return j;
    }


    // 更新数据
    private void setData(int l, int r, int fixedPivot, int currentPivot, int lt, int gt) {
        data.l = l;
        data.r = r;
        if (fixedPivot >= 0 && fixedPivot < data.N()) {
            data.fixedPovits[fixedPivot] = true;
        }
        data.currentPovit = currentPivot;
        data.lt = lt;
        data.gt = gt;
        frame.render(data);
        Helper.pause(DELAY);
    }

}

