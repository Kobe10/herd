package com.fenghuang.poetry.herd.service.provider.impl;

import com.fenghuang.poetry.herd.api.model.req.question.RandomQuestionReq;
import com.fenghuang.poetry.herd.common.enums.PageOrderStatusEnum;
import com.fenghuang.poetry.herd.common.util.StringUtils;
import com.fenghuang.poetry.herd.config.exception.BusinessException;
import com.fenghuang.poetry.herd.dao.CompetitionMapper;
import com.fenghuang.poetry.herd.dao.PageOrderMapper;
import com.fenghuang.poetry.herd.dao.UserMapper;
import com.fenghuang.poetry.herd.dao.entity.CompetitionEntity;
import com.fenghuang.poetry.herd.dao.entity.PageOrderEntity;
import com.fenghuang.poetry.herd.dao.entity.QuestionEntity;
import com.fenghuang.poetry.herd.dao.entity.UserEntity;
import com.fenghuang.poetry.herd.service.provider.CompetitionService;
import com.fenghuang.poetry.herd.service.provider.PageOrderService;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
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
 * @date Created in 2020年07月08日 20:15
 * @since 1.0
 */
@Slf4j
@Service
public class PageOrderServiceImpl implements PageOrderService {
    @Resource
    private CompetitionMapper competitionMapper;

    @Resource
    private CompetitionService competitionService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private PageOrderMapper pageOrderMapper;

    @Override
    public String createPageOrder(List<String> questionCodeList, RandomQuestionReq randomQuestionReq) {
        Date now = new Date();

        CompetitionEntity competitionEntity = competitionMapper.selectByCompetitionCode(randomQuestionReq.getCompetitionCode());
        //判断当前用户是否已经存在了答卷单  如果存在，返回之前创建的答卷单
        String pageOrderCode = competitionService.createUid();
        //获取用户信息
        UserEntity userEntity = userMapper.findUserByUid(competitionEntity.getUid());
        String questionStr = Joiner.on(",").join(questionCodeList); // 以逗号分割
        if (StringUtils.isBlank(questionStr)) {
            throw new BusinessException("创建答卷单失败!");
        }
        PageOrderEntity pageOrderEntity = PageOrderEntity.builder()
                .areaCode(userEntity.getAreaCode())
                .competitionCode(randomQuestionReq.getCompetitionCode())
                .userName(userEntity.getUserName())
                .school(userEntity.getSchool())
                .uid(userEntity.getUid())
                .sceneCode(competitionEntity.getSceneCode())
                .pageQuestion(questionStr)
                .provinceCode(userEntity.getProvinceCode())
                .startTime(now)
                .createPageTime(now)
//                .pageStatus("wks")
                .pageStatus(PageOrderStatusEnum.YKS.getCode())
                .pageCode(pageOrderCode)
                .createTime(now)
                .isDel(0)
                .build();

        pageOrderMapper.insertSelective(pageOrderEntity);
        return pageOrderCode;
    }

    /**
     * 查询答卷单信息
     *
     * @param pageOrderCode 答卷单号
     * @return
     */
    @Override
    public PageOrderEntity selectByPageOrderCode(String pageOrderCode) {
        return pageOrderMapper.selectByPageOrderCode(pageOrderCode);
    }
}
