package com.gyd.lib;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Set;

/**
 * Created by gongyidong on 2017/11/16.
 */
public class Helper {


    private Helper() {
    }

    public static final Color Red = new Color(0xF44336);
    public static final Color Pink = new Color(0xE91E63);
    public static final Color Purple = new Color(0x9C27B0);
    public static final Color DeepPurple = new Color(0x673AB7);
    public static final Color Indigo = new Color(0x3F51B5);
    public static final Color Blue = new Color(0x2196F3);
    public static final Color LightBlue = new Color(0x03A9F4);
    public static final Color Cyan = new Color(0x00BCD4);
    public static final Color Teal = new Color(0x009688);
    public static final Color Green = new Color(0x4CAF50);
    public static final Color LightGreen = new Color(0x8BC34A);
    public static final Color Lime = new Color(0xCDDC39);
    public static final Color Yellow = new Color(0xFFEB3B);
    public static final Color Amber = new Color(0xFFC107);
    public static final Color Orange = new Color(0xFF9800);
    public static final Color DeepOrange = new Color(0xFF5722);
    public static final Color Brown = new Color(0x795548);
    public static final Color Grey = new Color(0x9E9E9E);
    public static final Color BlueGrey = new Color(0x607D8B);
    public static final Color Black = new Color(0x0000000);
    public static final Color White = new Color(0xFFFFFF);

    /**
     * 设置颜色
     *
     * @param g2d
     * @param color
     */
    public static void setColor(Graphics2D g2d, Color color) {
        g2d.setColor(color);
    }

    /**
     * 设置线条宽度
     *
     * @param g2d
     * @param width 线条宽度
     */
    public static void setStrokeWidth(Graphics2D g2d, int width) {
        g2d.setStroke(new BasicStroke(width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }

    /**
     * 绘制空心圆
     *
     * @param g2d
     * @param x   圆心坐标
     * @param y   圆心坐标
     * @param r   圆的半径
     */
    public static void strokeCircle(Graphics2D g2d, int x, int y, int r) {
        Ellipse2D circle = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        g2d.draw(circle);
    }

    /**
     * 绘制实心圆
     *
     * @param g2d
     * @param x   圆心坐标
     * @param y   圆心坐标
     * @param r   圆的半径
     */
    public static void fillCircle(Graphics2D g2d, int x, int y, int r) {
        Ellipse2D circle = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        g2d.fill(circle);
    }


    public static void strokeRectangle(Graphics2D g2d, int x, int y, int w, int h) {
        Rectangle2D rectangle2D = new Rectangle2D.Double(x, y, w, h);
        g2d.draw(rectangle2D);
    }

    public static void fillRectangle(Graphics2D g2d, int x, int y, int w, int h) {
        Rectangle2D rectangle2D = new Rectangle2D.Double(x, y, w, h);
        g2d.fill(rectangle2D);
    }

    public static void pubImage(Graphics2D g2d, int x, int y, String imageURL) {
        ImageIcon icon = new ImageIcon(imageURL);
        Image image = icon.getImage();
        g2d.drawImage(image, x, y, null);
    }

    public static void drawText(Graphics2D g2d, String text, int centerX, int centerY) {

        if (text == null) {
            throw new IllegalArgumentException("字符串不能为NULL");
        }

        FontMetrics metrics = g2d.getFontMetrics();
        int w = metrics.stringWidth(text);
        int h = metrics.getDescent();
        g2d.drawString(text, centerX - w / 2, centerY + h);
    }

    /**
     * 暂停毫秒数
     *
     * @param t 毫秒
     */
    public static void pause(int t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            System.out.println("Error in sleeping");
        }
    }


    public static void printArray(int arr[], int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + (i == n - 1 ? "\n" : " "));
        }
    }


    public static Color[] randomColors(int n) {
        if (n <= 0) {
            return null;
        }
        Color[] colors = new Color[n];
        for (int i = 0; i < n; i++) {
            colors[i] = new Color((int)(Math.random() * 0x1000000));
        }
        return colors;
    }

}
