package com.chaos.delay_queue_task.service;

import com.chaos.delay_queue_task.model.MyDelayEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Executors;

/**
 * @program: delay_queue_task
 * * @description:
 * * @author: liaopeng
 * * @create: 2020-07-27 10:31
 **/
@Component
public class DelayQueueManage implements CommandLineRunner {

    private static final Logger logger = LogManager.getLogger(DelayQueueManage.class);

    private DelayQueue<MyDelayEntity> startQueue = new DelayQueue<>();
    

    @Override
    public void run(String... args) throws Exception {
        Executors.newSingleThreadExecutor().execute(new Thread(this::executeThread));
    }

    /**
     * 延时任务执行线程
     */
    public void executeThread(){
        while (true){
            try {
                MyDelayEntity task = startQueue.take();
                processTask(task);
            }catch (Exception e){

            }
        }
    }

    /**
     * 执行业务
     * @param task
     */
    public void processTask(MyDelayEntity task){
        logger.info("当前执行的是："+task.getId());
    }


    public void add(MyDelayEntity task) {
        logger.info("StartOrder" + task);
        startQueue.put(task);
    }

    public boolean remove(MyDelayEntity task) {
        return startQueue.remove(task);
    }

    public void remove(long id) {
        MyDelayEntity[] array = startQueue.toArray(new MyDelayEntity[]{});
        if (array == null || array.length <= 0) {
            return;
        }
        MyDelayEntity target = null;
        for (MyDelayEntity task : array) {
            if (task.getId().equals(id)) {
                target = task;
                break;
            }
        }
        if (target != null) {
            startQueue.remove(target);
        }
    }

    public MyDelayEntity get(long id) {
        MyDelayEntity[] array = startQueue.toArray(new MyDelayEntity[]{});
        if (array == null || array.length <= 0) {
            return null;
        }
        MyDelayEntity target = null;
        for (MyDelayEntity task : array) {
            if (task.getId().equals(id)) {
                target = task;
                break;
            }
        }
        return target;
    }
}
