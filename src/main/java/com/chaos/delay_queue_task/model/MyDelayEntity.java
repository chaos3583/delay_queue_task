package com.chaos.delay_queue_task.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * * @description:队列元素
 * * @author: liaopeng
 * * @create: 2020-07-27 10:36
 **/

@Data
@EqualsAndHashCode
public class MyDelayEntity implements Delayed {

    private Long id;

    private Long startTime;//时间戳

    public MyDelayEntity(Long id, Long startTime) {
        this.id = id;
        this.startTime = startTime;
    }

    /**
     * 定义了剩余到期时间
     * 超时判定是通过getDelay(TimeUnit.NANOSECONDS)方法的返回值小于等于0来判断
     *
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(startTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    /**
     * 定义了元素排序规则
     *
     * @param other
     * @return
     */
    @Override
    public int compareTo(Delayed other) {
        if (other == this) {
            return 0;
        }
        if (other instanceof MyDelayEntity) {
            MyDelayEntity otherRequest = (MyDelayEntity) other;
            long otherStartTime = otherRequest.getStartTime();
            return (int) (this.startTime - otherStartTime);
        }
        return 0;
    }
}
