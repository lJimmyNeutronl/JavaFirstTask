package org.example.task9;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PingPongBlock {
    private ReentrantLock locker;
    private Condition condition;

    private boolean turn = true;

    public PingPongBlock() {
        locker = new ReentrantLock();
        condition = locker.newCondition();
    }

    public void ping() throws InterruptedException {
        locker.lock();
        try {
            while (true) {
                if (turn)
                {
                    System.out.println("ping");
                    turn = false;
                    condition.signal();
                }
                else {
                    condition.await();
                }
            }
        } finally {
            locker.unlock();
        }
    }

    public void pong() throws InterruptedException {
        locker.lock();
        try {
            while (true) {
                if (!turn)
                {
                    System.out.println("pong");
                    turn = true;
                    condition.signal();
                }
                else {
                    condition.await();
                }
            }
        } finally {
            locker.unlock();
        }

    }
}
