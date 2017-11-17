package com.gyd.lib;

import java.util.ArrayList;

/**
 * Created by gongyidong on 2017/11/17.
 */

public class Observer<T> {

    private ArrayList<T> observers = new ArrayList<T>();

    public void addObserver(T observer) {
        if (observers.contains(observer)) {
            return;
        }
        observers.add(observer);
    }


    public void removeObserver(T observer) {
        if (!observers.contains(observer)) {
            return;
        }
        observers.remove(observer);
    }

    public ArrayList<T> getObservers() {
        return observers;
    }

}
