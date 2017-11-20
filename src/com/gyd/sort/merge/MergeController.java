package com.gyd.sort.merge;

import com.gyd.lib.Helper;
import com.gyd.lib.SortData;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

/**
 * Created by gongyidong on 2017/11/17.
 */
public class MergeController {

    private int screenWidth;
    private int screenHeight;
    private MergeData originData;
    private MergeData data;
    private MergeView frame;
    private static final int DELAY = 24;

    public MergeController(int screenWidth, int screenHeight, int N) {
        if (N <= 0) {
            return;
        }
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

//        data = new MergeData(N, screenHeight - 1, 1, SortData.TYPE.NearlyOrdered);
        data = new MergeData(N, screenHeight - 1);
        originData = new MergeData(data);
    }

    public void run() {
        // 事件分发线程
        EventQueue.invokeLater(() -> {
            frame = new MergeView("归并排序", screenWidth, screenHeight);
            frame.addKeyListener(new KeyboardLister());
            // 必须要放入一个线程中
            new Thread(() -> {
                _run();
            }).start();
        });

    }

    private void _run() {
        Helper.printArray(data.getNumbers(), data.N());
        setData(0, 1, -1);
        // 归并排序
        _merge(0, data.N() - 1);
        setData(data.N(), -1, -1);
        int[] arr = new int[data.N()];
        arr = originData.getNumbers();
        Arrays.sort(arr);
        Helper.printArray(arr, data.N());
        Helper.printArray(data.getNumbers(), data.N());

    }

    // 对[l...r]进行归并排序
    private void _merge(int l, int r) {
        if (l >= r) {
            return;
        }
        int midIndex = (r - l) / 2 + l;
        // 左右两边进行归并
        _merge(l, midIndex);//[l...midIndex]
        _merge(midIndex + 1, r);//[midIndex+1...r]
        // 进行合并
        if (data.get(midIndex + 1) < data.get(midIndex)) {
            _mergeSort(l, midIndex, r);
        }
    }

    private void _mergeSort(int l, int midIndex, int r) {
        int i = l, j = midIndex + 1;

        for (int k = l; k <= r; k++) {
            if (i > midIndex) {
                data.setIndex(k, data.get(j));
                j++;
                continue;
            }
            if (j > r) {
                data.setIndex(k, data.get(i));
                i++;
                continue;

            }
            if (data.get(i) > data.get(j)) {
                data.setIndex(k, data.get(j));
                j++;
            } else {
                data.setIndex(k, data.get(i));
                i++;
            }
        }


    }

    // 更新数据
    private void setData(int orderedIndex, int compareCurrentIndex, int insertIndex) {
        data.orderedIndex = orderedIndex;
        data.compareCurrentIndex = compareCurrentIndex;
        data.insertIndex = insertIndex;
        frame.render(data);
        Helper.pause(DELAY);
    }

    /**
     * 键盘事件监听类
     */
    private class KeyboardLister extends KeyAdapter {
        /**
         * Invoked when a key has been released.
         */
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyChar() == ' ') {
                System.out.println("空格键");
            }
        }
    }


}
