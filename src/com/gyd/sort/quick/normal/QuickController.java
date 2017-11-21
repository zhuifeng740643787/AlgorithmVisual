package com.gyd.sort.quick.normal;

import com.gyd.lib.AlgorithmController;
import com.gyd.lib.AlgorithmThread;
import com.gyd.lib.Helper;
import com.gyd.lib.SortData;
import com.gyd.sort.quick.QuickData;

import java.awt.*;

/**
 * Created by gongyidong on 2017/11/21.
 */
public class QuickController extends AlgorithmController {

    private QuickData data;
    private QuickView frame;

    public QuickController(int screenWidth, int screenHeight, int N) {
        super(screenWidth, screenHeight, N);
    }

    @Override
    public void initData() {
        data = new QuickData(N, screenHeight - 1, 1, QuickData.TYPE.Default);
    }

    @Override
    public void run() {
        // 事件分发线程
        EventQueue.invokeLater(() -> {
            frame = new QuickView("快速排序", screenWidth, screenHeight);
            frame.addKeyListener(new KeyboardLister());

            // 必须要放入一个线程中
            thread = new AlgorithmThread("快速排序", DELAY) {
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
        // 快速排序
        _quickSort(0, data.N() - 1);
        setData(0, data.N() - 1, -1, -1, -1, -1);
        Helper.printArray(data.getNumbers(), data.N());
    }

    // 对[l...r]进行快速排序
    private void _quickSort(int l, int r) {
        if (l > r) {
            return;
        }
        if (l == r) {
            setData(l, r, l, -1, -1, -1);
        }
        // 获取分割数组的索引位置
        int pIndex = _partition(l, r);
        setData(l, r, pIndex, -1, -1, -1);
        // 对[l...pIndex-1]进行快速排序
        _quickSort(l, pIndex - 1);
        // 对[pIndex+1...r]进行快速排序
        _quickSort(pIndex + 1, r);

    }

    // 对[l...r]进行分割
    private int _partition(int l, int r) {
        setData(l, r, -1, l, -1, -1);
        // 先对第一个元素与后面随机元素进行数据交换，防止近乎有序的数组进行排序时，退化为O(n^2)
        data.swap(l, (int) (Math.random() * (r - l + 1)) + l);
        // 以第一个索引值为比较对象
        int v = data.get(l);
        // [l+1...j] < v, [j...i-1] >= v
        int i = l + 1, j = l;
        for (; i <= r; i++) {

            if (thread.isSuspended()) {
                thread.pause();
            }

            setData(l, r, -1, l, i, j);
            if (data.get(i) < v) {
                data.swap(i, ++j);
            }
        }
        data.swap(j, l);
        return j;
    }


    // 更新数据
    private void setData(int l, int r, int fixedPivot, int currentPivot, int currentIndex, int lt) {
        data.l = l;
        data.r = r;
        data.currentIndex = currentIndex;
        if (fixedPivot >= 0 && fixedPivot < data.N()) {
            data.fixedPovits[fixedPivot] = true;
        }
        data.currentPovit = currentPivot;
        data.lt = lt;
        frame.render(data);
        Helper.pause(DELAY);
    }

}

