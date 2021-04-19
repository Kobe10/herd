package com.fenghuang.poetry.herd.service.provider.impl;

import com.fenghuang.poetry.herd.dao.QuestionMapper;
import com.fenghuang.poetry.herd.service.provider.QuestionService;
import com.fenghuang.poetry.herd.service.provider.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

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
 * @date Created in 2020年07月18日 17:01
 * @since 1.0
 */
@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class TaskServiceImpl implements TaskService {
    @Autowired
    private QuestionService questionService;

    @Resource
    private QuestionMapper questionMapper;

    //3.添加定时任务
    @Override
    @Scheduled(cron = "0 0 0 */1 * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    public void questionOptionToRedisTasks() {
        List<String> questionCodeList = questionMapper.selectAll();
        questionCodeList.forEach(questionCode -> {
            questionService.getQuestionOption(questionCode);
        });
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
    }
}
