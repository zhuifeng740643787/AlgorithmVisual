package com.gyd.circle_move;

import com.gyd.lib.*;
import com.gyd.lib.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by gongyidong on 2017/11/16.
 */
public class CircleMoveView extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    public CircleMoveView(String title, int canvasWidth, int canvasHeight) {
        super(title);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        _setFrame();
    }

    public CircleMoveView(String title) {
        super(title);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.canvasWidth = (int) screenSize.getWidth();
        this.canvasHeight = (int) screenSize.getHeight();
        _setFrame();
    }

    private void _setFrame() {
        Canvas canvas = new Canvas();
        canvas.setBackground(Color.RED);
        // 设置内容面板
        setContentPane(canvas);
        // frame根据面板内容进行自动布局整理
        pack();

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private Circle[] circles;

    // 渲染
    public void render(Circle[] circles) {
        this.circles = circles;
        // 重新绘制画布
        repaint();
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }


    /**
     * 绘制面板 实际绘制区域
     * JFrame = MenuBar + Content Pane(Container = JPanel)
     */
    private class Canvas extends Panel {

        public Canvas() {
            super(canvasWidth, canvasHeight);
        }

        /**
         * 绘制组件
         *
         * @param g 绘图上下文环境
         */
        @Override
        public void paintComponent(Graphics g) {

            Graphics2D g2d = (Graphics2D) g;
            // 抗锯齿
            RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.addRenderingHints(hints);

            // 绘制圆圈
            for (Circle circle: circles) {
                if (circle.isMaster() || circle.isFilled) {
                    Helper.setColor(g2d, Color.RED);
                    Helper.fillCircle(g2d, circle.x, circle.y, circle.getR());
                    continue;
                }
                Helper.setColor(g2d, Color.BLUE);
                Helper.strokeCircle(g2d, circle.x, circle.y, circle.getR());
            }

        }

    }


}
