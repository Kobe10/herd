package com.fenghuang.poetry.herd.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Api(value = "系统心跳", tags = {"【心跳】"}, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class KeepaliveController {

    @ApiOperation(
            value = "系统心跳",
            notes = "系统心跳",
            response = String.class,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/ok")
    private String ok() throws UnknownHostException {
        InetAddress ia=InetAddress.getLocalHost();
//        String localname=ia.getHostName();
        String localip=ia.getHostAddress();
        System.out.println("call me " );
        return "i am " + localip;
    }
}
