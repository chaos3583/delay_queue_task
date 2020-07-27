package com.chaos.delay_queue_task.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


/**
 * @program: delay_queue_task
 * * @description:
 * * @author: liaopeng
 * * @create: 2020-07-27 11:17
 **/
@Component
public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger log = LogManager.getLogger(MyApplicationListener.class);


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        addDelayQueue();
    }

    public void addDelayQueue(){
        log.info("项目启动初始化队列");
        
    }
}
