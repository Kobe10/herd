package com.fenghuang.poetry.herd.service.provider;

import com.fenghuang.poetry.herd.service.model.dto.CompetitionDto;
import com.fenghuang.poetry.herd.service.model.dto.PageInfoDto;
import com.fenghuang.poetry.herd.service.model.dto.UserExtraInfoDto;
import com.fenghuang.poetry.herd.service.model.dto.UserInfoDto;
import com.fenghuang.poetry.herd.web.model.req.QueryUserReq;
import com.fenghuang.poetry.herd.web.model.resp.user.QueryUserCountVo;
import com.fenghuang.poetry.herd.web.model.resp.user.QueryUserResultVo;
import com.fenghuang.poetry.herd.web.model.resp.user.UserDetailInfoVo;

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
 * @date Created in 2020年05月11日 13:34
 * @since 1.0
 */
public interface UserService {
    /**
     * 查询用户是否存在
     *
     * @param userInfoDto 用户信息dto
     * @return UserInfoDto
     */
    UserInfoDto checkUserIsExist(UserInfoDto userInfoDto);

    /**
     * 海选报名
     *
     * @param userInfoDto      用户信息
     * @param userExtraInfoDto 用户额外信息
     * @return CompetitionDto
     */
    CompetitionDto sign(UserInfoDto userInfoDto, UserExtraInfoDto userExtraInfoDto);

    /**
     * 查询用户比赛码
     *
     * @param uid       用户uid
     * @param sceneCode 场景编码
     * @return
     */
    CompetitionDto findCompetitionCode(String uid, String sceneCode);

    /**
     * 查询用户列表
     *
     * @param queryUserReq 查询用户列表入参
     * @return
     */
    PageInfoDto<UserDetailInfoVo> findUserListByCondition(QueryUserReq queryUserReq);

    /**
     * 用户数量统计
     * @return
     */
    QueryUserCountVo countUser();
}
