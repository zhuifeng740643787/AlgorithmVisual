package com.gyd;

import com.gyd.random_simulate.monte_carlo.PIController;
import com.gyd.random_simulate.share_money.MoneyController;
import com.gyd.random_simulate.three_door.WinController;
import com.gyd.sort.selection.SelectionController;
import com.gyd.sort.selection.SelectionView;

import javax.swing.*;

/**
 * Created by gongyidong on 2017/11/16.
 */
public class Main extends JFrame {


    public static void main(String[] args) {
        int screenWidth = 800;
        int screenHeight = 800;

//        CircleController controller = new CircleController(screenWidth, screenHeight, (int) (Math.random() * 5) + 5);
//        MoneyController controller = new MoneyController(screenWidth, screenHeight, 200);
//        PIController controller = new PIController(screenWidth, screenHeight);
//        WinController controller = new WinController(screenWidth, screenHeight);

        SelectionController controller = new SelectionController(screenWidth, screenHeight, 100);

        controller.run();
//        test();
    }

    private static void test() {
        int n = 10;
        for (int i = 0; i < n; i++) {
            System.out.println((int) (Math.random() * n));
        }
    }

}
