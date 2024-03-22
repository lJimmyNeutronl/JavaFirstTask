package org.example.task7;

public class PingPong
{
    private boolean turn = true;
    public synchronized void ping() throws InterruptedException
    {
        while (true){
            if(turn)
            {
                System.out.println("ping");
                turn = false;
                notify();
            }
            else
            {
                wait();
            }
        }
    }
    public synchronized void pong() throws InterruptedException
    {
        while(true){
            if(!turn)
            {
                System.out.println("pong");
                turn = true;
                notify();
            }
            else
            {
                wait();
            }
        }
    }
}
