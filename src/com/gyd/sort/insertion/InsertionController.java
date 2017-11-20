package com.gyd.sort.insertion;

import com.gyd.lib.Helper;
import com.gyd.lib.SortData;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by gongyidong on 2017/11/17.
 */
public class InsertionController {

    private int screenWidth;
    private int screenHeight;
    private InsertionData data;
    private InsertionView frame;
    private static final int DELAY = 24;

    public InsertionController(int screenWidth, int screenHeight, int N) {
        if (N <= 0) {
            return;
        }
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

//        data = new MergeData(N, screenHeight - 1, 1, SortData.TYPE.NearlyOrdered);
        data = new InsertionData(N, screenHeight - 1, 1, SortData.TYPE.NearlyOrdered);
    }

    public void run() {
        // 事件分发线程
        EventQueue.invokeLater(() -> {
            frame = new InsertionView("插入排序", screenWidth, screenHeight);
            frame.addKeyListener(new KeyboardLister());
            // 必须要放入一个线程中
            new Thread(() -> {
                _run();
            }).start();
        });

    }

    private void _run() {
        setData(0, 1, -1);
        // 插入排序
        for (int i = 1; i < data.N(); i++) {
            // 临时存储当前要比较的值
            int currentData = data.get(i);
            setData(i, i, -1);
            int j;
            for (j = i; j > 0; j--) {
                // 向前遍历终止的条件
                if (data.get(j - 1) <= currentData) {
                    break;
                }
                setData(i, i, j );
                data.setIndex(j, data.get(j - 1));
            }
            if (j != i)
                data.setIndex(j, currentData);
        }
        setData(data.N(), -1, -1);

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
