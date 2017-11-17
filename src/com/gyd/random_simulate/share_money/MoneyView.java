package com.gyd.random_simulate.share_money;

import com.gyd.lib.Helper;
import com.gyd.lib.Panel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * Created by gongyidong on 2017/11/16.
 */
public class MoneyView extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    public MoneyView(String title, int canvasWidth, int canvasHeight) {
        super(title);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        _setFrame();
    }

    public MoneyView(String title) {
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

    private int[] data;

    // 渲染
    public void render(int[] data) {
        this.data = data;
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

            int w = Math.max(1, (int) (canvasWidth / data.length));// 每个小矩形的宽度
            Arrays.sort(data);
            for (int i = 0; i < data.length; i++) {
                if (data[i] >= 0) {
                    g2d.setColor(Helper.Green);
//                    Helper.drawText(g2d, Integer.toString(data[i]), i * (w + 1) + w / 2, canvasHeight / 2 - data[i] - 10);
                    Helper.fillRectangle(g2d, i * (w + 1) + 1, canvasHeight / 2 - data[i], w, data[i]);
                } else {
                    g2d.setColor(Helper.Red);
//                    Helper.drawText(g2d, Integer.toString(data[i]), i * (w + 1) + w / 2, canvasHeight / 2 - data[i] + 10);
                    Helper.fillRectangle(g2d, i * (w + 1) + 1, canvasHeight / 2, w, -data[i]);
                }

            }

        }

    }


}
