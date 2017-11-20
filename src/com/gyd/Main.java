package com.gyd;

import com.gyd.lib.Helper;
import com.gyd.random_simulate.monte_carlo.PIController;
import com.gyd.random_simulate.share_money.MoneyController;
import com.gyd.random_simulate.three_door.WinController;
import com.gyd.sort.bubble.BubbleController;
import com.gyd.sort.insertion.InsertionController;
import com.gyd.sort.merge.MergeController;
import com.gyd.sort.selection.SelectionController;
import com.gyd.sort.selection.SelectionView;
import com.gyd.sort.shell.ShellController;

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
        MergeController controller = new MergeController(screenWidth, screenHeight, 10);
//        controller.run();

        test();
    }

    private static void test() {
        int n = 10;
        int[] data = new int[n];
        int[] d1 = new int[n];
        for (int k = 0; k < n; k++) {
            data[k] = (int) (Math.random() * n) + 1;
            d1[k] = data[k];
        }
        Helper.printArray(data, n);
        Arrays.sort(d1);
        Helper.printArray(d1, n);

        _merge(data, 0, n - 1);


        Helper.printArray(data, n);
    }


    // 对[l...r]进行归并排序
    private static void _merge(int[] data, int l, int r) {
        if (l >= r) {
            return;
        }
        int midIndex = (r - l) / 2 + l;
        // 左右两边进行归并
        _merge(data, l, midIndex);//[l...midIndex]
        _merge(data, midIndex + 1, r);//[midIndex+1...r]
        // 进行合并
        if (data[midIndex + 1] < data[midIndex]) {
            _mergeSort(data, l, midIndex, r);
        }
    }

    private static void _mergeSort(int[] data, int l, int midIndex, int r) {
        int i = l, j = midIndex + 1;

        // 复制[l...r]的数组
        int[] arr = new int[r - l + 1];
        for (int k = l; k <= r; k++) {
            arr[k-l] = data[k];
        }

        for (int k = l; k <= r; k++) {
            if (i > midIndex) {
                data[k] = arr[j-l];
                j++;
                continue;
            }
            if (j > r) {
                data[k] = arr[i-l];
                i++;
                continue;

            }
            if (arr[i-l] > arr[j-l]) {
                data[k] = arr[j-l];
                j++;
            } else {
                data[k] = arr[i-l];
                i++;
            }
        }


    }


}
