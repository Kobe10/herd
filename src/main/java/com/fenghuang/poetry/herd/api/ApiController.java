package com.fenghuang.poetry.herd.api;

import com.alibaba.fastjson.JSONObject;
import com.fenghuang.poetry.herd.api.model.req.LoginInfoReq;
import com.fenghuang.poetry.herd.api.model.req.LoginUserInfoReq;
import com.fenghuang.poetry.herd.api.model.resp.ApplyInfoVo;
import com.fenghuang.poetry.herd.api.model.resp.LoginInfoVo;
import com.fenghuang.poetry.herd.api.model.resp.ReviveInfoVo;
import com.fenghuang.poetry.herd.common.util.LimitUtil;
import com.fenghuang.poetry.herd.common.util.RegexUtil;
import com.fenghuang.poetry.herd.common.web.Resp;
import com.fenghuang.poetry.herd.config.annotation.UserLoginToken;
import com.fenghuang.poetry.herd.config.exception.BusinessException;
import com.fenghuang.poetry.herd.service.facade.LoginFacade;
import com.fenghuang.poetry.herd.service.provider.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
 * @Desc 外部调用接口层
 * @date Created in 2020年05月10日 09:10
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/base")
@Api(value = "【登录接口相关】", tags = {"【登录接口相关】"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiController {
    @Autowired
    private LoginFacade loginFacade;

    @Autowired
    private UserService userService;

    @ApiOperation(
            value = "比赛登录接口",
            notes = "比赛登录接口",
            response = LoginInfoVo.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "POST"
    )
    @PostMapping("/login")
    public Object login(@RequestBody LoginInfoReq loginInfoReq) {
        //1、权限拦截
        log.info("【选手登录-login，入参:{}】", JSONObject.toJSONString(loginInfoReq));
        if (!LimitUtil.tryLoginAcquire()) {
            log.warn("login-报名接口并发量查过100，活动火爆,拒绝请求");
            return new Resp(500, "活动太火爆了，请稍后再试");
        }

        // 2、报名
        LoginInfoVo loginInfoVo = new LoginInfoVo();
        try {
            loginInfoVo = loginFacade.login(loginInfoReq);
        } catch (BusinessException e) {
            log.error("登录信息异常，异常信息:{}", e.getMessage());
            return new Resp(500, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (Objects.isNull(loginInfoVo)) {
            return new Resp(500, "当前并非您的答题时间");
        }
        //出参
        log.info("【海选报名-报名结果:{}】", JSONObject.toJSONString(loginInfoVo));
        return new Resp(loginInfoVo);
    }


//    /**
//     * 海选赛复活机会接口
//     *
//     * @return
//     */
//    @ApiOperation(
//            value = "海选赛复活机会接口",
//            notes = "海选赛复活机会接口",
//            response = ReviveInfoVo.class,
//            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE,
//            httpMethod = "POST"
//    )
//    // 需要登录
//    @UserLoginToken
//    @PostMapping("/revive/user/{competitionCode}")
//    public Object getReviveChoice() {
//        return null;
//    }


    /**
     * 修改用户信息接口
     *
     * @return
     */
    @ApiOperation(
            value = "修改用户信息接口",
            notes = "修改用户信息接口",
            response = ReviveInfoVo.class,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE,
            httpMethod = "POST"
    )
    // 需要登录
    @UserLoginToken
    @PostMapping("/update/user/info")
    public Object updateUserInfo(@RequestBody LoginUserInfoReq loginUserInfoReq) {
        log.info("【选手修改信息入参-update-info，入参:{}】", JSONObject.toJSONString(loginUserInfoReq));
        boolean flag = userService.updateUserInfo(loginUserInfoReq);
        if (!flag) {
            return new Resp(500, "修改个人信息失败！");
        }
        return new Resp(null);

    }
}
