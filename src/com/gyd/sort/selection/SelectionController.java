package com.gyd.sort.selection;

import com.gyd.lib.Helper;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by gongyidong on 2017/11/17.
 */
public class SelectionController {

    private int screenWidth;
    private int screenHeight;
    private SelectionData data;
    private SelectionView frame;
    private static final int DELAY = 24;

    public SelectionController(int screenWidth, int screenHeight, int N) {
        if (N <= 0) {
            return;
        }
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        data = new SelectionData(N, screenHeight - 1);
    }

    public void run() {
        // 事件分发线程
        EventQueue.invokeLater(() -> {
            frame = new SelectionView("选择排序", screenWidth, screenHeight);
            frame.addKeyListener(new KeyboardLister());
            // 必须要放入一个线程中
            new Thread(() -> {
                _run();
            }).start();
        });

    }

    private void _run() {

        setData(-1, -1, -1);
        // 排序
        for (int i = 0; i < data.N(); i++) {
            int minIndex = i;
            setData(i, i, -1);
            for (int j = i; j < data.N(); j++) {
                if (data.get(j) < data.get(minIndex)) {
                    minIndex = j;
                    setData(i, i, minIndex);
                }
            }
            if (minIndex != i) {
                data.swap(i, minIndex);
            }
        }
        setData(data.N(), -1, -1);

    }

    // 更新数据
    private void setData(int orderedIndex, int compareCurrentIndex, int minIndex) {
        data.orderedIndex = orderedIndex;
        data.compareCurrentIndex = compareCurrentIndex;
        data.minIndex = minIndex;
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
