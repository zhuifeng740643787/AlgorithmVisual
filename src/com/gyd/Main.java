package com.gyd;

import com.gyd.lib.Helper;
import com.gyd.sort.quick.normal.QuickController;
import com.gyd.sort.quick.three_ways.QuickThreeWaysController;
import com.gyd.sort.quick.two_ways.QuickTwoWaysController;

import javax.swing.*;
import java.util.Arrays;

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

//        SelectionController controller = new SelectionController(screenWidth, screenHeight, 100);
//        MergeController controller = new MergeController(screenWidth, screenHeight, 100);
//        BubbleController controller = new BubbleController(screenWidth, screenHeight, 100);
//        ShellController controller = new ShellController(screenWidth, screenHeight, 100);
//        MergeController controller = new MergeController(screenWidth, screenHeight, 100);
//        QuickThreeWaysController controller = new QuickThreeWaysController(screenWidth, screenHeight, 100);
//        QuickTwoWaysController controller = new QuickTwoWaysController(screenWidth, screenHeight, 10);
        QuickThreeWaysController controller = new QuickThreeWaysController(screenWidth, screenHeight, 100);
        controller.run();
//        test();
    }

    private static void test() {
        int n = 100;
        int[] data = new int[n];
        int[] d1 = new int[n];
        for (int k = 0; k < n; k++) {
            data[k] = (int) (Math.random() * n) + 1;
            d1[k] = data[k];
        }
        Helper.printArray(d1, n);
        Arrays.sort(d1);
        Helper.printArray(d1, n);

        sort(data, 0, n - 1);


        Helper.printArray(data, n);
    }


    // 对[l...r]进行归并排序
    private static void sort(int[] data, int l, int r) {
        if (l >= r) {
            return;
        }
        int v = data[l];
        // [l+1...lt] < v, [lt+1...i) == v, [gt...r] > v
        int i = l + 1, lt = l, gt = r + 1;
        for (; i < gt; i++) {
            if (data[i] > v) {
                swap(data, --gt, i--);
            } else if (data[i] < v) {
                swap(data, ++lt, i);
            }
        }
        // 将v放入合适的位置
        swap(data, l, lt--);
        sort(data, l, lt);
        sort(data, gt, r);
    }

    private static void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

}
