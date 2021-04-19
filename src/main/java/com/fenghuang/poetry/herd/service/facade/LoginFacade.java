package com.fenghuang.poetry.herd.service.facade;

import com.fenghuang.poetry.herd.api.model.req.LoginInfoReq;
import com.fenghuang.poetry.herd.api.model.resp.LoginInfoVo;
import org.springframework.transaction.annotation.Transactional;

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
public interface LoginFacade {
    /**
     * 用户登录
     *
     * @param loginInfoReq
     * @return
     */
    LoginInfoVo login(LoginInfoReq loginInfoReq) throws Exception;
}
