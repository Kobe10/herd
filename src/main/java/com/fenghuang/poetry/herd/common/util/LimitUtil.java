package com.fenghuang.poetry.herd.common.util;

import com.google.common.util.concurrent.RateLimiter;

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
 * @Desc 限流工具类
 * @date Created in 2020年05月24日 13:02
 * @since 1.0
 */
public class LimitUtil {
    //每秒发出100个令牌，限制并发量为1000
    private static RateLimiter rateLimiter = RateLimiter.create(1000);

    /**
     * 获取令牌，如果没有令牌，则请求失败，请稍后再试
     *
     * @return
     */
    public static boolean tryAcquire() {
        return rateLimiter.tryAcquire();
    }
}
