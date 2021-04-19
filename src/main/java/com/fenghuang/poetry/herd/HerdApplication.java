package com.fenghuang.poetry.herd;

import com.cxytiandi.encrypt.springboot.annotation.EnableEncrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.TimeZone;

@SpringBootApplication(
        exclude = {

                JacksonAutoConfiguration.class,
                ThymeleafAutoConfiguration.class
        })
@EnableAsync
@EnableEncrypt
@EnableRetry
@ServletComponentScan  //注册过滤器注解
@Configuration
public class HerdApplication {
    @Value("${server.port:}")
    private String port;

    @PostConstruct
    void started() {
//        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
//TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
    }

    public static void main(String[] args) {
        SpringApplication.run(HerdApplication.class, args);
    }

    @GetMapping("/test/ok")
    public String hello() throws UnknownHostException {
        InetAddress ia = InetAddress.getLocalHost();
//        String localname=ia.getHostName();
        String localip = ia.getHostAddress();
        System.out.println("call me " + port);
        return "i am " + localip;
    }
}
