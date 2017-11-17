package com.gyd.random_simulate.share_money;

import com.gyd.lib.Helper;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by gongyidong on 2017/11/17.
 */
public class MoneyController {

    private int screenWidth;
    private int screenHeight;
    private int[] data;
    private MoneyView frame;

    private static final int DELAY = 24;

    public MoneyController(int screenWidth, int screenHeight, int N) {
        if (N <= 0) {
            return;
        }
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        data = new int[N];
        for (int i = 0; i < N; i++) {
            data[i] = 100;
        }

    }

    public void run() {
        // 事件分发线程
        EventQueue.invokeLater(() -> {
            frame = new MoneyView("随机模拟 - 分钱问题", screenWidth, screenHeight);
            frame.addKeyListener(new KeyboardLister());
            // 必须要放入一个线程中
            new Thread(() -> {
                _run();
            }).start();
        });

    }

    private void _run() {
        while (true) {
            // 绘制数据
            frame.render(data);
            // 暂停
            Helper.pause(DELAY);
            // 更新数据
            setData();
        }
    }

    // 更新数据
    private void setData() {

        // 加快模拟，循环50次
        for (int loop = 0; loop < 50; loop++) {
            for (int i = 0; i < data.length; i++) {
                int random = (int) (Math.random() * data.length);
                data[i]--;
                data[random]++;
            }
        }
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
