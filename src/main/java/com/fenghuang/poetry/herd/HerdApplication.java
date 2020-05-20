package com.fenghuang.poetry.herd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(
        exclude = {

                JacksonAutoConfiguration.class,
                ThymeleafAutoConfiguration.class
        })
@EnableAsync
@EnableRetry
@ServletComponentScan  //注册过滤器注解
@Configuration
public class HerdApplication {

    public static void main(String[] args) {
        SpringApplication.run(HerdApplication.class, args);
    }

}
