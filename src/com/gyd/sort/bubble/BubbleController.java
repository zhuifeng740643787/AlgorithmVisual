package com.gyd.sort.bubble;

import com.gyd.lib.Helper;
import com.gyd.lib.SortData;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by gongyidong on 2017/11/17.
 */
public class BubbleController {

    private int screenWidth;
    private int screenHeight;
    private BubbleData data;
    private BubbleView frame;
    private static final int DELAY = 24;

    public BubbleController(int screenWidth, int screenHeight, int N) {
        if (N <= 0) {
            return;
        }
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

//        data = new BubbleData(N, screenHeight - 1);
        data = new BubbleData(N, screenHeight - 1, 1, SortData.TYPE.NearlyOrdered);
    }

    public void run() {
        // 事件分发线程
        EventQueue.invokeLater(() -> {
            frame = new BubbleView("冒泡排序", screenWidth, screenHeight);
            frame.addKeyListener(new KeyboardLister());
            // 必须要放入一个线程中
            new Thread(() -> {
                _run();
            }).start();
        });

    }

    private void _run() {

        setData(data.N(), -1, -1);
        // 排序
        for (int i = data.N() - 1; i > 0; i--) {
            boolean hadSwap = false;
            setData(i+1, i, -1);
            for (int j = 0; j < i; j++) {
                setData(i+1, i, j);
                if (data.get(j) > data.get(j + 1)) {
                    setData(i+1, i, j);
                    data.swap(j, j+1);
                    hadSwap = true;
                }
            }
            if (!hadSwap) {
                break;
            }
        }
        setData(0, -1, -1);

    }

    // 更新数据
    private void setData(int orderedIndex, int currentIndex, int maxIndex) {
        data.orderedIndex = orderedIndex;
        data.currentIndex = currentIndex;
        data.maxIndex = maxIndex;
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
