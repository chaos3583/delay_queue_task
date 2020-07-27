package com.chaos.delay_queue_task.listener;

import com.chaos.delay_queue_task.model.MyDelayEntity;
import com.chaos.delay_queue_task.service.DelayQueueManage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * @program: delay_queue_task
 * * @description:项目启动监听，初始化队列（将需要执行的放入延时队列）
 * * @author: liaopeng
 * * @create: 2020-07-27 11:17
 **/
@Component
public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger log = LogManager.getLogger(MyApplicationListener.class);

    @Autowired
    private DelayQueueManage delayQueueManage;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        addDelayQueue();
    }

    public void addDelayQueue(){
        log.info("项目启动初始化队列,将需要放入队列的添加到队列");
        MyDelayEntity entity = new MyDelayEntity(0L,new Date().getTime());
        delayQueueManage.add(entity);
    }
}
