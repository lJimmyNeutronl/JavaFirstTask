package org.example.task1;

public class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread());
    }
}
