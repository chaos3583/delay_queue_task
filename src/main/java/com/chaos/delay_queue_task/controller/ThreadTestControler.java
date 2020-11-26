package com.chaos.delay_queue_task.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: delay_queue_task
 * * @description:
 * * @author: liaopeng
 * * @create: 2020-07-28 14:56
 **/
class ThreadTestControler {
    private static Logger log = LogManager.getLogger(ThreadTestControler.class);

    public static Lock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        /*ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        log.info("歇够了没");
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                log.info("歇够了，上班");
            }
        }, 30, TimeUnit.SECONDS);*/
        Lock1 lock1 = new Lock1();
        methodA(lock1);
    }

    public static void methodA(Lock1 lock1) throws InterruptedException {
        lock1.lock();
        log.info("开始执行方法A");
        methodB(lock1);

        lock1.unlock();
        log.info("执行方法A");
    }

    public static void methodB(Lock1 lock1) throws InterruptedException {
        log.info("开始执行方法B");
        lock1.lock();
        log.info("执行方法B");
        lock1.unlock();
    }

}

class Lock1{
    private boolean isLocked = false;
    public synchronized void lock() throws InterruptedException{
        while(isLocked){
            wait();
        }
        isLocked = true;
    }
    public synchronized void unlock(){
        isLocked = false;
        notify();
    }
}