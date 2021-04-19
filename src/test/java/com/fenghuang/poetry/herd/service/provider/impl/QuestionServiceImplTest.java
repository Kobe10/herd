package com.fenghuang.poetry.herd.service.provider.impl;

import com.fenghuang.poetry.herd.HerdApplication;
import com.fenghuang.poetry.herd.common.util.UUIDGenerator;
import com.fenghuang.poetry.herd.dao.QuestionMapper;
import com.fenghuang.poetry.herd.dao.entity.QuestionEntity;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

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
 * @date Created in 2020年07月08日 19:53
 * @since 1.0
 */
@Slf4j
@SpringBootTest(classes = HerdApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@ActiveProfiles("default")
public class QuestionServiceImplTest {
    @Resource
    private QuestionMapper questionMapper;

    @Test
    public void test(){
        for (int i = 0; i < 800 ; i++) {
            QuestionEntity questionEntity = new QuestionEntity();
            questionEntity.setBankCode("1");
            questionEntity.setQuestionType("mot");
            questionEntity.setQuestionTitle("hahah");
            questionEntity.setAnswer("222");
            questionEntity.setOrderValue(i);
            questionEntity.setOptionCount(i);
            questionEntity.setDifficult("nan");
            questionEntity.setIsDel(0);
            questionEntity.setQuestionCode(UUIDGenerator.hexUUID());
            questionMapper.insertSelective(questionEntity);
        }

        System.out.println(1);
    }
}