package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.example.task1.MyThread;
import org.example.task10.MyConcurrentHashMap;
import org.example.task11.FormatDateThread;
import org.example.task11.Formatter;
import org.example.task2.JoinThread;
import org.example.task3.ThreadJoin1;
import org.example.task3.ThreadJoin2;
import org.example.task3.ThreadJoin3;
import org.example.task4.AddNumberThread;
import org.example.task4.DeleteNumberThread;
import org.example.task5.AddDeleteNumberThread;
import org.example.task6.AddNumberSynThread;
import org.example.task6.DeleteNumberSynThread;
import org.example.task7.PingPong;
import org.example.task8.AddNumberLockThread;
import org.example.task8.DeleteNumberLockThread;
import org.example.task9.PingPongBlock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest
{
    @Test
    public void task1()
    {
        MyThread myThread = new MyThread();
        myThread.start();//вывод свойств потока
    }
    @Test
    public void task2() throws InterruptedException
    {
        var joinThread = new JoinThread();
        joinThread.start();
        joinThread.join();
        System.out.println("Поток завершился");
        assertFalse(joinThread.isAlive());
    }
    @Test
    public void task3() throws InterruptedException
    {
        var threadJoin1 = new ThreadJoin1();
        var threadJoin2 = new ThreadJoin2();
        var threadJoin3 = new ThreadJoin3();

        threadJoin1.start();
        threadJoin2.start();
        threadJoin3.start();

        threadJoin1.join();
        threadJoin2.join();
        threadJoin3.join();

        assertFalse(threadJoin1.isAlive());
        assertFalse(threadJoin2.isAlive());
        assertFalse(threadJoin3.isAlive());
    }
    @Test
    public void task4() throws InterruptedException
    {
        var list = new ArrayList<Integer>();
        var addNumber = new AddNumberThread(list);
        var deleteNumber = new DeleteNumberThread(list);

        deleteNumber.start();
        addNumber.start();

        deleteNumber.join();
        addNumber.join();

        assertTrue(list.isEmpty());
    }
    @Test
    public void task5() throws InterruptedException
    {
        var list = new ArrayList<Integer>();

        var addDeleteNumber = new AddDeleteNumberThread(list);

        var addNumber = new Thread(addDeleteNumber);
        var deleteNumber = new Thread(addDeleteNumber);

        deleteNumber.start();
        addNumber.start();
        addNumber.join();
        deleteNumber.join();

        assertTrue(list.isEmpty());

    }
    @Test
    public void task6() throws InterruptedException
    {
        var synList = Collections.synchronizedList(new ArrayList<Integer>());

        var addNumber = new AddNumberSynThread(synList);
        var deleteNumber = new DeleteNumberSynThread(synList);

        addNumber.start();
        deleteNumber.start();

        addNumber.join();
        deleteNumber.join();

        System.out.println(synList.size());
        assertTrue(synList.isEmpty());

    }
    @Test
    public void task7()
    {
        var pingPong = new PingPong();
        Runnable ping = () ->{
            try{
                pingPong.ping();
            }catch(InterruptedException e){
                throw new RuntimeException(e);
            }
        };
        Runnable pong = () ->{
            try{
                pingPong.pong();
            }catch(InterruptedException e){
                throw new RuntimeException(e);
            }
        };
        var pingThread = new Thread(ping);
        var pongThread = new Thread(pong);

        pongThread.start();
        pingThread.start();
    }
    @Test
    public void task8() throws InterruptedException
    {
        var list = new ArrayList<Integer>();
        ReentrantLock locker = new ReentrantLock();
        Condition condition = locker.newCondition();

        var addNumberLock = new AddNumberLockThread(list, locker,condition);
        var deleteNumberLock = new DeleteNumberLockThread(list, locker, condition);

        addNumberLock.start();
        deleteNumberLock.start();

        addNumberLock.join();
        deleteNumberLock.join();

        System.out.println(list.size());

        assertTrue(list.isEmpty());
    }
    @Test
    public void task9()
    {
        var pingPong = new PingPongBlock();
        Runnable ping = () ->{
            try{
                pingPong.ping();
            }catch(InterruptedException e){
                throw new RuntimeException(e);
            }
        };
        Runnable pong = () ->{
            try{
                pingPong.pong();
            }catch(InterruptedException e){
                throw new RuntimeException(e);
            }
        };
        var pingThread = new Thread(ping);
        var pongThread = new Thread(pong);

        pingThread.start();
        pongThread.start();


    }
    @Test
    public void task10() throws InterruptedException
    {
        MyConcurrentHashMap<String, Integer> map = new MyConcurrentHashMap<>();

        var lock = new ReentrantLock();

        var writerThread = new Thread(() ->{
           for(int i = 0; i < 10; i++)
           {
               map.put("key " + i, i );
               System.out.println("Map size " + map.size());
           }
        });

        var readerThread = new Thread(() ->{
            for(int i = 0; i < 10; i++)
            {
                System.out.println(map.get("key " + i));
            }
        });

        var deleterThread = new Thread(() ->{
            for(int i = 0; i < 10; i++)
            {
                map.remove("key " + i);
                System.out.println("Delete " + i);
            }
        });
        System.out.println("writer thread: ");

        writerThread.start();
        writerThread.join();


        assertEquals(10, map.size());
        System.out.println("reader thread:");

        readerThread.start();
        readerThread.join();

        System.out.println("deleter thread:");
        deleterThread.start();
        deleterThread.join();

        assertEquals(0, map.size());

    }

    @Test
    public void task11() throws InterruptedException
    {
        Formatter formatter = new Formatter();
        Date date = new Date();

        Thread[] threads = new Thread[5];

        for (int i = 0; i < 5; i++) {
            threads[i] = new FormatDateThread(formatter, date);
            threads[i].start();
        }

        for (int i = 0; i < 5; i++) {
            threads[i].join();
        }
    }
}
