package com.gyd.lib;

/**
 * Created by gongyidong on 2017/11/21.
 */
public abstract class AlgorithmThread implements Runnable {

    private String threadName;
    public Thread thread;
    boolean suspended = false;
    public int sleep = 24;

    public AlgorithmThread(String threadName, int sleep) {
        this.threadName = threadName;
        this.sleep = sleep;
        System.out.println("Creating threadName = [" + threadName + "]");
    }

    @Override
    public void run() {
        Helper.pause(sleep);
        synchronized (this) {
            handle();

        }
        System.out.println("Thread " + threadName + " exiting.");
    }

    public abstract void handle();

    public void start() {
        if (thread == null) {
            thread = new Thread(this, threadName);
            thread.start();
        }
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void suspend() {
        suspended = true;
    }

    public void pause() {
        try {
            wait();
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " suspended.");
        }
    }

    public synchronized void resume() {
        suspended = false;
        notify();
    }

}
