package com.gyd.lib;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by gongyidong on 2017/11/21.
 */
public abstract class AlgorithmController {
    protected int screenWidth;
    protected int screenHeight;
    protected int N;
    protected SortData data;
    protected AlgorithmFrame frame;
    protected static int DELAY = 24;
    protected AlgorithmThread thread;

    private AlgorithmController() {
    }

    public AlgorithmController(int screenWidth, int screenHeight, int N) {
        if (N <= 0) {
            return;
        }
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.N = N;

        // 设置data
        initData();
    }

    // 初始化数据
    public abstract void initData();

    // run方法
    public abstract void run();

    /**
     * 键盘事件监听类
     */
    public class KeyboardLister extends KeyAdapter {
        /**
         * Invoked when a key has been released.
         */
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyChar() == ' ') {
                if (thread.isSuspended()) {
                    System.out.println("继续。。。");
                    thread.resume();
                } else {
                    System.out.println("暂停。。。");
                    thread.suspend();
                }
            }
        }
    }

}
