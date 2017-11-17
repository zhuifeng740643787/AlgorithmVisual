package com.gyd.random_simulate.three_door;

import com.gyd.lib.Helper;
import com.gyd.random_simulate.monte_carlo.Circle;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

/**
 * Created by gongyidong on 2017/11/17.
 */
public class WinController {

    private WinView frame;
    private int screenWidth;
    private int screenHeight;

    private Circle circle;
    private static final int DELAY = 50;

    private int total;
    private int winTotal;
    private Boolean stop = false;//是否停止运行

    public WinController(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }


    public void run() {
        // 事件分发线程
        EventQueue.invokeLater(() -> {
            frame = new WinView("蒙特卡洛算法 - 三门问题", screenWidth, screenHeight);
            frame.addKeyListener(new KeyListener());
            // 必须要放入一个线程中
            new Thread(() -> {
                _run();
            }).start();
        });

    }

    private void _run() {
        while (true) {
            if (stop) {
                Helper.pause(DELAY);
                continue;
            }
            // 更新数据
            setData();
            // 绘制数据
            frame.render(total, winTotal);
            // 暂停
            Helper.pause(DELAY);
        }
    }

    // 更新数据
    private void setData() {
        int n = (int) (Math.random() * 10 + 5);
        for (int i = 0; i < n; i++) {
            total++;
            if (play(false)) {
                winTotal++;
            }
        }
    }

    // 玩一次, 是否换门
    private boolean play(boolean changeDoor) {
        // 三个门 0,1,2
        int choiceDoor = (int) (Math.random() * 3);
        int winDoor = (int) (Math.random() * 3);

        // 第一次开门中奖
        if (choiceDoor == winDoor) {
            return changeDoor ? false : true;
        }

        return changeDoor ? true : false;
    }


    private class KeyListener extends KeyAdapter {


        /**
         * Invoked when a key has been released.
         */
        @Override
        public void keyReleased(KeyEvent e) {

            if (e.getKeyChar() == ' ') {
                stop = !stop;
                System.out.println("空格键");
            }
        }
    }

}

