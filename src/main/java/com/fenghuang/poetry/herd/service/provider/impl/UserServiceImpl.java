package com.fenghuang.poetry.herd.service.provider.impl;

import com.fenghuang.poetry.herd.api.model.req.LoginUserInfoReq;
import com.fenghuang.poetry.herd.common.enums.AreaEnum;
import com.fenghuang.poetry.herd.common.enums.CompetitionStatusEnum;
import com.fenghuang.poetry.herd.common.util.DateUtil;
import com.fenghuang.poetry.herd.common.util.PinYin4jUtils;
import com.fenghuang.poetry.herd.common.util.StringUtils;
import com.fenghuang.poetry.herd.config.exception.BusinessException;
import com.fenghuang.poetry.herd.dao.CompetitionMapper;
import com.fenghuang.poetry.herd.dao.SceneConfigMapper;
import com.fenghuang.poetry.herd.dao.UserMapper;
import com.fenghuang.poetry.herd.dao.UserStageMapper;
import com.fenghuang.poetry.herd.dao.entity.CompetitionEntity;
import com.fenghuang.poetry.herd.dao.entity.SceneConfigEntity;
import com.fenghuang.poetry.herd.dao.entity.UserEntity;
import com.fenghuang.poetry.herd.dao.entity.UserStageEntity;
import com.fenghuang.poetry.herd.service.model.dto.*;
import com.fenghuang.poetry.herd.service.provider.CompetitionService;
import com.fenghuang.poetry.herd.service.provider.SceneConfigService;
import com.fenghuang.poetry.herd.service.provider.UserService;
import com.fenghuang.poetry.herd.web.model.req.QueryUserReq;
import com.fenghuang.poetry.herd.web.model.resp.user.QueryUserCountVo;
import com.fenghuang.poetry.herd.web.model.resp.user.UserCountVo;
import com.fenghuang.poetry.herd.web.model.resp.user.UserDetailInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
 * @Desc
 * @date Created in 2020年05月11日 13:34
 * @since 1.0
 */
