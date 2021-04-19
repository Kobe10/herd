package com.fenghuang.poetry.herd.service.provider.impl;

import com.alibaba.fastjson.JSONObject;
import com.fenghuang.poetry.herd.api.model.req.PersonalRankInfoReq;
import com.fenghuang.poetry.herd.api.model.req.UserRankInfoReq;
import com.fenghuang.poetry.herd.api.model.resp.question.PersonRankDetailVo;
import com.fenghuang.poetry.herd.api.model.resp.question.UserListRankDetailVo;
import com.fenghuang.poetry.herd.common.constants.ErrMsg;
import com.fenghuang.poetry.herd.common.enums.AreaEnum;
import com.fenghuang.poetry.herd.common.enums.RankNameTypeEnum;
import com.fenghuang.poetry.herd.common.enums.RankTypeEnum;
import com.fenghuang.poetry.herd.common.util.DateUtil;
import com.fenghuang.poetry.herd.common.util.RedisUtils;
import com.fenghuang.poetry.herd.common.util.StringUtils;
import com.fenghuang.poetry.herd.common.web.Resp;
import com.fenghuang.poetry.herd.config.redis.RedisSupport;
import com.fenghuang.poetry.herd.dao.PageOrderMapper;
import com.fenghuang.poetry.herd.dao.SceneMapper;
import com.fenghuang.poetry.herd.dao.entity.PageOrderEntity;
import com.fenghuang.poetry.herd.dao.entity.SceneEntity;
import com.fenghuang.poetry.herd.service.model.dto.RankRedisDTO;
import com.fenghuang.poetry.herd.service.provider.RankService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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
 * @date Created in 2020年07月16日 20:57
 * @since 1.0
 */
@Slf4j
@Service
public class RankServiceImpl implements RankService {
    @Resource
    private PageOrderMapper pageOrderMapper;

    @Autowired
    private RedisSupport redisSupport;

    @Resource
    private SceneMapper sceneMapper;

