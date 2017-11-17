package com.gyd;

import com.gyd.circle_move.Circle;
import com.gyd.circle_move.CircleController;
import com.gyd.circle_move.CircleMoveView;
import com.gyd.lib.Helper;

import javax.swing.*;
import java.awt.*;

/**
 * Created by gongyidong on 2017/11/16.
 */
public class Main extends JFrame {


    public static void main(String[] args) {
        int screenWidth = 600;
        int screenHeight = 400;

        int N = (int) (Math.random() * 5) + 5;// 定义圆圈的个数

        CircleController controller = new CircleController(screenWidth, screenHeight, N);
        controller.run();
    }

}
