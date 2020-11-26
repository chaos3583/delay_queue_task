package com.chaos.delay_queue_task.controller;

import com.chaos.delay_queue_task.model.MyDelayEntity;
import com.chaos.delay_queue_task.service.DelayQueueManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: delay_queue_task
 * * @description:
 * * @author: liaopeng
 * * @create: 2020-07-27 10:49
 **/
@RestController
@RequestMapping("/delay")
public class TestController {

    @Autowired
    private DelayQueueManage delayQueueManage;


    @GetMapping("/addDelay")
    public void testDelayQueue(String time) {
        try {

            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = format.parse(time);
            date.getTime();
            MyDelayEntity order = new MyDelayEntity(1L, date.getTime());
            MyDelayEntity order2 = new MyDelayEntity(1L, date.getTime());
            delayQueueManage.add(order);
            delayQueueManage.add(order2);
        } catch (Exception e) {

        }
    }
}
