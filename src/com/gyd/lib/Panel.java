package com.gyd.lib;

import javax.swing.*;
import java.awt.*;

/**
 * Created by gongyidong on 2017/11/17.
 */
public class Panel extends JPanel{

    private int canvasWidth;
    private int canvasHeight;

    public Panel(int canvasWidth, int canvasHeight) {
        super(true);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
    }


    // 设置面板尺寸
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(canvasWidth, canvasHeight);
    }
}
