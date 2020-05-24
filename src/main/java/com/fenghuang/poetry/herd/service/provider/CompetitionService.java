package com.fenghuang.poetry.herd.service.provider;

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
 * @Desc 报名码生成
 * @date Created in 2020年05月12日 14:13
 * @since 1.0
 */
public interface CompetitionService {
    /**
     * 生成报名码
     *
     * @return
     */
    String createCompetitionCode();

    /**
     * 查询报名码是否存在
     *
     * @param competitionCode 报名码
     * @return
     */
    String findByCompetitionCode(String competitionCode);

    /**
     * uid 生成
     *
     * @return
     */
    String createUid();

    /**
     * uuid 后四位
     *
     * @return
     */
    String createUUidLast4();
}
