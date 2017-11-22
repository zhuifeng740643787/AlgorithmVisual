package com.gyd.sort.heap;

import com.gyd.lib.AlgorithmController;
import com.gyd.lib.AlgorithmThread;
import com.gyd.lib.Helper;
import com.gyd.lib.SortData;

import java.awt.*;

/**
 * Created by gongyidong on 2017/11/21.
 */
public class HeapController extends AlgorithmController {

    private HeapData data;
    private HeapView frame;

    public HeapController(int screenWidth, int screenHeight, int N) {
        super(screenWidth, screenHeight, N);
    }

    @Override
    public void initData() {
        data = new HeapData(N, screenHeight - 1, 1, HeapData.TYPE.NearlyOrdered);
    }

    @Override
    public void run() {
        // 事件分发线程
        EventQueue.invokeLater(() -> {
            frame = new HeapView("原地堆排序", screenWidth, screenHeight);
            frame.addKeyListener(new KeyboardLister());

            // 必须要放入一个线程中
            thread = new AlgorithmThread("原地堆排序", DELAY) {
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
        setData(data.N());
        Helper.printArray(data.getNumbers(), data.N());
        // 建堆, 从倒数第一个非叶子节点（包含一个或两个子节点）的索引位置进行shiftDown操作
        for (int i = (data.N() - 1 - 1) / 2; i >= 0; i--) {
            _shiftDown(data.N(), i);
        }

        // 堆排序
        for (int i = data.N() - 1; i > 0; i--) {
            // 交换第一个与最后一个元素的位置
            data.swap(0, i);
            setData(i);
            // 对第一个元素进行shiftDown操作
            _shiftDown(i, 0);
        }

        Helper.printArray(data.getNumbers(), data.N());
        setData(0);
    }

    // 对k位置在n个元素内进行shiftDown操作
    private void _shiftDown(int n, int k) {
        while (2 * k + 1 < n) {
            int j = 2 * k + 1;
            if (j + 1 < n && data.get(j) < data.get(j + 1)) {
                j++;
            }

            if (data.get(k) >= data.get(j)) {
                break;
            }

            data.swap(k, j);
            k = j;
        }
    }

    // 更新数据
    private void setData(int heapIndex) {
        data.heapIndex = heapIndex;
        frame.render(data);
        Helper.pause(DELAY);
    }

}

