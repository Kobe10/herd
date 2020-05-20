package com.fenghuang.poetry.herd.common.web;

/**
 * Created by lyy on 16/8/29.
 */
public class ServiceException extends RuntimeException{

    private final Err err;
    private  String[] formatmsg;

    public ServiceException(String msg){
        super(String.format(Err.SERVICE_USE_EXCEPTION.getMessage(),msg));
        this.err=Err.SERVICE_USE_EXCEPTION;
        formatmsg=new String[]{msg};
    }
    public ServiceException(Err err) {
        super(err.getMessage());
        this.err = err;
    }

    public ServiceException(Err err, String... args) {
        super(String.format(err.getMessage(),args)) ;
        this.err = err;
        this.formatmsg=args;
    }

    public Err getErr() {
        return err;
    }

    public String[] getFormatmsg() {
        return formatmsg;
    }
}
