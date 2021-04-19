package com.fenghuang.poetry.herd.common.web;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * desc ：接口成功
 * author ：lfq
 * date ：2018-04-20 16:42
 * </pre>
 */
@Setter
@Getter
@ToString
@Slf4j
public class Resp<T> {
    private int code;
    //success,fail
    private String status;
    private String message;
    private T data;

    public Boolean isSuccess() {
        return "success".equals(status);
    }

    public Resp(int code, String status, String message, T data) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.data = data;
        log.debug(this.toString());
    }

    public Resp(T data) {
        this.code = 200;
        this.status = "success";
        this.message = "成功";
        this.data = data;
        log.debug(this.toString());
    }

    public Resp(Err err, String... args) {
        this.code = err.getResult();
        this.message = String.format(err.getMessage(), args);
        log.warn(this.toString());
    }

    public Resp(int code, String message) {
        this.code = code;
        this.status = "error";
        this.message = message;
        log.debug(this.toString());
    }

    public Resp(int code, String status, String message) {
        this.code = code;
        this.status = "fail";
        this.message = message;
        log.debug(this.toString());
    }

    public static Resp error(String message) {
        Resp resp = new Resp(1, message);
        log.debug(resp.toString());
        return resp;
    }

    public static Resp ok(Object data) {
        Resp resp = new Resp(data);
        log.debug(resp.toString());
        return resp;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