@Slf4j
@Component
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private CompetitionMapper competitionMapper;

    @Resource
    private UserStageMapper userStageMapper;

    @Resource
    private SceneConfigMapper sceneConfigMapper;

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private SceneConfigService sceneConfigService;


    /**
     * 查询用户是否存在
     *
     * @param userInfoDto 用户信息dto
     * @return UserInfoDto
     */
    @Override
    public UserInfoDto checkUserIsExist(UserInfoDto userInfoDto) {
        UserEntity userEntity = userMapper.findUserByNameAndPhone(userInfoDto);
        if (!Objects.isNull(userEntity)) {
            UserInfoDto userInfo = new UserInfoDto();
            BeanUtils.copyProperties(userEntity, userInfo);
            return userInfo;
        }
        return null;
    }

    /**
     * 海选报名
     *
     * @param userInfoDto      用户信息
     * @param userExtraInfoDto 用户额外信息
     * @return CompetitionDto
     */
    @Transactional
    @Override
    public CompetitionDto sign(UserInfoDto userInfoDto, UserExtraInfoDto userExtraInfoDto) {
        CompetitionDto competitionDto = new CompetitionDto();
        UserInfoDto userInfo = this.checkUserIsExist(userInfoDto);
        if (!Objects.isNull(userInfo)) {
            throw new BusinessException("当前用户已经注册！");
        }

//        //获取中文名  压测使用
//        String userName = ChineseNameUtil.getName();
//        userInfoDto.setUserName(userName);

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userInfoDto, userEntity);
        //生成uuid
        String uid = competitionService.createUid();
        userEntity.setUid(uid);
        //杭州
        userEntity.setProvinceCode("330000");
        userEntity.setCreateTime(new Date());
        // 保存用户信息
        try {
            userMapper.insertSelective(userEntity);
        } catch (Exception e) {
            throw new BusinessException("该手机号已使用，请更换手机号!");
        }
        String prefix = PinYin4jUtils.getFirstPinYin(userInfoDto.getUserName()) + "-";
        // 创建报名码
        String competitionCode = prefix + competitionService.createCompetitionCode();
        // 验证报名码是否重复
        if (StringUtils.isNotBlank(competitionService.findByCompetitionCode(competitionCode))) {
            //如果重复 -- 后面加上uuid后四位
            competitionCode = prefix + competitionService.createCompetitionCode() + competitionService.createUUidLast4();
        }
        competitionDto.setCompetitionCode(competitionCode);
        competitionDto.setCompetitionCodeStatus(CompetitionStatusEnum.WSY.getCode());

        CompetitionEntity competitionEntity = new CompetitionEntity();
        competitionEntity.setCompetitionCode(competitionCode);
        competitionEntity.setCompetitionStatus(CompetitionStatusEnum.WSY.getCode());
        competitionEntity.setUid(uid);
        competitionEntity.setSceneCode("HX");
        competitionEntity.setUseTimes(1);
        competitionEntity.setCreateTime(new Date());
        try {
            competitionMapper.insertSelective(competitionEntity);
        } catch (Exception e) {
            log.error("报名码插入异常，报名码实体信息:{}", competitionEntity);
            competitionCode = competitionService.createCompetitionCode() + competitionService.createUUidLast4();
            competitionDto.setCompetitionCode(competitionCode);
            competitionEntity.setCompetitionCode(competitionCode);
            try {
                competitionMapper.insertSelective(competitionEntity);
            } catch (Exception e1) {
                throw new BusinessException("活动火爆，请稍后重试!");
            }
        }
        //插入用户的阶段数据
        UserStageEntity userStageEntity = new UserStageEntity();
        userStageEntity.setStageIndex(1);
        userStageEntity.setUid(uid);
        userStageEntity.setSceneCode("HX");
        userStageEntity.setStageCode("SC-HX");
        userStageEntity.setCreateTime(new Date());
        userStageMapper.insertSelective(userStageEntity);
        return competitionDto;
    }

    /**
     * 查询用户比赛码
     *
     * @param uid       用户uid
     * @param sceneCode 场景编码
     * @return
     */
    @Override
    public CompetitionDto findCompetitionCode(String uid, String sceneCode) {
        CompetitionEntity competitionEntity = competitionMapper.findByUidAndSceneCode(uid, sceneCode);
        if (!Objects.isNull(competitionEntity)) {
            return CompetitionDto.builder()
                    .competitionCode(competitionEntity.getCompetitionCode())
                    .competitionCodeStatus(competitionEntity.getCompetitionStatus())
                    .build();
        }
        return null;
    }

    /**
     * 查询用户列表
     *
     * @param queryUserReq 查询用户列表入参
     * @return
     */
    @Override
    public PageInfoDto<UserDetailInfoVo> findUserListByCondition(QueryUserReq queryUserReq) {
        PageHelper.startPage(queryUserReq.getPageNum(), queryUserReq.getPageSize());

        List<UserDetailInfoVo> userInfoList = Lists.newArrayList();
        List<UserEntity> userEntityList = userMapper.findUserListByCondition(queryUserReq);
        if (CollectionUtils.isNotEmpty(userEntityList)) {
            userEntityList.forEach(userEntity -> {
                UserDetailInfoVo userDetailInfoVo = new UserDetailInfoVo();
                BeanUtils.copyProperties(userEntity, userDetailInfoVo);
                userDetailInfoVo.setAreaName(AreaEnum.getAreaNameByCode(userDetailInfoVo.getAreaCode()));
                userInfoList.add(userDetailInfoVo);
            });
        }

        final PageInfo pageInfo = new PageInfo<>(userInfoList);

        return PageInfoDto.builder()
                .list(pageInfo.getList())
                .total(pageInfo.getTotal())
                .pageNum(pageInfo.getPageNum())
                .pageSize(pageInfo.getPageSize())
                .size(pageInfo.getSize())
                .build();
    }

    /**
     * 用户数量统计
     *
     * @return
     */
    @Override
    public QueryUserCountVo countUser() {
        List<UserCountResultDto> userCountResultDtos = userMapper.countUser();
        List<UserCountVo> userCountList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(userCountResultDtos)) {
            userCountResultDtos.forEach(userCountResultDto -> {
                UserCountVo userCountVo = new UserCountVo();
                BeanUtils.copyProperties(userCountResultDto, userCountVo);
                userCountVo.setAreaName(AreaEnum.getAreaNameByCode(userCountVo.getAreaCode()));
                userCountList.add(userCountVo);
            });
        }
        int totalUser = userMapper.countAll();
        return QueryUserCountVo.builder()
                .totalUserNum(totalUser)
                .userCountList(userCountList)
                .build();
    }

    /**
     * 校验当前用户参赛资格
     *
     * @param competitionEntity 参赛码详细信息
     * @return
     */
    @Override
    public boolean checkUserEligibility(CompetitionEntity competitionEntity) throws Exception {
        //1、通过场景编码查询当前场景配置，判断当前激活码是否满足场景配置(是否作答两次以上，是否已经完成作答)
        String sceneCode = competitionEntity.getSceneCode();
        if (StringUtils.isBlank(sceneCode)) {
            throw new BusinessException("场景编码不存在");
        }
        // 查询当前场景的激活码使用配置次数
        SceneConfigEntity sceneConfig = sceneConfigService.findConfigBySceneCode(sceneCode);
        if (Objects.isNull(sceneConfig)) {
            log.warn("当前场景未配置,请配置: 场景编码:{}", sceneCode);
            throw new BusinessException("当前场景未配置,请配置: 场景编码:{}");
        }
        //答题时间规则限制
        Date now = new Date();
        if (now.compareTo(sceneConfig.getCompetingStartTime()) < 0) {
            log.info("当前系统时间：{}", now);
            log.info("场景开放时间：{}", sceneConfig.getCompetingStartTime());
            throw new BusinessException("答题时间还未开始，请稍候再试");
        }
        if (now.compareTo(sceneConfig.getCompetingEndTime()) > 0) {
            throw new BusinessException("来迟啦~本轮比赛已结束");
        }

        if (StringUtils.equals(competitionEntity.getCompetitionStatus(), "ysy")) {
            throw new BusinessException("当前并非您的答题时间");
        }

        Integer sceneAnswerTimes = sceneConfig.getAnswerTimes();
        // 如果当前答题次数大于场景配置次数
        if (competitionEntity.getUseTimes() > sceneAnswerTimes) {
            return false;
        }
        return true;
    }

    /**
     * 修改用户信息
     *
     * @param loginUserInfoReq 用户信息
     * @return
     */
    @Override
    public boolean updateUserInfo(LoginUserInfoReq loginUserInfoReq) {
        String uid = loginUserInfoReq.getUid();
        if (StringUtils.isBlank(uid)) {
            return false;
        }
        UserEntity userEntity = userMapper.findUserByUid(uid);
        if (userEntity.getIsVerify() == 1) {
            return true;
        }
        BeanUtils.copyProperties(loginUserInfoReq, userEntity);
        userEntity.setIsVerify(1);
        userEntity.setLastModifyTime(new Date());
        //修改用户信息 ， 并且当前认证状态改为1
        userMapper.updateByPrimaryKey(userEntity);
        return true;
    }
}
