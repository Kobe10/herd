package com.fenghuang.poetry.herd.service.facade.impl;

import com.fenghuang.poetry.herd.api.model.req.ApplyInfoReq;
import com.fenghuang.poetry.herd.api.model.req.QueryApplyInfoReq;
import com.fenghuang.poetry.herd.api.model.resp.ApplyInfoVo;
import com.fenghuang.poetry.herd.api.model.resp.CompetitionCodeInfoVo;
import com.fenghuang.poetry.herd.common.enums.*;
import com.fenghuang.poetry.herd.common.web.Resp;
import com.fenghuang.poetry.herd.dao.SceneMapper;
import com.fenghuang.poetry.herd.dao.entity.SceneEntity;
import com.fenghuang.poetry.herd.service.facade.UserFacade;
import com.fenghuang.poetry.herd.service.model.dto.CompetitionDto;
import com.fenghuang.poetry.herd.service.model.dto.UserExtraInfoDto;
import com.fenghuang.poetry.herd.service.model.dto.UserInfoDto;
import com.fenghuang.poetry.herd.service.provider.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

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
@Slf4j
@Service
public class UserFacadeImpl implements UserFacade {
    @Autowired
    private UserService userService;

    @Resource
    private SceneMapper sceneMapper;

    /**
     * 海选报名
     *
     * @param applyInfoReq 报名请求入参
     * @return ApplyInfoVo
     */
    @Override
    public ApplyInfoVo sign(ApplyInfoReq applyInfoReq) {
        UserInfoDto userInfoDto = new UserInfoDto();
        BeanUtils.copyProperties(applyInfoReq, userInfoDto);

        UserExtraInfoDto userExtraInfoDto = new UserExtraInfoDto();
        userExtraInfoDto.setSceneCode("HX");
        userExtraInfoDto.setStageCode(StageEnum.SC_HX.getCode());
        CompetitionDto competitionDto = userService.sign(userInfoDto, userExtraInfoDto);
        if (!Objects.isNull(competitionDto)) {
            CompetitionCodeInfoVo competitionCodeInfoVo = new CompetitionCodeInfoVo();
            BeanUtils.copyProperties(competitionDto, competitionCodeInfoVo);
            return ApplyInfoVo.builder()
                    .applyStatus(ApplyStatusEnum.YBM.getCode())
                    .competitionCodeInfoVo(competitionCodeInfoVo)
                    .wxGroupChatQRUrl(AreaWxChatEnum.getPngPathByCode(userInfoDto.getAreaCode()) +
                            "-" + GradeEnum.getByCode(userInfoDto.getGrade()).getGeneraCode() + ".png")
                    .build();
        }
        return null;
    }

    /**
     * 查询参赛码
     *
     * @param queryApplyInfoReq
     * @return
     */
    @Override
    public ApplyInfoVo findCompetition(QueryApplyInfoReq queryApplyInfoReq) {
        UserInfoDto userInfoDto = UserInfoDto.builder()
                .userName(queryApplyInfoReq.getUserName())
                .phone(queryApplyInfoReq.getPhone())
                .build();

        UserInfoDto userInfo = userService.checkUserIsExist(userInfoDto);
        // 查询场景编码： 默认是初赛； 依次遍历； 如果当前场景有效，那就使用当前场景，否则递归使用下一个场景
        List<SceneEntity> allSceneList = sceneMapper.selectAllList();
        if (CollectionUtils.isEmpty(allSceneList)) {

        }
        String sceneCode = "FS";
        for (SceneEntity sceneEntity : allSceneList) {
            if (sceneEntity.getSceneStatus() == 1) {
                sceneCode = sceneEntity.getSceneCode();
                break;
            }
        }
        if (!Objects.isNull(userInfo)) {
            CompetitionDto competitionDto = userService.findCompetitionCode(userInfo.getUid(), sceneCode);
            if (!Objects.isNull(competitionDto)) {
                CompetitionCodeInfoVo competitionCodeInfoVo = new CompetitionCodeInfoVo();
                BeanUtils.copyProperties(competitionDto, competitionCodeInfoVo);
                return ApplyInfoVo.builder()
                        .applyStatus(ApplyStatusEnum.YBM.getCode())
                        .competitionCodeInfoVo(competitionCodeInfoVo)
                        .wxGroupChatQRUrl(AreaWxChatEnum.getPngPathByCode(userInfo.getAreaCode()) +
                                "-" + GradeEnum.getByCode(userInfo.getGrade()).getGeneraCode() + ".png")
                        .build();
            }
        }
        return null;
    }
}
