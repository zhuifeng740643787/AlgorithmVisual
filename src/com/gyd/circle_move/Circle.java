package com.gyd.circle_move;

import com.gyd.lib.Observer;

import java.awt.*;

/**
 * Created by gongyidong on 2017/11/16.
 */
public class Circle extends Observer<Circle> {

    public int x, y;//圆圈坐标
    private int r;//圆圈半径
    public int vx, vy;//圆圈移动速度
    public boolean isFilled = false;// 是否实心圆
    private boolean master; // 是否为主圆圈

    public Circle(int x, int y, int r, int vx, int vy, boolean master) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.vx = vx == 0 ? 5 : vx;
        this.vy = vy == 0 ? 5 : vy;
        this.master = master;
    }

    public Circle(int maxWidth, int maxHeight, boolean isMaster) {
        this.r = (int) (Math.random() * 20) + 40;
        this.x = (int) (Math.random() * (maxWidth - 2 * r)) + r;
        this.y = (int) (Math.random() * (maxHeight - 2 * r)) + r;
        this.vx = (int) (Math.random() * 10) - 5;
        this.vy = (int) (Math.random() * 10) - 5;
        this.master = isMaster;
    }

    public boolean isMaster() {
        return master;
    }

    public int getR() {
        return r;
    }

    // 移动
    public void move(int minX, int minY, int maxX, int maxY) {
        x += vx;
        y += vy;
        checkWallCollision(minX, minY, maxX, maxY);
        checkCircleCollision();
    }


    // 碰撞检测 - 墙面
    private void checkWallCollision(int minX, int minY, int maxX, int maxY) {
        if (x - r < minX) {
            x = r;
            vx = -vx;
        }
        if (x + r >= maxX) {
            x = maxX - r;
            vx = -vx;
        }
        if (y - r < minY) {
            y = r;
            vy = -vy;
        }
        if (y + r >= maxY) {
            y = maxY - r;
            vy = -vy;
        }
    }

    // 碰撞检测 - 墙面
    private void checkCircleCollision() {
        for (Circle circle : getObservers()) {
            if (((x - circle.x) * (x - circle.x) + (y - circle.y) * (y - circle.y)) <= (r + circle.getR()) * (r + circle.getR())) {
                x -= vx;
                y -= vy;
                vx = -vx;
                vy = -vy;
                circle.x -= circle.vx;
                circle.y -= circle.vy;
                circle.vx = -circle.vx;
                circle.vy = -circle.vy;
            }


        }
    }

    public boolean contain(Point point) {
        return (x - point.x) * (x - point.x) + (y - point.y) * (y - point.y) <= r * r;
    }


}
