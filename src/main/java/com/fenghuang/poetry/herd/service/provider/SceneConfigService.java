package com.fenghuang.poetry.herd.service.provider;

import com.fenghuang.poetry.herd.dao.entity.SceneConfigEntity;

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
 * @Desc 场景配置
 * @date Created in 2020年07月07日 19:54
 * @since 1.0
 */
public interface SceneConfigService {

    /**
     * 根据场景编码查询配置
     *
     * @param sceneCode
     * @return
     */
    SceneConfigEntity findConfigBySceneCode(String sceneCode);
}
