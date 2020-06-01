package com.fenghuang.poetry.herd.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/question")
@Api(value = "【问题】", tags = {"【海选报名】"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class QuestionController {

    @ApiOperation(
            value = "系统心跳",
            notes = "系统心跳",
            response = String.class,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/ok")
    private String ok() {
        return "ok";
    }
}
