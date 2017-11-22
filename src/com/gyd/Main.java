package com.gyd;

import com.gyd.lib.Helper;
import com.gyd.sort.heap.HeapController;
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
//        QuickThreeWaysController controller = new QuickThreeWaysController(screenWidth, screenHeight, 100);
        HeapController controller = new HeapController(screenWidth, screenHeight, 100);
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

        heapify(data, n);
        for (int i = n - 1; i > 0; i--) {
            swap(data, 0, i);
            shitDown(data, i, 0);
        }

        Helper.printArray(data, n);
    }

    private static void heapify(int[] data, int n) {
        for (int i = (n - 1 - 1) / 2; i >= 0; i--) {
            shitDown(data, n, i);
        }
    }

    private static void shitDown(int[] data, int n, int k) {
        while (2 * k + 1 < n) {
            int j = 2 * k + 1;
            if (j + 1 < n && data[j] < data[j + 1]) {
                j++;
            }
            if (data[k] >= data[j]) {
                break;
            }
            swap(data, k, j);
            k = j;
        }
    }


    private static void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

}
