package com.gyd.random_simulate.three_door;

import com.gyd.lib.Helper;
import com.gyd.lib.Panel;
import com.gyd.random_simulate.monte_carlo.Circle;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * Created by gongyidong on 2017/11/16.
 */
public class WinView extends JFrame {

    private int canvasWidth;
    private int canvasHeight;
    private Circle circle;

    public WinView(String title, int canvasWidth, int canvasHeight) {
        super(title);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        _setFrame();
    }

    public WinView(String title) {
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

    private int winTotal;
    private int total;

    // 渲染
    public void render(int total, int winTotal) {
        this.total = total;
        this.winTotal = winTotal;
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


            int lineLevelY = (int) (canvasHeight * 0.6);
            int w = (int) (canvasWidth * 0.2);
            double winPercent = (double) winTotal / total * 100;
            double losePercent = 100 - winPercent;
            int winHeight = (int) ((double) winTotal / total * lineLevelY);
            int loseHeight = (int) ((double) (total - winTotal) / total * lineLevelY);

            Helper.setColor(g2d, Helper.Grey);
            g2d.drawLine(0, lineLevelY, canvasWidth, lineLevelY);

            Helper.setColor(g2d, Helper.Green);
            Helper.fillRectangle(g2d, canvasWidth / 2 - w - 20, lineLevelY - winHeight, w, winHeight);
            Helper.drawText(g2d, Double.toString(winPercent) + "%", canvasWidth / 2 - w / 2 - 20, lineLevelY - winHeight - 20);
            Helper.drawText(g2d, "不换门", canvasWidth / 2 - w / 2 - 20, lineLevelY + 50);

            Helper.setColor(g2d, Helper.Red);
            Helper.fillRectangle(g2d, canvasWidth / 2 + 20, lineLevelY - loseHeight, w, loseHeight);
            Helper.drawText(g2d, Double.toString(losePercent) + "%", canvasWidth / 2 + 20 + w / 2, lineLevelY - loseHeight - 20);
            Helper.drawText(g2d, "换门", canvasWidth / 2 + 20 + w / 2, lineLevelY + 50);

        }

    }


}
