package com.xinyuan.data.model;

import com.xinyuan.base.common.web.Param;
import lombok.Data;

import java.util.List;

/**
 * @author liang
 */
@Data
public class BaseDataSaveRequest {

    private String tableName;

    private List<Param> paramList;

    /**
     * 多记录的显示顺序
     */
    private Integer showFlag = 1;
    /**
     * 是否是多记录
     */
    private boolean isMultiple;

    /**
     *
     */
    private String companyId;
}
