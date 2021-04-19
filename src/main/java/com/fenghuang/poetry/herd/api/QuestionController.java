package com.fenghuang.poetry.herd.api;

import com.alibaba.fastjson.JSONObject;
import com.cxytiandi.encrypt.springboot.annotation.Decrypt;
import com.cxytiandi.encrypt.springboot.annotation.Encrypt;
import com.fenghuang.poetry.herd.api.model.req.question.RandomQuestionReq;
import com.fenghuang.poetry.herd.api.model.req.question.SubmitAnswerReq;
import com.fenghuang.poetry.herd.api.model.resp.CompetitionRuleInfoVo;
import com.fenghuang.poetry.herd.api.model.resp.question.QuestionAnswerDetailVo;
import com.fenghuang.poetry.herd.api.model.resp.question.RandomQuestionVo;
import com.fenghuang.poetry.herd.api.model.resp.question.SubmitAnswerVo;
import com.fenghuang.poetry.herd.common.util.LimitUtil;
import com.fenghuang.poetry.herd.common.web.Resp;
import com.fenghuang.poetry.herd.service.facade.QuestionFacade;
import com.fenghuang.poetry.herd.service.provider.QuestionService;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Random;


/**
 * 加密文档
 * https://blog.csdn.net/fishinhouse/article/details/80566138
 * https://github.com/yinjihuan/monkey-api-encrypt
 */
@Slf4j
@RestController
@RequestMapping("/api/question")
@Api(value = "【答题接口】", tags = {"【答题接口】【当前领域接口所有入参出参都需进行加密处理  加密技术详见后端开发】"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class QuestionController {

    @Autowired
    private QuestionFacade questionFacade;

    @ApiOperation(
            value = "获取所有答卷题目信息  随机策略  （出参加密、入参也是加密的）",
            notes = "获取所有答卷题目信息  随机策略  （出参加密、入参也是加密的）",
            response = RandomQuestionVo.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "POST"
    )
    //接口加密
    @PostMapping("/random/get")
    private Object getRandomQuestion(@RequestBody RandomQuestionReq randomQuestionReq) {
        // 获取考试问题
        log.info("【随机获取问题参数-getRandomQuestion，入参:{}】", JSONObject.toJSONString(randomQuestionReq));

        if (!LimitUtil.tryGetQuestionAcquire()) {
            log.warn("getRandomQuestion-报名接口并发量查过100，活动火爆,拒绝请求");
            return new Resp(500, "活动太火爆了，请稍后再试");
        }

        Resp resp = questionFacade.getQuestion(randomQuestionReq);
        return resp;
    }


    @ApiOperation(
            value = "提交答案  （出参加密、入参也是加密的）",
            notes = "提交答案  （出参加密、入参也是加密的）",
            response = SubmitAnswerVo.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "POST"
    )

    @PostMapping("/submit/answer")
    private Object submitAnswer(@RequestBody SubmitAnswerReq submitAnswerReq) {
        log.info("【提交答卷-submitAnswer，入参:{}】", JSONObject.toJSONString(submitAnswerReq));

        if (!LimitUtil.trySaveAcquire()) {
            log.warn("getRandomQuestion-报名接口并发量查过100，活动火爆,拒绝请求");
            return new Resp(500, "活动太火爆了，请稍后再试");
        }

        Resp resp = questionFacade.submitAnswerReq(submitAnswerReq);
        log.info("【提交答卷-submitAnswer：返回结果: {}", JSONObject.toJSONString(resp));
        return resp;
    }

    @ApiOperation(
            value = "提交答案  （出参加密、入参也是加密的）",
            notes = "提交答案  （出参加密、入参也是加密的）",
            response = SubmitAnswerVo.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "POST"
    )

    @GetMapping("/ok")
    private Object ok(@RequestBody RandomQuestionReq submitAnswerReq) {

        return "ok";
    }

}
