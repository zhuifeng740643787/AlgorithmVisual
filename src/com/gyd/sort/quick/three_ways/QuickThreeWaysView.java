package com.gyd.sort.quick.three_ways;

import com.gyd.lib.AlgorithmFrame;
import com.gyd.lib.Helper;
import com.gyd.lib.Panel;
import com.gyd.sort.quick.QuickData;

import java.awt.*;

/**
 * Created by gongyidong on 2017/11/21.
 */
public class QuickThreeWaysView extends AlgorithmFrame {


    public QuickThreeWaysView(String title, int canvasWidth, int canvasHeight) {
        super(title, canvasWidth, canvasHeight);
    }

    @Override
    protected void setCanvas() {
        canvas = new Canvas();
    }

    private QuickData data;

    // 渲染
    public void render(QuickData data) {
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

            if (data == null) {
                return;
            }
            Graphics2D g2d = (Graphics2D) g;
            // 抗锯齿
            RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.addRenderingHints(hints);

            int w = Math.max((int) (canvasWidth / data.N()), 2);

            for (int i = 0; i < data.N(); i++) {
                Helper.setColor(g2d, Helper.Grey);
                if (i >= data.l && i <= data.r) {
                    Helper.setColor(g2d, Helper.LightGreen);
                }
                if ((i > data.l && i <= data.lt) || (i>=data.gt && i<=data.r)) {
                    Helper.setColor(g2d, Helper.Cyan);
                }
                if (i == data.currentPovit) {
                    Helper.setColor(g2d, Helper.Yellow);
                }
                if (data.fixedPovits[i]) {
                    Helper.setColor(g2d, Helper.Red);
                }

                Helper.fillRectangle(g2d, i * w + 1,canvasHeight - data.get(i), w-1, data.get(i));
            }

        }

    }


}

