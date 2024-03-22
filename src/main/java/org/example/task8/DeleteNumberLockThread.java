package org.example.task8;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DeleteNumberLockThread extends Thread {
    private List<Integer> list;

    private ReentrantLock locker;

    private Condition condition;

    public DeleteNumberLockThread(List<Integer> list, ReentrantLock locker, Condition condition) {
        this.list = list;
        this.locker = locker;
        this.condition = condition;
    }

    @Override
    public void run() {
        Random random = new Random();

        locker.lock();
        try {
            if (list.isEmpty())
                condition.await();
            for (int i = 0; i < 10000; i++) {
                if (!list.isEmpty()) {
                    list.remove(random.nextInt(list.size()));
                    System.out.println("Delete " + i);
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            locker.unlock();
        }
    }

}