    /**
     * 个人排行榜
     *
     * @param personalRankInfoReq 个人排行榜入参
     * @return
     */
    @Override
    public Resp getPersonalRank(PersonalRankInfoReq personalRankInfoReq) {
        Date date = new Date();

        // 查询redis 信息
        String competitionCode = personalRankInfoReq.getCompetitionCode();

        // 查询场景编码： 默认是初赛； 依次遍历； 如果当前场景有效，那就使用当前场景，否则递归使用下一个场景
        List<SceneEntity> allSceneList = sceneMapper.selectAllList();
        if (CollectionUtils.isEmpty(allSceneList)) {
            return new Resp(500, "排行榜无信息");
        }
        String sceneCode = "FS";
        for (SceneEntity sceneEntity : allSceneList) {
            if (sceneEntity.getSceneStatus() == 1) {
                sceneCode = sceneEntity.getSceneCode();
                break;
            }
        }

        if (StringUtils.equals(RankTypeEnum.province.getCode(), personalRankInfoReq.getRankType())) {
            List<PageOrderEntity> pageOrderEntities = pageOrderMapper.selectByCompetitionCodeAndSceneCode(competitionCode, sceneCode);
            if (CollectionUtils.isNotEmpty(pageOrderEntities)) {
                PageOrderEntity pageOrderEntity = pageOrderEntities.get(0);
                //设置上一次的redisDto对象
                String redisKeyForProvince = RedisUtils.getRedisRankKeyForPro(pageOrderEntity.getSceneCode(), pageOrderEntity.getProvinceCode());

                // 存入缓存中
                RankRedisDTO rankRedisDTO = RankRedisDTO.builder()
                        .uid(pageOrderEntity.getUid())
                        .userName(pageOrderEntity.getUserName())
                        .areaCode(pageOrderEntity.getAreaCode())
                        .provinceCode(pageOrderEntity.getProvinceCode())
                        .school(pageOrderEntity.getSchool())
                        .completeTime(pageOrderEntity.getSpeedTime())
                        .rightNum(pageOrderEntity.getRightSubjectNum())
                        .competitionCode(pageOrderEntity.getCompetitionCode())
                        .build();
                String rankRedisStr = JSONObject.toJSONString(rankRedisDTO);
                Long rank = redisSupport.getUserCurrentRank(redisKeyForProvince, rankRedisStr);
                Long count = redisSupport.getUserCount(redisKeyForProvince);


                DecimalFormat fnum = new DecimalFormat("##0.00");
                String rate = fnum.format((float) (count - rank) / (float) count * 100);
                //判断称谓
                String rankName = null;
                String appellation = null;
                if (pageOrderEntity.getRightSubjectNum() <= 5 && pageOrderEntity.getRightSubjectNum() >= 0) {
                    rankName = RankNameTypeEnum.XSND.getName();
                    appellation = RankNameTypeEnum.XSND.getFenxiang();
                }
                if (pageOrderEntity.getRightSubjectNum() <= 10 && pageOrderEntity.getRightSubjectNum() >= 6) {
                    rankName = RankNameTypeEnum.ZLTJ.getName();
                    appellation = RankNameTypeEnum.ZLTJ.getFenxiang();
                }
                if (pageOrderEntity.getRightSubjectNum() <= 15 && pageOrderEntity.getRightSubjectNum() >= 11) {
                    rankName = RankNameTypeEnum.FYSS.getName();
                    appellation = RankNameTypeEnum.FYSS.getFenxiang();
                }
                if (pageOrderEntity.getRightSubjectNum() <= 19 && pageOrderEntity.getRightSubjectNum() >= 16) {
                    rankName = RankNameTypeEnum.ZEBQ.getName();
                    appellation = RankNameTypeEnum.ZEBQ.getFenxiang();
                }
                if (pageOrderEntity.getRightSubjectNum() == 20) {
                    rankName = RankNameTypeEnum.JCJJ.getName();
                    appellation = RankNameTypeEnum.JCJJ.getFenxiang();
                }
                PersonRankDetailVo personRankDetailVo = PersonRankDetailVo.builder()
                        .uid(pageOrderEntity.getUid())
                        .userName(pageOrderEntity.getUserName())
                        .provinceRankNum(Math.toIntExact(rank))
                        .provinceBeatRate(rate)
                        .competitionCode(pageOrderEntity.getCompetitionCode())
                        .rankProvinceName("浙江省")
                        .rankName(rankName)
                        .appellation(appellation)
                        .areaCode(pageOrderEntity.getAreaCode())
                        .rankAreaName(AreaEnum.getAreaNameByCode(pageOrderEntity.getAreaCode()))
                        .countEndTime(DateUtil.toStringmdHms(new Date()))
                        .rightQuestionNum(pageOrderEntity.getRightSubjectNum())
                        .userTime(pageOrderEntity.getSpeedTime() != null ? DateUtil.secToTimeChinese(pageOrderEntity.getSpeedTime()) : null)
                        .build();
                return new Resp(personRankDetailVo);
            }
            return new Resp(500, ErrMsg.NO_PERSON_RANK_AREA_MESSAGE);
        }
        if (StringUtils.equals(RankTypeEnum.area.getCode(), personalRankInfoReq.getRankType())) {
            List<PageOrderEntity> pageOrderEntities = pageOrderMapper.selectByCompetitionCodeAndSceneCode(competitionCode, sceneCode);
            if (CollectionUtils.isNotEmpty(pageOrderEntities)) {
                PageOrderEntity pageOrderEntity = pageOrderEntities.get(0);
                //设置上一次的redisDto对象
                String redisKeyForArea = RedisUtils.getRedisRankKeyForArea(pageOrderEntity.getSceneCode(), pageOrderEntity.getProvinceCode(), pageOrderEntity.getAreaCode());

                // 存入缓存中
                RankRedisDTO rankRedisDTO = RankRedisDTO.builder()
                        .uid(pageOrderEntity.getUid())
                        .userName(pageOrderEntity.getUserName())
                        .areaCode(pageOrderEntity.getAreaCode())
                        .provinceCode(pageOrderEntity.getProvinceCode())
                        .school(pageOrderEntity.getSchool())
                        .completeTime(pageOrderEntity.getSpeedTime())
                        .rightNum(pageOrderEntity.getRightSubjectNum())
                        .competitionCode(pageOrderEntity.getCompetitionCode())
                        .build();
                String rankRedisStr = JSONObject.toJSONString(rankRedisDTO);
                Long rank = redisSupport.getUserCurrentRank(redisKeyForArea, rankRedisStr);
                Long count = redisSupport.getUserCount(redisKeyForArea);
                if (rank == null) {
                    return new Resp(500, ErrMsg.NO_PERSON_RANK_AREA_MESSAGE);
                }

                String rankName = null;
                String appellation = null;
                if (pageOrderEntity.getRightSubjectNum() <= 5 && pageOrderEntity.getRightSubjectNum() >= 0) {
                    rankName = RankNameTypeEnum.XSND.getName();
                    appellation = RankNameTypeEnum.XSND.getFenxiang();
                }
                if (pageOrderEntity.getRightSubjectNum() <= 10 && pageOrderEntity.getRightSubjectNum() >= 6) {
                    rankName = RankNameTypeEnum.ZLTJ.getName();
                    appellation = RankNameTypeEnum.ZLTJ.getFenxiang();
                }
                if (pageOrderEntity.getRightSubjectNum() <= 15 && pageOrderEntity.getRightSubjectNum() >= 11) {
                    rankName = RankNameTypeEnum.FYSS.getName();
                    appellation = RankNameTypeEnum.FYSS.getFenxiang();
                }
                if (pageOrderEntity.getRightSubjectNum() <= 19 && pageOrderEntity.getRightSubjectNum() >= 16) {
                    rankName = RankNameTypeEnum.ZEBQ.getName();
                    appellation = RankNameTypeEnum.ZEBQ.getFenxiang();
                }
                if (pageOrderEntity.getRightSubjectNum() == 20) {
                    rankName = RankNameTypeEnum.JCJJ.getName();
                    appellation = RankNameTypeEnum.JCJJ.getFenxiang();
                }

                DecimalFormat fnum = new DecimalFormat("##0.00");
                String rate = fnum.format((float) (count - rank) / (float) count * 100);
                PersonRankDetailVo personRankDetailVo = PersonRankDetailVo.builder()
                        .uid(pageOrderEntity.getUid())
                        .userName(pageOrderEntity.getUserName())
                        .areaNum(Math.toIntExact(rank))
                        .competitionCode(pageOrderEntity.getCompetitionCode())
                        .areaBeatRate(rate)
                        .rankProvinceName("浙江省")
                        .rankName(rankName)
                        .areaCode(pageOrderEntity.getAreaCode())
                        .appellation(appellation)
                        .areaCode(pageOrderEntity.getAreaCode())
                        .rankAreaName(AreaEnum.getAreaNameByCode(pageOrderEntity.getAreaCode()))
                        .countEndTime(DateUtil.toStringmdHms(new Date()))
                        .rightQuestionNum(pageOrderEntity.getRightSubjectNum())
                        .userTime(pageOrderEntity.getSpeedTime() != null ? DateUtil.secToTimeChinese(pageOrderEntity.getSpeedTime()) : null)
                        .build();
                return new Resp(personRankDetailVo);
            }
            return new Resp(500, ErrMsg.NO_PERSON_RANK_AREA_MESSAGE);
        }
        if (StringUtils.equals(RankTypeEnum.all.getCode(), personalRankInfoReq.getRankType())) {
            List<PageOrderEntity> pageOrderEntities = pageOrderMapper.selectByCompetitionCodeAndSceneCode(competitionCode, sceneCode);
            if (CollectionUtils.isNotEmpty(pageOrderEntities)) {
                PageOrderEntity pageOrderEntity = pageOrderEntities.get(0);
                //设置上一次的redisDto对象
                String redisKeyForArea = RedisUtils.getRedisRankKeyForArea(pageOrderEntity.getSceneCode(), pageOrderEntity.getProvinceCode(), pageOrderEntity.getAreaCode());
                String redisKeyForProvince = RedisUtils.getRedisRankKeyForPro(pageOrderEntity.getSceneCode(), pageOrderEntity.getProvinceCode());

                // 存入缓存中
                RankRedisDTO rankRedisDTO = RankRedisDTO.builder()
                        .uid(pageOrderEntity.getUid())
                        .userName(pageOrderEntity.getUserName())
                        .areaCode(pageOrderEntity.getAreaCode())
                        .provinceCode(pageOrderEntity.getProvinceCode())
                        .school(pageOrderEntity.getSchool())
                        .completeTime(pageOrderEntity.getSpeedTime())
                        .rightNum(pageOrderEntity.getRightSubjectNum())
                        .competitionCode(pageOrderEntity.getCompetitionCode())
                        .build();
                String rankRedisStr = JSONObject.toJSONString(rankRedisDTO);
                Long areaRank = redisSupport.getUserCurrentRank(redisKeyForArea, rankRedisStr);

                Long proRank = redisSupport.getUserCurrentRank(redisKeyForProvince, rankRedisStr);
                Long count = redisSupport.getUserCount(redisKeyForProvince);
                if (proRank == null || areaRank == null) {
                    return new Resp(500, ErrMsg.NO_PERSON_RANK_AREA_MESSAGE);
                }

                String rankName = null;
                String appellation = null;
                if (pageOrderEntity.getRightSubjectNum() <= 5 && pageOrderEntity.getRightSubjectNum() >= 0) {
                    rankName = RankNameTypeEnum.XSND.getName();
                    appellation = RankNameTypeEnum.XSND.getFenxiang();
                }
                if (pageOrderEntity.getRightSubjectNum() <= 10 && pageOrderEntity.getRightSubjectNum() >= 6) {
                    rankName = RankNameTypeEnum.ZLTJ.getName();
                    appellation = RankNameTypeEnum.ZLTJ.getFenxiang();
                }
                if (pageOrderEntity.getRightSubjectNum() <= 15 && pageOrderEntity.getRightSubjectNum() >= 11) {
                    rankName = RankNameTypeEnum.FYSS.getName();
                    appellation = RankNameTypeEnum.FYSS.getFenxiang();
                }
                if (pageOrderEntity.getRightSubjectNum() <= 19 && pageOrderEntity.getRightSubjectNum() >= 16) {
                    rankName = RankNameTypeEnum.ZEBQ.getName();
                    appellation = RankNameTypeEnum.ZEBQ.getFenxiang();
                }
                if (pageOrderEntity.getRightSubjectNum() == 20) {
                    rankName = RankNameTypeEnum.JCJJ.getName();
                    appellation = RankNameTypeEnum.JCJJ.getFenxiang();
                }

                DecimalFormat fnum = new DecimalFormat("##0.00");
                String rate = fnum.format((float) (count - proRank) / (float) count * 100);
                PersonRankDetailVo personRankDetailVo = PersonRankDetailVo.builder()
                        .uid(pageOrderEntity.getUid())
                        .competitionCode(pageOrderEntity.getCompetitionCode())
                        .userName(pageOrderEntity.getUserName())
                        .areaNum(Math.toIntExact(areaRank))
                        .provinceRankNum(Math.toIntExact(proRank))
                        .areaBeatRate(rate)
                        .rankProvinceName("浙江省")
                        .rankName(rankName)
                        .appellation(appellation)
                        .areaCode(pageOrderEntity.getAreaCode())
                        .rankAreaName(AreaEnum.getAreaNameByCode(pageOrderEntity.getAreaCode()))
                        .countEndTime(DateUtil.toStringmdHms(new Date()))
                        .rightQuestionNum(pageOrderEntity.getRightSubjectNum())
                        .userTime(pageOrderEntity.getSpeedTime() != null ? DateUtil.secToTimeChinese(pageOrderEntity.getSpeedTime()) : null)
                        .build();
                return new Resp(personRankDetailVo);
            }
            return new Resp(500, ErrMsg.NO_PERSON_RANK_AREA_MESSAGE);
        }
        return new Resp(null);
    }

