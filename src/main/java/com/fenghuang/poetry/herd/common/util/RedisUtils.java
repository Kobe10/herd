package com.fenghuang.poetry.herd.common.util;

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
 * @date Created in 2020年07月17日 18:54
 * @since 1.0
 */
public class RedisUtils {
    /**
     * 获取地区key
     *
     * @param sceneCode
     * @param province
     * @param areaCode
     * @return
     */
    public static String getRedisRankKeyForArea(String sceneCode, String province, String areaCode) {
        return sceneCode + "_" + province + "_" + areaCode;
    }

    /**
     * 获取省key
     *
     * @param sceneCode 场景编码
     * @param province  省编码
     * @return
     */
    public static String getRedisRankKeyForPro(String sceneCode, String province) {
        return sceneCode + "_" + province;
    }


}
