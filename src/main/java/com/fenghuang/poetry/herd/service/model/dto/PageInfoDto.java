package com.fenghuang.poetry.herd.service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @Author fengbo.yue
 * @Date Created in 2019年05月20日 16:36
 * @Version 1.0
 * @Since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageInfoDto<T> implements Serializable {

    private static final long serialVersionUID = -7979666521096142224L;

    //当前页
    private int pageNum;

    //每页的数量
    private int pageSize;

    //当前页的数量
    private int size;

    //总记录数
    private long total;

    //结果集
    private List<T> list;

}
