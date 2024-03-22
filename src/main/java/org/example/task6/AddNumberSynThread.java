package org.example.task6;

import java.util.List;
import java.util.Random;

public class AddNumberSynThread extends Thread{
    private List<Integer> list;

    public AddNumberSynThread(List<Integer> list)
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
            System.out.println("Add " + i);
            list.add(random.nextInt());
            i++;
        }
    }
}
