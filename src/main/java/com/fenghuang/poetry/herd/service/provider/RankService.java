package com.fenghuang.poetry.herd.service.provider;

import com.fenghuang.poetry.herd.api.model.req.PersonalRankInfoReq;
import com.fenghuang.poetry.herd.api.model.req.UserRankInfoReq;
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
 * @Desc 排行榜
 * @date Created in 2020年07月16日 20:57
 * @since 1.0
 */
public interface RankService {
    /**
     * 个人排行榜
     *
     * @param personalRankInfoReq 个人排行榜入参
     * @return
     */
    Resp getPersonalRank(PersonalRankInfoReq personalRankInfoReq);

    /**
     * 个人或者省排行榜入参
     *
     * @param personalRankInfoReq
     * @return
     */
    Resp getUserRankList(UserRankInfoReq personalRankInfoReq);
}
