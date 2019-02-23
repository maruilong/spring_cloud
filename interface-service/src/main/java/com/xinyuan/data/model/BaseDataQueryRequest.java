package com.xinyuan.data.model;

import com.xinyuan.base.common.web.PageBody;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author liang
 */
@Data
public class BaseDataQueryRequest extends PageBody {

    /**
     * 主表表名 （联合查询时候使用）
     */
    private String primaryTableName;

    /**
     * 状态
     */
    private List<Integer> status;

    /**
     * 起效时间
     */
    private Date startTime;

    /**
     * 查询的信息集
     */
    private List<TableQuery> tables;

    /**
     * 条件参数
     */
//    private List<Conditions> conditions;

}
