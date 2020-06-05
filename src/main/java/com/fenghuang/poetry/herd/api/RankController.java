package com.fenghuang.poetry.herd.api;

import com.fenghuang.poetry.herd.api.model.req.CompetitionInfoReq;
import com.fenghuang.poetry.herd.api.model.req.LoginInfoReq;
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
 * @Desc 排行榜
 * @date Created in 2020年05月10日 09:10
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/rank")
@Api(value = "【排行榜】", tags = {"【排行榜】"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class RankController {
    @ApiOperation(
            value = "排行榜",
            notes = "排行榜",
            response = LoginUserInfoVo.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "POST"
    )
    @PostMapping("/leaderboard")
    public Object leaderboard(@RequestBody LoginInfoReq loginInfoReq) {
        return null;
    }
}
