package com.gyd.circle_move;

import com.gyd.lib.Helper;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by gongyidong on 2017/11/17.
 */
public class CircleController {

    private int screenWidth;
    private int screenHeight;
    private Circle[] circles;
    private CircleMoveView frame;
    private boolean isAnimated = true;


    public CircleController(int screenWidth, int screenHeight, int N) {
        if (N <= 0) {
            return;
        }
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        Circle[] circles = new Circle[N];

        Circle masterCircle = new Circle(screenWidth, screenHeight, true);
        circles[0] = masterCircle;
        for (int i = 1; i < N; i++) {
            circles[i] = new Circle(screenWidth, screenHeight, false);
            masterCircle.addObserver(circles[i]);
            circles[i].addObserver(masterCircle);
        }
        this.circles = circles;

    }

    public void run() {
        // 事件分发线程
        EventQueue.invokeLater(() -> {
            frame = new CircleMoveView("Circle Move", screenWidth, screenHeight);
            frame.addKeyListener(new KeyboardLister());
            frame.addMouseListener(new MouseListener());
            // 必须要放入一个线程中
            new Thread(() -> {
                _run();
            }).start();
        });

    }

    private void _run() {
        while (true) {
            // 绘制数据
            frame.render(circles);
            // 暂停
            Helper.pause(24);
            // 更新数据
            setData();
        }
    }

    // 更新数据
    private void setData() {
        if (!isAnimated) {
            return;
        }
        for (Circle circle : circles) {
            circle.move(0, 0, screenWidth, screenHeight);
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
                isAnimated = !isAnimated;
                System.out.println("空格键");
            }
        }
    }

    private class MouseListener extends MouseAdapter {

        /**
         * 鼠标左键按下事件
         */
        @Override
        public void mousePressed(MouseEvent e) {

            // 鼠标坐标便宜（去除menubar的高度）
            int menuBarHeight = frame.getBounds().height - frame.getCanvasHeight();
            e.translatePoint(0, -menuBarHeight);

            for (Circle circle: circles) {
                if (circle.isMaster()) {
                    continue;
                }

                if (circle.contain(e.getPoint())) {
                    circle.isFilled = !circle.isFilled;
                }
            }
        }
    }


//    public static void main(String[] args) {
//        int screenWidth = 600;
//        int screenHeight = 400;
//
//        int N = (int) (Math.random() * 5) + 5;// 定义圆圈的个数
//
//        CircleController controller = new CircleController(screenWidth, screenHeight, N);
//        controller.run();
//    }

}
