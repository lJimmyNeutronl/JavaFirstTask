package org.example.task6;

import java.util.List;
import java.util.Random;

public class DeleteNumberSynThread extends Thread{
    private List<Integer> list;

    public DeleteNumberSynThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run()
    {
        Random random = new Random();
        int i = 0;
        while(i < 10000){
            if(!list.isEmpty())
            {
                System.out.println("Delete " + i);
                list.remove(random.nextInt(list.size()));
                i++;
            }
        }
    }

}
