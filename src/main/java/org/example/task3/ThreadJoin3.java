package org.example.task3;

public class ThreadJoin3 extends Thread{

    @Override
    public void run()
    {
        try{
            System.out.println("Третий поток запустился");
            Thread.sleep(1000);
        }
        catch(InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}