package org.example.task4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeleteNumberThread extends Thread {
    private List<Integer> list;

    public DeleteNumberThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        Random random = new Random();
        int i = 0;
        while (i < 10000) {
            synchronized (list) {
                if (!list.isEmpty()) {
                    System.out.println("Delete " + i);
                    list.remove(random.nextInt(list.size()));
                    i++;
                } else {
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}