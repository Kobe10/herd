package com.fenghuang.poetry.herd.api;

import com.alibaba.fastjson.JSONObject;
import com.fenghuang.poetry.herd.api.model.req.CompetitionInfoReq;
import com.fenghuang.poetry.herd.api.model.req.LoginInfoReq;
import com.fenghuang.poetry.herd.api.model.req.PersonalRankInfoReq;
import com.fenghuang.poetry.herd.api.model.req.UserRankInfoReq;
import com.fenghuang.poetry.herd.api.model.resp.LoginUserInfoVo;
import com.fenghuang.poetry.herd.api.model.resp.question.PersonRankDetailVo;
import com.fenghuang.poetry.herd.api.model.resp.question.SubmitAnswerVo;
import com.fenghuang.poetry.herd.api.model.resp.question.UserListRankDetailVo;
import com.fenghuang.poetry.herd.common.util.LimitUtil;
import com.fenghuang.poetry.herd.common.web.Resp;
import com.fenghuang.poetry.herd.config.exception.BusinessException;
import com.fenghuang.poetry.herd.service.model.dto.RankRedisDTO;
import com.fenghuang.poetry.herd.service.provider.RankService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

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
 * @Desc 排行榜
 * @date Created in 2020年05月10日 09:10
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/rank")
@Api(value = "【排行榜】", tags = {"【排行榜】"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class RankController {
    @Autowired
    private RankService rankService;

    @ApiOperation(
            value = "个人成绩排行榜",
            notes = "个人成绩排行榜",
            response = PersonRankDetailVo.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "POST"
    )
    @PostMapping("/personal/leaderboard")
    public Object personalLeaderboard(@RequestBody PersonalRankInfoReq personalRankInfoReq) {
        log.info("个人排行榜：入参:{}", JSONObject.toJSONString(personalRankInfoReq));
        try {
            return rankService.getPersonalRank(personalRankInfoReq);
        } catch (Exception e) {
            return new Resp(500, e.getMessage());
        }
    }

    @ApiOperation(
            value = "地区或者省级别排行榜",
            notes = "地区或者省级别排行榜",
            response = UserListRankDetailVo.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "POST"
    )
    @PostMapping("/all/leaderboard")
    private Resp getUserRankList(@RequestBody UserRankInfoReq userRankInfoReq) {
        log.info("地区或者省级别排行榜：入参:{}", JSONObject.toJSONString(userRankInfoReq));

        String START_TIME = "2020-08-16 20:00:00";
        String END_TIME = "2020-08-20 13:30:59";

        DateTimeFormatter DATE_FOMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

        LocalDateTime startDate = LocalDateTime.parse(START_TIME, DATE_FOMAT);
        LocalDateTime endDate = LocalDateTime.parse(END_TIME, DATE_FOMAT);

        //todo  活动时间限制
        if (System.currentTimeMillis() < Instant.from(startDate.atZone(ZoneId.systemDefault())).toEpochMilli()) {
            return new Resp(500, "排行榜暂未开启显示，请稍后再试！");
        }


        if (System.currentTimeMillis() >= Instant.from(endDate.atZone(ZoneId.systemDefault())).toEpochMilli()) {
            return new Resp(500, "排行榜暂已关闭");
        }

        if (!LimitUtil.tryRankAcquire()) {
            log.warn("getRandomQuestion-报名接口并发量查过100，活动火爆,拒绝请求");
            return new Resp(500, "活动太火爆了，请稍后再试");
        }

        Resp resp = rankService.getUserRankList(userRankInfoReq);
        return resp;
    }
}
