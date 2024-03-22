package org.example.task5;

import java.util.List;
import java.util.Random;

public class AddDeleteNumberThread extends Thread {

    private List<Integer> list;

    public AddDeleteNumberThread(List<Integer> list) {
        this.list = list;
    }

    public synchronized void execute() {
        Random random = new Random();

        if (!list.isEmpty()) {
            System.out.println("Delete");
            list.remove(random.nextInt(list.size()));
        } else {
            System.out.println("Add");
            list.add(random.nextInt());
        }

    }

    @Override
    public void run() {

        for (int i = 0; i < 10000; i++)
            execute();
    }

}
