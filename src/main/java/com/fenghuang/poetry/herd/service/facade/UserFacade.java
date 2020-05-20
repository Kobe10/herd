package com.fenghuang.poetry.herd.service.facade;

import com.fenghuang.poetry.herd.api.model.req.ApplyInfoReq;
import com.fenghuang.poetry.herd.api.model.req.QueryApplyInfoReq;
import com.fenghuang.poetry.herd.api.model.resp.ApplyInfoVo;

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
 * @date Created in 2020年05月11日 13:33
 * @since 1.0
 */
public interface UserFacade {
    /**
     * 海选报名
     *
     * @param applyInfoReq 报名请求入参
     * @return ApplyInfoVo
     */
    ApplyInfoVo sign(ApplyInfoReq applyInfoReq);

    /**
     * 查询参赛码
     * @param queryApplyInfoReq
     * @return
     */
    ApplyInfoVo findCompetition(QueryApplyInfoReq queryApplyInfoReq);
}
