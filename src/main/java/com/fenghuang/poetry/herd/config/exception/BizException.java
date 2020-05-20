package com.fenghuang.poetry.herd.config.exception;

/**
 * <pre>
 * desc ：业务异常
 * author ：lfq
 * date ：2018-11-21 15:47
 * </pre>
 */

public class BizException extends RuntimeException {

    private static final long serialVersionUID = -7718312104092715209L;

    public BizException(int code, String message){
        super("BIZ|"+code+"|"+message);
    }
    public BizException(int code, String format, Object... args){
        super("BIZ|"+code+"|"+String.format(format,args));
    }
}
