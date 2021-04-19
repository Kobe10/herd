package com.fenghuang.poetry.herd.service.facade;

import com.fenghuang.poetry.herd.api.model.req.question.RandomQuestionReq;
import com.fenghuang.poetry.herd.api.model.req.question.SubmitAnswerReq;
import com.fenghuang.poetry.herd.api.model.resp.question.RandomQuestionVo;
import com.fenghuang.poetry.herd.common.web.Resp;

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
 * @date Created in 2020年07月07日 20:41
 * @since 1.0
 */
public interface QuestionFacade {
    /**
     * 获取随机问题
     * @param randomQuestionReq
     * @return
     */
    Resp getQuestion(RandomQuestionReq randomQuestionReq);

    /**
     * 提交答案
     * @param submitAnswerReq 提交答案
     * @return
     */
    Resp submitAnswerReq(SubmitAnswerReq submitAnswerReq);
}
