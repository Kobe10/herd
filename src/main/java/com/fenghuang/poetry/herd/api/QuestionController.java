package com.fenghuang.poetry.herd.api;

import com.fenghuang.poetry.herd.api.model.req.ApplyInfoReq;
import com.fenghuang.poetry.herd.api.model.req.question.SubmitAnswerReq;
import com.fenghuang.poetry.herd.api.model.resp.ApplyInfoVo;
import com.fenghuang.poetry.herd.api.model.resp.CompetitionRuleInfoVo;
import com.fenghuang.poetry.herd.api.model.resp.question.QuestionAnswerDetailVo;
import com.fenghuang.poetry.herd.api.model.resp.question.RandomQuestionVo;
import com.fenghuang.poetry.herd.api.model.resp.question.SubmitAnswerVo;
import com.fenghuang.poetry.herd.common.web.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/question")
@Api(value = "【问题】", tags = {"【海选报名】"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class QuestionController {

    @ApiOperation(
            value = "获取所有答卷题目信息  随机策略",
            notes = "考试报名接口",
            response = RandomQuestionVo.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "POST"
    )
    @PostMapping("/random/get")
    private Resp getRandomQuestion(@RequestBody CompetitionRuleInfoVo competitionRuleInfoVo) {
        return null;
    }


    @ApiOperation(
            value = "提交答案",
            notes = "提交答案",
            response = SubmitAnswerVo.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "POST"
    )
    @PostMapping("/submit/answer")
    private Resp submitAnswer(@RequestBody SubmitAnswerReq submitAnswerReq) {
        return null;
    }

    @ApiOperation(
            value = "答案详情接口",
            notes = "答案详情接口",
            response = QuestionAnswerDetailVo.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "POST"
    )
    @PostMapping("/question/detail/{questionId}")
    private Resp getAnswerDetail(@PathVariable("questionId") String questionId) {
        return null;
    }

    @ApiOperation(
            value = "查询个人成绩",
            notes = "查询个人成绩",
            response = SubmitAnswerVo.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "POST"
    )
    @PostMapping("/user/score/{competitionCode}")
    private Resp getUserScore(@PathVariable("competitionCode") String competitionCode) {
        return null;
    }
}
