package com.fenghuang.poetry.herd.web;

import com.fenghuang.poetry.herd.service.provider.TaskService;
import com.fenghuang.poetry.herd.service.provider.impl.TaskServiceImpl;
import com.fenghuang.poetry.herd.web.model.resp.user.UserDetailInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
 * @Desc
 * @date Created in 2020年07月18日 16:59
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/web/task")
@Api(value = "【定时任务】", tags = {"【定时任务】"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * 上传二维码图片
     *
     * @param imageName
     * @return
     */
    @ApiOperation(
            value = "问题选项填充缓存",
            notes = "问题选项填充缓存",
            response = String.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "POST"
    )
    @PostMapping("/fill/question/option/redis")
    @ResponseBody
    public Object fillQuestionOptionToRedis() throws IOException {
        taskService.questionOptionToRedisTasks();;
        return "成功";
    }
}
