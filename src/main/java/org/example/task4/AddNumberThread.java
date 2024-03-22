package org.example.task4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AddNumberThread extends Thread
{
    private List<Integer> list;

    public AddNumberThread(List<Integer> list)
    {
        this.list = list;
    }

    @Override
    public void run()
    {
        Random random = new Random();
        int i = 0;
        while(i < 10000)
        {
            synchronized (list) {
                System.out.println("Add " + i);
                list.add(random.nextInt());
                list.notify();
                i++;
            }
        }
    }
}
