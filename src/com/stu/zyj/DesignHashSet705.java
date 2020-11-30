package com.stu.zyj;

public class DesignHashSet705 {
    /** Initialize your data structure here. */
    private boolean[] arr;

    public DesignHashSet705() {
        this.arr = new boolean[1000001];
    }

    public void add(int key) {
        arr[key] = true;
    }

    public void remove(int key) {
        arr[key] = false;
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        return arr[key];
    }
}
