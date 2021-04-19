package com.fenghuang.poetry.herd.service.provider;

import com.fenghuang.poetry.herd.api.model.req.question.RandomQuestionReq;
import com.fenghuang.poetry.herd.dao.entity.PageOrderEntity;
import org.springframework.transaction.annotation.Transactional;

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
 * @Desc 答卷单服务
 * @date Created in 2020年07月08日 20:15
 * @since 1.0
 */
public interface PageOrderService {

    /**
     * 创建答卷单
     *
     * @param sceneConfig       场景配置
     * @param randomQuestionReq 随机问题
     * @return
     */
    @Transactional
    String createPageOrder(List<String> sceneConfig, RandomQuestionReq randomQuestionReq);

    /**
     * 查询答卷单信息
     *
     * @param pageOrderCode  答卷单号
     * @return
     */
    PageOrderEntity selectByPageOrderCode(String pageOrderCode);
}