    /**
     * 个人或者省排行榜入参
     *
     * @param personalRankInfoReq
     * @return
     */
    @Override
    public Resp getUserRankList(UserRankInfoReq personalRankInfoReq) {
        // 查询场景编码： 默认是初赛； 依次遍历； 如果当前场景有效，那就使用当前场景，否则递归使用下一个场景
        List<SceneEntity> allSceneList = sceneMapper.selectAllList();
        if (CollectionUtils.isEmpty(allSceneList)) {
            return new Resp(500, "排行榜无信息");
        }
        String sceneCode = "FS";
        for (SceneEntity sceneEntity : allSceneList) {
            if (sceneEntity.getSceneStatus() == 1) {
                sceneCode = sceneEntity.getSceneCode();
                break;
            }
        }

        // 查询redis 信息
        String competitionCode = personalRankInfoReq.getCompetitionCode();

        List<UserListRankDetailVo> userListRankDetailVoList = Lists.newArrayList();
        if (StringUtils.isNotBlank(competitionCode)) {
            //查个人成绩
            List<PageOrderEntity> pageOrderEntities = pageOrderMapper.selectByCompetitionCode(competitionCode);
            if (CollectionUtils.isNotEmpty(pageOrderEntities)) {
                PageOrderEntity pageOrderEntity = pageOrderEntities.get(0);
                //设置上一次的redisDto对象
                String redisKey = null;
                if (StringUtils.equals(RankTypeEnum.province.getCode(), personalRankInfoReq.getRankType())) {
                    redisKey = RedisUtils.getRedisRankKeyForPro(sceneCode, personalRankInfoReq.getProvinceCode());
                }
                if (StringUtils.equals(RankTypeEnum.area.getCode(), personalRankInfoReq.getRankType())) {
                    redisKey = RedisUtils.getRedisRankKeyForArea(sceneCode, personalRankInfoReq.getProvinceCode(), personalRankInfoReq.getAreaCode());
                }

                // 存入缓存中
                RankRedisDTO rankRedisDTO = RankRedisDTO.builder()
                        .uid(pageOrderEntity.getUid())
                        .userName(pageOrderEntity.getUserName())
                        .areaCode(pageOrderEntity.getAreaCode())
                        .provinceCode(pageOrderEntity.getProvinceCode())
                        .school(pageOrderEntity.getSchool())
                        .completeTime(pageOrderEntity.getSpeedTime())
                        .rightNum(pageOrderEntity.getRightSubjectNum())
                        .competitionCode(pageOrderEntity.getCompetitionCode())
                        .build();
                String rankRedisStr = JSONObject.toJSONString(rankRedisDTO);
                Long rank = redisSupport.getUserCurrentRank(redisKey, rankRedisStr);
                if (rank == null) {
                    return new Resp(500, "无排名信息");
                }
                //转换答题时间
                String speedTime = "12:00";
                if (!Objects.isNull(pageOrderEntity.getSpeedTime()) && pageOrderEntity.getSpeedTime() <= 720) {
                    speedTime = DateUtil.secToTime(pageOrderEntity.getSpeedTime());
                }
                userListRankDetailVoList.add(UserListRankDetailVo.builder()
                        .uid(pageOrderEntity.getUid())
                        .userName(pageOrderEntity.getUserName())
                        .school(pageOrderEntity.getSchool())
                        .rightSubjectNum(pageOrderEntity.getRightSubjectNum())
                        .rank(Math.toIntExact(rank))
                        .userTime(speedTime)
                        .build());
                return new Resp(userListRankDetailVoList);
            }
            return new Resp(500, "无排名信息");
        }
        if (StringUtils.equals(RankTypeEnum.province.getCode(), personalRankInfoReq.getRankType())) {
            //查前500
            String redisKey = RedisUtils.getRedisRankKeyForPro(sceneCode, personalRankInfoReq.getProvinceCode());
            List<RankRedisDTO> rankRedisDTOSet = redisSupport.getRank(redisKey, 0, 10000000, 0, 500);
            //遍历值， 返回值
            if (CollectionUtils.isEmpty(rankRedisDTOSet)) {
                return new Resp(500, "当前排行榜无信息");
            }
            AtomicInteger rank = new AtomicInteger(0);
            userListRankDetailVoList = rankRedisDTOSet.stream().map(rankRedisDTO -> {
                //转换答题时间
                String speedTime = "12:00";
                if (!Objects.isNull(rankRedisDTO.getCompleteTime()) && rankRedisDTO.getCompleteTime() <= 720) {
                    speedTime = DateUtil.secToTime(rankRedisDTO.getCompleteTime());
                }
                rank.incrementAndGet();
                return UserListRankDetailVo.builder()
                        .uid(rankRedisDTO.getUid())
                        .userName(rankRedisDTO.getUserName())
                        .school(rankRedisDTO.getSchool())
                        .rightSubjectNum(rankRedisDTO.getRightNum())
                        .rank(Math.toIntExact(rank.get()))
                        .userTime(speedTime)
                        .build();
            }).collect(Collectors.toList());
            return new Resp(userListRankDetailVoList);
        }
        if (StringUtils.equals(RankTypeEnum.area.getCode(), personalRankInfoReq.getRankType())) {
            //查前200
            //查前500
            String redisKey = RedisUtils.getRedisRankKeyForArea(sceneCode, personalRankInfoReq.getProvinceCode(), personalRankInfoReq.getAreaCode());
            List<RankRedisDTO> rankRedisDTOSet = redisSupport.getRank(redisKey, 0, 10000000, 0, 200);
            //遍历值， 返回值
            if (CollectionUtils.isEmpty(rankRedisDTOSet)) {
                return new Resp(500, "当前排行榜无信息");
            }
            AtomicInteger rank = new AtomicInteger(0);
            userListRankDetailVoList = rankRedisDTOSet.stream().map(rankRedisDTO -> {
                //转换答题时间
                String speedTime = "12:00";
                if (!Objects.isNull(rankRedisDTO.getCompleteTime()) && rankRedisDTO.getCompleteTime() <= 720) {
                    speedTime = DateUtil.secToTime(rankRedisDTO.getCompleteTime());
                }
                rank.incrementAndGet();
                return UserListRankDetailVo.builder()
                        .uid(rankRedisDTO.getUid())
                        .userName(rankRedisDTO.getUserName())
                        .school(rankRedisDTO.getSchool())
                        .rightSubjectNum(rankRedisDTO.getRightNum())
                        .rank(Math.toIntExact(rank.get()))
                        .userTime(speedTime)
                        .build();
            }).collect(Collectors.toList());
            return new Resp(userListRankDetailVoList);
        }
        return new Resp(500, "当前无排行榜信息，请稍后再来");
    }
}
