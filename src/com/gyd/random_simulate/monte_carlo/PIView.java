package com.gyd.random_simulate.monte_carlo;

import com.gyd.lib.Helper;
import com.gyd.lib.Panel;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * Created by gongyidong on 2017/11/16.
 */
public class PIView extends JFrame {

    private int canvasWidth;
    private int canvasHeight;
    private Circle circle;

    public PIView(String title, int canvasWidth, int canvasHeight) {
        super(title);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        _setFrame();
    }

    public PIView(String title) {
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

    private LinkedList<Point> points;

    // 渲染
    public void render(Circle circle, LinkedList<Point> points) {
        this.points = points;
        this.circle = circle;
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

            Helper.setColor(g2d, Helper.Grey);
            // 画一个矩形
            Helper.fillRectangle(g2d, 0, 0, canvasWidth, canvasHeight);
            // 画一个实心圆
            Helper.setColor(g2d, Helper.Brown);
            Helper.fillCircle(g2d, circle.getX(), circle.getY(), circle.getR());

            Helper.setColor(g2d, Helper.Orange);
            for (int i = 0; i < points.size(); i++) {
                Point point = points.get(i);
                Helper.fillCircle(g2d, point.x, point.y, 3);
            }
        }

    }


}
