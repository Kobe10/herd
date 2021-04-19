package com.fenghuang.poetry.herd.service.provider.impl;

import com.fenghuang.poetry.herd.dao.SceneConfigMapper;
import com.fenghuang.poetry.herd.dao.entity.SceneConfigEntity;
import com.fenghuang.poetry.herd.service.provider.SceneConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
 * @date Created in 2020年07月07日 19:55
 * @since 1.0
 */
@Service
public class SceneConfigServiceImpl implements SceneConfigService {
    @Resource
    private SceneConfigMapper sceneConfigMapper;

    @Override
    public SceneConfigEntity findConfigBySceneCode(String sceneCode) {

        return sceneConfigMapper.selectBySceneCode(sceneCode);
    }
}
