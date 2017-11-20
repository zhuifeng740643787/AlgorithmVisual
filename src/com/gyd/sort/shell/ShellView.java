package com.gyd.sort.shell;

import com.gyd.lib.AlgorithmFrame;
import com.gyd.lib.Helper;
import com.gyd.lib.Panel;
import com.gyd.sort.insertion.InsertionData;

import java.awt.*;

/**
 * Created by gongyidong on 2017/11/16.
 */
public class ShellView extends AlgorithmFrame {


    public ShellView(String title, int canvasWidth, int canvasHeight) {
        super(title, canvasWidth, canvasHeight);
    }

    @Override
    protected void setCanvas() {
        canvas = new Canvas();
    }

    private ShellData data;

    // 渲染
    public void render(ShellData data) {
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

            int w = Math.max((int) (canvasWidth / data.N()), 2);

            int total = data.distance;
            Color[] colors = Helper.randomColors(total);

            for (int i = 0; i < data.N(); i++) {
                Helper.setColor(g2d, Helper.Grey);

                if (data.distance <= 0) {
                    Helper.setColor(g2d, Helper.Green);
                } else {
                    // 设置分组颜色
                    Helper.setColor(g2d, colors[i % data.distance]);
                    if (i == data.currentIndex) {
                        Helper.setColor(g2d, Helper.Black);
                    } else if (i == data.insertIndex) {
                        Helper.setColor(g2d, Helper.White);
                    }
                }
                Helper.fillRectangle(g2d, i * w + 1, canvasHeight - data.get(i), w - 1, data.get(i));
            }

        }

    }


}
