package com.gyd.sort.shell;

import com.gyd.lib.Helper;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by gongyidong on 2017/11/17.
 */
public class ShellController {

    private int screenWidth;
    private int screenHeight;
    private ShellData data;
    private ShellView frame;
    private static final int DELAY = 24;

    public ShellController(int screenWidth, int screenHeight, int N) {
        if (N <= 0) {
            return;
        }
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        data = new ShellData(N, screenHeight - 1, 1);
//        data = new ShellData(N, screenHeight - 1, 1, SortData.TYPE.NearlyOrdered);
    }

    public void run() {
        // 事件分发线程
        EventQueue.invokeLater(() -> {
            frame = new ShellView("希尔排序", screenWidth, screenHeight);
            frame.addKeyListener(new KeyboardLister());
            // 必须要放入一个线程中
            new Thread(() -> {
                _run();
            }).start();
        });

    }

    private void _run() {
        setData(data.N(), 1, -1);
        // 希尔排序, 加大交换的跨度，提高排序速度
        int distance;//数组元素的间隔, d/2, d/4 ... 1
        for (distance = data.N() / 2; distance >= 1; distance /= 2) {
            // 对不同间隔组成的数组进行插入排序
            for (int p = 0; p < distance; p++) {
                // 插入排序[p, p+distance, p+2*distance, ... N)
                for (int i = p + distance; i < data.N(); i += distance) {
                    int t = data.get(i);
                    int j = i;
                    for (; j > p; j -= distance) {
                        if (data.get(j - distance) <= t) {
                            break;
                        }
                        setData(distance, i, j);
                        // 将当前索引的值变为前面索引的数据
                        data.setIndex(j, data.get(j - distance));
                    }
                    if (j != i) {
                        data.setIndex(j, t);
                    }
                }
            }
        }


        setData(0, -1, -1);

    }

    // 更新数据
    private void setData(int distance, int currentIndex, int insertIndex) {
        data.distance = distance;
        data.currentIndex = currentIndex;
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
