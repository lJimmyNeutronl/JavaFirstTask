package org.example.task8;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AddNumberLockThread extends Thread {
    private List<Integer> list;
    private ReentrantLock locker;
    private Condition condition;

    public AddNumberLockThread(List<Integer> list, ReentrantLock locker, Condition condition) {
        this.list = list;
        this.locker = locker;
        this.condition = condition;
    }

    @Override
    public void run() {
        Random random = new Random();
        locker.lock();
        try {
            condition.signal();
            for (int i = 0; i < 10000; i++) {
                list.add(random.nextInt());
                System.out.println("Add " + i);
            }
        } finally {
            locker.unlock();
        }
    }
}
