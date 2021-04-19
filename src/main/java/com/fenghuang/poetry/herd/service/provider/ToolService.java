package com.fenghuang.poetry.herd.service.provider;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
 * @date Created in 2020年07月16日 21:57
 * @since 1.0
 */
public interface ToolService {
    void importQuestion(MultipartFile files) throws IOException;
}
