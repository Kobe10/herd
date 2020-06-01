package com.fenghuang.poetry.herd.api;

import com.fenghuang.poetry.herd.api.model.req.CompetitionInfoReq;
import com.fenghuang.poetry.herd.api.model.req.LoginInfoReq;
import com.fenghuang.poetry.herd.api.model.resp.CompetitionRuleInfoVo;
import com.fenghuang.poetry.herd.api.model.resp.LoginUserInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

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
    @ApiOperation(
            value = "比赛登录接口",
            notes = "比赛登录接口",
            response = LoginUserInfoVo.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "POST"
    )
    @PostMapping("/login")
    public Object apply(@RequestBody LoginInfoReq loginInfoReq) {
        return null;
    }


    /**
     * 获取海选赛复活码
     *
     * @param competitionInfoReq 分享选手参赛码请求体
     * @return
     */
    @ApiOperation(
            value = "获取复活二维码接口   接口返回二维码图片流",
            notes = "获取复活二维码接口   接口返回二维码图片流",
            response = Response.class,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE,
            httpMethod = "POST"
    )
    @PostMapping("/qr/code")
    public Object getQrCode(@RequestBody CompetitionInfoReq competitionInfoReq) {
        return null;
    }
}
