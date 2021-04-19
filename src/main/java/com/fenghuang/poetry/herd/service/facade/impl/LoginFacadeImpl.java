package com.fenghuang.poetry.herd.service.facade.impl;

import com.fenghuang.poetry.herd.api.model.req.LoginInfoReq;
import com.fenghuang.poetry.herd.api.model.resp.LoginInfoVo;
import com.fenghuang.poetry.herd.api.model.resp.LoginUserInfoVo;
import com.fenghuang.poetry.herd.common.enums.GradeEnum;
import com.fenghuang.poetry.herd.common.util.BeanUtil;
import com.fenghuang.poetry.herd.common.web.Resp;
import com.fenghuang.poetry.herd.config.exception.BusinessException;
import com.fenghuang.poetry.herd.dao.CompetitionMapper;
import com.fenghuang.poetry.herd.dao.SceneMapper;
import com.fenghuang.poetry.herd.dao.UserMapper;
import com.fenghuang.poetry.herd.dao.entity.CompetitionEntity;
import com.fenghuang.poetry.herd.dao.entity.SceneEntity;
import com.fenghuang.poetry.herd.dao.entity.UserEntity;
import com.fenghuang.poetry.herd.service.facade.LoginFacade;
import com.fenghuang.poetry.herd.service.provider.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
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
 * @Desc 登录
 * @date Created in 2020年07月06日 18:42
 * @since 1.0
 */
@Slf4j
@Service
public class LoginFacadeImpl implements LoginFacade {

    @Resource
    private CompetitionMapper competitionMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;

    @Resource
    private SceneMapper sceneMapper;

    /**
     * 用户登录
     *
     * @param loginInfoReq
     * @return
     */
    @Override
    public LoginInfoVo login(LoginInfoReq loginInfoReq) throws Exception {
        //1、校验当前参赛码是否存在，取到uid和场景编码
        // 查询场景编码： 默认是初赛； 依次遍历； 如果当前场景有效，那就使用当前场景，否则递归使用下一个场景
        List<SceneEntity> allSceneList = sceneMapper.selectAllList();

        String sceneCode = "FS";
        for (SceneEntity sceneEntity : allSceneList) {
            if (sceneEntity.getSceneStatus() == 1) {
                sceneCode = sceneEntity.getSceneCode();
                break;
            }
        }
        CompetitionEntity competitionEntity = competitionMapper.selectByCompetitionCodeAndSceneCode(loginInfoReq.getCompetitionCode(), sceneCode);
        if (Objects.isNull(competitionEntity)) {
            return null;
        }
        //
        //2、规则校验  加锁、并且加事务
        boolean isOk = true;
        try {
            isOk = userService.checkUserEligibility(competitionEntity);
        } catch (Exception e) {
            throw e;
        }

        if (!isOk) {
            //校验资格未通过
            throw new BusinessException("您已达到本次比赛答题次数上限!");
        }
        //3、 当前用户信息是否验证通过
        UserEntity userEntity = userMapper.findUserByUid(competitionEntity.getUid());
        LoginUserInfoVo loginUserInfoVo = LoginUserInfoVo.builder().build();
        BeanUtils.copyProperties(userEntity, loginUserInfoVo);
        GradeEnum gradeEnum = GradeEnum.getByCodeForUpdate(userEntity.getGrade());
        if (Objects.isNull(gradeEnum)) {
            userEntity.setGrade(null);
        }

        //4、返回当前用户信息
        return LoginInfoVo.builder()
                .competitionCode(loginInfoReq.getCompetitionCode())
                .isCheckUserInfo(userEntity.getIsVerify())
                .loginUserInfoVo(loginUserInfoVo)
                .build();
    }
}
