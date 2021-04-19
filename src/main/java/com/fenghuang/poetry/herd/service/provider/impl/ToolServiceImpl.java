package com.fenghuang.poetry.herd.service.provider.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import com.fenghuang.poetry.herd.service.provider.ToolService;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
 * @date Created in 2020年07月16日 21:58
 * @since 1.0
 */
@Service
public class ToolServiceImpl implements ToolService {
    @Override
    public void importQuestion(MultipartFile files) throws IOException {
        //问卷数据导入
        EasyExcel.read(files.getInputStream(), new QuestionDataListener()).sheet().doRead();

    }
}
