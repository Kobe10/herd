package com.fenghuang.poetry.herd.service.provider;

import com.fenghuang.poetry.herd.dao.entity.AnswerEntity;
import com.fenghuang.poetry.herd.dao.entity.PageOrderEntity;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
 * @Desc 答案服务类
 * @date Created in 2020年07月13日 22:53
 * @since 1.0
 */
public interface AnswerService {
    /**
     * 存储答案
     * @param pageOrderCode    答卷编码
     * @param atomicInteger    正确答题数
     * @param userUseTime      答题时间 秒
     * @param answerEntityList 答案list
     * @param submitAnswerTime
     * @param pageOrderEntity
     * @param times
     */
    @Transactional
    void handlerAnswer(String pageOrderCode, AtomicInteger atomicInteger, Integer userUseTime, List<AnswerEntity> answerEntityList, LocalDateTime submitAnswerTime, PageOrderEntity pageOrderEntity, Integer times);
}
