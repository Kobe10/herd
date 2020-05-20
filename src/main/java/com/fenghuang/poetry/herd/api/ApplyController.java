package com.fenghuang.poetry.herd.api;

import com.alibaba.fastjson.JSONObject;
import com.fenghuang.poetry.herd.api.model.req.ApplyInfoReq;
import com.fenghuang.poetry.herd.api.model.req.QueryApplyInfoReq;
import com.fenghuang.poetry.herd.api.model.resp.ApplyInfoVo;
import com.fenghuang.poetry.herd.common.util.RegexUtil;
import com.fenghuang.poetry.herd.common.web.Resp;
import com.fenghuang.poetry.herd.config.exception.BusinessException;
import com.fenghuang.poetry.herd.service.facade.UserFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author fuzq
 * @version 1.0
 * @Desc 报名
 * @date Created in 2020年05月10日 14:24
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/apply")
@Api(value = "【海选报名】", tags = {"【海选报名】"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApplyController {
    @Autowired
    private UserFacade userFacade;


    @ApiOperation(
            value = "考试报名接口",
            notes = "考试报名接口",
            response = ApplyInfoVo.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "POST"
    )
    @PostMapping("/sign")
    public Object apply(@RequestBody ApplyInfoReq applyInfoReq) {
        // 1、参数校验 注解校验
        log.info("【海选报名-sign，入参:{}】", JSONObject.toJSONString(applyInfoReq));
        // 验证是否为手机号
        if (!RegexUtil.isChinaPhoneLegal(applyInfoReq.getPhone())) {
            return new Resp(500, "手机号格式不正确");
        }

        // 2、报名
        ApplyInfoVo applyInfoVo = new ApplyInfoVo();
        try {
            applyInfoVo = userFacade.sign(applyInfoReq);
        } catch (BusinessException e) {
            log.error("海选报名异常，异常信息:{}", e.getMessage());
            return new Resp(500, e.getMessage());
        }

        if (Objects.isNull(applyInfoVo)) {
            return new Resp(500, "服务器繁忙，请稍后再试");
        }
        //出参
        log.info("【海选报名-报名结果:{}】", JSONObject.toJSONString(applyInfoVo));
        return new Resp(applyInfoVo);
    }

    @ApiOperation(
            value = "查询参赛码接口",
            notes = "查询参赛码接口",
            response = ApplyInfoVo.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "POST"
    )
    @PostMapping("/get/info")
    public Object getApplyInfo(@RequestBody QueryApplyInfoReq queryApplyInfoReq) {
        log.info("【查询报名码-getApplyInfo，入参:{}】", JSONObject.toJSONString(queryApplyInfoReq));
        ApplyInfoVo applyInfoVo = userFacade.findCompetition(queryApplyInfoReq);
        if (Objects.isNull(applyInfoVo)) {
            return new Resp(500, "您还没有报名，请重新报名");
        }
        return new Resp(applyInfoVo);
    }

    @ApiOperation(
            value = "查询参赛码接口",
            notes = "查询参赛码接口",
            response = ApplyInfoVo.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "POST"
    )
    @GetMapping("/get/h5")
    public Object getH5() {

        return null;
    }
}
