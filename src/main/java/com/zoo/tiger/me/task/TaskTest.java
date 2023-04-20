package com.zoo.tiger.me.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Tiger
 */
@Slf4j
@Component
@EnableScheduling
// @Configuration
// @ConfigurationProperties(prefix = "time")
public class TaskTest {

    // 设置自定义的任务处理器，如果不设置默认是单线程的，会出现排队
    // 实现了
    @Bean
    public ScheduledExecutorService taskScheduler() {
        // 设置需要并行执行的任务数量
        int corePoolSize = Runtime.getRuntime().availableProcessors();

        log.info("schedule coreSize={}", corePoolSize);
        return new ScheduledThreadPoolExecutor(corePoolSize);
    }

    // @Scheduled(cron = "${time.cron}")
    // void testPlaceholder1() {
    //     System.out.println("Execute at " + System.currentTimeMillis());
    // }


    // 推送方法，每秒执行一次
    // @Scheduled(fixedRate = 10000)
    public void push1() throws InterruptedException {
        // 休眠2秒，模拟耗时操作
        TimeUnit.SECONDS.sleep(2);
        System.out.println(Thread.currentThread().getName() + "    push1    模拟推送消息，" + System.currentTimeMillis());
    }

    // 推送方法，每秒执行一次
    // @Scheduled(fixedRate = 10000)
    public void push2() {
        System.out.println(Thread.currentThread().getName() + "    push2    模拟推送消息，" + System.currentTimeMillis());
    }

}
