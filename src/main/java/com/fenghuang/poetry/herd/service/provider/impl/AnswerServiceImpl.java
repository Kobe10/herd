package com.fenghuang.poetry.herd.service.provider.impl;

import com.alibaba.fastjson.JSONObject;
import com.fenghuang.poetry.herd.common.enums.PageOrderStatusEnum;
import com.fenghuang.poetry.herd.common.util.DateTimeUtils;
import com.fenghuang.poetry.herd.common.util.RedisUtils;
import com.fenghuang.poetry.herd.config.redis.RedisSupport;
import com.fenghuang.poetry.herd.dao.AnswerMapper;
import com.fenghuang.poetry.herd.dao.CompetitionMapper;
import com.fenghuang.poetry.herd.dao.PageOrderMapper;
import com.fenghuang.poetry.herd.dao.SceneConfigMapper;
import com.fenghuang.poetry.herd.dao.entity.AnswerEntity;
import com.fenghuang.poetry.herd.dao.entity.PageOrderEntity;
import com.fenghuang.poetry.herd.dao.entity.SceneConfigEntity;
import com.fenghuang.poetry.herd.service.model.dto.RankRedisDTO;
import com.fenghuang.poetry.herd.service.provider.AnswerService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

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
 * @Desc 答案服务
 * @date Created in 2020年07月13日 22:53
 * @since 1.0
 */
@Service
public class AnswerServiceImpl implements AnswerService {

    @Resource
    private PageOrderMapper pageOrderMapper;

    @Resource
    private AnswerMapper answerMapper;

    @Resource
    private CompetitionMapper competitionMapper;

    @Autowired
    private RedisSupport redisSupport;

    @Resource
    private SceneConfigMapper sceneConfigMapper;

    /**
     * 存储答案
     *
     * @param pageOrderCode    答卷编码
     * @param atomicInteger    正确答题数
     * @param userUseTime      答题时间 秒
     * @param answerEntityList 答案list
     * @param submitAnswerTime
     * @param pageOrderEntity
     * @param times            答题次数
     */
    @Override
    public void handlerAnswer(String pageOrderCode, AtomicInteger atomicInteger, Integer userUseTime,
                              List<AnswerEntity> answerEntityList, LocalDateTime submitAnswerTime,
                              PageOrderEntity pageOrderEntity, Integer times) {

        AtomicBoolean flag = new AtomicBoolean(true);
        AtomicBoolean deleteRedisFlag = new AtomicBoolean(false);
        AtomicReference<RankRedisDTO> deleteRankRedisDTO = new AtomicReference<>(RankRedisDTO.builder().build());
        //比较两次的成绩  取最好的一个
        if (times == 3) {
            //第二次答题
            List<PageOrderEntity> pageOrderEntityList = pageOrderMapper.selectByCompetitionCode(pageOrderEntity.getCompetitionCode());
            pageOrderEntityList.forEach(pageOrderEntity1 -> {
                if (!Objects.isNull(pageOrderEntity1.getRightSubjectNum())) {
                    //比较两次成绩
                    if ((atomicInteger.get() == pageOrderEntity1.getRightSubjectNum() && userUseTime < pageOrderEntity1.getSpeedTime())
                            || (atomicInteger.get() > pageOrderEntity1.getRightSubjectNum())) {
                        //取消第一次
                        pageOrderEntity1.setIsDel(1);
                        pageOrderMapper.updateByPrimaryKeySelective(pageOrderEntity1);
                        // 存入缓存中
                        deleteRankRedisDTO.set(RankRedisDTO.builder()
                                .uid(pageOrderEntity1.getUid())
                                .userName(pageOrderEntity1.getUserName())
                                .areaCode(pageOrderEntity1.getAreaCode())
                                .provinceCode(pageOrderEntity1.getProvinceCode())
                                .school(pageOrderEntity1.getSchool())
                                .completeTime(pageOrderEntity1.getSpeedTime())
                                .rightNum(pageOrderEntity1.getRightSubjectNum())
                                .competitionCode(pageOrderEntity1.getCompetitionCode())
                                .build());

                        // 删除缓存
                        deleteRedisFlag.set(true);
                    } else {
                        flag.set(false);

                    }
                }
            });
        }
        if (flag.get()) {
            // 更新pageOrder、插入排行榜数据
            pageOrderEntity.setEndTime(new Date());
            pageOrderEntity.setPageStatus(PageOrderStatusEnum.YJJ.getCode());
            pageOrderEntity.setRightSubjectNum(atomicInteger.get());
            pageOrderEntity.setSubmitPageTime(DateTimeUtils.LocalDateTimeToUdate(submitAnswerTime));
            pageOrderEntity.setSpeedTime(userUseTime);
            pageOrderMapper.updateByPrimaryKeySelective(pageOrderEntity);


            // 插入答案list
            if (CollectionUtils.isNotEmpty(answerEntityList)) {
                answerMapper.insertBatchAnswer(answerEntityList);
            }

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
            // 获取场景编码的最大秒数
            SceneConfigEntity sceneConfigEntity = sceneConfigMapper.selectBySceneCode(pageOrderEntity.getSceneCode());
            Integer defaultTime = sceneConfigEntity.getCompetingTime();

            //计算当前分数  正确题数+所剩时间数
            double score = atomicInteger.get() * 100000 + (defaultTime - userUseTime);

            redisSupport.zsetAdd(redisKeyForArea, rankRedisStr, score);
            redisSupport.zsetAdd(redisKeyForProvince, rankRedisStr, score);
            // 删除较差的一次排名
            if (deleteRedisFlag.get()) {
                String deleteRankStr = JSONObject.toJSONString(deleteRankRedisDTO.get());
                redisSupport.zsetRemove(redisKeyForArea, deleteRankStr);
                redisSupport.zsetRemove(redisKeyForProvince, deleteRankStr);
            }
        } else {
            // 更新pageOrder、插入排行榜数据
            pageOrderEntity.setEndTime(new Date());
            pageOrderEntity.setPageStatus(PageOrderStatusEnum.YJJ.getCode());
            pageOrderEntity.setIsDel(1);
            pageOrderEntity.setRightSubjectNum(atomicInteger.get());
            pageOrderEntity.setSubmitPageTime(DateTimeUtils.LocalDateTimeToUdate(submitAnswerTime));
            pageOrderEntity.setSpeedTime(userUseTime);
            pageOrderMapper.updateByPrimaryKeySelective(pageOrderEntity);


            // 插入答案list
            if (CollectionUtils.isNotEmpty(answerEntityList)) {
                answerMapper.insertBatchAnswer(answerEntityList);
            }
        }
    }
}
