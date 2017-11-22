package com.gyd.sort.quick.three_ways;

import com.gyd.lib.AlgorithmController;
import com.gyd.lib.AlgorithmThread;
import com.gyd.lib.Helper;
import com.gyd.lib.SortData;
import com.gyd.sort.quick.QuickData;

import java.awt.*;

/**
 * Created by gongyidong on 2017/11/21.
 */
public class QuickThreeWaysController extends AlgorithmController {

    private QuickData data;
    private QuickThreeWaysView frame;

    public QuickThreeWaysController(int screenWidth, int screenHeight, int N) {
        super(screenWidth, screenHeight, N);
    }

    @Override
    public void initData() {
        data = new QuickData(N, screenHeight - 1, 1, QuickData.TYPE.NearlyIdentical);
    }

    @Override
    public void run() {
        // 事件分发线程
        EventQueue.invokeLater(() -> {
            frame = new QuickThreeWaysView("三路快速排序", screenWidth, screenHeight);
            frame.addKeyListener(new KeyboardLister());

            // 必须要放入一个线程中
            thread = new AlgorithmThread("三路快速排序", DELAY) {
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
        setData(-1, -1, -1, -1, -1, -1, -1);
        // 三路快速排序
        _quickSort3Ways(0, data.N() - 1);
        setData(0, data.N() - 1, -1, -1, -1, -1, -1);
        Helper.printArray(data.getNumbers(), data.N());
    }

    // 对[l...r]进行快速排序
    private void _quickSort3Ways(int l, int r) {
        if (l > r) {
            return;
        }
        if (l == r) {
            setData(l, r, l, -1, -1, -1, -1);
            return;
        }

        data.swap(l, (int) (Math.random() * (r - l + 1)) + l);

        int v = data.get(l);
        // [l+1...lt] < v, [lt+1...i) == v, [gt...r] > v
        int i = l + 1, lt = l, gt = r + 1;
        for (; i < gt; i++) {
            if (data.get(i) > v) {
                data.swap(i--, --gt);
            } else if (data.get(i) < v) {
                data.swap(i, ++lt);
            }
            setData(l, r, -1, l, i, lt, gt);

        }
        // l与lt位置交换，且lt--
        data.swap(l, lt--);
        setData(l, r, lt + 1, l, -1, lt, gt);

        // 对[l...lt]进行快速排序
        _quickSort3Ways(l, lt);
        // 对[gt...r]进行快速排序
        _quickSort3Ways(gt, r);

    }


    // 更新数据
    private void setData(int l, int r, int fixedPivot, int currentPivot, int currentIndex, int lt, int gt) {
        data.l = l;
        data.r = r;
        if (fixedPivot >= 0 && fixedPivot < data.N()) {
            int index = fixedPivot;
            while (index < data.N() && data.get(index) == data.get(fixedPivot)) {
                data.fixedPovits[index] = true;
                index++;
            }
        }
        data.currentPovit = currentPivot;
        data.currentIndex = currentIndex;
        data.lt = lt;
        data.gt = gt;
        frame.render(data);
        Helper.pause(DELAY);
    }

}

