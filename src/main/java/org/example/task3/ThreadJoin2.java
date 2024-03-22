package org.example.task3;

public class ThreadJoin2 extends Thread{

    @Override
    public void run()
    {
        try{
            System.out.println("Второй поток запустился");
            Thread.sleep(1000);
        }
        catch(InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}