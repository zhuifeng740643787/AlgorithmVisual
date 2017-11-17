package com.gyd.lib;

import javax.swing.*;
import java.awt.*;

/**
 * Created by gongyidong on 2017/11/17.
 */
public abstract class AlgorithmFrame extends JFrame {

    protected int canvasWidth;
    protected int canvasHeight;
    protected Panel canvas;

    // 设置canvas
    protected abstract void setCanvas();

    public AlgorithmFrame(String title, int canvasWidth, int canvasHeight) {
        super(title);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        setFrame();
    }

    public AlgorithmFrame(String title) {
        super(title);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.canvasWidth = (int) screenSize.getWidth();
        this.canvasHeight = (int) screenSize.getHeight();
        setFrame();
    }


    protected void setFrame() {
        setCanvas();
        // 设置内容面板
        setContentPane(canvas);
        // frame根据面板内容进行自动布局整理
        pack();

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }
}
