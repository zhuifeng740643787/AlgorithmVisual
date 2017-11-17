package com.gyd.random_simulate.monte_carlo;

import java.awt.*;

/**
 * Created by gongyidong on 2017/11/17.
 */
public class Circle {

    private int r;
    private int x;
    private int y;

    public Circle(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getR() { return r; }

    public boolean contain(Point point) {
        return (x - point.x) * (x - point.x) + (y - point.y) * (y - point.y) <= r * r;
    }

}
