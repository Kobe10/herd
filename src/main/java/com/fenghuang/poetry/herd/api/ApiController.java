package com.fenghuang.poetry.herd.api;

import com.fenghuang.poetry.herd.api.model.req.ApplyInfoReq;
import com.fenghuang.poetry.herd.api.model.resp.ApplyInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/api")
@Api(value = "【C端 通用接口】", tags = {"【C端 通用接口】"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiController {
    @ApiOperation(
            value = "比赛登录接口",
            notes = "比赛登录接口",
            response = ApplyInfoVo.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "POST"
    )
    @PostMapping("/login")
    public Object apply(@RequestBody ApplyInfoReq applyInfoReq) {
        return null;
    }
}
