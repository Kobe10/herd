package com.fenghuang.poetry.herd.service.provider.impl;

import com.fenghuang.poetry.herd.common.util.CompletionCodeUtils;
import com.fenghuang.poetry.herd.dao.CompetitionMapper;
import com.fenghuang.poetry.herd.service.provider.CompetitionService;
import com.xuanner.seq.SnowflakeSeqBuilder;
import com.xuanner.seq.sequence.Sequence;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

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
 * @date Created in 2020年05月12日 14:14
 * @since 1.0
 */
@Component
public class CompetitionServiceImpl implements CompetitionService {
    public static Sequence sequence = SnowflakeSeqBuilder.create().datacenterId(1).workerId(5).build();

    @Resource
    private CompetitionMapper competitionMapper;

    @Override
    public String createCompetitionCode() {
        // 海选报名码规则
        String prefix = "zjscdh-";
        String hxCompletionCode = CompletionCodeUtils.create((byte) 1, 1, 12, CompletionCodeUtils.allCapitalStrings);
        return prefix + hxCompletionCode;
    }

    /**
     * 查询报名码是否存在
     *
     * @param competitionCode 报名码
     * @return
     */
    @Override
    public String findByCompetitionCode(String competitionCode) {
        return competitionMapper.findByCompetitionCode(competitionCode);
    }

    /**
     * uid 生成
     *
     * @return
     */
    @Override
    public String createUid() {
        long uid = sequence.nextValue();
        return String.valueOf(uid);
    }

    /**
     * uuid 后四位
     *
     * @return
     */
    @Override
    public String createUUidLast4() {
        UUID uuid = UUID.randomUUID();    //获取UID的值
        String uuidStr = uuid.toString();
        return uuidStr.substring(uuidStr.length() - 2);
    }
}
