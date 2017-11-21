package com.gyd.sort.merge;

import com.gyd.lib.AlgorithmController;
import com.gyd.lib.AlgorithmThread;
import com.gyd.lib.Helper;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by gongyidong on 2017/11/17.
 */
public class MergeController extends AlgorithmController{

    private MergeData data;
    private MergeView frame;

    public MergeController(int screenWidth, int screenHeight, int N) {
        super(screenWidth, screenHeight, N);
    }

    @Override
    public void initData() {
        data = new MergeData(N, screenHeight - 1);
    }

    public void run() {
        // 事件分发线程
        EventQueue.invokeLater(() -> {
            frame = new MergeView("归并排序", screenWidth, screenHeight);
            frame.addKeyListener(new KeyboardLister());

            // 必须要放入一个线程中
            thread = new AlgorithmThread("归并排序", DELAY) {
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
        setData(-1, -1, -1);
        // 归并排序
//        _mergeSortT2B(0, data.N() - 1);
        _mergeSortB2T(0, data.N() - 1);
        setData(0, data.N() - 1, data.N() - 1);
    }

    // 自底向上对[l...r]进行排序, 减少了递归的开销
    private void _mergeSortB2T(int l, int r) {
        for (int sz = 1; sz < data.N(); sz += sz) {
            for (int i = 0; i < data.N() - sz; i += 2 * sz) {
                // 对[i...i+sz-1]和[i+sz...i+2*sz-1]
                int rIndex = Math.min(data.N() - 1, i + 2 * sz - 1);
                int midIndex = i + sz - 1;
                setData(i, rIndex, -1);
                _merge(i, midIndex, rIndex);
            }
        }
    }


    // 自顶向下对[l...r]进行归并排序
    private void _mergeSortT2B(int l, int r) {
        if (l >= r) {
            return;
        }
        setData(l, r, -1);
        int midIndex = (r - l) / 2 + l;
        // 左右两边进行归并
        _mergeSortT2B(l, midIndex);//[l...midIndex]
        _mergeSortT2B(midIndex + 1, r);//[midIndex+1...r]
        // 进行合并
        if (data.get(midIndex + 1) < data.get(midIndex)) {
            _merge(l, midIndex, r);
        }
    }

    private void _merge(int l, int midIndex, int r) {

        // 临时存储[l...r]区间的数据，用于比较
        // 方法1
//        int[] arr = new int[r - l + 1];
//        for (int i = l; i <= r; i++) {
//            arr[i - l] = data.get(i);
//        }
        // 方法2
        int[] arr = Arrays.copyOfRange(data.getNumbers(), l, r + 1);

        int i = l, j = midIndex + 1;

        for (int k = l; k <= r; k++) {
            if (thread.isSuspended()) {
                thread.pause();
            }
            if (i > midIndex) {
                data.setIndex(k, arr[j - l]);
                j++;
                continue;
            }
            if (j > r) {
                data.setIndex(k, arr[i - l]);
                i++;
                continue;
            }
            if (arr[i - l] > arr[j - l]) {
                data.setIndex(k, arr[j - l]);
                j++;
            } else {
                data.setIndex(k, arr[i - l]);
                i++;
            }
            setData(l, r, k);
        }
    }

    // 更新数据
    private void setData(int l, int r, int mergeIndex) {
        data.l = l;
        data.r = r;
        data.mergeIndex = mergeIndex;
        frame.render(data);
        Helper.pause(DELAY);
    }

}
