package org.example.task2;

public class JoinThread extends Thread{

    @Override
    public void run()
    {
        System.out.println("Поток запустился");
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e) {
         throw new RuntimeException(e);
        }
    }
}
