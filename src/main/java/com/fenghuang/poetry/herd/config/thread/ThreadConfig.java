package com.fenghuang.poetry.herd.config.thread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;


/**
 * Created by philalina on 2019-11-14.
 * com.ziroom.evaluate.config.
 * ziroom-evaluate-service.
 */

@Configuration
public class ThreadConfig {

    @Bean
    public TaskExecutor simpleTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(10);
        // 设置最大线程数
        executor.setMaxPoolSize(30);
        // 设置队列容量
        executor.setQueueCapacity(20000);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(120);
        // 设置默认线程名称
        executor.setThreadNamePrefix("Evaluate-Async-");
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}