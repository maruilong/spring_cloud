package com.xinyuan.task.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * @author liang
 */
@Component
public class TableTaskSchedule extends AbstractTaskSchedule {

    /**
     */
    @Scheduled(fixedRate = 1000 * 5)
    public void checkTableTask() {

        System.out.println("定时任务");
    }

}
