package com.gyd.random_simulate.monte_carlo;

import com.gyd.lib.Helper;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

/**
 * Created by gongyidong on 2017/11/17.
 */
public class PIController {

    private PIView frame;
    private int screenWidth;
    private int screenHeight;

    private int innerTotalPoints;
    private Circle circle;
    private static final int DELAY = 24;
    private LinkedList<Point> points;
    private Boolean stop = false;//是否停止运行

    public PIController(int screenWidth, int screenHeight) {
        this.screenWidth = Math.min(screenWidth, screenHeight);
        this.screenHeight = this.screenWidth;
        points = new LinkedList<Point>();
        circle = new Circle(this.screenWidth / 2, this.screenWidth / 2, this.screenWidth / 2);
    }


    public void run() {
        // 事件分发线程
        EventQueue.invokeLater(() -> {
            frame = new PIView("蒙特卡洛算法 - 计算PI值", screenWidth, screenHeight);
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
            frame.render(circle, points);
            // 暂停
            Helper.pause(DELAY);
            System.out.println("样本个数="+ points.size() + " PI=" + Double.toString((4 * (double)innerTotalPoints / points.size())));
        }
    }

    // 更新数据
    private void setData() {
        int n = (int) (Math.random() * 10 + 5);
        for (int i = 0; i < n; i++) {
            int x = (int) (Math.random() * screenWidth);
            int y = (int) (Math.random() * screenHeight);
            Point point = new Point(x, y);
            points.add(point);
            if (circle.contain(point)) {
                innerTotalPoints++;
            }
        }
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

