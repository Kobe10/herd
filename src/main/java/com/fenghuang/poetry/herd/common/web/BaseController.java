package com.fenghuang.poetry.herd.common.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class BaseController {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Resp<String> exception(Exception e) {


        if (e instanceof RuntimeException) {
            log.error(e.getMessage(), e);
            String message = e.getMessage();
            //约定格式解析
            if (StringUtils.isNotBlank(message) && message.startsWith("BIZ")) {
                Integer code = Integer.valueOf(message.split("|")[1]);
                String mess = message.split("|")[2];

                return new Resp<String>(code, mess);
            }
            return new Resp<String>(1, "ERROR");
        } else {
            log.error(e.getMessage(), e);
            return new Resp<String>(1, "ERROR");
        }
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Resp<String> exception(HttpServletRequest req, Exception e) {
        log.error(e.getMessage(), e);
        return new Resp<>(1, "请校验入参是否正确!");
    }

    @ExceptionHandler({
            IllegalArgumentException.class,
            HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    public Resp<String> exception1(HttpServletRequest req, Exception e) {
        log.error(e.getMessage(), e);
        return new Resp<>(1, e.getMessage());
    }
}
